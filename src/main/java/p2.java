	import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class p2 implements PlayerStrategy{
	Support function = new Support();
	HandleJoker checkMeld = new HandleJoker();
	
	PlayerStrategy2 old_p2 = new PlayerStrategy2();
	//split hand into 5 lists with 4 colors red, blue, green, orange and joker.
	ArrayList<Tile> blue = new ArrayList<Tile>();
	ArrayList<Tile> green = new ArrayList<Tile>();
	ArrayList<Tile> red = new ArrayList<Tile>();
	ArrayList<Tile> orange = new ArrayList<Tile>();
	ArrayList<Tile> joker = new ArrayList<Tile>();
	boolean usefull=false;
	
	
	
	public boolean playTheGame(Player p) {
		if(p.getCanPlay()) {
			int num = checkMeld.NumberOfJoker(p.getHand().getTiles());
			int num2=0;
			for (int i=0;i<p.getTable().getTable().size();i++) {
				num2+=checkMeld.NumberOfJoker(p.getTable().getTable().get(i));
			}//System.out.println(num2);
			
			boolean complete_first_turn = p.getIsFirstMeldComplete();
			
			ArrayList<Tile> sample = new ArrayList<Tile>(p.getHand().getTiles());
			ArrayList<Tile> sample1 = new ArrayList<Tile>(p.getHand().getTiles());
			
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			checkMeld.separateList(p.getHand().getTiles()); //  initial tile for each list of 5
			
			if((num == 0 )&&(num2 == 0)) {System.out.println("PLAYS AI2 NORMALLY");
			return old_p2.playTheGame(p);}
			
			else if(p.getTable().getNumberOfTile() > 0 && p.getIsFirstMeldComplete() == false) {
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
						System.out.println("testing:      " + out);
						System.out.println("Things left" + p.getHand().getTiles());
						return true;
					}
					return false;
				}
				else if (p.getIsFirstMeldComplete()) {
					/*
					 * Get as much as meld as possible from the player hand (p2).
					 */
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
					//System.out.println(first);
					
					if(function.getSizeOf(object) <= function.getSizeOf(object1)) output = object1;
					else output = object; 
					
					checkMeld.initialOutput(output,p.getPlayerHand().getTiles());
					/*
					 * Add tiles are not used to create meld in player in a list
					 * So tiles in the list will be used to play on the table
					 */
					//System.out.println(output);
					
					ArrayList<Tile> useless_tile = new ArrayList<Tile>(p.getHand().getTiles());
					ArrayList<Tile> usefull_tile = new ArrayList<Tile>();
					for(int i = output.size()-1; i > -1 ;i--) {
						for(int u = 0; u < output.get(i).size();u++) {
							if(output.get(i).get(u).isJoker()) {
								for(int k =0; k < useless_tile.size( );k++) {
									if(useless_tile.get(k).isJoker() && useless_tile.get(k).getJokerColor().equals(output.get(i).get(u).getJokerColor())
											&& useless_tile.get(k).getJokerPoint() == output.get(i).get(u).getJokerPoint()) 
									{
										usefull_tile.add(useless_tile.get(k));
										useless_tile.remove(useless_tile.get(k));
										
									}
								}	
							}
							else {
								if (useless_tile.contains(output.get(i).get(u))) {
									usefull_tile.add(output.get(i).get(u));
									useless_tile.remove(output.get(i).get(u));
									
								}
							}
						}
					}
					
					
							
					ArrayList<ArrayList<Tile>> table = new ArrayList<ArrayList<Tile>>(p.getTable().getTable());
					
					//System.out.println(useless_tile);
					//System.out.println(table);
					
					
					
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
					//System.out.println(index);
					if (index!=999) {
					for (int i=0;i<table.get(index).size();i++) {
						if (table.get(index).get(i).isJoker()) {
							tempIndex=i;
							 s = (table.get(index).get(tempIndex).getJokerColor());
							//System.out.println(tempIndex);
						}
					}
					}
					
					if(index != 999) {
						for(int i =0; i < useless_tile.size();i++) {
							if(useless_tile.get(i).getNumber() == table.get(index).get(index_meld).getJokerPoint()) { //System.out.println(table.get(index));
								if(checkMeld.isSet(table.get(index))) {//System.out.println(i);
									HashSet<String> checking_color = new HashSet<String>();
									for(int x =0; x< table.get(index).size();x++) {
										checking_color.add(table.get(index).get(x).getColor());
									}
									//System.out.println(i);
										indexToReplace = i;
									
								}
								
								else if(useless_tile.get(i).getColor().equals(s)) {
										indexToReplace = i;
									}
								
								
								else {
									usefull=true;
									for(int z =0; z < usefull_tile.size();z++) {
										if(usefull_tile.get(z).getNumber() == table.get(index).get(index_meld).getJokerPoint()) { 
											if(checkMeld.isSet(table.get(index))) {//System.out.println(i);
												HashSet<String> checking_color = new HashSet<String>();
												for(int x =0; x< table.get(index).size();x++) {
													checking_color.add(table.get(index).get(x).getColor());
												}
												//System.out.println(i);
													indexToReplace = z;
												
											}
											
											else if(usefull_tile.get(z).getColor().equals(table.get(index).get(index_meld).getJokerColor())) {//System.out.println(table.get(index));
													indexToReplace = z;//System.out.println("LOL");
												}

										}
									}
								}
							}
						}
					}//System.out.println(indexToReplace); 
					//System.out.println("TEST 4");
					if (usefull==false) {
					if(indexToReplace != 999) {    //System.out.println(index);
					//Replace tiles on hand to the table.
					table.get(index).get(index_meld).setJoker(false);
					table.get(index).get(index_meld).setColor(useless_tile.get(indexToReplace).getColor());
					table.get(index).get(index_meld).setNumber(useless_tile.get(indexToReplace).getNumber());
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
								break loop;
							}
							else if (checkMeld.isJokerSet(a)) {
								table.get(i).add(useless_tile.get(indexToReplace));
								break loop;
							}
						}
						return true;
					}
					// if can play all tiles, p2 play all the tiles and become a winner
					if(function.getSizeOf(melds) == useless_tile.size()) {
						for(int u =0; u < p.getHand().sizeOfHand();u++) {
							p.getHand().playTileFromHand(p.getHand().getTile(u));
							u--;
						}
						//System.out.println(11);
						p.getHand().HandReader();
						p.setWinner();
						return true;
					}
					// else it plays as most as tiles as it can and put in on the table
					else if(melds.size() > 0 ) {
						// remove tiles from player' hand
						for(int k =0; k < melds.size();k++) {
							p.getTable().addTiles(melds.get(k));
							for(int  u =0; u < melds.get(k).size();u++) {
								if(melds.get(k).get(u).isJoker()) {
									loop: for(int x =0; x < p.getHand().sizeOfHand();x++) {
										if(p.getHand().getTile(x).isJoker()) {
											if(p.getHand().getTile(x).getJokerPoint() == melds.get(k).get(u).getJokerPoint() && 
													p.getHand().getTile(x).getColor().equals( melds.get(k).get(u).getJokerColor()))
												p.getHand().removeTile(p.getHand().getTile(x));
										}
									}
								}
								else p.getHand().playTileFromHand(melds.get(k).get(u));	
								}
							}
						if ((p.getHand().sizeOfHand()==1)&&(p.getHand().getTile(0).isJoker())) {
							Tile t = new Tile();
							t = p.getHand().getTile(0);
							p.getHand().removeTile(t);
							p.setWinner();
						}
						return true;
						}
						else return false;
					}
					}
					else {
						if(indexToReplace != 999) {    //System.out.println(index);
							//Replace tiles on hand to the table.
							table.get(index).get(index_meld).setJoker(false);
							table.get(index).get(index_meld).setColor(usefull_tile.get(indexToReplace).getColor());
							table.get(index).get(index_meld).setNumber(usefull_tile.get(indexToReplace).getNumber());
							useless_tile.get(indexToReplace).setJoker(true);
							useless_tile.get(indexToReplace).setColor("J");   
							useless_tile.get(indexToReplace).setNumber(14);
							// use joker tile to play tile on hand first.
							ArrayList<ArrayList<Tile>> melds = new ArrayList<ArrayList<Tile>>();
							if(checkMeld.getJokerSequences(true, 1, usefull_tile).size() > 0 && checkMeld.getPoint(checkMeld.getJokerSequences(true, 1, usefull_tile)) > 0) {
								melds = checkMeld.getJokerSequences(true, 1, usefull_tile);
							}
							else if (checkMeld.getJokerSet(true, 1, usefull_tile).size() > 0) {
								melds = checkMeld.getJokerSet(true, 1, usefull_tile);
							}
							else {
								// if can't use joker to play tiles on hand, find place to put it back to the table.
								loop : for(int i =0; i < table.size();i++) {
									ArrayList<Tile> a = new ArrayList<Tile>(table.get(i));
									a.add(useless_tile.get(indexToReplace));
									if(checkMeld.isJokerSequences(a)) {
										table.get(i).add(usefull_tile.get(indexToReplace));
										break loop;
									}
									else if (checkMeld.isJokerSet(a)) {
										table.get(i).add(usefull_tile.get(indexToReplace));
										break loop;
									}
								}
								return true;
							}
							// if can play all tiles, p2 play all the tiles and become a winner
							if(function.getSizeOf(melds) == usefull_tile.size()) {
								for(int u =0; u < p.getHand().sizeOfHand();u++) {
									p.getHand().playTileFromHand(p.getHand().getTile(u));
									u--;
								}
								//System.out.println(11);
								p.getHand().HandReader();
								p.setWinner();
								return true;
							}
							// else it plays as most as tiles as it can and put in on the table
							else if(melds.size() > 0 ) {
								// remove tiles from player' hand
								for(int k =0; k < melds.size();k++) {
									p.getTable().addTiles(melds.get(k));
									for(int  u =0; u < melds.get(k).size();u++) {
										if(melds.get(k).get(u).isJoker()) {
											loop: for(int x =0; x < p.getHand().sizeOfHand();x++) {
												if(p.getHand().getTile(x).isJoker()) {
													if(p.getHand().getTile(x).getJokerPoint() == melds.get(k).get(u).getJokerPoint() && 
															p.getHand().getTile(x).getColor().equals( melds.get(k).get(u).getJokerColor()))
														p.getHand().removeTile(p.getHand().getTile(x));
												}
											}
										}
										else p.getHand().playTileFromHand(melds.get(k).get(u));	
										}
									}
								if ((p.getHand().sizeOfHand()==1)&&(p.getHand().getTile(0).isJoker())) {
									Tile t = new Tile();
									t = p.getHand().getTile(0);
									p.getHand().removeTile(t);
									p.setWinner();
								}
								return true;
								}
								else return false;
							}
					}//System.out.println(usefull_tile.size());
					if (usefull_tile.size()>2) { 
						String out = "";  //System.out.println("TEST 5");
						int a = 0;
						loop:for(int i = output.size()-1; i > -1 ;i--) {
							p.getTable().addTiles(output.get(i));
							a += checkMeld.getPointOfSeq(output.get(i));
							for(int u = 0; u < output.get(i).size();u++) {
								if(output.get(i).get(u).isJoker()) {
									myloop: for(int k =0; k < p.getHand().getTiles().size();k++) {
										if(p.getHand().getTiles().get(k).isJoker() && p.getHand().getTiles().get(k).getJokerColor().equals(output.get(i).get(u).getJokerColor())
											&& p.getHand().getTiles().get(k).getJokerPoint() == output.get(i).get(u).getJokerPoint()) {
											p.getHand().getTiles().remove(k);
											
										}
									}	
								}
								else {
									p.getHand().getTiles().remove(output.get(i).get(u)); 
									//System.out.println(p.getHand().getTiles().size());
									
								}
								out += output.get(i).get(u).toString();
								System.out.println("testing:      " + out);
								System.out.println("Things left" + p.getHand().getTiles());
							}
							
						}
						
					}//System.out.println("TEST 8");
					if (useless_tile.size()==1) {
						function.playLastTile(p);//System.out.println("TEST 6");
						return true;
					}
				
				}
			
		return false;}
		else return false;
	}
}