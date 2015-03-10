package com.sample;

import java.util.Date;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;

public class ChangeSetTest {
	
	public static final void main(String[] args) throws Exception {
		try {
			ChangeSetTest changeSetTest = new ChangeSetTest();
			changeSetTest.executeExample();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    public void executeExample() throws Exception {
    	//Case 1 : Applicant
    	Applicant applicant = new Applicant();
        applicant.setAge(15);
        applicant.setName("John");       
        applicant.setDateApplied(new Date());            
    	
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //kbuilder.add( ResourceFactory.newClassPathResource("Applicant.drl", DroolsTest.class), ResourceType.DRL );
        kbuilder.add(ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/changeset.xml"), ResourceType.CHANGE_SET);            
        if ( kbuilder.hasErrors() ) {
        	System.err.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute(applicant);
        System.err.println( "------ is Valid ------" + applicant.isValid() );   
        
        
        //Case 2 : Sprinkler
        StatefulKnowledgeSession ksession1 = kbase.newStatefulKnowledgeSession();
        String[] names = new String[] { "kitchen", "bedroom", "office", "livingroom" };
        for(String name: names) {
        	Room room2 = new Room();
        	room2.setName(name);
        	ksession1.insert( room2 ); 
        	Sprinkler sprinkler2 = new Sprinkler();
        	sprinkler2.setRoom(room2);
        	ksession1.insert( sprinkler2 ); 
        }
        ksession1.fireAllRules(); 
    }
    
}
