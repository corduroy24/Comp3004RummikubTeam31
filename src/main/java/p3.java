import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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
		
		if(p.getCanPlay()) {
			int num = checkMeld.NumberOfJoker(p.getHand().getTiles());
			boolean complete_first_turn = p.getIsFirstMeldComplete();
			
			ArrayList<Tile> sample = new ArrayList<Tile>(p.getHand().getTiles());
			ArrayList<Tile> sample1 = new ArrayList<Tile>(p.getHand().getTiles());
			
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			checkMeld.separateList(p.getHand().getTiles()); //  initial tile for each list of 5
			
			if(num == 0 && p.getIsFirstMeldComplete() == false) return old_p3.playTheGame(p);
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
					if(function.getSizeOf(output) == p.getHand().sizeOfHand()) {
						for(int i =0; i < p.getHand().getTiles().size();i++) {
							p.getHand().getTiles().remove(i);
							i--;
						}
						p.setWinner();}
					Collections.reverse(output);
					if(checkMeld.getPoint(output) >= 30) {
						String out = "";
						int a = 0;
						loop :for(int i = output.size()-1; i > -1 ;i--) {
							p.getTable().AiAddTiles(output.get(i));
							a += checkMeld.getPointOfSeq(output.get(i));
							for(int u = 0; u < output.get(i).size();u++) {
								if(output.get(i).get(u).isJoker()) {
									myloop: for(int k =0; k < p.getHand().getTiles().size();k++) {
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
					
					
					if(function.getSizeOf(object) <= function.getSizeOf(object1)) output = object1;
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
					// If some player has 3 fewer tiles than p3, p3 will play all the melds it can
					if(p.isEligibleforP3()==true) {
						String out = "";
						boolean check = false;
						if(output.size() > 0) check = true;
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
						return check;
					}
					// noOne has 3 fewer tiles than p3, p3 just play use_less tiles on the table
					else {
						ArrayList<ArrayList<Tile>> table = new ArrayList<ArrayList<Tile>>(p.getTable().getTable());
						// index of meld on the table, which contain joker tiles
						int index = 999;
						int index_meld = 0; // index of joker tile in the meld
						// find the joker meld in the table
						FindIndex: for(int i =0; i < table.size();i++) {
							for(int u =0; u < table.get(i).size();u++) {
								if(table.get(i).get(u).isJoker()) 
									if(index == 999 ) {index = i; index_meld = u;
									break FindIndex;
									}
							}
						}	
						//check useless tile can replace joker tiles on the table or not.
						String s = "";
						int indexToReplace = 999;
						int tempIndex=0;
						if (index!=999) {
						for (int i=0;i<table.get(index).size();i++) {
							if (table.get(index).get(i).isJoker()) {
								tempIndex=i;
								 s = (table.get(index).get(tempIndex).getJokerColor());
							}
						}
						}
						
						if(index != 999) {
							for(int i =0; i < useless_tile.size();i++) {
								if(useless_tile.get(i).getNumber() == table.get(index).get(index_meld).getJokerPoint()) {
									if(checkMeld.isSet(table.get(index))) {
										HashSet<String> checking_color = new HashSet<String>();
										for(int x =0; x< table.get(index).size();x++) {
											checking_color.add(table.get(index).get(x).getColor());
										}
										if(checking_color.add(useless_tile.get(i).getColor()))
											indexToReplace = i;
										
									}
									else if(useless_tile.get(i).getColor().equals(s)) { 
											indexToReplace = i;
									}
								}
							}
						}
						String out = " ";
						if(indexToReplace != 999) {    //System.out.println(index);
							//Replace tiles on hand to the table.
							
							table.get(index).get(index_meld).setJoker(false);
							table.get(index).get(index_meld).setColor(useless_tile.get(indexToReplace).getColor());
							table.get(index).get(index_meld).setNumber(useless_tile.get(indexToReplace).getNumber());
							out += useless_tile.get(indexToReplace).toString();
							useless_tile.get(indexToReplace).setJoker(true);
							useless_tile.get(indexToReplace).setColor("J");
							useless_tile.get(indexToReplace).setNumber(14);
							// use joker tile to play tile on hand first.
							ArrayList<ArrayList<Tile>> melds = new ArrayList<ArrayList<Tile>>();
							if(checkMeld.getJokerSequences(true, 1, useless_tile).size() > 0 && checkMeld.getPoint(checkMeld.getJokerSequences(true, 1, useless_tile)) > 0) {
								melds = checkMeld.getJokerSequences(true, 1, useless_tile);
							}
							else if (checkMeld.getJokerSet(true, 1, useless_tile).size() > 0) {
								melds = checkMeld.getJokerSet(true, 1, useless_tile);
							}
							else {
								// if can't use joker to play tiles on hand, find place to put it back to the table.
								loop : for(int i =0; i < table.size();i++) {
									ArrayList<Tile> a = new ArrayList<Tile>(table.get(i));
									a.add(useless_tile.get(indexToReplace));
									if(checkMeld.isJokerSequences(a)) {
										table.get(i).add(useless_tile.get(indexToReplace));
										for(int x =0; x < p.getHand().sizeOfHand();x++) {
											if(p.getHand().getTile(x).isJoker()) {
												if(p.getHand().getTile(x).getJokerPoint() == useless_tile.get(indexToReplace).getJokerPoint() && 
														p.getHand().getTile(x).getColor().equals(useless_tile.get(indexToReplace).getJokerColor()))
													p.getHand().removeTile(p.getHand().getTile(x));
											}
										}
										break loop;
									}
									else if (checkMeld.isJokerSet(a)) {
										table.get(i).add(useless_tile.get(indexToReplace));
										for(int x =0; x < p.getHand().sizeOfHand();x++) {
											if(p.getHand().getTile(x).isJoker()) {
												if(p.getHand().getTile(x).getJokerPoint() == useless_tile.get(indexToReplace).getJokerPoint() && 
														p.getHand().getTile(x).getColor().equals(useless_tile.get(indexToReplace).getJokerColor()))
													p.getHand().removeTile(p.getHand().getTile(x));
											}
										}
										break loop;
									}
								}
								return true;
							}
							// if can play all tiles, p3 play all the tiles and become a winner
							if(function.getSizeOf(melds) == useless_tile.size()) {
								for(int i =0; i < melds.size();i++)
									p.getTable().AiAddTiles(melds.get(i));
								for(int u =0; u < p.getHand().sizeOfHand();u++) {
									out += p.getHand().getTile(u).toString();
									p.getHand().playTileFromHand(p.getHand().getTile(u));
									u--;
								}
								out += "\n";
								p.set_report(out);
								p.setWinner();
								return true;
							}
							// else it plays as most as tiles as it can and put in on the table
							else if(melds.size() > 0 ) {
								checkMeld.initialOutput(melds,useless_tile);
								// remove tiles from player' hand
								System.out.println(melds);
								for(int k =0; k < melds.size();k++) {
									p.getTable().AiAddTiles(melds.get(k));
									for(int  u =0; u < melds.get(k).size();u++) {
										if(melds.get(k).get(u).isJoker()) {
											 for(int x =0; x < p.getHand().sizeOfHand();x++) {
												if(p.getHand().getTile(x).isJoker()) {
													if(p.getHand().getTile(x).getJokerPoint() == melds.get(k).get(u).getJokerPoint() && 
															p.getHand().getTile(x).getColor().equals( melds.get(k).get(u).getJokerColor()))
														p.getHand().removeTile(p.getHand().getTile(x));
												}
											}
										}
										else {p.getHand().playTileFromHand(melds.get(k).get(u));
										out += melds.get(k).get(u).toString();
										}	
										}
									}
								if ((p.getHand().sizeOfHand()==1)&&(p.getHand().getTile(0).isJoker())) {
									Tile t = new Tile();
									t = p.getHand().getTile(0);
									p.getHand().removeTile(t);
									p.setWinner();
								}
								out += "\n";
								p.set_report(out);
								return true;
								}
							}

						if(function.one_short(p))
							return true;
						output = new ArrayList<ArrayList<Tile>>();
						int length = useless_tile.size();
						int max_tiles = 0;
						// this array list will hold the best result (as most tiles) and will be use to update play on the table legally
						ArrayList<Tile> TilesWillBeStore = new ArrayList<Tile>();
						Table t = new Table();
						t.setTable(p.getTable().getTable());
						ArrayList<ArrayList<Tile>> jokerMelds = new ArrayList<ArrayList<Tile>>();
						for(int u =0; u < t.getTable().size();u++) 
							if(checkMeld.NumberOfJoker(t.get(u)) > 0) 
								jokerMelds.add(t.getTable().remove(u));
						//System.out.println("current table of P3" + p.getTable().getTable());
						for (int i = 0; i < (1<<length); i++) {
							ArrayList<Tile> subset = new ArrayList<Tile>();
							for (int j = 0; j < length; j++)  
				                if ((i & (1 << j)) > 0) 
				                	subset.add(useless_tile.get(j));

							// merge subset and table hand
							output = function.merge(subset,t);
							// check if the output array list from merge is perfect
							if(function.getSizeOf(output) == t.getNumberOfTile() + subset.size()) {
								if(subset.size() > max_tiles) {
									max_tiles = subset.size();
									TilesWillBeStore = subset;		
								}
							}
						}

							if(TilesWillBeStore == null || TilesWillBeStore.size() == 0) {
								for(int i =0; i < jokerMelds.size();i++)
									p.getTable().AiAddTiles(jokerMelds.get(i));
								return false;}
					                    
							//Update the table and remove TilesWillBeStore' tile from hands
							output = function.merge(TilesWillBeStore,t);
							p.getTable().setTable(output);
							for(int i =0; i < jokerMelds.size();i++)
								p.getTable().AiAddTiles(jokerMelds.get(i));
							
							
							out = "";
							for(int i =0; i < TilesWillBeStore.size();i++) {
								p.getHand().playTileFromHand(TilesWillBeStore.get(i));
								p.getPlayedList().add(TilesWillBeStore.get(i));
								out += TilesWillBeStore.get(i).toString();
							}
							out += "\n";
							p.set_report(out);
							return true;
						}	
					
					}
				}
			}
		else return false;
	}
}
