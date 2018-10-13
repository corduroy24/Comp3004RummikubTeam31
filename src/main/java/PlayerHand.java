import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerHand {
	
	String name = "";
	
	PlayerHand(String x){
	//	super(); 
		name=x;	
	}
	private ArrayList<Tile> hand = new ArrayList<Tile>();

	
	public void drawFirst14(Deck x) {
		for (int i=0;i<14;i++) {
			hand.add(x.Draw());
		}
	}
	
	
    public List<Tile> getTiles() {
    	return this.hand;
    }
    
    public Tile getTile (int index) {
    	return this.hand.get(index);
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
    
    public void sortTilesByNumber () {// sorts tiles from least to greatest
    	ArrayList<Tile> sortedHand = this.hand; 
    	
    	Collections.sort(sortedHand, new SortByX());

    }
    //inspired from 
    //https://github.com/Ratchette/Rummikub/blob/master/src/rummikub/Hand.java
    public void sortTilesByColour() {
    	ArrayList<PlayerHand> handsByColour = new ArrayList<PlayerHand>();

    	ArrayList<Tile> tempHand = new ArrayList<Tile>(); 
    	handsByColour = seperateByColour(); 
    	

    	//sorts the hand by number then adds to hand
    	for(int i = 0; i < 4; i++) {
    		handsByColour.get(i).sortTilesByNumber();
    		tempHand.addAll(handsByColour.get(i).getTiles());	
    	}
    	
    	this.hand = tempHand; 
    }
    
    //inspired from:
    //https://github.com/Ratchette/Rummikub/blob/master/src/rummikub/Hand.java
    public ArrayList<PlayerHand> seperateByColour() {
    	ArrayList<PlayerHand> handsByColour = new ArrayList<PlayerHand>();
    	Tile currTile; 
    	
    	for(int i = 0; i < 4; i++)
    		handsByColour.add(new PlayerHand(""+i)); 
    	
    	//seperates the hand by colour 
    	for(int i = 0; i < this.sizeOfHand(); i++) {
    		currTile = this.getTile(i);
    		
    		if(currTile.getColor() == "R")
    			handsByColour.get(0).addTileToHand(currTile);
    		
    		else if(currTile.getColor() == "B")
    			handsByColour.get(1).addTileToHand(currTile);
    		
    		else if(currTile.getColor() == "G")
    			handsByColour.get(2).addTileToHand(currTile);
    		
    		else if(currTile.getColor() == "O")
    			handsByColour.get(3).addTileToHand(currTile);
    	}
    	return handsByColour; 
    }
    
    /*public ArrayList<List> getInitialMeld(){
    	int score = 0; 
    	int largestScore = 0; 
    	
    	ArrayList<Tile> currPossibility; 
    	
    	currPossibility = this.hand.
    	for(int i = 0; i < 5; i++) {
    		
    	}
    	return null; 
    }*/
    
    public boolean groupFound(PlayerHand x) {
    	ArrayList<Tile> y = new ArrayList<Tile>();
    	y.add(x.getTile(0));
    	int count=1;
    	for (int i=1;i<x.sizeOfHand();i++) {
    		if ((x.getTile(i).getNumber())-(y.get(count-1).getNumber())==1) {
    			y.add(x.getTile(i));
    			count++;
    			if (count==3) {
    				return true;
    			}
    		}
    			else if (y.size()>2) {
    				return true;
    			}
    			else if ((i==x.sizeOfHand()-1)&&(y.size()>2)){
        			return true;
        		}
    			else if ((i==x.sizeOfHand()-1)&&(y.size()<3)){
        			return false;
        		}
        		else if (i<x.sizeOfHand()-1) {
        			y.clear();
        			count=1;
        			
        			y.add(x.getTile(i));
        			
        			}
    	}
    	return false;
    }
    
    public PlayerHand findGroup() {
    	ArrayList<PlayerHand> colourSep = this.seperateByColour();
    	int count=0;
    	for (int i=0;i<4;i++) {
    	colourSep.get(i).sortTilesByNumber();
    	if (groupFound(colourSep.get(i))==false) {
    		count++;
    	}
    	}
    	if (count==4) {
    		//System.out.println("NO GROUPS FOUND");
    	}
    	else {
    		
    	}
    	
    	return this;
    }
    
    public boolean hasPossibilities(PlayerHand x) {
    	
    	
    	return false;
    }
    
}

class SortByX implements Comparator<Tile> 
{ 
    // Used for sorting in ascending order of 
    // tile number 
    public int compare(Tile a, Tile b) 
    { 

        return a.getNumber() - b.getNumber(); 
    } 
} 


