import junit.framework.TestCase;

public class TestPlan extends TestCase{
	
	public void testGameStart() {
		GameMaster game = new GameMaster(); 
		
		//Req. 1
		game.dealInitialHand();
		
		assertEquals(14, game.getHuman().getHand().sizeOfHand()); 
		assertEquals(14, game.getAI().getHand().sizeOfHand()); 
		assertEquals(14, game.getAI2().getHand().sizeOfHand()); 
		assertEquals(14, game.getAI3().getHand().sizeOfHand()); 
		
		//Req. 2 (players turn is mainly tracked by behavior)
		//game.getHuman().play(); 
		assertTrue(game.getHuman().getIsTurn()); 
		assertFalse(game.getAI().getIsTurn()); 
		assertFalse(game.getAI2().getIsTurn()); 
		assertFalse(game.getAI3().getIsTurn()); 

	}
}
