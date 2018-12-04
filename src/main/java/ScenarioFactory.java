
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
	      
	      return null;
	   }
}
