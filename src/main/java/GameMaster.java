import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;


//enum GameState {Game_Start, P1_Turn, P2_Turn, P3_Turn, P4_Turn, End_Game};

public class GameMaster extends Observable{
    
	
	private Player mainHuman;
	private Player humanTwo;
	private Player humanThree;
	private Player humanFour;
	private Player AI1;
	private Player AI2;
	private Player AI3;
	private Player AI4;
	private Deck deck;
	private Table table;
	private ArrayList<Player> players;
	private int IndexOfHuman;
	
	public GameMaster(){
		//create human, AI1, AI2, AI3, deck table GUI
		mainHuman = new Player("Human",0, new HumanPlayerStrategy());
		humanTwo = new Player("Human",1, new HumanPlayerStrategy());
		humanThree = new Player("Human",2, new HumanPlayerStrategy());
		humanFour = new Player("Human",3, new HumanPlayerStrategy());
		AI1 = new Player("AI1",1, new p1());
		AI2 = new Player("AI2",2, new p2());
		AI3 = new Player("AI3",3, new p3());
		AI4 = new Player("AI4",4, new p4());
		players = new ArrayList<Player>();
		deck = new Deck();
		deck.Shuffle();
		table = new Table();
		players.add(mainHuman);
		//Add human  and AIs to Observable
		this.addObserver(mainHuman);
		
	}
	
	public Memento saveData() {
		return new Memento(this);
	}
	
	
	public String deal() {
		Deck  dd = new Deck();
		dd.Shuffle();
		//randomly pick a tile
		for(int i =0; i < players.size();i++) {
			players.get(i).getHand().drawFirst14(dd);
			System.out.println(players.get(i).getName()+ " player randomly pick up the tile "+ players.get(i).getHand().getTile(0));
		}
		// sort players turn
		Collections.sort(players, new SortToFindTileBreak());	
		String tile_break = "So the order players should play their tie-break is: ";
		for(int i =0; i < players.size();i++) {
			if(i == 0 )players.get(i).set_TileBreak(null);
			else players.get(i).set_TileBreak(players.get(i-1));
			tile_break += players.get(i).getName() + " ";
		}
		System.out.println(tile_break + "\n");
		System.out.println("--------------------------------------------\n");
		
		Deck d = new Deck();
		d.Shuffle();
		
		String consoleOutput = "Deciding turn order\n";
		
		//randomly pick a tile
		for(int i =0; i < players.size();i++) {
			players.get(i).getHand().clear();
			players.get(i).getHand().drawFirst14(d);
			System.out.println(players.get(i).getName()+ " player randomly pick up the tile "+ players.get(i).getHand().getTile(0));
			consoleOutput += players.get(i).getName() + " drew " + players.get(i).getHand().getTile(0).getNumber() + ", ";
		}
		consoleOutput += "\n";
		
		
		
		// sort players turn
		Collections.sort(players, new SortByCommand());	
		String order= "So the order of the game is : ";
		//deal tiles for each player
		for(int i =0; i < players.size();i++) 
		{
			order += players.get(i).getName() + ", ";
			players.get(i).getHand().clear();
			if(players.get(i).getName().equals("Human")) {
				IndexOfHuman = i;
				players.get(i).setCanPlay();
			}
			players.get(i).getHand().drawFirst14(deck);
		}
		System.out.println(order);
		order+= "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		return consoleOutput+order;
	}
	public int getHumanPosition() {return IndexOfHuman;}
	
	public void addPlayer(int a) {
		
		if(a == 1) {
			players.add(AI1);
			addObserver(AI1);}
		else if(a == 2) {
			players.add(AI2);
			addObserver(AI2);}
		else if(a == 3) {
			players.add(AI3);
			addObserver(AI3);}
		else if(a == 4) {
			players.add(AI4);
			addObserver(AI4);}
		else if(a == 5) {
			players.add(humanTwo);
			addObserver(humanTwo);}
		else if(a == 6) {
			players.add(humanThree);
			addObserver(humanThree);}
		else if(a == 7) {
			players.add(humanFour);
			addObserver(humanFour);}
	}
	
	// main deck
	public Deck getDeck() {return deck;}
	
	//get human and AIs to update data to each player
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public Table getTable() 
	{
		return table;
	}
	
	public void setTable(Table newTable) 
	{
		table = newTable;
	}
	
	public Player getHuman() {
		return mainHuman;
	}
	
	public Player getAI() {
		return AI1;
	}
	
	public Player getAI2() {
		return AI2;
	}
	
