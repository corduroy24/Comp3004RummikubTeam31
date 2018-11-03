import java.util.ArrayList;

import junit.framework.TestCase;

public class TestPlan1 extends TestCase{
	

	ArrayList<Tile> inHand_1; 
	ArrayList<Tile> inHand_2;
	ArrayList<Tile> inHand_3;
	ArrayList<Tile> inHand_4; 

	//Req. 3 is UI based... 
	
	public void testInitialMelds() {
		//Req. 4 
		
		GameMaster game = new GameMaster(); 
		inHand_1 = new ArrayList<Tile>(); 
		inHand_2 = new ArrayList<Tile>(); 
		inHand_3 = new ArrayList<Tile>(); 
		inHand_4 = new ArrayList<Tile>(); 

		setHands(); 
		
		
		for(int i = 0; i < inHand_1.size(); i++)
			game.getHuman().getHand().addTileToHand(inHand_1.get(i));

		for(int i = 0; i < inHand_2.size(); i++)
			game.getAI().getHand().addTileToHand(inHand_2.get(i));
		
		for(int i = 0; i < inHand_3.size(); i++)
			game.getAI2().getHand().addTileToHand(inHand_4.get(i));

		for(int i = 0; i < inHand_4.size(); i++)
			game.getAI3().getHand().addTileToHand(inHand_4.get(i));
		game.getAI().getHand().HandReader();
		game.getHuman().getHand().HandReader();
		game.getAI2().getHand().HandReader();
		game.getAI3().getHand().HandReader();
		
		//a)
		game.getHuman().getHand().playTileFromHand(inHand_1.get(3));
		game.getHuman().getHand().playTileFromHand(inHand_1.get(4));
		game.getHuman().getHand().playTileFromHand(inHand_1.get(5));

		ArrayList<Tile> a = new ArrayList<Tile>();
		a.add(inHand_1.get(3)); 
		a.add(inHand_1.get(4)); 
		a.add(inHand_1.get(5)); 
		game.getHuman().getTable().addTiles(a);

		game.getHuman().setIsfirstMeldComplete(true);
		game.Announcement(game.getHuman());
		assertTrue(game.getHuman().getIsFirstMeldComplete()); 
		assertFalse(game.getHuman().isWinner()); 

		//b)
		game.getAI().play();
		game.Announcement();
		assertTrue(game.getAI().getIsFirstMeldComplete()); 
		assertFalse(game.getAI().isWinner()); 
		
		game.getAI2().play();
		game.Announcement();
		assertTrue(game.getAI2().getIsFirstMeldComplete()); 
		assertFalse(game.getAI2().isWinner()); 

		game.getAI3().getHand().HandReader();
		game.getAI3().play();
		game.Announcement();
		assertTrue(game.getAI3().getIsFirstMeldComplete()); 
		assertTrue(game.getAI3().isWinner()); 
	}
	
	public void setHands() {
		//one meld with 30 points 
		inHand_1.add(new Tile(2,10));//
		inHand_1.add(new Tile(2,10));
		inHand_1.add(new Tile(2,10));
		
		inHand_1.add(new Tile(1,4));
		inHand_1.add(new Tile(1,5)); 
		inHand_1.add(new Tile(2,4));
		inHand_1.add(new Tile(3,10));//
		inHand_1.add(new Tile(3,10));
		inHand_1.add(new Tile(3,10));
		inHand_1.add(new Tile(3,10));
		inHand_1.add(new Tile(3,10));
		inHand_1.add(new Tile(4,10));//
		inHand_1.add(new Tile(4,10));
		inHand_1.add(new Tile(4,10)); 
		
		//meld with more than 30 points 
		inHand_2.add(new Tile(1,12));
		inHand_2.add(new Tile(1,11)); 
		inHand_2.add(new Tile(1,10));
		
		inHand_2.add(new Tile(2,9));
		inHand_2.add(new Tile(2,6));
		inHand_2.add(new Tile(2,11));
		inHand_2.add(new Tile(3,10));
		inHand_2.add(new Tile(3,10));
		inHand_2.add(new Tile(3,10));
		inHand_2.add(new Tile(3,10));
		inHand_2.add(new Tile(3,10));
		inHand_2.add(new Tile(4,10));
		inHand_2.add(new Tile(4,10));
		inHand_2.add(new Tile(4,10)); 
		
		
		//several melds with exactly 30 points 
		inHand_3.add(new Tile(1,9));
		inHand_3.add(new Tile(1,8)); 
		inHand_3.add(new Tile(1,7));
		
		inHand_3.add(new Tile(2,2));
		inHand_3.add(new Tile(2,3));
		inHand_3.add(new Tile(2,7));
		
		inHand_3.add(new Tile(3,2));
		inHand_3.add(new Tile(3,4));
		inHand_3.add(new Tile(3,4));
		inHand_3.add(new Tile(3,4));
		inHand_3.add(new Tile(3,10));
		inHand_3.add(new Tile(4,6));
		inHand_3.add(new Tile(4,2));
		inHand_3.add(new Tile(4,10)); 
		

		
		//several melds with more than 30 points 
		//player wins on 1st turn
		inHand_4.add(new Tile(1,12));
		inHand_4.add(new Tile(1,11)); 
		inHand_4.add(new Tile(1,10));
		inHand_4.add(new Tile(1,9));
		inHand_4.add(new Tile(1,8));
		inHand_4.add(new Tile(1,7));
		inHand_4.add(new Tile(1,6));
		inHand_4.add(new Tile(1,5));
		inHand_4.add(new Tile(1,4));
		inHand_4.add(new Tile(1,3));

		
		inHand_4.add(new Tile(2,7));
		inHand_4.add(new Tile(2,8));
		inHand_4.add(new Tile(2,9));
		inHand_4.add(new Tile(2,10));

	}
}
