
public class InitialHandFactory {
	   //use getShape method to get object of type shape 
	public InitialHand getHand(String handType){
	      if(handType == null){
	         return null;
	      }		
	      if(handType.equalsIgnoreCase("s1")){
	         return new dealStrategy1();
	         
	      } 
	      else if(handType.equalsIgnoreCase("s2")){
	         return new dealStrategy2();
	         
	      }
	      else if(handType.equalsIgnoreCase("s3")){
	         return new dealStrategy3();
	      }
	      
	      else if(handType.equalsIgnoreCase("s4")){
		         return new dealStrategy4();
		      }
	      
	      return null;
	   }
}
