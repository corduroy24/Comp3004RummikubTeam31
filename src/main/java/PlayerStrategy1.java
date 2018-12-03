import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class PlayerStrategy1 implements PlayerStrategy {
	private Support functions;
	
	public boolean playTheGame(Player p) {
		functions = new Support();
		p.renewPlayedList();
		p.set_report("");
		if(functions.one_short(p)) {
			return true;
		}
		else if(p.getIsFirstMeldComplete()) {
			//copy player hand to sample hand
			ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
			ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
			
			// create output 
			ArrayList<ArrayList<Tile>> output;
			//find all sequences first
			ArrayList<ArrayList<Tile>> firstMelds = functions.getFirstOutput(first_hand);
			//find all sets first
			ArrayList<ArrayList<Tile>> secondMelds = functions.getSecondOutput(second_hand);
			int x = 0,y = 0;
			//check the size of 2 possible output, and get the one whose size is bigger than other.
			x = functions.getSizeOf(firstMelds);
			y = functions.getSizeOf(secondMelds);
			
			if(x >= y) output = firstMelds;
			else output = secondMelds;
		
			
			if (output.size() == 0) return false;
			//add tiles in the table and remove tiles from player hand.
			System.out.println("Tiles played from AI1 are: ");
			String out = "";
			for(int i = output.size()-1; i > -1 ;i--) {
				p.getTable().addTiles(output.get(i));
				for(int u = 0; u < output.get(i).size();u++) {
					p.getHand().playTileFromHand(output.get(i).get(u));
					out += output.get(i).get(u).toString();
					p.getPlayedList().add(output.get(i).get(u));
				}
			}
			out += "\n";
			p.set_report(out);
			//if size ==0, this player is the winner
			if(p.getHand().getTiles().size() == 0) p.setWinner();
			return true;
		}
		else {
			//copy player hand to sample hand
			ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
			ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
			
			// create output 
			ArrayList<ArrayList<Tile>> output;
			ArrayList<ArrayList<Tile>> firstMelds = functions.getFirstOutput(first_hand);
			ArrayList<ArrayList<Tile>> secondMelds = functions.getSecondOutput(second_hand);
			int x = 0,y = 0;
			//check the size of 2 possible output, and get the one whose size is bigger than other.
			x = functions.getSizeOf(firstMelds);
			y = functions.getSizeOf(secondMelds);
			
			if(x >= y) output = firstMelds;
			else output = secondMelds;
			
			if ( output == null ||output.size() == 0) return false;
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
				System.out.println("Tiles played from AI1 are: ");
				String out = "";
				for(int i = output.size()-1; i > -1 ;i--) {
					p.getTable().addTiles(output.get(i));
					for(int u = 0; u < output.get(i).size();u++) {
						p.getHand().playTileFromHand(output.get(i).get(u));
						out += output.get(i).get(u).toString();
						p.getPlayedList().add(output.get(i).get(u));
					}
				}
				out += "\n";
				p.set_report(out);
				//set the fist meld complete
				p.setIsfirstMeldComplete(true);
				//set winner, no tiles left
				if(p.getHand().getTiles().size() == 0) p.setWinner();
				return true;
			}
			return false;
		}	
	}	
}
