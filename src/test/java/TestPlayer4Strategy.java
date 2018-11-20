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
		assertTrue(p2==1);
	}
}
