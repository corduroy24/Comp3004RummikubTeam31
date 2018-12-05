import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class p4 implements PlayerStrategy{
	Support function = new Support();
	HandleJoker checkMeld = new HandleJoker();
	
	PlayerStrategy4 old_p4 = new PlayerStrategy4();
	//split hand into 5 lists with 4 colors red, blue, green, orange and joker.
	ArrayList<Tile> blue = new ArrayList<Tile>();
	ArrayList<Tile> green = new ArrayList<Tile>();
	ArrayList<Tile> red = new ArrayList<Tile>();
	ArrayList<Tile> orange = new ArrayList<Tile>();
	ArrayList<Tile> joker = new ArrayList<Tile>();
	
	
	
	
	public boolean playTheGame(Player p) {
		if(p.getCanPlay()) {
			int num = checkMeld.NumberOfJoker(p.getHand().getTiles());
			boolean complete_first_turn = p.getIsFirstMeldComplete();
			
			ArrayList<Tile> sample = new ArrayList<Tile>(p.getHand().getTiles());
			ArrayList<Tile> sample1 = new ArrayList<Tile>(p.getHand().getTiles());
			
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			checkMeld.separateList(p.getHand().getTiles()); //  initial tile for each list of 5
			
			if(num == 0) return old_p4.playTheGame(p);
			else {
				if(p.getIsFirstMeldComplete() == false && p.getCanPlay()) {
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
					
					if(checkMeld.getPoint(output) >= 30) {
						String out = "";
						for(int i = output.size()-1; i > -1 ;i--) {
							p.getTable().AiAddTiles(output.get(i));
							for(int u = 0; u < output.get(i).size();u++) {
								if(output.get(i).get(u).isJoker()) {
									myloop: for(int k =0; k < p.getHand().getTiles().size();k++) {
										if(k == p.getHand().sizeOfHand()) break myloop;
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
						p.setIsfirstMeldComplete(true);
						return true;
					}
					return false;
				}
				
				else {
					ArrayList<ArrayList<Tile>> first = new ArrayList<ArrayList<Tile>>();
					ArrayList<ArrayList<Tile>> second = new ArrayList<ArrayList<Tile>>();
					
					ArrayList<ArrayList<Tile>> object = new ArrayList<ArrayList<Tile>>();
					ArrayList<ArrayList<Tile>> object1 = new ArrayList<ArrayList<Tile>>();
					//get runs, then get sets
					first = checkMeld.getFirst(complete_first_turn,num,sample);
					second = checkMeld.getSecond(complete_first_turn,num,sample);
					if(function.getSizeOf(first) <= function.getSizeOf(second)) object = second;
					else object = first;
					
					//get sets then get runs
					first = checkMeld.getSecond(complete_first_turn,num,sample1);
					second = checkMeld.getFirst(complete_first_turn,num,sample1);
					if(function.getSizeOf(first) <= function.getSizeOf(second)) object1 = second;
					else object1 = first;
					
					
					if(function.getSizeOf(object) <= function.getSizeOf(object1)) output = object1;
					else output = object;
					
					checkMeld.initialOutput(output,p.getPlayerHand().getTiles());
					if(output == null || output.size() == 0) return false;
					
					String out = "";
					for(int i = output.size()-1; i > -1 ;i--) {
						p.getTable().AiAddTiles(output.get(i));
						for(int u = 0; u < output.get(i).size();u++) {
							if(output.get(i).get(u).isJoker()) {
								myloop: for(int k =0; i < p.getHand().getTiles().size();k++) {
									if(k == p.getHand().getTiles().size()) break myloop;
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
			}
		}
		else return false;
	}
}