	public Player getAI3() {
		return AI3;
	}
	public Player getAI4() {
		return AI4;
	}
	
	


	//draw card to human and AI
	public int[] dealInitialHand(int[] ai) 
	{
		Arrays.sort(ai);
		
		int[][] turnOrder = new int[ai.length][2];
		Deck turnDeck = new Deck();
		turnDeck.Shuffle();
		
		for(int x=1;x<ai.length;x++)
		{
			if(ai[x]==1)
			{
				//System.out.println("You picked AI 1");
				
				addPlayer(1);
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 1;
				
				AI1.getHand().drawFirst14(deck);
				AI1.getHand().sortTilesByColour();
				AI1.getHand().HandReader();
			}
			else if(ai[x]==2)
			{
				//System.out.println("You picked AI 2");
				
				addPlayer(2);
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 2;
				
				AI2.getHand().drawFirst14(deck);
				AI2.getHand().sortTilesByColour();
				AI2.getHand().HandReader();
			}
			else if(ai[x]==3)
			{
				//System.out.println("You picked AI 3");
				
				addPlayer(3);
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 3;
				
				AI3.getHand().drawFirst14(deck);
				AI3.getHand().sortTilesByColour();
				AI3.getHand().HandReader();
			}
			else if(ai[x]==4)
			{
				//System.out.println("You picked AI 4");
				
				addPlayer(4);
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 4;
				
				AI4.getHand().drawFirst14(deck);
				AI4.getHand().sortTilesByColour();
				AI4.getHand().HandReader();
			}
		}
		
		turnOrder[0][0] = turnDeck.Draw().getNumber();

		mainHuman.getHand().drawFirst14(deck);
		mainHuman.getHand().sortTilesByColour();
		mainHuman.getHand().HandReader();
		mainHuman.setIsTurn(true);
		
		boolean doubles = true;
		int xOne = -1;
		int xTwo = -1;
		
		do
		{
			if(xOne != -1)
			{
				turnOrder[xOne][0] = turnDeck.Draw().getNumber();
				turnOrder[xTwo][0] = turnDeck.Draw().getNumber();
			}
			
			Arrays.sort(turnOrder, new Comparator<int[]>() 
			{
			    public int compare(int[] a, int[] b) 
			    {
			        return Integer.compare(b[0], a[0]);
			    }
			});
			
			int findDoubles = turnOrder[0][0];
			
			doubles = false;
			
			for(int x=1;x<turnOrder.length;x++)
			{
				if(findDoubles == turnOrder[x][0])
				{
					doubles = true;
					xOne = x-1;
					xTwo = x;
				}
				else
				{
					findDoubles = turnOrder[x][0];
				}
			}
			
		}while(doubles);
		
		int[] temp = new int[ai.length];
		int[] returnOrder = new int[ai.length];
		
		
		for(int x=0;x<temp.length;x++)
		{
			for(int y=0;y<ai.length;y++)
			{
				if(temp[x]==ai[y])
				{
					returnOrder[x] = y;
				}
			}
		}
		
		Announcement();
		
		return returnOrder;
	}
	
	
	
	
	// send notification to observers
	public void Announcement(){
		setChanged();
		notifyObservers();
	}
	public void Announcement(Player human){
		setChanged();
		notifyObservers(human);
	}

	class SortByCommand implements Comparator<Player> 
	{ 	
	    public int compare(Player a, Player b) 
	    { int i =0;
	    	while(b.getHand().getTile(i).getNumber() - a.getHand().getTile(i).getNumber() == 0){
	    		i++;
	    		if(b.getHand().getTile(i).getNumber() - a.getHand().getTile(i).getNumber() != 0)
	    		{
	    			return b.getHand().getTile(i).getNumber() -a.getHand().getTile(i).getNumber();}
	    		
	    	}   	
	    	return b.getHand().getTile(i).getNumber() - a.getHand().getTile(i).getNumber();
	    }
	}
	
	class SortToFindTileBreak implements Comparator<Player> 
	{ 	
	    public int compare(Player a, Player b) 
	    { int i =0;
	    	while(b.getHand().getTile(i).getNumber() - a.getHand().getTile(i).getNumber() == 0){
	    		i++;
	    		if(b.getHand().getTile(i).getNumber() - a.getHand().getTile(i).getNumber() != 0)
	    		{
	    			return b.getHand().getTile(i).getNumber() -a.getHand().getTile(i).getNumber();}
	    		
	    	}   	
	    	return b.getHand().getTile(i).getNumber() - a.getHand().getTile(i).getNumber();
	    }
	}
	
}
