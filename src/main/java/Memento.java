
public class Memento {
   private Table table = new Table();
   private PlayerHand human = new PlayerHand("human");

   public Memento(GameMaster g){
     for(int i =0; i < g.getHuman().getHand().sizeOfHand();i++) {
    	 human.addTileToHand(g.getHuman().getHand().getTile(i));
     }
     for(int i =0; i < g.getTable().getTable().size();i++) {
    	 table.AiAddTiles(g.getTable().get(i));
     }   
   }

   public Table getStateTable(){
      return table;
   }
   
   public PlayerHand getStateHumanHand(){
	      return human;
	  }
}