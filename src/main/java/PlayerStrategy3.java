import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class PlayerStrategy3 implements PlayerStrategy {
	private Support functions;
	
	public boolean playTheGame(Player p) {
		// TODO Auto-generated method stub
		ArrayList<Tile> input = new ArrayList<Tile>();
		p.set_report("");
		p.renewPlayedList();
		functions = new Support();
		if(p.getIsFirstMeldComplete()) {
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			// all set and all sequence could be generate from hands and tables
			output = merge(p.getHand().getTiles(),p.getTable());
			// if number of tiles in output = (hand + table) tiles
			// play and set win
			if(functions.one_short(p))
				return true;
			else if (p.isEligibleforP3()==true){   //MAKES IT RUN WITH P2 FUNCTIONALITY
				ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
				ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
				ArrayList<Tile> targets;
				ArrayList<ArrayList<Tile>> first_sample = functions.getFirstOutput(first_hand);
				ArrayList<ArrayList<Tile>> second_sample = functions.getSecondOutput(second_hand);
				
				//targets is the list contain tiles on hand which is not used to generate any meld.
				// the algorithm is find the most melds as much as possible in its hand. Then mark them in targets list.
				if (functions.getSizeOf(first_sample) >=  functions.getSizeOf(second_sample)) 
					targets = first_hand;
				else targets = second_hand;
				
				// merge table with tiles on target to find the most possible melds can be generated
				output = new ArrayList<ArrayList<Tile>>();
				int max_tiles = 0;
				//find all subset of targets, then merge it with tiles on the table

				
				int length = targets.size();
				// this array list will hold the best result (as most tiles) and will be use to update play on the table legally
				ArrayList<Tile> TilesWillBeStore = new ArrayList<Tile>();

				// idea to find subsets is from https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
				for (int i = 0; i < (1<<length); i++) {
					ArrayList<Tile> subset = new ArrayList<Tile>();
					for (int j = 0; j < length; j++)  
		                if ((i & (1 << j)) > 0) 
		                	subset.add(targets.get(j));

					// merge subset and table hand
					output = merge(subset,p.getTable());
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
				output = merge(TilesWillBeStore,p.getTable());
				p.getTable().setTable(output);
				String out = "";
				for(int i =0; i < TilesWillBeStore.size();i++) {
					p.getHand().playTileFromHand(TilesWillBeStore.get(i));
					p.getPlayedList().add(TilesWillBeStore.get(i));
					out += TilesWillBeStore.get(i).toString();
				}
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
			
			else {
				//copy player hand to sample hand
				ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
				ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
				
				// create output 
				//find all sequences first
				ArrayList<ArrayList<Tile>> firstMelds = functions.getFirstOutput(first_hand);
				//find all sets first
				ArrayList<ArrayList<Tile>> secondMelds = functions.getSecondOutput(second_hand);
				int x = 0,y = 0;
				//check the size of 2 possible output, and get the one whose size is bigger than other.
				x = functions.getSizeOf(firstMelds);
				y = functions.getSizeOf(secondMelds);
				
				if(x >= y) output = firstMelds ;
				else output = secondMelds;
			
				
				if (output.size() == 0) return false;
				//add tiles in the table and remove tiles from player hand.
				String out = "";
				for(int i = output.size()-1; i > -1 ;i--) {
					p.getTable().addTiles(output.get(i));
					for(int u = 0; u < output.get(i).size();u++) {
						p.getHand().playTileFromHand(output.get(i).get(u));
						p.getPlayedList().add(output.get(i).get(u));
						out += output.get(i).get(u).toString();
					}
				}
				p.set_report(out);
				//if size ==0, this player is the winner
				if(p.getHand().getTiles().size() == 0) p.setWinner();
				return true;
			}
		}
		else {
			
			if(functions.one_short(p))
				return true;
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
				//System.out.println(secondMelds.get(1).get(2).getNumber());
				
				if(x >= y) output = firstMelds;
				else output = secondMelds;
				
				if (output.size() == 0) return false;
				int point = 0;
				//add tiles in the table and remove tiles from player hand.
				// use point to sum up tiles' value
				myloop: for(int i = output.size()-1; i > -1 ;i--) {
					for(int u = 0; u < output.get(i).size();u++) {
						point += output.get(i).get(u).getNumber(); 
					}
					
				}
				// if point >= 30, add tiles to the table and remove them from hand
				if(point >= 30) {point=0;
					System.out.println("Tiles played from AI3 are: ");
					String out = "";
					for(int i = output.size()-1; i > -1 ;i--) {
				
						p.getTable().addTiles(output.get(i));
						
						for(int u = 0; u < output.get(i).size();u++) {
							p.getHand().playTileFromHand(output.get(i).get(u));
							out += output.get(i).get(u).toString();
							p.getPlayedList().add(output.get(i).get(u));
							
								point +=  output.get(i).get(u).getNumber();
								if (point>29) { 
									u = output.get(i).size()+1;
									i = 0;
											
								}
								
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
	// merge an list with tiles on the table, then return a 2d array list
	// which contains sets and sequences.
	private ArrayList<ArrayList<Tile>> merge(ArrayList<Tile> TileOnHand, Table table) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> first_sample = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>>  second_sample = new ArrayList<ArrayList<Tile>>();
		
		
		ArrayList<Tile> merge_list = new ArrayList<Tile>(TileOnHand);
		ArrayList<Tile> merge_list1 = new ArrayList<Tile>(TileOnHand);


		for(int i =0; i < table.getTable().size();i++) {
			for(int u =0; u<table.get(i).size();u++) {
				merge_list.add(table.get(i).get(u));
				merge_list1.add(table.get(i).get(u));
				
			}
		}
		
		first_sample = functions.getFirstOutput(merge_list);
		second_sample = functions.getSecondOutput(merge_list1);
		int x = 0,y = 0;
		//check the size of 2 possible output, and get the one whose size is bigger than other.
		x = functions.getSizeOf(first_sample);
		y = functions.getSizeOf(second_sample);
		
		if(x >= y) output = first_sample;
		else output = second_sample;
		
		return output;
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
