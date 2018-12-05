import java.util.ArrayList;

import junit.framework.TestCase;

public class TestPlayer4Strategy extends TestCase {	
	
	public Support functions;
	
	public void testgetProbability() {
		functions = new Support();
		Tile t1 =new Tile(1,7);
		Tile t2=new Tile(1,8);
		Tile t3=new Tile(1,9);
		Tile t4 = new Tile(1,10);
		Deck D = new Deck();
		Table table = new Table();
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
		ArrayList<Tile> op = new ArrayList<Tile>();
		
		hand.add(t1); 
		hand.add(t2); 
		hand.add(t3); 
		table.addTiles(hand);
		op.add(new Tile(2,3));
		op.add(new Tile(2,4));
		op.add(new Tile(2,5));
		table.addTiles(op);
		
		double p= functions.getProbability(t1,  table, D.DeckofTiles,0,0,0);
		double p2= functions.getProbability(t4,  table, D.DeckofTiles,0,0,0);
		//System.out.println(p2);System.out.println(table.getTable().size());
		//System.out.println(D.DeckofTiles.size());
		assertTrue(p==0);
		assertTrue(p2==10);
	}
	
	public void testAmrsPlan() {  System.out.println("TEST ");
		functions = new Support();
		Tile t1 =new Tile(1,7);
		Tile t2=new Tile(1,8);
		Tile t3=new Tile(1,9);
		Tile t4 =new Tile(1,10);
		Tile t5=new Tile(2,10);
		Tile t6=new Tile(3,10);
		Tile t7 =new Tile(1,5);

		
		ArrayList<Tile> hand = new ArrayList<Tile>();
		Table table = new Table();
		ArrayList<Tile>t = new ArrayList<Tile>();
		
		hand.add(t1);hand.add(t2);hand.add(t3);hand.add(t4);hand.add(t5);hand.add(t6);hand.add(t7);
		
		ArrayList<ArrayList<Tile>> x1 = new ArrayList<ArrayList<Tile>>();
		ArrayList<ArrayList<Tile>> x2 = new ArrayList<ArrayList<Tile>>();
		
		t.add(t1);
		t.add(t2);
		t.add(t3);
		table.addTiles(t);
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
		x2=functions.getSets(hand, true);
		
		ArrayList<Tile> leastSeq = new ArrayList<Tile>();
		ArrayList<Tile> leastSet = new ArrayList<Tile>();

		double sum=10000000;
		double sum2=1000000;
		
		 for (int i=0;i<x1.size();i++) {double tempsum=0;
			for (int j = 0; j<x1.get(i).size();j++) {
				                               //Tile         //Table   //Deck     p1 
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
		 
		// assertEquals(result, leastSeq);
		 
	}
	
	
	Player player = new Player("P4",999,new PlayerStrategy4());
	Player player2 = new Player("Player2",9999,new p1());
	Player player3 = new Player("Player3",99,new p1());
	Player player4 = new Player("Player4",9,new p1());	
	
	public void testP4afterInitial() {  System.out.println("AFTER INITIAL");
		Deck D = new Deck();
		//player = new Player("P4",999,new PlayerStrategy4());
		player.setIsfirstMeldComplete(true);
		D=player.getDeck();
		player.getHand().DrawThis(new Tile(1,2),D);player.getHand().DrawThis(new Tile(1,3),D);player.getHand().DrawThis(new Tile(1,4),D);
		player.getHand().DrawThis(new Tile(2,6),D);player.getHand().DrawThis(new Tile(3,6),D);player.getHand().DrawThis(new Tile(4,6),D);
		player.getHand().DrawThis(new Tile(2,9),D);
		D=player.getDeck();
		//player2.getHand().DrawThis(new Tile(1,2),D);player2.getHand().DrawThis(new Tile(1,3),D);player2.getHand().DrawThis(new Tile(1,4),D);
		player2.getHand().DrawThis(new Tile(1,9),D);
		player.getHand().HandReader();
		 ArrayList<Tile> t = new ArrayList<Tile>();
		 t.add(new Tile(1,2));t.add(new Tile(1,3));t.add(new Tile(1,4));
		player.getTable().addTiles(t);
		player.play();
		
		
	}
	
	public void testP4Initial() {  System.out.println(" INITIAL");
		Deck D = new Deck();
	
		player.setIsfirstMeldComplete(false);
		D=player.getDeck();
		player.getHand().DrawThis(new Tile(1,2),D);player.getHand().DrawThis(new Tile(1,3),D);player.getHand().DrawThis(new Tile(1,4),D);
		player.getHand().DrawThis(new Tile(1,8),D);player.getHand().DrawThis(new Tile(1,5),D);
		player.getHand().DrawThis(new Tile(2,10),D);player.getHand().DrawThis(new Tile(3,10),D);player.getHand().DrawThis(new Tile(4,10),D);
		D=player.getDeck();
		//player2.getHand().DrawThis(new Tile(1,2),D);player2.getHand().DrawThis(new Tile(1,3),D);player2.getHand().DrawThis(new Tile(1,4),D);
		player2.getHand().DrawThis(new Tile(1,9),D);
		player.getHand().HandReader();
		 ArrayList<Tile> t = new ArrayList<Tile>();
		// t.add(new Tile(1,2));t.add(new Tile(1,3));t.add(new Tile(1,4));
		player.getTable().addTiles(t);
		player.play();
		
		
	}
	
	public void testP4playAllHand() {  System.out.println(" INITIAL PLAY ALL HAND");
		Deck D = new Deck();
		
		player.setIsfirstMeldComplete(false);
		D=player.getDeck();
		player.getHand().DrawThis(new Tile(1,2),D);player.getHand().DrawThis(new Tile(1,3),D);player.getHand().DrawThis(new Tile(1,4),D);
		//player.getHand().DrawThis(new Tile(1,8),D);player.getHand().DrawThis(new Tile(1,5),D);
		player.getHand().DrawThis(new Tile(2,10),D);player.getHand().DrawThis(new Tile(3,10),D);player.getHand().DrawThis(new Tile(4,10),D);
		D=player.getDeck();
		//player2.getHand().DrawThis(new Tile(1,2),D);player2.getHand().DrawThis(new Tile(1,3),D);player2.getHand().DrawThis(new Tile(1,4),D);
		player2.getHand().DrawThis(new Tile(1,9),D);
		player.getHand().HandReader();
		 ArrayList<Tile> t = new ArrayList<Tile>();
		// t.add(new Tile(1,2));t.add(new Tile(1,3));t.add(new Tile(1,4));
		player.getTable().addTiles(t);
		player.play();
		player.getHand().HandReader();
		assertTrue(player.isWinner());
	}
	

	
}
