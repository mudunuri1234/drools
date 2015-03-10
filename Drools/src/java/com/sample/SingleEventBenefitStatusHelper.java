package com.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class SingleEventBenefitStatusHelper {
	   
    /**
     * Drools specific code to invoke the rule engine 
     * @return StatelessKnowledgeSession - session used to execute rules.
     * @throws Exception - if there are any errors.
     */
    public StatelessKnowledgeSession getKnowledgeSessionForDrools() throws Exception {
    	DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtableconfiguration.setInputType( DecisionTableInputType.XLS );

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Book1.xls"),
                      ResourceType.DTABLE, dtableconfiguration );

        if ( kbuilder.hasErrors() ) {
            System.err.println( kbuilder.getErrors() );
            return null;
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        //Typical decision tables are used statelessly
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        
        //Convert Decision table to DRL
//    	String drlString = DecisionTableFactory.loadFromInputStream(getSpreadsheetStream(), dtableconfiguration);
//    	System.out.println(drlString);
        
        return ksession;
    }
    
    private InputStream getSpreadsheetStream() throws Exception {
    	return new FileInputStream(new File("C:/projects/Learning/Drools/src/rules/Book1.xls"));
	}

}
