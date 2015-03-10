package com.sample;

import java.util.Date;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class ApplicantTest {
	
	public static final void main(String[] args) throws Exception {
		try {
			ApplicantTest applicantTest = new ApplicantTest();
			applicantTest.executeExample();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    public void executeExample() throws Exception {
    	    	
    	Applicant applicant = new Applicant();
        applicant.setAge(15);
        applicant.setName("John");       
        applicant.setDateApplied(new Date());    
        
    	
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //kbuilder.add( ResourceFactory.newClassPathResource("Applicant.drl", DroolsTest.class), ResourceType.DRL );
        kbuilder.add(ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Applicant.drl"), ResourceType.DRL );            
        if ( kbuilder.hasErrors() ) {
        	System.err.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute(applicant);
        System.err.println( "------ is Valid ------" + applicant.isValid() );        
    }

}
