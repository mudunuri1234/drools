package com.sample;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class DecisionTableToDRLTest {

	public static final void main(String[] args) throws Exception {
		try {
			DecisionTableToDRLTest decisionTableToDRLTest = new DecisionTableToDRLTest();
			decisionTableToDRLTest.executeExample();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    public void executeExample() throws Exception {    	
    	Status status = new Status();
        status.setStatus("T");
        StatusOutput statusOutput = new StatusOutput();
    	
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/oneevent.drl"), ResourceType.DRL );            
        if ( kbuilder.hasErrors() ) {
        	System.err.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute(status);
        System.out.println( "Action Is: " + statusOutput.getAction());        
    }	
	
}