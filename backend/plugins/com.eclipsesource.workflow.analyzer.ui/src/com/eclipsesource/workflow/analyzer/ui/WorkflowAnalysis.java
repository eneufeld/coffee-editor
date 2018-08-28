package com.eclipsesource.workflow.analyzer.ui;

import static java.util.stream.Collectors.toMap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.util.UMLSwitch;

import com.eclipsesource.workflow.dsl.workflow.ProbabilityConfiguration;
import com.eclipsesource.workflow.dsl.workflow.WorkflowConfiguration;
import com.eclipsesource.workflow.dsl.workflow.WorkflowFactory;
import com.google.gson.Gson;

import kotlin.Pair;
import workflowanalyzer.Decision;
import workflowanalyzer.ForkOrJoin;
import workflowanalyzer.Merge;
import workflowanalyzer.Node;
import workflowanalyzer.NodeExecutionIterator;
import workflowanalyzer.Performer;
import workflowanalyzer.Task;
import workflowanalyzer.Workflow;

public class WorkflowAnalysis {

	private static final String ILLEGAL_FILE_CHARS_PATTERN = "[^a-zA-Z0-9\\.\\-]"; //$NON-NLS-1$

	private String activityName;
	private Workflow workflow;
	private Map<ActivityNode, Node> nodeMap;
	private Map<Task, String> taskTypeMap;
	private ProbabilityConfiguration probconf;

	public WorkflowAnalysis(Activity activity) {

		initializeWorkflow(activity);

	}

	private void initializeWorkflow(Activity activity) {
		taskTypeMap = new HashMap<>();
		activityName = activity.getName();
		initializeProbabilityConfiguration(activity);
		initializeNodeMap(activity);
		initializeConnections(activity);
		setProbabilities(activity);
		workflow = new Workflow(nodeMap.values().stream().collect(Collectors.toList()));
	}

	private void initializeNodeMap(Activity activity) {
		Stream<ActivityNode> nodes = activity.getOwnedNodes().stream();
		Stream<Pair<ActivityNode, Node>> nodePairs = nodes.map((node) -> createNodePair(node));
		nodeMap = nodePairs.collect(toMap(Pair::getFirst, Pair::getSecond));
	}

	private void initializeConnections(Activity activity) {
		Stream<ControlFlow> flows = filterByType(activity.getOwnedElements().stream(), ControlFlow.class);
		flows.forEach(this::connectSourceAndTarget);
	}

	private void setProbabilities(Activity activity) {
		Stream<DecisionNode> decisions = filterByType(activity.getOwnedElements().stream(), DecisionNode.class);
		decisions.forEach(this::setProbabilities);
	}

	private Pair<ActivityNode, Node> createNodePair(ActivityNode umlNode) {
		return new UMLSwitch<Pair<ActivityNode, Node>>() {
			public Pair<ActivityNode, Node> caseOpaqueAction(OpaqueAction object) {
				return new Pair<ActivityNode, Node>(object, createTask(object));
			}

			public Pair<ActivityNode, Node> caseDecisionNode(DecisionNode object) {
				return new Pair<ActivityNode, Node>(object, createDecision(object));
			};

			public Pair<ActivityNode, Node> caseMergeNode(MergeNode object) {
				return new Pair<ActivityNode, Node>(object, createMerge(object));
			};

			public Pair<ActivityNode, Node> caseJoinNode(JoinNode object) {
				return new Pair<ActivityNode, Node>(object, createForkOrJoin(object));
			};

			public Pair<ActivityNode, Node> caseForkNode(ForkNode object) {
				return new Pair<ActivityNode, Node>(object, createForkOrJoin(object));
			};
		}.doSwitch(umlNode);
	}

	private Task createTask(OpaqueAction object) {
		Task task = new Task(object.getName(), new Performer("unkown"),
				(int) getStereotypeAttribute(object, "duration"));
		if (isAutomaticTask(object)) {
			taskTypeMap.put(task, "automatic");
		} else if (isManualTask(object)) {
			taskTypeMap.put(task, "manual");
		}
		return task;
	}

	private boolean isAutomaticTask(OpaqueAction object) {
		return object.getStereotypeApplications().stream()
				.anyMatch((stereotype) -> "AutomaticTask".equals(stereotype.eClass().getName()));
	}

	private boolean isManualTask(OpaqueAction object) {
		return object.getStereotypeApplications().stream()
				.anyMatch((stereotype) -> "ManualTask".equals(stereotype.eClass().getName()));
	}

	private Node createDecision(DecisionNode object) {
		return new Decision(getId(object), new HashMap<>());
	}

	private Node createMerge(MergeNode object) {
		return new Merge(getId(object));
	}

	private Node createForkOrJoin(ControlNode object) {
		return new ForkOrJoin(getId(object));
	}

	private void connectSourceAndTarget(ControlFlow flow) {
		Node sourceNode = nodeMap.get(flow.getSource());
		sourceNode.connectTo(nodeMap.get(flow.getTarget()));
	}

	private <T> Stream<T> filterByType(Stream<?> elements, Class<T> clazz) {
		return (Stream<T>) elements.filter(clazz::isInstance).map(clazz::cast);
	}

	private void setProbabilities(DecisionNode umlDecisionNode) {
		Decision decision = (Decision) nodeMap.get(umlDecisionNode);
		for (ActivityEdge edge : umlDecisionNode.getOutgoings()) {
			Object probability = getStereotypeAttribute(edge, "probability");
			Node targetNode = nodeMap.get(edge.getTarget());
			decision.getProbabilities().put(targetNode, toFloat(probability));
		}
	}

