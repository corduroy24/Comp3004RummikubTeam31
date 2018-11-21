	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.HashSet;


	public class PlayerStrategy4 implements PlayerStrategy {
		private Support functions;
		
		public boolean playTheGame(Player p) {
			functions = new Support();
			// TODO Auto-generated method stub
	 if(p.getIsFirstMeldComplete()) {
				//copy player hand to sample hand
				ArrayList<Tile> first_hand = new ArrayList<Tile>(p.getHand().getTiles());
				ArrayList<Tile> second_hand = new ArrayList<Tile>(p.getHand().getTiles());
				
				double sum=1000000;
				double sum2=1000000;
				
				// create output 
				ArrayList<ArrayList<Tile>> output ;
				//find all sequences first
				ArrayList<ArrayList<Tile>> firstMelds = functions.getFirstOutput(first_hand);
				//find all sets first
				ArrayList<ArrayList<Tile>> secondMelds = functions.getSecondOutput(second_hand);
				int x = 0,y = 0;
				//check the size of 2 possible output, and get the one whose size is bigger than other.
				x = functions.getSizeOf(firstMelds);
				y = functions.getSizeOf(secondMelds);
				
				ArrayList<Tile> result = new ArrayList<Tile>();
				ArrayList<Tile> leastSeq = new ArrayList<Tile>();
				ArrayList<Tile> leastSet = new ArrayList<Tile>();
				
				ArrayList<ArrayList<Tile>> AllLeastSeqs = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> AllLeastSets = new ArrayList<ArrayList<Tile>>();
				
				ArrayList<ArrayList<Tile>> x1 = new ArrayList<ArrayList<Tile>>();
				ArrayList<ArrayList<Tile>> x2 = new ArrayList<ArrayList<Tile>>();
				x1=functions.getSequences(p.getHand().getTiles());
				x2=functions.getSets(p.getHand().getTiles());
				
				
				int p1=0;int p2=0; int p3=0;
				p.setPoints(p1,p2,p3);
			
				 for (int i=0;i<x1.size();i++) {double tempsum=0;
					for (int j = 0; j<x1.get(i).size();j++) {
						                               //Tile         //Table   //Deck     //p1 p2 p3 hand size 
					tempsum+=functions.getProbability(x1.get(i).get(j),p.getTable(),p.getDeck().DeckofTiles,p1,p2,p3);
				}
					if (tempsum==0) {
						AllLeastSeqs.add(x1.get(i));
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
						
					tempsum+=functions.getProbability(x2.get(i).get(j),p.getTable(),p.getDeck().DeckofTiles,p1,p2,p3); 
				}
					if (tempsum==0) {
						AllLeastSets.add(x2.get(i));
					}
					if (tempsum<sum2) {
						sum2=tempsum;
						leastSeq=x2.get(i);
					}
				 }
				 if (sum2!=0) {
					 AllLeastSets.add(leastSet);
				 }
				 
		//		 System.out.println(x1);System.out.println(x2);
			//	 System.out.println(sum);System.out.println(sum2);
				 
				 if ((sum+sum2)==0) {
						if(x >= y) { output = firstMelds; }
						
						else {output = secondMelds; }
					}
				 else if (sum<sum2) {
					 output=AllLeastSeqs;
				 }
				 else if (sum>sum2){
					 output=AllLeastSets;
				 }
				 else {
					 if(x >= y) { output = firstMelds; }
						
						else {output = secondMelds; }
					
				 }
			//	System.out.println(output);
			
				
				
				if (output.size() == 0) return false;
				//add tiles in the table and remove tiles from player hand.
				System.out.println("Tiles played from AI4 are: ");
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
				System.out.println(out);
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
					System.out.println("Tiles played from AI4 are: ");
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
					System.out.println(out);
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


