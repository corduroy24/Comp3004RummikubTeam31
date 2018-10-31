import java.util.ArrayList;

import junit.framework.TestCase;

public class TestPlan extends TestCase{
	
	public void testGameStart() {
		GameMaster game = new GameMaster(); 
		
		//Req. 1
		game.dealInitialHand();
		
		ArrayList<Player> players = game.getPlayers(); 
		for(int i = 0; i < players.size(); i++ )
			assertEquals(14, players.get(i).getHand().sizeOfHand()); 
				
		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < game.getHuman().getHand().sizeOfHand() - 1 ; j++) {
				if(players.get(i).getHand().getTile(j).getColor().equals(players.get(i).getHand().getTile(j+1).getColor())){
					if(players.get(i).getHand().getTile(j).getColor().equals("R")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
					else if(players.get(i).getHand().getTile(j).getColor().equals("B")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
					else if(players.get(i).getHand().getTile(j).getColor().equals("G")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
					else if(players.get(i).getHand().getTile(j).getColor().equals("O")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
				}
				//	System.out.println("/////////////"+i);
			}
		}
		
		//Req. 2 (players turn is mainly tracked by behavior)
		//game.getHuman().play(); 
		assertTrue(game.getHuman().getIsTurn()); 
		assertFalse(game.getAI().getIsTurn()); 
		assertFalse(game.getAI2().getIsTurn()); 
		assertFalse(game.getAI3().getIsTurn()); 

	}
}
