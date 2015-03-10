package com.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;


public class RuleDTExampleTest {

	public static final void main(String[] args) throws Exception {
		try {
			RuleDTExampleTest launcher = new RuleDTExampleTest();
			launcher.executeExample();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }

    public void executeExample() throws Exception {
    	
        DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtableconfiguration.setInputType( DecisionTableInputType.XLS );
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Book1.xls"),
                      ResourceType.DTABLE,
                      dtableconfiguration );

        if ( kbuilder.hasErrors() ) {
            System.err.print( kbuilder.getErrors() );
            return;
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        // typical decision tables are used statelessly
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();        
        
        Status status = new Status();        
        status.setStatus("P");
        StatusOutput statusOutput = new StatusOutput();
        ksession.execute( Arrays.asList(new Object[] {status, statusOutput}) );
        System.out.println( "Action Is: " + statusOutput.getAction());
    }
		
	public void executeExample1() throws Exception {
		/*
		DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtableconfiguration.setInputType( DecisionTableInputType.XLS );		
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource xlsRes = ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Book1.xls");
		System.out.println(xlsRes);
		kbuilder.add( xlsRes, ResourceType.DTABLE, dtableconfiguration );	
		
		
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(); 
		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtconf.setInputType( DecisionTableInputType.XLS );
		dtconf.setWorksheetName( "Tables" ); //Sheet containing the decision table
		kbuilder.add(ResourceFactory.newFileResource("C:/projects/Learning/Drools/src/rules/Book1.xls"), ResourceType.DTABLE, dtconf);
	
					
		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
    	String drlString = DecisionTableFactory.loadFromInputStream(getSpreadsheetStream(), dtconf);
    	System.out.println(drlString);
    		*/
	}
	  
	/*
    public void executeExample() throws Exception {
    	
    	//first we compile the decision table into a whole lot of rules.
    	SpreadsheetCompiler compiler = new SpreadsheetCompiler();
    	InputStream is = getSpreadsheetStream();
    	System.out.println(is);
    	String drl = compiler.compile(getSpreadsheetStream(), InputType.XLS);
    	
    	
    	//UNCOMMENT ME TO SEE THE DRL THAT IS GENERATED
    	System.out.println("------- DRL Start ------");
    	System.out.println(drl);
    	System.out.println("------- DRL End ------");
    	
    	RuleBase ruleBase = buildRuleBase(drl);
    	System.out.println("------- ruleBase ------");
    	
    	
        // typical decision tables are used statelessly
		//StatelessSession session = ruleBase.newStatelessSession();
		
		//now create some test data
		//Driver driver = new Driver();
		//Policy policy = new Policy();
		
        //session.execute( new Object[] { driver, policy } );
		
		//System.out.println("BASE PRICE IS: " + policy.getBasePrice());
		//System.out.println("DISCOUNT IS: " + policy.getDiscountPercent());
		
        //return policy.getBasePrice();  
    	
    } */


    /** Build the rule base from the generated DRL */
 /*	private RuleBase buildRuleBase(String drl) throws DroolsParserException, IOException, Exception {
		//now we build the rule package and rulebase, as if they are normal rules
		PackageBuilder builder = new PackageBuilder();
		builder.addPackageFromDrl( new StringReader(drl) );
		
		//add the package to a rulebase (deploy the rule package).
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage( builder.getPackage() );
		return ruleBase;
	} */
    
    private InputStream getSpreadsheetStream() throws Exception {
    	//return new FileInputStream(new File("C:/projects/Learning/Drools/src/rules/Book1.xls"));
    	return new FileInputStream(new File("C:/projects/Learning/Drools/src/rules/BenefitStatus.xls"));
    	//return this.getClass().getResourceAsStream("C:/projects/Learning/Drools/src/rules/BenefitStatus.xls");
    	//return new FileInputStream(this.getClass().getResource("C:/projects/Learning/Drools/src/rules/BenefitStatus.xls").getFile());

	}

			
}
