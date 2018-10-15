import java.util.ArrayList;

import junit.framework.TestCase;

public class PlayerHandTest extends TestCase{
	
	public void testSizeOfHand() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
		deck.Shuffle();

		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

		assertEquals(3, hand.sizeOfHand()); 
		
		
		hand.playTileFromHand(tempTile);
		
		assertEquals(2, hand.sizeOfHand()); 

	}
	
	public void testEmptyfHand() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
		deck.Shuffle();

		Tile tempTile_1, tempTile_2, tempTile_3; 
		hand.addTileToHand(tempTile_1 = deck.Draw());
		hand.addTileToHand(tempTile_2 = deck.Draw());
		hand.addTileToHand(tempTile_3 = deck.Draw());
		
		
		hand.playTileFromHand(tempTile_1);
		hand.playTileFromHand(tempTile_2);
		hand.playTileFromHand(tempTile_3);

		assertTrue(hand.isEmpty());
	}
	
	
	
	public void testPlay() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
		
		deck.Shuffle();

		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

		
		hand.playTileFromHand(tempTile);
		
		assertEquals(2, hand.sizeOfHand()); 
		assertFalse(hand.getTiles().contains(tempTile)); 

	}
	
	
	public void testAddTile() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
		deck.Shuffle();

		
		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

				
		assertEquals(3, hand.sizeOfHand()); 
		assertTrue(hand.getTiles().contains(tempTile)); 
		assertEquals(tempTile.getNumber(), hand.getTiles().get(0).getNumber()); 

	}

	public void testadd14() {
		Deck deck = new Deck();
		PlayerHand  hand  = new PlayerHand ("X");
		deck.Shuffle();
		hand.drawFirst14(deck);
		assertEquals(hand.sizeOfHand(),14);
	}
	
	public void testRunFind() {      //tests if it can find a run
		PlayerHand  hand  = new PlayerHand ("X");
		Tile x1 = new Tile(1,3);
		Tile x2 = new Tile(1,5);
		Tile x3 = new Tile(1,6);
		Tile x4 = new Tile(1,7);
		hand.addTileToHand(x1);
		hand.addTileToHand(x2);
		hand.addTileToHand(x3);
		hand.addTileToHand(x3);
		hand.addTileToHand(x2);
		hand.addTileToHand(x3);
		hand.addTileToHand(x4);
		boolean x = hand.runFound(hand);
		
		assertEquals(x, true);
	}
	
	public void testRunHighest() {      //tests if it can find a run and return highest value run
		PlayerHand  hand  = new PlayerHand ("X");
		Tile x1 = new Tile(1,4);
		Tile x2 = new Tile(1,5);
		Tile x3 = new Tile(3,6);
		Tile x4 = new Tile(3,7);
		Tile x5 = new Tile(3,8);
		Tile x6 = new Tile(3,9);
		Tile x7 = new Tile(3,10);
		hand.addTileToHand(x1);
		hand.addTileToHand(x2);
		hand.addTileToHand(x3);
		hand.addTileToHand(x4);
		hand.addTileToHand(x5);
		hand.addTileToHand(x6);
		hand.addTileToHand(x1);
	
		ArrayList<Tile> x = hand.findRun(hand); 
		assertEquals(x.get(0).getNumber(), 6 );
	}
	
	public void testFindSet() { 
		PlayerHand  hand  = new PlayerHand ("X");
		Tile x1 = new Tile(1,4);
		Tile x2 = new Tile(4,4);
		Tile x3 = new Tile(2,4);
		Tile x4 = new Tile(3,1);
		hand.addTileToHand(x1);
		hand.addTileToHand(x2);
		hand.addTileToHand(x3);
		hand.addTileToHand(x4);
		boolean x = hand.foundSet(hand);
		assertEquals(x, true);
		
		ArrayList<Tile> y = hand.findSet(); 
		
		for(int i = 0; i < y.size()-1; i++) {
			assertTrue(y.get(i).getColor() !=  y.get(i+1).getColor()); 
		}
		
	}
	
	
	public void testSortByNumber() {
		Deck deck = new Deck();
		deck.Shuffle();
		PlayerHand  hand  = new PlayerHand ("X");
		hand.drawFirst14(deck);
		
		//hand.HandReader();
		hand.sortTilesByNumber();
		for(int i = 0; i < hand.sizeOfHand()-1; i++) 
			assertTrue((hand.getTiles().get(i).getNumber() <= hand.getTiles().get(i+1).getNumber()));
		
	}
	
	//testing seperatebycolour and sortbycolour
	public void testSortByColour () {
		Deck deck = new Deck();
		deck.Shuffle();
		PlayerHand  hand  = new PlayerHand ("X");
		hand.drawFirst14(deck);
		
		
		//hand.HandReader();
		hand.sortTilesByColour();
		int j =0; 
		for(int i = 0; i < hand.sizeOfHand()-1; i++) {
			if(hand.getTile(i).getColor() == "R"  & hand.getTile(i+1).getColor() == "R")
				assertTrue((hand.getTiles().get(i).getNumber() <= hand.getTiles().get(i+1).getNumber()));
			else if(hand.getTile(i).getColor() == "B"  & hand.getTile(i+1).getColor() == "B"){
				assertTrue((hand.getTiles().get(i).getNumber() <= hand.getTiles().get(i+1).getNumber()));
			}
			else if(hand.getTile(i).getColor() == "G"  & hand.getTile(i+1).getColor() == "G") {
				assertTrue((hand.getTiles().get(i).getNumber() <= hand.getTiles().get(i+1).getNumber()));
			}
			else if(hand.getTile(i).getColor() == "O"  & hand.getTile(i+1).getColor() == "O"){
				assertTrue((hand.getTiles().get(i).getNumber() <= hand.getTiles().get(i+1).getNumber()));
			}
			
		//	hand.HandReader();

		}
	}
	

	
}
