
public class ScenarioFactory {
	   //use getScenario method to get object of type scenario 
	public Scenario getScenario(String scenarioType){
	      if(scenarioType == null){
	         return null;
	      }	
	      
	      else if(scenarioType.equalsIgnoreCase("s1")){
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
	      else if(scenarioType.equalsIgnoreCase("s12")){
		         return new Scenario12();
		  }
	      
	      else if(scenarioType.equalsIgnoreCase("s13")){
		         return new Scenario13();
		  }
	      else if(scenarioType.equalsIgnoreCase("s14")){
		         return new Scenario14();
		  }
	      
	      else if(scenarioType.equalsIgnoreCase("s15")){
		         return new Scenario15();
		  }
	      else if(scenarioType.equalsIgnoreCase("s16")){
		         return new Scenario16();
		  }
	      
	      else if(scenarioType.equalsIgnoreCase("s17")){
		         return new Scenario17();
		  }
	      
	      else if(scenarioType.equalsIgnoreCase("s18")){
		         return new Scenario18();
		  }
	      else if(scenarioType.equalsIgnoreCase("s19")){
		         return new Scenario19();
	      }
	      else if(scenarioType.equalsIgnoreCase("s20")){
		         return new Scenario20();
	      }
	      else if(scenarioType.equalsIgnoreCase("s21")){
		         return new Scenario21();
	      }
	      else if(scenarioType.equalsIgnoreCase("s22")){
		         return new Scenario22();
	      }
	      /*else if(scenarioType.equalsIgnoreCase("s23")){
		         return new Scenario23();
	      }*/
	      else if(scenarioType.equalsIgnoreCase("s24")){
		         return new Scenario24();
	      }
	      else if(scenarioType.equalsIgnoreCase("s25")){
		         return new Scenario25();
	      }

	      return null;
	   }
}
