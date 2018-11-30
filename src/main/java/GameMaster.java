import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;


//enum GameState {Game_Start, P1_Turn, P2_Turn, P3_Turn, P4_Turn, End_Game};

public class GameMaster extends Observable{
    
	
	private Player human;
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
		human = new Player("Human",1, new HumanPlayerStrategy());
		AI1 = new Player("AI1",1, new p1());
		AI2 = new Player("AI2",2, new p2());
		AI3 = new Player("AI3",3, new p3());
		AI4 = new Player("AI4",4, new p4());
		players = new ArrayList<Player>();
		deck = new Deck();
		deck.Shuffle();
		table = new Table();
		players.add(human);
		//Add human  and AIs to Observable
		this.addObserver(human);
		
	}
	
	public Memento saveData() {
		return new Memento(this);
	}
	public void deal() {
		Deck  d = new Deck();
		d.Shuffle();
		for(int i =0; i < players.size();i++) {
			players.get(i).getHand().drawFirst14(d);
		}
		Collections.sort(players, new SortByCommand());		
		
		
		for(int i =0; i < players.size();i++) 
		{
			players.get(i).getHand().clear();
			if(players.get(i).getName().equals("Human"))
			{
				IndexOfHuman = i;
				
				/*
				for(int x=1;x<13;x++)
				{
					players.get(i).getHand().addTileToHand(new Tile(1, x));
				}
				
				players.get(i).getHand().addTileToHand(new Tile(2, 1));
				players.get(i).getHand().addTileToHand(new Tile(14, 14));
				*/
				
				players.get(i).getHand().drawFirst14(deck);
			}
			else
			{
				players.get(i).getHand().drawFirst14(deck);
			}
		}
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
	}
	
	// main deck
	public Deck getDeck() {return deck;}
	
	//get human and AIs to update data to each player
	public ArrayList<Player> getPlayers(){
		return players;}
	
	public Table getTable() 
	{
		return table;
	}
	
	public void setTable(Table newTable) 
	{
		table = newTable;
	}
	
	public Player getHuman() {
		return human;
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
	
	
	public void AI_play() {
		human.setIsTurn(false);
		AI1.setIsTurn(true);
		System.out.println("AI1 REPORT:  ");
		//AI1.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI1.getHand().sizeOfHand());
		if(AI1.play()) System.out.println("AI 1 decide to play");
		else {
			if(deck.getDeck().size() > 0)
				AI1.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 1 decide not to play");
		}
		
		AI3.getHand().sortTilesByColour();
		AI1.setIsTurn(false);
		AI2.setIsTurn(true);
		Announcement();
		System.out.println("Number of tiles are: " + AI1.getHand().sizeOfHand());
		
		System.out.println("------------------------------");
		


		System.out.println("AI2 REPORT:  ");
		//AI2.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI2.getHand().sizeOfHand());
		if(AI2.play()) System.out.println("AI 2 decide to play");
		else {
			if(deck.getDeck().size() > 0) AI2.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 2 decide not to play");}
		AI2.getHand().sortTilesByColour();
		AI2.setIsTurn(false);
		AI3.setIsTurn(true);
		Announcement();
		System.out.println("Number of tiles are: " + AI2.getHand().sizeOfHand());
		
		System.out.println("------------------------------");
		


		System.out.println("AI3 REPORT:  ");
		//AI3.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI3.getHand().sizeOfHand());
		if(AI3.play()) System.out.println("AI 3 decide to play");
		else {
			if(deck.getDeck().size() > 0) AI3.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 3 decide not to play");}
		AI3.getHand().sortTilesByColour();
		AI3.setIsTurn(false);
		human.setIsTurn(true);
		Announcement();
		System.out.println("Number of tiles are: " + AI3.getHand().sizeOfHand() + "\n");
		
		
		
		System.out.println("AI4 REPORT:  ");
		//AI3.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI4.getHand().sizeOfHand());
		if(AI4.play()) System.out.println("AI 4 decide to play");
		else {
			if(deck.getDeck().size() > 0) AI4.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 4 decide not to play");}
		AI4.getHand().sortTilesByColour();
		AI4.setIsTurn(false);
		human.setIsTurn(true);
		Announcement();
		System.out.println("Number of tiles are: " + AI4.getHand().sizeOfHand() + "\n");
		

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

		human.getHand().drawFirst14(deck);
		human.getHand().sortTilesByColour();
		human.getHand().HandReader();
		human.setIsTurn(true);
		
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
		
		/*
		System.out.println("\nGM Class");
		for(int x=0;x<turnOrder.length;x++)
		{
			temp[x] = turnOrder[x][1];
			System.out.println(temp[x]);
		}
		*/
		
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
	
	
	public int[] turnOrderAI(int[] ai) 
	{
		Arrays.sort(ai);
		
		int[][] turnOrder = new int[ai.length][2];
		Deck turnDeck = new Deck();
		turnDeck.Shuffle();
		
		for(int x=0;x<ai.length;x++)
		{
			if(ai[x]==1)
			{
				//System.out.println("You picked AI 1");
				
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
			
				AI1.getHand().sortTilesByColour();
				AI1.getHand().HandReader();
			}
			else if(ai[x]==2)
			{
				//System.out.println("You picked AI 2");
				
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
				
				AI2.getHand().sortTilesByColour();
				AI2.getHand().HandReader();
			}
			else if(ai[x]==3)
			{
				//System.out.println("You picked AI 3");
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
				
				AI3.getHand().sortTilesByColour();
				AI3.getHand().HandReader();
			}
			else if(ai[x]==4)
			{
				//System.out.println("You picked AI 4");
				
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
				
				AI4.getHand().sortTilesByColour();
				AI4.getHand().HandReader();
			}
		}
				
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
		
		System.out.println("\nGM Class");
		for(int x=0;x<turnOrder.length;x++)
		{
			temp[x] = turnOrder[x][1];
			System.out.println(temp[x]);
		}
		
		
		for(int x=0;x<temp.length;x++)
		{
			for(int y=0;y<ai.length;y++)
			{
				if(temp[x]==ai[y])
				{
					returnOrder[x] = y+1;
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
	    			return b.getHand().getTile(i).getNumber() -a.getHand().getTile(i).getNumber();
	    	}
	    	System.out.println("hello");
	    	return b.getHand().getTile(i).getNumber() - a.getHand().getTile(i).getNumber();
	    }
	}
	
}
