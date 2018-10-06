import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
	
	String name = "";
	
	PlayerHand(String x){
	name=x;	
	}
	private ArrayList<Tile> hand = new ArrayList<Tile>();

	
	public void drawFirst14(Deck x) {
		for (int i=0;i<14;i++) {
			hand.add(x.Draw());
		}
	}
	
	
    public List<Tile> getHand() {
    	return this.hand;
    }
    
    public void addTileToHand(Tile newTile ) {
    	hand.add(newTile);
    }

    
    public int sizeOfHand() {
    	return hand.size(); 
    }
	
    public void playTileFromHand(Tile tileToPlay) {
    //	this.playerStrategy.playTiles(hand, tileToPlay);
    	hand.remove(tileToPlay); 
    	hand.trimToSize();

    }
    
    public boolean isEmpty() {
    	return hand.isEmpty();
    }
    
    public void HandReader() {
    	if (isEmpty()) {
    		System.out.println("Player: "+ name +" has no tiles");
    	}
    	else {
    		System.out.println("Displaying Player: "+ name +"'s tiles");
    		for (int i=0;i<sizeOfHand();i++) {
    			String Color="";
    			if (hand.get(i).getColor().equals("O")) {
    				Color="Orange";
    			}
    			if (hand.get(i).getColor().equals("B")) {
    				Color="Blue";
    			}
    			if (hand.get(i).getColor().equals("G")) {
    				Color="Green";
    			}
    			if (hand.get(i).getColor().equals("R")) {
    				Color="Red";
    			}
    			System.out.println(i+1 +". " + Color + " " + hand.get(i).getNumber());
    		}
    	}
    }
    
}
