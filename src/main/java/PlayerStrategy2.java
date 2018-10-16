import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class PlayerStrategy2 implements PlayerStrategy {
	private Support functions;

	public boolean playTheGame(Player p) {
		// TODO Auto-generated method stub
		ArrayList<Tile> input = new ArrayList<Tile>();
		functions = new Support();
		if(p.getIsFirstMeldComplete()) {
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			//output = merge(p.getHand().getTiles(),p.getTable());
			// if number of tiles in output = (hand + table) tiles
			// play and set win
			//else
			//output = getAllSetAndSequence(p.getHand().getTiles());
			// remove every tile of output from player hand.
			// then merge it with table tiles
			//  set = find all sets and sequences without reminder tiles
			// if set.size() >0, return true
			//else return false
		
			
			
			return true;
		}
		else {
			if(p.getTable().getNumberOfTile() > 0) {
			
				ArrayList<Tile> first_hand = new ArrayList<Tile>();
				ArrayList<Tile> second_hand = new ArrayList<Tile>();
				
				//copy player hand to sample hand
				for(int i =0; i < p.getHand().sizeOfHand();i++) {
					first_hand.add(p.getHand().getTile(i));
					second_hand.add(p.getHand().getTile(i));
				}
				// create output 
				ArrayList<ArrayList<Tile>> output;
				ArrayList<ArrayList<Tile>> firstMelds = functions.getFirstOutput(first_hand);
				ArrayList<ArrayList<Tile>> secondMelds = functions.getSecondOutput(second_hand);
				//sort by array list
				Collections.sort(firstMelds, new SortByArrayList());
				Collections.sort(secondMelds, new SortByArrayList());
				
				int check_1 = 0;
				boolean has_1_30 = false;
			
				int check_2 = 0;
				boolean has_2_30 = false;
				
				int point = 0;
				//Pick one list has initial turn higher than 30 and using less melds than other.
				//check for the number of tiles the first array list need to have 30 points
				myloop: for(int i =firstMelds.size()-1; i >-1 ;i--) {
					for(int u =0; u < firstMelds.get(i).size();u++) {
						check_1++;
						point += firstMelds.get(i).get(u).getNumber();
					}
					if (point >= 30) {
						has_1_30 = true;
						break myloop;}
				}
				point = 0;
				//check for the number of tiles the second array list need to have 30 points
				myloop: for(int i =secondMelds.size()-1; i > -1;i--) {
					for(int u =0; u < secondMelds.get(i).size();u++) {
						check_2++;
						point += secondMelds.get(i).get(u).getNumber();
					}
					if(point >= 30) { 
						has_2_30 = true;
						break myloop;}
				}
				
				int removeNumber = 0;
				
				//use to decide which set of meld will be chosen
				if(has_1_30 && has_2_30) {
					if(check_1 >= check_2) {
						output = secondMelds;
						removeNumber = check_2;
					}
					else {output = firstMelds; removeNumber = check_1;}
				}
				else if (has_1_30) {output = firstMelds;removeNumber = check_1;}
				else if (has_2_30) {output = secondMelds;removeNumber = check_2;}
				else return false;
				
				if(output.size() == 0) return false;
				
				// update table and remove tiles from player hand
				myloop: for(int i =output.size()-1; i >-1 ;i--) {
					p.getTable().addTiles(output.get(i));
					for(int u =0; u < output.get(i).size();u++) {
						removeNumber--;
						p.getHand().getTiles().remove(output.get(i).get(u));
					}
					if(removeNumber == 0) break myloop;
				}
				p.setIsfirstMeldComplete(true);
				return true;
				}
			return false;	
		}
	}
	//sort by tile' value
	class SortByArrayList implements Comparator<ArrayList<Tile>> 
	{ 	
	    public int compare(ArrayList<Tile> a , ArrayList<Tile> b) 
	    { int x = 0;
			int y = 0;
			for(int i =0; i < a.size();i++)
				x += a.get(i).getNumber();
			for(int i =0; i < b.size();i++)
				y += b.get(i).getNumber();

			return x - y;    
	    } 
	}
	
}
