package com.sample;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.drools.KnowledgeBase;
//import org.drools.KnowledgeBaseFactory;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
//import org.drools.builder.KnowledgeBuilder;
//import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.compiler.PackageBuilder;
//import org.drools.io.ResourceFactory;
//import org.drools.builder.ResourceType;
import org.drools.rule.Package;
//import org.drools.runtime.StatelessKnowledgeSession;

//import org.drools.rule.*;
//import org.drools.runtime.*;
//import org.drools.*;
//import org.drools.builder.*;
//import org.drools.io.*;


/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
        	
        	//load up the rulebase
        	RuleBase ruleBase = readRule();
//            WorkingMemory workingMemory = ruleBase..newWorkingMemory();
            
            //go ! 
            /*
            Message message = new Message();
            message.setMessage(  "Hello World" );
            message.setStatus( Message.HELLO );
            workingMemory.assertObject( message );
            workingMemory.fireAllRules(); */
            
            //Applicant
        /*	Applicant applicant = new Applicant();
            applicant.setAge(15);
            applicant.setName("John");       
            applicant.setDateApplied( new Date());            
            workingMemory.assertObject( applicant );
            workingMemory.fireAllRules(); */
            
            //Room
          /*  Room room = new Room();
            room.setName("office");          
            Sprinkler sprinkler = new Sprinkler();
            sprinkler.setRoom(room);
            sprinkler.setOn(false);             
            Fire fire = new Fire();
            fire.setRoom(room); 
            workingMemory.assertObject( sprinkler );            
            workingMemory.assertObject( fire );
            
                       
            Room room = new Room();
            room.setName("office"); 
            Sprinkler sprinkler = new Sprinkler();
            sprinkler.setRoom(room);
            workingMemory.assertObject( room );              
            workingMemory.assertObject( sprinkler );                               
            System.out.println(" --- Set Value here --- " + sprinkler.getOn());            
            */
            
            String[] names = new String[] { "kitchen", "bedroom", "office", "livingroom" };
            //Map<String,Room> name2room = new HashMap<String,Room>();
//            for(String name: names) {
//            	Room room1 = new Room();
//            	room1.setName(name);
//            	//name2room.put( name, room1 );
//            	workingMemory.assertObject( room1 );              	
//            	Sprinkler sprinkler1 = new Sprinkler();
//            	sprinkler1.setRoom(room1);
//            	workingMemory.assertObject( sprinkler1 );                 	
//            }
                  
                 
            
            
//            workingMemory.fireAllRules();
            
        	/*
            KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            //kbuilder.add( ResourceFactory.newClassPathResource("Applicant.drl", DroolsTest.class), ResourceType.DRL );
            kbuilder.add(ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Applicant.drl"), ResourceType.DRL );            
            if ( kbuilder.hasErrors() ) {
            	System.err.println( kbuilder.getErrors().toString() );
            }
            KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
            kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
            StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
            Applicant applicant1 = new Applicant("Mr John Smith", 16, true);
            System.err.println( applicant1.isValid() );
            ksession.execute( applicant1 );
            System.err.println( applicant1.isValid() );
            */
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Please note that this is the "low level" rule assembly API.
     */
	private static RuleBase readRule() throws Exception {
		//read in the source
		//Reader source = new InputStreamReader( DroolsTest.class.getResourceAsStream( "C:/projects/Learning/Drools/src/rules/Sample.drl" ) );		
		//Reader source = new InputStreamReader(new FileInputStream("C:/projects/Learning/Drools/src/rules/Sample.drl"));
		//Reader source = new InputStreamReader(new FileInputStream("C:/projects/Learning/Drools/src/rules/Applicant.drl"));
		Reader source = new InputStreamReader(new FileInputStream("C:/projects/Learning/Drools/src/rules/Alaram.drl"));
		
		//optionally read in the DSL (if you are using it).
		//Reader dsl = new InputStreamReader( DroolsTest.class.getResourceAsStream( "/mylang.dsl" ) );

		//Use package builder to build up a rule package.
		//An alternative lower level class called "DrlParser" can also be used...
		
		PackageBuilder builder = new PackageBuilder();

		//this wil parse and compile in one step
		//NOTE: There are 2 methods here, the one argument one is for normal DRL.
		builder.addPackageFromDrl( source );

		//Use the following instead of above if you are using a DSL:
		//builder.addPackageFromDrl( source, dsl );
		
		//get the compiled package (which is serializable)
		Package pkg = builder.getPackage();
		
		//add the package to a rulebase (deploy the rule package).
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage( pkg );
		return ruleBase;
	}
	
	public static class Message {
		public static final int HELLO = 0;
		public static final int GOODBYE = 1;
		
		private String message;
		
		private int status;
		
		public String getMessage() {
			return this.message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public int getStatus() {
			return this.status;
		}
		
		public void setStatus( int status ) {
			this.status = status;
		}
	}
	
}
