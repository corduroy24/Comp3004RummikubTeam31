import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class PlayerStrategy2 implements PlayerStrategy {
	private Support functions;

	public boolean playTheGame(Player p) {
		// TODO Auto-generated method stub
		p.renewPlayedList();
		p.set_report("");
		ArrayList<Tile> input = new ArrayList<Tile>();
		functions = new Support();
		if(p.getIsFirstMeldComplete()) { 
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			// all set and all sequence could be generate from hands and tables
			output = functions.merge(p.getHand().getTiles(),p.getTable());
			// if number of tiles in output = (hand + table) tiles
			// play and set win
			if(functions.one_short(p))
				return true;
			else {
				ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
				ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
				ArrayList<Tile> targets;
				ArrayList<ArrayList<Tile>> first_sample = functions.getFirstOutput(first_hand);
				ArrayList<ArrayList<Tile>> second_sample = functions.getSecondOutput(second_hand);
				
				
				ArrayList<ArrayList<Tile>> output2 = new ArrayList<ArrayList<Tile>>();
				
				//targets is the list contain tiles on hand which is not used to generate any meld.
				// the algorithm is find the most melds as much as possible in its hand. Then mark them in targets list.
				if (functions.getSizeOf(first_sample) >=  functions.getSizeOf(second_sample)) 
					targets = first_hand;
				else targets = second_hand;
				
				
				// merge table with tiles on target to find the most possible melds can be generated
				output = new ArrayList<ArrayList<Tile>>();
				int max_tiles = 0;
				System.out.println("Playable tiles: ");
				//find all subset of targets, then merge it with tiles on the table
				int length = targets.size();
				System.out.println(targets);
				// this array list will hold the best result (as most tiles) and will be use to update play on the table legally
				ArrayList<Tile> TilesWillBeStore = new ArrayList<Tile>();
				// idea to find subsets is from https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
				for (int i = 0; i < (1<<length); i++) {
					ArrayList<Tile> subset = new ArrayList<Tile>();
					for (int j = 0; j < length; j++)  
		                if ((i & (1 << j)) > 0) 
		                	subset.add(targets.get(j));

					// merge subset and table hand
					output = functions.merge(subset,p.getTable());
					// check if the output array list from merge is perfect
					
					if(functions.getSizeOf(output) == p.getTable().getNumberOfTile() + subset.size()) {
						if(subset.size() > max_tiles) {
							max_tiles = subset.size();
							TilesWillBeStore = subset;		
						}
					}
					
				}
				
				if(TilesWillBeStore == null || TilesWillBeStore.size() == 0)
					return false;
		                    
				//Update the table and remove TilesWillBeStore' tile from hands
				output = functions.merge(TilesWillBeStore,p.getTable());
				p.getTable().setTable(output);
				
				String out = "";
				if(TilesWillBeStore.size() > 0) {
					System.out.println("Tiles will be played by AI 2: ");
				}
				
				
				for(int i =0; i < TilesWillBeStore.size();i++) {
					p.getHand().playTileFromHand(TilesWillBeStore.get(i));
					p.getPlayedList().add(TilesWillBeStore.get(i));
					out += TilesWillBeStore.get(i).toString();
				}
				out += "\n";
				System.out.println(out);
				p.set_report(out);
				//else
				//output = getAllSetAndSequence(p.getHand().getTiles());
				// remove every tile of output from player hand.
				// then merge it with table tiles
				//  set = find all sets and sequences without reminder tiles
				// if set.size() >0, return true
				//else return false
			return true;
			}
		}
		else {
			if(functions.one_short(p))
				return true;
			
			if(p.getTable().getNumberOfTile() > 0) {	
				//copy player hand to sample hand
				ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
				ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
				
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
				//check for the number of tiles the first array list need to get so that this player have 30 points
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
				//check for the number of tiles the second array list need to get so that this player have 30 points
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
				System.out.println("Tiles played by AI2: ");
				String set_out = "";
				String out = "";
				myloop: for(int i =output.size()-1; i >-1 ;i--) {
					p.getTable().addTiles(output.get(i));
					for(int u =0; u < output.get(i).size();u++) {
						removeNumber--;
						p.getHand().getTiles().remove(output.get(i).get(u));
						p.getPlayedList().add(output.get(i).get(u));
						out += output.get(i).get(u).toString();
						if(removeNumber == 0) break myloop;
					}
				}
				System.out.println(out);
				set_out += out;
				set_out += "\n";
				p.set_report(set_out);
				
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
