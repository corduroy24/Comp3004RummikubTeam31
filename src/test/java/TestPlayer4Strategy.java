import java.util.ArrayList;

import junit.framework.TestCase;

public class TestPlayer4Strategy extends TestCase {

	public Support functions;
	
	public void testgetProbability() {
		functions = new Support();
		Tile t1 =new Tile(1,7);
		Tile t2=new Tile(1,8);
		Tile t3=new Tile(1,9);
		Deck D = new Deck();
		ArrayList<Tile> table = new ArrayList<Tile>();
		//System.out.println(D.length());
		for (int i=0;i<D.length();i++) {
			if ((D.getTile(i).getColor()==t1.getColor())&&((D.getTile(i).getNumber()==t1.getNumber())||(D.getTile(i).getNumber()==t2.getNumber())||(D.getTile(i).getNumber()==t3.getNumber()))){
				D.DeckofTiles.remove(i);
			}
			if ((D.getTile(i).getColor()=="B")&&(D.getTile(i).getNumber()==3)){
				D.DeckofTiles.remove(i);
			}
			
			if ((D.getTile(i).getColor()=="B")&&(D.getTile(i).getNumber()==4)){
				D.DeckofTiles.remove(i);
			}
		}
		D.DeckofTiles.add(t1);D.DeckofTiles.add(t2);
		//System.out.println(D.length());
		ArrayList<Tile> hand = new ArrayList<Tile>();
		
		hand.add(t1); 
		hand.add(t2); 
		//hand.add(t3); 
		table.addAll(hand);
		
		table.add(new Tile(2,3));
		table.add(new Tile(2,4));
		//table.add(t3);
		
		double p= functions.getProbability(t1,  table, D.DeckofTiles,0,0,0);
		double p2= functions.getProbability(t3,  table, D.DeckofTiles,0,0,0);
		//System.out.println(p2);
		//System.out.println(D.DeckofTiles.size());
		assertTrue(p==0);
		assertTrue(p2==10);
	}
	
	public void testAmrsPlan() {
		functions = new Support();
		Tile t1 =new Tile(1,7);
		Tile t2=new Tile(1,8);
		Tile t3=new Tile(1,9);
		Tile t4 =new Tile(1,10);
		Tile t5=new Tile(2,10);
		Tile t6=new Tile(3,10);
		Tile t7 =new Tile(1,5);

		
		ArrayList<Tile> hand = new ArrayList<Tile>();
		ArrayList<Tile> table = new ArrayList<Tile>();
		//ArrayList<Double> sums = new ArrayList<Double>();
		
		hand.add(t1);hand.add(t2);hand.add(t3);hand.add(t4);hand.add(t5);hand.add(t6);hand.add(t7);
		
		ArrayList<ArrayList<Tile>> x1 = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> x2 = new ArrayList<ArrayList<Tile>>();
		
		table.add(t1);
		table.add(t2);
		
		Deck D = new Deck();
		for (int i=0;i<D.length();i++) {
			if ((D.getTile(i).getColor()==t1.getColor())&&((D.getTile(i).getNumber()==t1.getNumber())||(D.getTile(i).getNumber()==t2.getNumber())||(D.getTile(i).getNumber()==t3.getNumber())||(D.getTile(i).getNumber()==10))){
				D.DeckofTiles.remove(i);
			}
			if ((D.getTile(i).getColor()==t5.getColor())&&((D.getTile(i).getNumber()==10))) {
				D.DeckofTiles.remove(i);
			}
			if ((D.getTile(i).getColor()==t6.getColor())&&((D.getTile(i).getNumber()==10))) {
				D.DeckofTiles.remove(i);
			}
			if ((D.getTile(i).getColor()==t7.getColor())&&((D.getTile(i).getNumber()==5))) {
				D.DeckofTiles.remove(i);
			}
		
		}
		D.DeckofTiles.add(t3);D.DeckofTiles.add(t4);D.DeckofTiles.add(t5);D.DeckofTiles.add(t6);D.DeckofTiles.add(t7);
		
		x1=functions.getSequences(hand);
		x2=functions.getSets(hand);
		
		ArrayList<Tile> leastSeq = new ArrayList<Tile>();
		ArrayList<Tile> leastSet = new ArrayList<Tile>();

		double sum=10000000;
		double sum2=1000000;
		
		 for (int i=0;i<x1.size();i++) {double tempsum=0;
			for (int j = 0; j<x1.get(i).size();j++) {
				                               //Tile         //Table   //Deck     //p1 p2 p3 hand size 
			tempsum+=functions.getProbability(x1.get(i).get(j),table,D.DeckofTiles,0,0,0);
		}
			if (tempsum<sum) {
				sum=tempsum;
				leastSeq=x1.get(i);
			}
		}
		 
		 for (int i=0;i<x2.size();i++) {double tempsum=0;
			for (int j = 0; j<x2.get(i).size();j++) {
				
			tempsum+=functions.getProbability(x2.get(i).get(j),table,D.DeckofTiles,0,0,0); 
		}
			if (tempsum<sum2) {
				sum2=tempsum; 
				leastSet=x1.get(i);
			}
		}
		 
	 //System.out.println(sum);
	 //System.out.println(sum2);
		 ArrayList<Tile> result = new ArrayList<Tile>();
		 
		 if (sum>sum2) {
			 result = leastSeq;
		 }
		 else {
			 result = leastSet;
		 }
		 
		 assertEquals(result, leastSeq);
		 
	}
}
