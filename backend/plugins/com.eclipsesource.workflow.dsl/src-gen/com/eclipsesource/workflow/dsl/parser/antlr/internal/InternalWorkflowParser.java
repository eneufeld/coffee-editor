package com.eclipsesource.workflow.dsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.eclipsesource.workflow.dsl.services.WorkflowGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalWorkflowParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'machine'", "':'", "'workflow'", "'assertions'", "','", "'=>'", "'probabilities'", "'low'", "'medium'", "'high'", "'.'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=5;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalWorkflowParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalWorkflowParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalWorkflowParser.tokenNames; }
    public String getGrammarFileName() { return "InternalWorkflow.g"; }



     	private WorkflowGrammarAccess grammarAccess;

        public InternalWorkflowParser(TokenStream input, WorkflowGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "WorkflowConfiguration";
       	}

       	@Override
       	protected WorkflowGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleWorkflowConfiguration"
    // InternalWorkflow.g:64:1: entryRuleWorkflowConfiguration returns [EObject current=null] : iv_ruleWorkflowConfiguration= ruleWorkflowConfiguration EOF ;
    public final EObject entryRuleWorkflowConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWorkflowConfiguration = null;


        try {
            // InternalWorkflow.g:64:62: (iv_ruleWorkflowConfiguration= ruleWorkflowConfiguration EOF )
            // InternalWorkflow.g:65:2: iv_ruleWorkflowConfiguration= ruleWorkflowConfiguration EOF
            {
             newCompositeNode(grammarAccess.getWorkflowConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWorkflowConfiguration=ruleWorkflowConfiguration();

            state._fsp--;

             current =iv_ruleWorkflowConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWorkflowConfiguration"


    // $ANTLR start "ruleWorkflowConfiguration"
    // InternalWorkflow.g:71:1: ruleWorkflowConfiguration returns [EObject current=null] : (otherlv_0= 'machine' otherlv_1= ':' ( (lv_machine_2_0= ruleFQN ) ) otherlv_3= 'workflow' otherlv_4= ':' ( (lv_model_5_0= ruleFQN ) ) ( (lv_probConf_6_0= ruleProbabilityConfiguration ) )? (otherlv_7= 'assertions' ( (lv_assertions_8_0= ruleAssertion ) ) (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )* )? ) ;
    public final EObject ruleWorkflowConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_machine_2_0 = null;

        AntlrDatatypeRuleToken lv_model_5_0 = null;

        EObject lv_probConf_6_0 = null;

        EObject lv_assertions_8_0 = null;

        EObject lv_assertions_10_0 = null;



        	enterRule();

        try {
            // InternalWorkflow.g:77:2: ( (otherlv_0= 'machine' otherlv_1= ':' ( (lv_machine_2_0= ruleFQN ) ) otherlv_3= 'workflow' otherlv_4= ':' ( (lv_model_5_0= ruleFQN ) ) ( (lv_probConf_6_0= ruleProbabilityConfiguration ) )? (otherlv_7= 'assertions' ( (lv_assertions_8_0= ruleAssertion ) ) (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )* )? ) )
            // InternalWorkflow.g:78:2: (otherlv_0= 'machine' otherlv_1= ':' ( (lv_machine_2_0= ruleFQN ) ) otherlv_3= 'workflow' otherlv_4= ':' ( (lv_model_5_0= ruleFQN ) ) ( (lv_probConf_6_0= ruleProbabilityConfiguration ) )? (otherlv_7= 'assertions' ( (lv_assertions_8_0= ruleAssertion ) ) (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )* )? )
            {
            // InternalWorkflow.g:78:2: (otherlv_0= 'machine' otherlv_1= ':' ( (lv_machine_2_0= ruleFQN ) ) otherlv_3= 'workflow' otherlv_4= ':' ( (lv_model_5_0= ruleFQN ) ) ( (lv_probConf_6_0= ruleProbabilityConfiguration ) )? (otherlv_7= 'assertions' ( (lv_assertions_8_0= ruleAssertion ) ) (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )* )? )
            // InternalWorkflow.g:79:3: otherlv_0= 'machine' otherlv_1= ':' ( (lv_machine_2_0= ruleFQN ) ) otherlv_3= 'workflow' otherlv_4= ':' ( (lv_model_5_0= ruleFQN ) ) ( (lv_probConf_6_0= ruleProbabilityConfiguration ) )? (otherlv_7= 'assertions' ( (lv_assertions_8_0= ruleAssertion ) ) (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )* )?
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getWorkflowConfigurationAccess().getMachineKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getWorkflowConfigurationAccess().getColonKeyword_1());
            		
            // InternalWorkflow.g:87:3: ( (lv_machine_2_0= ruleFQN ) )
            // InternalWorkflow.g:88:4: (lv_machine_2_0= ruleFQN )
            {
            // InternalWorkflow.g:88:4: (lv_machine_2_0= ruleFQN )
            // InternalWorkflow.g:89:5: lv_machine_2_0= ruleFQN
            {

            					newCompositeNode(grammarAccess.getWorkflowConfigurationAccess().getMachineFQNParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_5);
            lv_machine_2_0=ruleFQN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWorkflowConfigurationRule());
            					}
            					set(
            						current,
            						"machine",
            						lv_machine_2_0,
            						"com.eclipsesource.workflow.dsl.Workflow.FQN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getWorkflowConfigurationAccess().getWorkflowKeyword_3());
            		
            otherlv_4=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getWorkflowConfigurationAccess().getColonKeyword_4());
            		
            // InternalWorkflow.g:114:3: ( (lv_model_5_0= ruleFQN ) )
            // InternalWorkflow.g:115:4: (lv_model_5_0= ruleFQN )
            {
            // InternalWorkflow.g:115:4: (lv_model_5_0= ruleFQN )
            // InternalWorkflow.g:116:5: lv_model_5_0= ruleFQN
            {

            					newCompositeNode(grammarAccess.getWorkflowConfigurationAccess().getModelFQNParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_6);
            lv_model_5_0=ruleFQN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWorkflowConfigurationRule());
            					}
            					set(
            						current,
            						"model",
            						lv_model_5_0,
            						"com.eclipsesource.workflow.dsl.Workflow.FQN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalWorkflow.g:133:3: ( (lv_probConf_6_0= ruleProbabilityConfiguration ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==17) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalWorkflow.g:134:4: (lv_probConf_6_0= ruleProbabilityConfiguration )
                    {
                    // InternalWorkflow.g:134:4: (lv_probConf_6_0= ruleProbabilityConfiguration )
                    // InternalWorkflow.g:135:5: lv_probConf_6_0= ruleProbabilityConfiguration
                    {

                    					newCompositeNode(grammarAccess.getWorkflowConfigurationAccess().getProbConfProbabilityConfigurationParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_7);
                    lv_probConf_6_0=ruleProbabilityConfiguration();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getWorkflowConfigurationRule());
                    					}
                    					set(
                    						current,
                    						"probConf",
                    						lv_probConf_6_0,
                    						"com.eclipsesource.workflow.dsl.Workflow.ProbabilityConfiguration");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalWorkflow.g:152:3: (otherlv_7= 'assertions' ( (lv_assertions_8_0= ruleAssertion ) ) (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalWorkflow.g:153:4: otherlv_7= 'assertions' ( (lv_assertions_8_0= ruleAssertion ) ) (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )*
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_4); 

                    				newLeafNode(otherlv_7, grammarAccess.getWorkflowConfigurationAccess().getAssertionsKeyword_7_0());
                    			
                    // InternalWorkflow.g:157:4: ( (lv_assertions_8_0= ruleAssertion ) )
                    // InternalWorkflow.g:158:5: (lv_assertions_8_0= ruleAssertion )
                    {
                    // InternalWorkflow.g:158:5: (lv_assertions_8_0= ruleAssertion )
                    // InternalWorkflow.g:159:6: lv_assertions_8_0= ruleAssertion
                    {

                    						newCompositeNode(grammarAccess.getWorkflowConfigurationAccess().getAssertionsAssertionParserRuleCall_7_1_0());
                    					
                    pushFollow(FOLLOW_8);
                    lv_assertions_8_0=ruleAssertion();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getWorkflowConfigurationRule());
                    						}
                    						add(
                    							current,
                    							"assertions",
                    							lv_assertions_8_0,
                    							"com.eclipsesource.workflow.dsl.Workflow.Assertion");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalWorkflow.g:176:4: (otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==15) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // InternalWorkflow.g:177:5: otherlv_9= ',' ( (lv_assertions_10_0= ruleAssertion ) )
                    	    {
                    	    otherlv_9=(Token)match(input,15,FOLLOW_4); 

                    	    					newLeafNode(otherlv_9, grammarAccess.getWorkflowConfigurationAccess().getCommaKeyword_7_2_0());
                    	    				
                    	    // InternalWorkflow.g:181:5: ( (lv_assertions_10_0= ruleAssertion ) )
                    	    // InternalWorkflow.g:182:6: (lv_assertions_10_0= ruleAssertion )
                    	    {
                    	    // InternalWorkflow.g:182:6: (lv_assertions_10_0= ruleAssertion )
                    	    // InternalWorkflow.g:183:7: lv_assertions_10_0= ruleAssertion
                    	    {

                    	    							newCompositeNode(grammarAccess.getWorkflowConfigurationAccess().getAssertionsAssertionParserRuleCall_7_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_8);
                    	    lv_assertions_10_0=ruleAssertion();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getWorkflowConfigurationRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"assertions",
                    	    								lv_assertions_10_0,
                    	    								"com.eclipsesource.workflow.dsl.Workflow.Assertion");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkflowConfiguration"


    // $ANTLR start "entryRuleAssertion"
    // InternalWorkflow.g:206:1: entryRuleAssertion returns [EObject current=null] : iv_ruleAssertion= ruleAssertion EOF ;
    public final EObject entryRuleAssertion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssertion = null;


        try {
            // InternalWorkflow.g:206:50: (iv_ruleAssertion= ruleAssertion EOF )
            // InternalWorkflow.g:207:2: iv_ruleAssertion= ruleAssertion EOF
            {
             newCompositeNode(grammarAccess.getAssertionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssertion=ruleAssertion();

            state._fsp--;

             current =iv_ruleAssertion; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssertion"


    // $ANTLR start "ruleAssertion"
    // InternalWorkflow.g:213:1: ruleAssertion returns [EObject current=null] : ( ( (lv_before_0_0= ruleFQN ) ) otherlv_1= '=>' ( (lv_after_2_0= ruleFQN ) ) ) ;
    public final EObject ruleAssertion() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_before_0_0 = null;

        AntlrDatatypeRuleToken lv_after_2_0 = null;



        	enterRule();

        try {
            // InternalWorkflow.g:219:2: ( ( ( (lv_before_0_0= ruleFQN ) ) otherlv_1= '=>' ( (lv_after_2_0= ruleFQN ) ) ) )
            // InternalWorkflow.g:220:2: ( ( (lv_before_0_0= ruleFQN ) ) otherlv_1= '=>' ( (lv_after_2_0= ruleFQN ) ) )
            {
            // InternalWorkflow.g:220:2: ( ( (lv_before_0_0= ruleFQN ) ) otherlv_1= '=>' ( (lv_after_2_0= ruleFQN ) ) )
            // InternalWorkflow.g:221:3: ( (lv_before_0_0= ruleFQN ) ) otherlv_1= '=>' ( (lv_after_2_0= ruleFQN ) )
            {
            // InternalWorkflow.g:221:3: ( (lv_before_0_0= ruleFQN ) )
            // InternalWorkflow.g:222:4: (lv_before_0_0= ruleFQN )
            {
            // InternalWorkflow.g:222:4: (lv_before_0_0= ruleFQN )
            // InternalWorkflow.g:223:5: lv_before_0_0= ruleFQN
            {

            					newCompositeNode(grammarAccess.getAssertionAccess().getBeforeFQNParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_9);
            lv_before_0_0=ruleFQN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssertionRule());
            					}
            					set(
            						current,
            						"before",
            						lv_before_0_0,
            						"com.eclipsesource.workflow.dsl.Workflow.FQN");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,16,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getAssertionAccess().getEqualsSignGreaterThanSignKeyword_1());
            		
            // InternalWorkflow.g:244:3: ( (lv_after_2_0= ruleFQN ) )
            // InternalWorkflow.g:245:4: (lv_after_2_0= ruleFQN )
            {
            // InternalWorkflow.g:245:4: (lv_after_2_0= ruleFQN )
            // InternalWorkflow.g:246:5: lv_after_2_0= ruleFQN
            {

            					newCompositeNode(grammarAccess.getAssertionAccess().getAfterFQNParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_after_2_0=ruleFQN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssertionRule());
            					}
            					set(
            						current,
            						"after",
            						lv_after_2_0,
            						"com.eclipsesource.workflow.dsl.Workflow.FQN");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssertion"


    // $ANTLR start "entryRuleProbabilityConfiguration"
    // InternalWorkflow.g:267:1: entryRuleProbabilityConfiguration returns [EObject current=null] : iv_ruleProbabilityConfiguration= ruleProbabilityConfiguration EOF ;
    public final EObject entryRuleProbabilityConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProbabilityConfiguration = null;


        try {
            // InternalWorkflow.g:267:65: (iv_ruleProbabilityConfiguration= ruleProbabilityConfiguration EOF )
            // InternalWorkflow.g:268:2: iv_ruleProbabilityConfiguration= ruleProbabilityConfiguration EOF
            {
             newCompositeNode(grammarAccess.getProbabilityConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProbabilityConfiguration=ruleProbabilityConfiguration();

            state._fsp--;

             current =iv_ruleProbabilityConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProbabilityConfiguration"


    // $ANTLR start "ruleProbabilityConfiguration"
    // InternalWorkflow.g:274:1: ruleProbabilityConfiguration returns [EObject current=null] : ( (otherlv_0= 'probabilities' otherlv_1= 'low' otherlv_2= ':' ( (lv_low_3_0= ruleFloat ) ) ) (otherlv_4= 'medium' otherlv_5= ':' ( (lv_medium_6_0= ruleFloat ) ) ) (otherlv_7= 'high' otherlv_8= ':' ( (lv_high_9_0= ruleFloat ) ) ) ) ;
    public final EObject ruleProbabilityConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_low_3_0 = null;

        AntlrDatatypeRuleToken lv_medium_6_0 = null;

        AntlrDatatypeRuleToken lv_high_9_0 = null;



        	enterRule();

        try {
            // InternalWorkflow.g:280:2: ( ( (otherlv_0= 'probabilities' otherlv_1= 'low' otherlv_2= ':' ( (lv_low_3_0= ruleFloat ) ) ) (otherlv_4= 'medium' otherlv_5= ':' ( (lv_medium_6_0= ruleFloat ) ) ) (otherlv_7= 'high' otherlv_8= ':' ( (lv_high_9_0= ruleFloat ) ) ) ) )
            // InternalWorkflow.g:281:2: ( (otherlv_0= 'probabilities' otherlv_1= 'low' otherlv_2= ':' ( (lv_low_3_0= ruleFloat ) ) ) (otherlv_4= 'medium' otherlv_5= ':' ( (lv_medium_6_0= ruleFloat ) ) ) (otherlv_7= 'high' otherlv_8= ':' ( (lv_high_9_0= ruleFloat ) ) ) )
            {
            // InternalWorkflow.g:281:2: ( (otherlv_0= 'probabilities' otherlv_1= 'low' otherlv_2= ':' ( (lv_low_3_0= ruleFloat ) ) ) (otherlv_4= 'medium' otherlv_5= ':' ( (lv_medium_6_0= ruleFloat ) ) ) (otherlv_7= 'high' otherlv_8= ':' ( (lv_high_9_0= ruleFloat ) ) ) )
            // InternalWorkflow.g:282:3: (otherlv_0= 'probabilities' otherlv_1= 'low' otherlv_2= ':' ( (lv_low_3_0= ruleFloat ) ) ) (otherlv_4= 'medium' otherlv_5= ':' ( (lv_medium_6_0= ruleFloat ) ) ) (otherlv_7= 'high' otherlv_8= ':' ( (lv_high_9_0= ruleFloat ) ) )
            {
            // InternalWorkflow.g:282:3: (otherlv_0= 'probabilities' otherlv_1= 'low' otherlv_2= ':' ( (lv_low_3_0= ruleFloat ) ) )
            // InternalWorkflow.g:283:4: otherlv_0= 'probabilities' otherlv_1= 'low' otherlv_2= ':' ( (lv_low_3_0= ruleFloat ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_10); 

            				newLeafNode(otherlv_0, grammarAccess.getProbabilityConfigurationAccess().getProbabilitiesKeyword_0_0());
            			
            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            				newLeafNode(otherlv_1, grammarAccess.getProbabilityConfigurationAccess().getLowKeyword_0_1());
            			
            otherlv_2=(Token)match(input,12,FOLLOW_11); 

            				newLeafNode(otherlv_2, grammarAccess.getProbabilityConfigurationAccess().getColonKeyword_0_2());
            			
            // InternalWorkflow.g:295:4: ( (lv_low_3_0= ruleFloat ) )
            // InternalWorkflow.g:296:5: (lv_low_3_0= ruleFloat )
            {
            // InternalWorkflow.g:296:5: (lv_low_3_0= ruleFloat )
            // InternalWorkflow.g:297:6: lv_low_3_0= ruleFloat
            {

            						newCompositeNode(grammarAccess.getProbabilityConfigurationAccess().getLowFloatParserRuleCall_0_3_0());
            					
            pushFollow(FOLLOW_12);
            lv_low_3_0=ruleFloat();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getProbabilityConfigurationRule());
            						}
            						set(
            							current,
            							"low",
            							lv_low_3_0,
            							"com.eclipsesource.workflow.dsl.Workflow.Float");
            						afterParserOrEnumRuleCall();
            					

            }


            }


            }

            // InternalWorkflow.g:315:3: (otherlv_4= 'medium' otherlv_5= ':' ( (lv_medium_6_0= ruleFloat ) ) )
            // InternalWorkflow.g:316:4: otherlv_4= 'medium' otherlv_5= ':' ( (lv_medium_6_0= ruleFloat ) )
            {
            otherlv_4=(Token)match(input,19,FOLLOW_3); 

            				newLeafNode(otherlv_4, grammarAccess.getProbabilityConfigurationAccess().getMediumKeyword_1_0());
            			
            otherlv_5=(Token)match(input,12,FOLLOW_11); 

            				newLeafNode(otherlv_5, grammarAccess.getProbabilityConfigurationAccess().getColonKeyword_1_1());
            			
            // InternalWorkflow.g:324:4: ( (lv_medium_6_0= ruleFloat ) )
            // InternalWorkflow.g:325:5: (lv_medium_6_0= ruleFloat )
            {
            // InternalWorkflow.g:325:5: (lv_medium_6_0= ruleFloat )
            // InternalWorkflow.g:326:6: lv_medium_6_0= ruleFloat
            {

            						newCompositeNode(grammarAccess.getProbabilityConfigurationAccess().getMediumFloatParserRuleCall_1_2_0());
            					
            pushFollow(FOLLOW_13);
            lv_medium_6_0=ruleFloat();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getProbabilityConfigurationRule());
            						}
            						set(
            							current,
            							"medium",
            							lv_medium_6_0,
            							"com.eclipsesource.workflow.dsl.Workflow.Float");
            						afterParserOrEnumRuleCall();
            					

            }


            }


            }

            // InternalWorkflow.g:344:3: (otherlv_7= 'high' otherlv_8= ':' ( (lv_high_9_0= ruleFloat ) ) )
            // InternalWorkflow.g:345:4: otherlv_7= 'high' otherlv_8= ':' ( (lv_high_9_0= ruleFloat ) )
            {
            otherlv_7=(Token)match(input,20,FOLLOW_3); 

            				newLeafNode(otherlv_7, grammarAccess.getProbabilityConfigurationAccess().getHighKeyword_2_0());
            			
            otherlv_8=(Token)match(input,12,FOLLOW_11); 

            				newLeafNode(otherlv_8, grammarAccess.getProbabilityConfigurationAccess().getColonKeyword_2_1());
            			
            // InternalWorkflow.g:353:4: ( (lv_high_9_0= ruleFloat ) )
            // InternalWorkflow.g:354:5: (lv_high_9_0= ruleFloat )
            {
            // InternalWorkflow.g:354:5: (lv_high_9_0= ruleFloat )
            // InternalWorkflow.g:355:6: lv_high_9_0= ruleFloat
            {

            						newCompositeNode(grammarAccess.getProbabilityConfigurationAccess().getHighFloatParserRuleCall_2_2_0());
            					
            pushFollow(FOLLOW_2);
            lv_high_9_0=ruleFloat();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getProbabilityConfigurationRule());
            						}
            						set(
            							current,
            							"high",
            							lv_high_9_0,
            							"com.eclipsesource.workflow.dsl.Workflow.Float");
            						afterParserOrEnumRuleCall();
            					

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProbabilityConfiguration"


    // $ANTLR start "entryRuleFQN"
    // InternalWorkflow.g:377:1: entryRuleFQN returns [String current=null] : iv_ruleFQN= ruleFQN EOF ;
    public final String entryRuleFQN() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFQN = null;


        try {
            // InternalWorkflow.g:377:43: (iv_ruleFQN= ruleFQN EOF )
            // InternalWorkflow.g:378:2: iv_ruleFQN= ruleFQN EOF
            {
             newCompositeNode(grammarAccess.getFQNRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFQN=ruleFQN();

            state._fsp--;

             current =iv_ruleFQN.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFQN"


    // $ANTLR start "ruleFQN"
    // InternalWorkflow.g:384:1: ruleFQN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleFQN() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalWorkflow.g:390:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalWorkflow.g:391:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalWorkflow.g:391:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalWorkflow.g:392:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_14); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getFQNAccess().getIDTerminalRuleCall_0());
            		
            // InternalWorkflow.g:399:3: (kw= '.' this_ID_2= RULE_ID )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==21) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalWorkflow.g:400:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,21,FOLLOW_4); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getFQNAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_14); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getFQNAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFQN"


    // $ANTLR start "entryRuleFloat"
    // InternalWorkflow.g:417:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // InternalWorkflow.g:417:45: (iv_ruleFloat= ruleFloat EOF )
            // InternalWorkflow.g:418:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFloat"


    // $ANTLR start "ruleFloat"
    // InternalWorkflow.g:424:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalWorkflow.g:430:2: ( (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT ) )
            // InternalWorkflow.g:431:2: (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT )
            {
            // InternalWorkflow.g:431:2: (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT )
            // InternalWorkflow.g:432:3: this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_15); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getFloatAccess().getINTTerminalRuleCall_0());
            		
            kw=(Token)match(input,21,FOLLOW_11); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getFloatAccess().getFullStopKeyword_1());
            		
            this_INT_2=(Token)match(input,RULE_INT,FOLLOW_2); 

            			current.merge(this_INT_2);
            		

            			newLeafNode(this_INT_2, grammarAccess.getFloatAccess().getINTTerminalRuleCall_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloat"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000024002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200000L});

}