import java.util.ArrayList;

public class p3 implements PlayerStrategy {
	Support function = new Support();
	HandleJoker checkMeld = new HandleJoker();
	
	PlayerStrategy3 old_p3 = new PlayerStrategy3();
	//split hand into 5 lists with 4 colors red, blue, green, orange and joker.
	ArrayList<Tile> blue = new ArrayList<Tile>();
	ArrayList<Tile> green = new ArrayList<Tile>();
	ArrayList<Tile> red = new ArrayList<Tile>();
	ArrayList<Tile> orange = new ArrayList<Tile>();
	ArrayList<Tile> joker = new ArrayList<Tile>();
	public boolean playTheGame(Player p) {
		int num = checkMeld.NumberOfJoker(p.getHand().getTiles());
		boolean complete_first_turn = p.getIsFirstMeldComplete();
		
		ArrayList<Tile> sample = new ArrayList<Tile>(p.getHand().getTiles());
		ArrayList<Tile> sample1 = new ArrayList<Tile>(p.getHand().getTiles());
		
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		checkMeld.separateList(p.getHand().getTiles()); //  initial tile for each list of 5
		
		if(num == 0 && p.getIsFirstMeldComplete() == false) return old_p3.playTheGame(p);
		else {
			if(p.getIsFirstMeldComplete() == false) {
				ArrayList<ArrayList<Tile>> first = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> second = new ArrayList<ArrayList<Tile>>();
				
				ArrayList<ArrayList<Tile>> object = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> object1 = new ArrayList<ArrayList<Tile>>();
				
				//get runs, then get sets
				first = checkMeld.getFirst(complete_first_turn,num,sample);
				second = checkMeld.getSecond(complete_first_turn,num,sample);
				if(checkMeld.getPoint(first) <= checkMeld.getPoint(second)) object = second;
				else object = first;
				
				
				//get sets then get runs
				first = checkMeld.getSecond(complete_first_turn,num,sample1);
				second = checkMeld.getFirst(complete_first_turn,num,sample1);
				if(checkMeld.getPoint(first) <= checkMeld.getPoint(second)) object1 = second;
				else object1 = first;
				
				
				if(checkMeld.getPoint(object) <= checkMeld.getPoint(object1)) output = object1;
				else output = object;
				
				checkMeld.initialOutput(output,p.getPlayerHand().getTiles());
				if(function.getSizeOf(output) == p.getHand().sizeOfHand()) {
					for(int i =0; i < p.getHand().getTiles().size();i++) {
						p.getHand().getTiles().remove(i);
						i--;
					}
					p.setWinner();}
				
				if(checkMeld.getPoint(output) >= 30) {
					String out = "";
					int a = 0;
					loop :for(int i = output.size()-1; i > -1 ;i--) {
						p.getTable().addTiles(output.get(i));
						a += checkMeld.getPointOfSeq(output.get(i));
						for(int u = 0; u < output.get(i).size();u++) {
							if(output.get(i).get(u).isJoker()) {
								myloop: for(int k =0; k < p.getHand().getTiles().size();k++) {
									if(p.getHand().getTiles().get(k).isJoker() && p.getHand().getTiles().get(k).getJokerColor().equals(output.get(i).get(u).getJokerColor())
										&& p.getHand().getTiles().get(k).getJokerPoint() == output.get(i).get(u).getJokerPoint()) {
										p.getHand().getTiles().remove(k);
										break myloop;
									}
								}	
							}
							else
								p.getHand().getTiles().remove(output.get(i).get(u));
							out += output.get(i).get(u).toString();
						}
						if( a >= 30) break loop;
					}
					out += "\n";
					p.set_report(out);
					return true;
				}
				return false;
			}
			else {
				/*
				 * Get as much as meld as possible from the player hand p3.
				 */
				ArrayList<ArrayList<Tile>> first = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> second = new ArrayList<ArrayList<Tile>>();
				
				ArrayList<ArrayList<Tile>> object = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> object1 = new ArrayList<ArrayList<Tile>>();
				
				//get runs, then get sets
				first = checkMeld.getFirst(complete_first_turn,num,sample);
				second = checkMeld.getSecond(complete_first_turn,num,sample);
				if(checkMeld.getPoint(first) <= checkMeld.getPoint(second)) object = second;
				else object = first;
				
				
				//get sets then get runs
				first = checkMeld.getSecond(complete_first_turn,num,sample1);
				second = checkMeld.getFirst(complete_first_turn,num,sample1);
				if(checkMeld.getPoint(first) <= checkMeld.getPoint(second)) object1 = second;
				else object1 = first;
				
				
				if(checkMeld.getPoint(object) <= checkMeld.getPoint(object1)) output = object1;
				else output = object;
				checkMeld.initialOutput(output,p.getPlayerHand().getTiles());
				/*
				 * Add tiles are not used to create meld in player in a list
				 * So tiles in the list will be used to play on the table
				 */
				ArrayList<Tile> useless_tile = new ArrayList<Tile>(p.getHand().getTiles());
				for(int i = output.size()-1; i > -1 ;i--) {
					for(int u = 0; u < output.get(i).size();u++) {
						if(output.get(i).get(u).isJoker()) {
							for(int k =0; k < useless_tile.size();k++) {
								if(useless_tile.get(k).isJoker() && useless_tile.get(k).getJokerColor().equals(output.get(i).get(u).getJokerColor())
										&& useless_tile.get(k).getJokerPoint() == output.get(i).get(u).getJokerPoint()) 
								{
									useless_tile.remove(useless_tile.get(k));
								}
							}	
						}
						else {
							if (useless_tile.contains(output.get(i).get(u))) {
								useless_tile.remove(output.get(i).get(u));
							}
						}
					}
				}
				// If some player has 3 fewer tiles than p3, p3 will play all the melds it can.
				if(p.isEligibleforP3()==true) {
					String out = "";
					for(int i = output.size()-1; i > -1 ;i--) {
						p.getTable().addTiles(output.get(i));
						for(int u = 0; u < output.get(i).size();u++) {
							if(output.get(i).get(u).isJoker()) {
								myloop: for(int k =0; i < p.getHand().getTiles().size();k++) {
									if(p.getHand().getTiles().get(k).isJoker() && p.getHand().getTiles().get(k).getJokerColor().equals(output.get(i).get(u).getJokerColor())
										&& p.getHand().getTiles().get(k).getJokerPoint() == output.get(i).get(u).getJokerPoint()) {
										p.getHand().getTiles().remove(k);
										break myloop;
									}
								}	
							}
							else
								p.getHand().getTiles().remove(output.get(i).get(u));
							out += output.get(i).get(u).toString();
						}
					}
					out += "\n";
					p.set_report(out);
					return true;
				}
				// noOne has 3 fewer tiles than p3, p3 just play use_less tiles on the table
				else {
					
				}
				
			}
			
		}
		return false;
	}
	
	
	

}
