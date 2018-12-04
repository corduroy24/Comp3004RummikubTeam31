
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
	        
	      }/*
	      else if(handType.equalsIgnoreCase("s3")){
	         return new ();
	      }
	      
	      else if(handType.equalsIgnoreCase("s4")){
		         return new ();
		      }
	      */
	      return null;
	   }
}
