package com.sample;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;

public class SprinklerTest {
	
	public static final void main(String[] args) throws Exception {
		try {
			SprinklerTest sprinklerTest = new SprinklerTest();
			sprinklerTest.executeExample();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    public void executeExample() throws Exception {
    	KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Alaram.drl"), ResourceType.DRL );            
        if ( kbuilder.hasErrors() ) {
        	System.err.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        
        //Example 1
//        Room room = new Room();
//        room.setName("office");          
//        Sprinkler sprinkler = new Sprinkler();
//        sprinkler.setRoom(room);
//        sprinkler.setOn(false);             
//        Fire fire = new Fire();
//        fire.setRoom(room);                  
//        ksession.execute( Arrays.asList(new Object[] {sprinkler, fire}) );
        
        //Example 2
//        Room room1 = new Room();
//        room1.setName("office"); 
//        Sprinkler sprinkler1 = new Sprinkler();
//        sprinkler1.setRoom(room1);
//        ksession.execute( Arrays.asList(new Object[] {room1, sprinkler1}) );
//        System.out.println(" --- Set Value here --- " + sprinkler1.getOn());  
        
        //Example 3
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
