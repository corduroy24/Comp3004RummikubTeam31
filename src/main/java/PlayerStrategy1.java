import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class PlayerStrategy1 implements PlayerStrategy {
	private Support function;
	
	public boolean playTheGame(Player p) {
		function = new Support();
		// TODO Auto-generated method stub
		if(p.getIsFirstMeldComplete()) {
			ArrayList<Tile> first_hand = new ArrayList<Tile>();
			ArrayList<Tile> second_hand = new ArrayList<Tile>();
			
			//copy player hand to sample hand
			for(int i =0; i < p.getHand().sizeOfHand();i++) {
				first_hand.add(p.getHand().getTile(i));
				second_hand.add(p.getHand().getTile(i));
			}
			// create output 
			ArrayList<ArrayList<Tile>> output;
			//find all sequences first
			ArrayList<ArrayList<Tile>> firstMelds = function.getFirstOutput(first_hand);
			//find all sets first
			ArrayList<ArrayList<Tile>> secondMelds = function.getSecondOutput(second_hand);
			int x = 0,y = 0;
			//check the size of 2 possible output, and get the one whose size is bigger than other.
			for(int i = 0; i < firstMelds.size();i++) {	x += firstMelds.get(i).size();	}
			for(int i = 0; i < secondMelds.size();i++) {y += secondMelds.get(i).size();}
			if(x >= y) output = firstMelds;
			else output = secondMelds;
		
			if (output.size() == 0) return false;
			//add tiles in the table and remove tiles from player hand.
			for(int i = output.size()-1; i > -1 ;i--) {
				p.getTable().addTiles(output.get(i));
				for(int u = 0; u < output.get(i).size();u++) {
					p.getHand().getTiles().remove(output.get(i).get(u));
				}
			}
			//if size ==0, this player is the winner
			if(p.getHand().getTiles().size() == 0) p.setWinner();
			return true;
		}
		else {
			ArrayList<Tile> first_hand = new ArrayList<Tile>();
			ArrayList<Tile> second_hand = new ArrayList<Tile>();
			
			//copy player hand to sample hand
			for(int i =0; i < p.getHand().sizeOfHand();i++) {
				first_hand.add(p.getHand().getTile(i));
				second_hand.add(p.getHand().getTile(i));
			}
			// create output 
			ArrayList<ArrayList<Tile>> output;
			ArrayList<ArrayList<Tile>> firstMelds = function.getFirstOutput(first_hand);
			ArrayList<ArrayList<Tile>> secondMelds = function.getSecondOutput(second_hand);
			int x = 0,y = 0;
			//check the size of 2 possible output, and get the one whose size is bigger than other.
			for(int i = 0; i < firstMelds.size();i++) {	x += firstMelds.get(i).size();	}
			for(int i = 0; i < secondMelds.size();i++) {y += secondMelds.get(i).size();}
			if(x >= y) output = firstMelds;
			else output = secondMelds;
			
			if (output.size() == 0) return false;
			int point = 0;
			//add tiles in the table and remove tiles from player hand.
			// use point to sum up tiles' value
			for(int i = output.size()-1; i > -1 ;i--) {
				for(int u = 0; u < output.get(i).size();u++) {
					point += output.get(i).get(u).getNumber();
				}
			}
			// if point >= 30, add tiles to the table and remove them from hand
			if(point >= 30) {
				for(int i = output.size()-1; i > -1 ;i--) {
					p.getTable().addTiles(output.get(i));
					for(int u = 0; u < output.get(i).size();u++) {
						p.getHand().getTiles().remove(output.get(i).get(u));
					}
				}
				//set the fist meld complete
				p.setIsfirstMeldComplete(true);
			}
			//set winner, no tiles left
			if(p.getHand().getTiles().size() == 0) p.setWinner();
			return false;
		}	
	}	
}