	private Object getStereotypeAttribute(Element element, String attributeName) {
		EList<EObject> stereotypeApplications = element.getStereotypeApplications();
		for (EObject stereotypeApplication : stereotypeApplications) {
			for (EAttribute eAttribute : stereotypeApplication.eClass().getEAllAttributes()) {
				if (attributeName.equals(eAttribute.getName())) {
					return stereotypeApplication.eGet(eAttribute);
				}
			}
		}
		return null;
	}

	private Float toFloat(Object probability) {
		if (probability instanceof EEnumLiteral) {
			EEnumLiteral literal = (EEnumLiteral) probability;
			if ("low".equals(literal.getName())) {
				return probconf.getLow();
			} else if ("medium".equals(literal.getName())) {
				return probconf.getMedium();
			} else if ("high".equals(literal.getName())) {
				return probconf.getHigh();
			}
		}
		return 1f;
	}

	private String getId(Element object) {
		XMIResource resource = (XMIResource) object.eResource();
		return resource.getID(object);
	}

	public IFile persistIn(IContainer container, IProgressMonitor monitor) throws CoreException {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		IFile file = container.getFile(createAnalysisFilePath());
		if (file.exists()) {
			file.delete(true, subMonitor.split(1));
		}
		subMonitor.setWorkRemaining(2);
		file.create(generateAnalysisData(subMonitor.split(1)), true, subMonitor.split(1));
		return file;
	}

	public File persistIn(File container, IProgressMonitor monitor) {
		File destination = new File(container, createAnalysisFilePath().toString());
		if (destination.exists()) {
			destination.delete();
		}
		try {
			FileUtils.writeStringToFile(destination, generateAnalysisDataAsJson(monitor));
			return destination;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

	public String generateAnalysisDataAsJson(IProgressMonitor monitor) {
		final TaskElement rootTask = new TaskElement("Root", "", 1000);
		buildTaskElementHierarchy(getFirstTask(), rootTask, 1f);
		return new Gson().toJson(rootTask);
	}

	protected InputStream generateAnalysisData(IProgressMonitor monitor) {
		final TaskElement rootTask = new TaskElement("Root", "", 1000);
		buildTaskElementHierarchy(getFirstTask(), rootTask, 1f);
		String json = new Gson().toJson(rootTask);
		return new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
	}

	private Task getFirstTask() {
		return (Task) workflow.getNodes().stream()
				.filter((node) -> node instanceof Task && node.getIncoming().isEmpty()).findFirst().get();
	}

	private void buildTaskElementHierarchy(Node node, TaskElement rootTaskElement, float probability) {
		TaskElement currentTaskElement = rootTaskElement;
		int size = Math.round(currentTaskElement.size * probability);
		NodeExecutionIterator iterator = node.getNodeExecutionIterator();
		while (iterator.hasNext()) {
			final Node next = iterator.next();
			if (next instanceof Task) {
				TaskElement taskElement = createTaskElement((Task) next, size);
				currentTaskElement.children.add(taskElement);
				currentTaskElement = taskElement;
			} else if (next instanceof Decision) {
				for (Node nodeAfterDecision : next.getOutgoing()) {
					buildTaskElementHierarchy(nodeAfterDecision, currentTaskElement,
							nodeAfterDecision.getProbabilityInBranch());
				}
				return;
			}
		}
	}

	private TaskElement createTaskElement(Task task, int probability) {
		String type = Optional.ofNullable(taskTypeMap.get(task)).orElse("");
		TaskElement taskElement = new TaskElement(task.getName(), type, probability);
		return taskElement;
	}

	protected Path createAnalysisFilePath() {
		return new Path(cleanForFileName(activityName) + "_" + //$NON-NLS-1$
				createTimestamp() + "." + WorkflowAnalysisEditor.EXTENSION); //$NON-NLS-1$
	}

	private String createTimestamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())); //$NON-NLS-1$
	}

	private String cleanForFileName(String activityName) {
		return activityName.replaceAll(ILLEGAL_FILE_CHARS_PATTERN, "_"); //$NON-NLS-1$
	}

	private class TaskElement {
		@SuppressWarnings("unused")
		String name, type;

		@SuppressWarnings("unused")
		int size;

		List<TaskElement> children = new ArrayList<>();

		public TaskElement(String name, String type, int size) {
			super();
			this.name = name;
			this.type = type;
			this.size = size;
		}
	}

	private ProbabilityConfiguration loadConfiguration(Activity activity) {
		URI uri = activity.eResource().getURI();
		File modelFile = new File(CommonPlugin.resolve(uri).toFileString());

		File modelDir = modelFile.getParentFile();

		if (modelDir != null) {
			File configFile = Arrays.stream(modelDir.listFiles()).filter(f -> f.getName().endsWith(".wf")).findFirst()
					.orElse(null);
			if (configFile != null) {
				URI fileURI = URI.createURI(uri.trimSegments(1).appendSegment(configFile.getName()).toString());
				Resource resource = loadResource(fileURI, activity.eResource().getResourceSet());
				try {
					resource.load(Collections.EMPTY_MAP);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ((WorkflowConfiguration) resource.getContents().get(0)).getProbConf();
			}

		}
		return null;
	}

	public static Resource loadResource(URI uri, ResourceSet resourceSet) {
		Resource resource = resourceSet.getResource(uri, true);
		try {
			resource.load(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}

	private void initializeProbabilityConfiguration(Activity activity) {
		probconf = loadConfiguration(activity);
		if (probconf == null) {
			probconf = WorkflowFactory.eINSTANCE.createProbabilityConfiguration();
			probconf.setLow(0.25f);
			probconf.setMedium(0.5f);
			probconf.setHigh(0.75f);
		}
	}

}
