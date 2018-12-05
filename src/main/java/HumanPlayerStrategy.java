import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;


public class HumanPlayerStrategy implements PlayerStrategy{
	
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
		// TODO Auto-generated method stub
		
		return suggestions(p); 
	}
	
	public boolean suggestions(Player p) {
		
		
		int num = checkMeld.NumberOfJoker(p.getHand().getTiles());

		
		if(num == 0) return playTheGameWOJ(p);
		else return playTheGameWJ(p); 
		
	}	
	
	public boolean playTheGameWOJ(Player p) {
		// TODO Auto-generated method stub
		
		if(p.getIsFirstMeldComplete()) {
			if(function.one_short(p)) {return true;}
					//copy player hand to sample hand
				ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
					ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
					
					double sum=1000000;
					double sum2=1000000;
					double betterSum = 0.0; 
					
					// create output 
					ArrayList<ArrayList<Tile>> output ;
					//find all sequences first
					ArrayList<ArrayList<Tile>> firstMelds = function.getFirstOutput(first_hand);
					//find all sets first
					ArrayList<ArrayList<Tile>> secondMelds = function.getSecondOutput(second_hand);
					int x = 0,y = 0;
					//check the size of 2 possible output, and get the one whose size is bigger than other.
					x = function.getSizeOf(firstMelds);
					y = function.getSizeOf(secondMelds);
					
					ArrayList<Tile> leastSeq = new ArrayList<Tile>();
					ArrayList<Tile> leastSet = new ArrayList<Tile>();
					
					ArrayList<ArrayList<Tile>> AllLeastSeqs = new ArrayList<ArrayList<Tile>>();
					ArrayList<ArrayList<Tile>> AllLeastSets = new ArrayList<ArrayList<Tile>>();
					
					ArrayList<ArrayList<Tile>> x1 = new ArrayList<ArrayList<Tile>>();
					ArrayList<ArrayList<Tile>> x2 = new ArrayList<ArrayList<Tile>>();
					x1=function.getSequences(p.getHand().getTiles());
					x2=function.getSets(p.getHand().getTiles(),false);
					
					
					int p1=0;int p2=0; int p3=0;
					p.setPoints(p1,p2,p3);
				
					//prob. for sets 
					for (int i=0;i<x1.size();i++) {
						double tempsum=0;
						for (int j = 0; j<x1.get(i).size();j++) {
							                                       //Tile         //Table   //Deck     //p1 p2 p3 hand size 
							tempsum+=function.getProbability(x1.get(i).get(j),p.getTable(),p.getDeck().DeckofTiles,p1,p2,p3);
						}
						
						if (tempsum==0) {
							AllLeastSeqs.add(x1.get(i)); AllLeastSets.add(x1.get(i)); 
						}
						if (tempsum<sum) {
							sum=tempsum;
							leastSeq=x1.get(i);
						}
					}
					
					 if (sum!=0) {
						 AllLeastSeqs.add(leastSeq); 
					 }
					 //prob for sequences 
					 for (int i=0;i<x2.size();i++) {double tempsum=0;
						for (int j = 0; j<x2.get(i).size();j++) 
							tempsum+=function.getProbability(x2.get(i).get(j),p.getTable(),p.getDeck().DeckofTiles,p1,p2,p3); 
						
						if (tempsum==0) {
							AllLeastSets.add(x2.get(i)); AllLeastSeqs.add(x2.get(i));
						}
						if (tempsum<sum2) {
							sum2=tempsum;
							leastSet=x2.get(i);
						}
					 }
					 
					 if (sum2!=0) {
						 AllLeastSets.add(leastSet);
					 }
					 
					 
					 //find best result 
					 if ((sum+sum2)==0) {
							if(x >= y) { output = firstMelds; }
							
							else {output = secondMelds; }
						}
					 else if (sum<sum2) {
						 output=AllLeastSeqs;
						 betterSum = sum; 
					 }
					 else if (sum>sum2){
						 output=AllLeastSets;
						 betterSum = sum2; 
					 }
					 else {
						 int z1= function.getSizeOf(AllLeastSeqs);
						 int z2=function.getSizeOf(AllLeastSets);
						 if (z1>z2) {
							 output=AllLeastSeqs; 
						 }
						 else {
						 output=AllLeastSets;
						 }
					 }

					if (output.size() == 0) return false;
					
					int point = 0;
					for(int i = output.size()-1; i > -1 ;i--) {
						for(int u = 0; u < output.get(i).size();u++) {
							point += output.get(i).get(u).getNumber();
						}
					}
					
					//add tiles in the table and remove tiles from player hand.
				//	System.out.println("Tiles Player should play are: ");
					String out = "";
					for(int i = output.size()-1; i > -1 ;i--) {
						for(int u = 0; u < output.get(i).size();u++) {
							out += output.get(i).get(u).toString();
							p.getSuggPlayList().add(output.get(i).get(u));
						}
					}
					out += "\n";
					//System.out.println(out);
					p.set_report(out);
					
					System.out.println("Why: ");
					System.out.println("     Points: "+ point);
					System.out.println("     Probability: Output 1 -- " + betterSum); 
					if(p.getHand().getTiles().size() == 0) 
						System.out.println("     Winner");

					return true;
				}
				else {
					if(function.one_short(p)) {
						return true;}
					double sum=1000000;
					double sum2=1000000;
					//copy player hand to sample hand
					ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
					ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
					
					// create output 
					ArrayList<ArrayList<Tile>> output;
					ArrayList<ArrayList<Tile>> output2;
					ArrayList<ArrayList<Tile>> output3;
					
					ArrayList<ArrayList<Tile>> firstMelds = function.getFirstOutput(first_hand);
					ArrayList<ArrayList<Tile>> secondMelds = function.getSecondOutput(second_hand);
					int x = 0,y = 0;
					//check the size of 2 possible output, and get the one whose size is bigger than other.
					x = function.getSizeOf(firstMelds);
					y = function.getSizeOf(secondMelds);
					
					
					ArrayList<Tile> leastSeq = new ArrayList<Tile>();
					ArrayList<Tile> leastSet = new ArrayList<Tile>();
					
					ArrayList<ArrayList<Tile>> AllLeastSeqs = new ArrayList<ArrayList<Tile>>();
					ArrayList<ArrayList<Tile>> AllLeastSets = new ArrayList<ArrayList<Tile>>();
					
					ArrayList<ArrayList<Tile>> x1 = new ArrayList<ArrayList<Tile>>();
					ArrayList<ArrayList<Tile>> x2 = new ArrayList<ArrayList<Tile>>();
					x1=function.getSequences(p.getHand().getTiles());
					x2=function.getSets(p.getHand().getTiles(),false);
					
					
					if(x >= y) { output3 = firstMelds;  }
					
					else {output3 = secondMelds; }
					
					int p1=0;int p2=0; int p3=0;
					p.setPoints(p1,p2,p3);
				
					 for (int i=0;i<x1.size();i++) {double tempsum=0;
						for (int j = 0; j<x1.get(i).size();j++) {
							                               //Tile         //Table   //Deck     //p1 p2 p3 hand size 
						tempsum+=function.getProbability(x1.get(i).get(j),p.getTable(),p.getDeck().DeckofTiles,p1,p2,p3);
					}
						if (tempsum==0) {
							AllLeastSeqs.add(x1.get(i)); AllLeastSets.add(x1.get(i)); 
						}
						if (tempsum<sum) {
							sum=tempsum;
							leastSeq=x1.get(i);
						}
					}
					 if (sum!=0) {
						 AllLeastSeqs.add(leastSeq); 
					 }
					 
					 
					 for (int i=0;i<x2.size();i++) {double tempsum=0;
						for (int j = 0; j<x2.get(i).size();j++) {
							
						tempsum+=function.getProbability(x2.get(i).get(j),p.getTable(),p.getDeck().DeckofTiles,p1,p2,p3); 
					}
						if (tempsum==0) {
							AllLeastSets.add(x2.get(i)); AllLeastSeqs.add(x1.get(i));
						}
						if (tempsum<sum2) {
							sum2=tempsum;
							leastSet=x2.get(i);
						}
					 }
					 if (sum2!=0) {
						 AllLeastSets.add(leastSet);
					 }
					 
					 
					 
					 if ((sum+sum2)==0) {
							if(x >= y) { output = firstMelds; output2=secondMelds;}
							
							else {output = secondMelds; output2=firstMelds; }
						}
					 else if (sum<sum2) {
						 output=AllLeastSeqs; output2=AllLeastSets;
					 }
					 else if (sum>sum2){
						 output=AllLeastSets; output2=AllLeastSeqs;
					 }
					 else {
						 output=AllLeastSeqs; output2=AllLeastSets; //System.out.println("TEST");
						
					 }
				/////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					if (output.size() == 0) return false;
					int point = 0;
					int point2 = 0;
					int point3 = 0;
					//add tiles in the table and remove tiles from player hand.
					// use point to sum up tiles' value
					for(int i = output.size()-1; i > -1 ;i--) {
						for(int u = 0; u < output.get(i).size();u++) {
							point += output.get(i).get(u).getNumber();
						}
					}
					for(int i = output2.size()-1; i > -1 ;i--) {
						for(int u = 0; u < output2.get(i).size();u++) {
							point2 += output2.get(i).get(u).getNumber();
						}
					} 
					for(int i = output3.size()-1; i > -1 ;i--) {
						for(int u = 0; u < output3.get(i).size();u++) {
							point3 += output3.get(i).get(u).getNumber();
						}
					}
					// if point >= 30, add tiles to the table and remove them from hand
					if(point >= 30) {
						//System.out.println("Tiles Player should play are: ");
						String out = "";
						for(int i = output.size()-1; i > -1 ;i--) {
							for(int u = 0; u < output.get(i).size();u++) {
								out += output.get(i).get(u).toString();
								p.getSuggPlayList().add(output.get(i).get(u));
							}
						}
						out += "\n";
						//System.out.println(out);
						p.set_report(out);
						
						System.out.println("Why: ");
						System.out.println("     First Initial Meld Complete");
						System.out.println("     Points: "+ point);
						System.out.println("     Probability: Output 1 -- " + (sum+sum2)); 
						
						//set winner, no tiles left
						if(p.getHand().getTiles().size() == 0) 
							System.out.println("     Winner");

						return true;
					}
					else if(point2 >= 30) {
						System.out.println("Tiles Player should play are: ");
						String out = "";
						for(int i = output2.size()-1; i > -1 ;i--) {
							for(int u = 0; u < output2.get(i).size();u++) {
								out += output2.get(i).get(u).toString();
								p.getSuggPlayList().add(output2.get(i).get(u));
							}
						}
						out += "\n";
						System.out.println(out);
						p.set_report(out);
						//set the fist meld complete
						System.out.println("Why: ");
						System.out.println("     First Initial Meld Complete");
						System.out.println("     Points: "+ point);
						System.out.println("     Probability: Output 2 -- " + sum2); 
						
						if(p.getHand().getTiles().size() == 0) p.setWinner();
							System.out.println("     Winner");

						return true;
					}
					else if(point3 >= 30) {
						//System.out.println("Tiles Player should play are: ");					
						String out = "";
						for(int i = output3.size()-1; i > -1 ;i--) {
							for(int u = 0; u < output3.get(i).size();u++) {
								out += output3.get(i).get(u).toString();
								p.getSuggPlayList().add(output3.get(i).get(u));
							}
						}
						out += "\n";
						//System.out.println(out);
						p.set_report(out);
						
						System.out.println("Why: ");
						System.out.println("     First Initial Meld Complete");
						System.out.println("     Points: "+ point);
						System.out.println("     Probability: Output 1 -- " + sum); 

						//if size ==0, this player is the winner
						if(p.getHand().getTiles().size() == 0) 
							System.out.println("     Winner");
						return true;
						}
				return false;
				}
	}

	
	public boolean playTheGameWJ(Player p) {
		int num = checkMeld.NumberOfJoker(p.getHand().getTiles());

		boolean complete_first_turn = p.getIsFirstMeldComplete();
		
		ArrayList<Tile> sample = new ArrayList<Tile>(p.getHand().getTiles());
		ArrayList<Tile> sample1 = new ArrayList<Tile>(p.getHand().getTiles());
		
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> output2 = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> output3= new ArrayList<ArrayList<Tile>>();
		checkMeld.separateList(p.getHand().getTiles()); //  initial tile for each list of 5
		
			if(p.getIsFirstMeldComplete() == false) {
				ArrayList<ArrayList<Tile>> first = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> second = new ArrayList<ArrayList<Tile>>();
				
				ArrayList<ArrayList<Tile>> object = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> object1 = new ArrayList<ArrayList<Tile>>();
		
				
				ArrayList<Tile> tempOut = new ArrayList<Tile>();
				
				ArrayList<ArrayList<Tile>> AllOut = new ArrayList<ArrayList<Tile>>();
				
				
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
						p.getTable().addTiles(output.get(i));
						for(int u = 0; u < output.get(i).size();u++) {
							if(output.get(i).get(u).isJoker()) {
								myloop: for(int k =0; k < p.getHand().getTiles().size();k++) {
									if(p.getHand().getTiles().get(k).isJoker() && p.getHand().getTiles().get(k).getJokerColor().equals(output.get(i).get(u).getJokerColor())
										&& p.getHand().getTiles().get(k).getJokerPoint() == output.get(i).get(u).getJokerPoint()) {
										p.getHand().getTiles().remove(k);
										p.getSuggPlayList().add(p.getHand().getTile(k));

										break myloop;
									}
								}	
							}
							else
								p.getSuggPlayList().add(output.get(i).get(u));
							out += output.get(i).get(u).toString();
						}
					}
					out += "\n";
					p.set_report(out);
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
					p.getTable().addTiles(output.get(i));
					for(int u = 0; u < output.get(i).size();u++) {
						if(output.get(i).get(u).isJoker()) {
							myloop: for(int k =0; i < p.getHand().getTiles().size();k++) {
								if(p.getHand().getTiles().get(k).isJoker() && p.getHand().getTiles().get(k).getJokerColor().equals(output.get(i).get(u).getJokerColor())
									&& p.getHand().getTiles().get(k).getJokerPoint() == output.get(i).get(u).getJokerPoint()) {
									p.getSuggPlayList().add(p.getHand().getTile(k));
									break myloop;
								}
							}	
						}
						else
							p.getSuggPlayList().add(output.get(i).get(u));
						out += output.get(i).get(u).toString();
					}
				}
				out += "\n";
				p.set_report(out);
				return true;
			}
	}	
}
