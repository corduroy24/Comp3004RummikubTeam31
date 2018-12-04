
public class ScenarioFactory {
	   //use getScenario method to get object of type scenario 
	public Scenario getScenario(String scenarioType){
	      if(scenarioType == null){
	         return null;
	      }	
	      
	      if(scenarioType.equalsIgnoreCase("s1")){
	         return new Scenario1();
	         
	      } 
	     else if(scenarioType.equalsIgnoreCase("s2")){
	         return new Scenario2();
	        
	      }
	      else if(scenarioType.equalsIgnoreCase("s3")){
	         return new Scenario3();
	      }
	      
	      else if(scenarioType.equalsIgnoreCase("s4")){
		         return new Scenario4();
		  }
	      
	      else if(scenarioType.equalsIgnoreCase("s5")){
		         return new Scenario5();
		  }
	      else if(scenarioType.equalsIgnoreCase("s6")){
		         return new Scenario6();
		  }
	      
	      else if(scenarioType.equalsIgnoreCase("s7")){
		         return new Scenario7();
		  }
	      else if(scenarioType.equalsIgnoreCase("s8")){
		         return new Scenario8();
		  }
	      else if(scenarioType.equalsIgnoreCase("s9")){
		         return new Scenario9();
		  }
	      else if(scenarioType.equalsIgnoreCase("s10")){
		         return new Scenario10();
		  }
	      else if(scenarioType.equalsIgnoreCase("s11")){
		         return new Scenario11();
		  }
	      return null;
	   }
}
