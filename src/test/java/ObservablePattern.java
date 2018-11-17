import junit.framework.TestCase;

public class ObservablePattern extends TestCase{
	GameMaster game = new GameMaster();
	
	public void TestViewingHands() {
		//game.dealInitialHand();
		assertTrue(game.getPlayers().get(0).getFirstPlayerHand() == game.getPlayers().get(1).getSecondPlayerHand());
		assertTrue(game.getPlayers().get(2).getFirstPlayerHand() == game.getPlayers().get(3).getSecondPlayerHand());
		assertTrue(game.getPlayers().get(0).getFirstPlayerHand() == game.getPlayers().get(2).getSecondPlayerHand());
	}
	
	public void testViewingTable() {
		assertTrue(game.getPlayers().get(0).getTable().equals(game.getPlayers().get(1).getTable()));
		assertTrue(game.getPlayers().get(2).getTable().equals(game.getPlayers().get(3).getTable()));
		assertTrue(game.getPlayers().get(0).getTable().equals(game.getPlayers().get(2).getTable()));		
	}
	
	
	
}
