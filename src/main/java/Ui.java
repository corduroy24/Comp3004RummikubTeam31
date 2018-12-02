import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Ui extends Application
{
	Stage window;
	
	Scene mainMenuScene;
	Scene rummiScene;
	
	AnchorPane mainScreen;
	
	Button twoPlayer;
	Button threePlayer;
	Button fourPlayer;
	
	Button aiOne;
	Button aiTwo;
	Button aiThree;
	Button aiFour;
	
	Button endTurnButton;
	Button suggestions;
	
	Button scenarios;
	Button scenarioOne;
	Button scenarioTwo;
	Button scenarioThree;
	Button scenarioFour;
	
	boolean isTimerOn=false;
	Button timerButton;
	Timer timer;
	boolean details=false;
	Button[][] tableButtons = new Button[20][7];
	//Boolean[][] isTableSet = new Boolean[20][7];
	
	ArrayList<Button> playerHandButtons = new ArrayList<Button>();
	
	TextArea console;
	TextArea scoreConsole;
	
	HBox playerHand;
	
	ImageView mainImageNode;
	ImageView secondImageNode;
	
	Boolean played = false;
	
	String prevString = "";
	boolean OutOfTime = false;
	int playerScore = 0;
	int numPlayers = 0;
	int[] aiType;
	ArrayList<Integer> turnOrder = new ArrayList<Integer>();
	ArrayList<Integer> recentlyPlayed = new ArrayList<Integer>();
	
	private GameMaster game;
	private HandleJoker checkMeld;
	private Memento lastMove;
	private int turnOfHuman = 0;
	
	int timing = 120;
	Support functions= new Support();
	boolean validMove = true;
	
	public static void main(String [] args) 
	{		
		Application.launch(args);
		
	}
	
	public void start(Stage primaryStage) throws Exception 
	{
		game = new GameMaster();
		lastMove = new Memento(game);
		window = primaryStage;
		window.setTitle("Rummikub");
	
		InputStream mainImagePath = getClass().getResourceAsStream("TitleImage.png");
		Image mainImage = new Image(mainImagePath);
		mainImageNode = new ImageView(mainImage);
		mainImageNode.setX(310);
		mainImageNode.setY(100);
		
		//Sets up the 2 player Button
		twoPlayer = new Button();
		twoPlayer.setText("2 Players");
		twoPlayer.setMinSize(100, 50);
		twoPlayer.setDisable(false);
		twoPlayer.setLayoutX(390);
		twoPlayer.setLayoutY(500);
		twoPlayer.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//System.out.println("Pressed 2 player button");
		    	clearMainScreen();
		    	whoGoesFirst(2);
		    }
		});
		
		//Sets up the 3 player Button
		threePlayer = new Button();
		threePlayer.setText("3 Players");
		threePlayer.setMinSize(100, 50);
		threePlayer.setDisable(false);
		threePlayer.setLayoutX(500);
		threePlayer.setLayoutY(500);
		threePlayer.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//System.out.println("Pressed 3 player button");
		    	clearMainScreen();
		    	whoGoesFirst(3);
		    }
		});
		
		//Sets up the 4 player Button
		fourPlayer = new Button();
		fourPlayer.setText("4 Players");
		fourPlayer.setMinSize(100, 50);
		fourPlayer.setDisable(false);
		fourPlayer.setLayoutX(610);
		fourPlayer.setLayoutY(500);
		fourPlayer.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//System.out.println("Pressed 4 player button");
		    	clearMainScreen();
		    	whoGoesFirst(4);
		    }
		});
		
		//Sets up Scenario Button
		scenarios = new Button();
		scenarios.setText("Scenarios");
		scenarios.setMinSize(100, 50);
		scenarios.setDisable(false);
		scenarios.setLayoutX(445);
		scenarios.setLayoutY(600);
		scenarios.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	scenariosList();
		    }
		});

		//Sets up the timer button
		timerButton = new Button();
		timerButton.setText("Timer On/Off");
		timerButton.setMinSize(100, 50);
		timerButton.setDisable(false);
		timerButton.setLayoutX(555);
		timerButton.setLayoutY(600);
		timerButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//clearMainScreen();
		    	if (isTimerOn==false) {
		    	isTimerOn=true;
		    	System.out.println("Timer turned on");
		    	}
		    	else {
		    		isTimerOn=false;
		    		System.out.println("Timer turned off");
		    	}
		    
		    }
		});
		
		mainScreen = new AnchorPane(mainImageNode, twoPlayer, threePlayer, fourPlayer, scenarios,timerButton);

		mainScreen.setMinSize(1095,	790);
		
		mainMenuScene = new Scene(mainScreen);
		
		InputStream iconImagePath = getClass().getResourceAsStream("iconImage.png");
		Image iconImage = new Image(iconImagePath);
		
		window.getIcons().add(iconImage);
		window.setScene(mainMenuScene);
		window.show();
		
		
	}
	
	//turnOrder goes by ai numbers and the player is listed as 10
	public void mainGame()
	{
		turnOfHuman = game.getHumanPosition();
		lastMove= new Memento(game);
		if (isTimerOn==true) {
			class Time extends TimerTask{
				@Override
				public void run() {
					System.out.println("Timing left for human: " + timing--);
					if(timing == 0) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								updateTableAndHand();
								System.out.println("OUT OF TIME");
						    	if(game.getDeck().getDeck().size() > 0)
					    			drawTile();
						    	updateHand();
						    	lastMove = new Memento(game);
						    	game.Announcement();
						    	String report = "";
						    	for(int i = turnOfHuman; i < game.getPlayers().size();i++) 
						    	{
					    			game.getPlayers().get(i).getHand().sortTilesByColour();
						    		if(!game.getPlayers().get(i).getName().equals("Human") && game.getPlayers().get(i).play()) 
						    		{
						    			report += game.getPlayers().get(i).getName() + " played: ";
						    			report += game.getPlayers().get(i).return_report();
						    		}
						    		else if (!game.getPlayers().get(i).getName().equals("Human") && game.getDeck().getDeck().size() > 0) 
						    		{
							    		Tile t= game.getDeck().Draw();
							    		report += game.getPlayers().get(i).getName() + " drew: ";
							    		game.getPlayers().get(i).getHand().addTileToHand(t);
							    		report += t.toString() + "\n";
							    	}
						    		game.Announcement();
						    		lastMove = new Memento(game);
						    		prevString = report;
						    		console.setText(console.getText() + prevString);  
						    	}
						    	AisPlay(turnOfHuman,report);
						    	updateTableAndHand();
						    	updateTable();
						    	updateHand();	
							}
						});
						timing = 120;
					}
				}
			}
			
		timer = new Timer();
		timer.scheduleAtFixedRate(new Time(), 0, 1000);
		}
		
		//The layoutPane for the gridded table in the center
		TilePane tablePane = new TilePane();
		tablePane.setPrefRows(7); //Sets the row length to 7
		tablePane.setPrefColumns(20); //Sets the column length to 20
		tablePane.setMaxSize(1100, 1000); //Makes the resize not squish the table
		tablePane.setHgap(5);
		tablePane.setVgap(5);
		ObservableList<Node> list = tablePane.getChildren();
		
		//Adds all of the table buttons onto the table
		for(int x=0;x<tableButtons[0].length;x++)
		{
			for(int y=0;y<tableButtons.length;y++)
			{
				tableButtons[y][x] = new Button();
				tableButtons[y][x].setText(" ");
				tableButtons[y][x].setMinSize(50, 80);
				list.addAll(tableButtons[y][x]);
				
				final int numberX = x;
				final int numberY = y;
				//Shows the button as droppable if it isn't from itself
				tableButtons[y][x].setOnDragOver(new EventHandler<DragEvent>() 
				{
				    public void handle(DragEvent event) 
				    {
				        if (event.getGestureSource() != tableButtons[numberY][numberX] && tableButtons[numberY][numberX].getStyle().length() < 1) 
				        {
				            //Sets up the copy of color and number
				            event.acceptTransferModes(TransferMode.ANY);
				        }
				        
				        event.consume();
				    }
				});
				
				//Turns the table button into the button that was dropped on it
				tableButtons[y][x].setOnDragDropped(new EventHandler<DragEvent>()
				{
				    public void handle(DragEvent event) 
				    {
				    	played = true;
				        Dragboard db = event.getDragboard();
				        boolean successText = false;
				        boolean successColor = false;
				        
				        if(db.getRtf().length()==3)
                        {
                            Tile temp = new Tile(Integer.parseInt(db.getRtf().substring(0, 1)), Integer.parseInt(db.getRtf().substring(2, 3)));
                            game.getHuman().getHand().removeTile(temp);
                            playerScore+=temp.getNumber();
                        }
				        else if(db.getRtf().length()==5)
				        {
				        	Tile temp = new Tile(Integer.parseInt(db.getRtf().substring(0, 2)), Integer.parseInt(db.getRtf().substring(3, 5)));
                            game.getHuman().getHand().removeTile(temp);
                            playerScore+=temp.getNumber();
				        }
                        else
                        {
                            Tile temp = new Tile(Integer.parseInt(db.getRtf().substring(0, 1)), Integer.parseInt(db.getRtf().substring(2, 4)));
                            game.getHuman().getHand().removeTile(temp);
                            playerScore+=temp.getNumber();
                        }
                        
                        updateHand();
				        
				        //Add the text to the button
				        if (db.hasString()) 
				        {
				        	tableButtons[numberY][numberX].setText(db.getString());
				        	successText = true;
				        }
				        
				        //Add the color to the button
				        if (db.hasUrl()) 
				        {
				        	tableButtons[numberY][numberX].setStyle(db.getUrl());
				        	successColor = true;
				        }
				        
				        //Clears the tile it came from if it was moved from the table
				        if(db.hasHtml())
				        {
				        	String coordinates = db.getHtml();
				        	String[] temp = coordinates.split(",");
				        	
				        	clearTile(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
				        }
				        
				        game.getTable().addTableCounter();
				        
				        event.setDropCompleted(successText && successColor);
				        event.consume();
				     }
				});
				
				//Lights up the button green if it is possible to drop the tile here
				tableButtons[y][x].setOnDragEntered(new EventHandler<DragEvent>() 
				{
				    public void handle(DragEvent event) 
				    {
				    	//System.out.println(tableButtons[numberY][numberX].getStyle().length());
				    	
						if (event.getGestureSource() != tableButtons[numberY][numberX] && tableButtons[numberY][numberX].getStyle().length() < 1) 
						{
							DropShadow dropShadow = new DropShadow();
							dropShadow.setRadius(10.0);
							dropShadow.setColor(Color.GREEN);
							tableButtons[numberY][numberX].setEffect(dropShadow);
						}
						else
						{
							DropShadow dropShadow = new DropShadow();
							dropShadow.setRadius(10.0);
							dropShadow.setColor(Color.RED);
							tableButtons[numberY][numberX].setEffect(dropShadow);
						}
						
						event.consume();
				    }
				});
				
				//Changes the color back to normal when you stop hovering on the button
				tableButtons[y][x].setOnDragExited(new EventHandler<DragEvent>() 
				{
				    public void handle(DragEvent event) 
				    {
				    	tableButtons[numberY][numberX].setEffect(null);

				        event.consume();
				    }
				});
				
				//Makes the table button draggable when the player drops a tile onto it
				tableButtons[y][x].setOnDragDetected(new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event) 
					{
						if(game.getHuman().getIsFirstMeldComplete())
						{
							if(tableButtons[numberY][numberX].getStyle().length() > 1) 
							{
								Dragboard db = tableButtons[numberY][numberX].startDragAndDrop(TransferMode.ANY);
								
								String temp = "";
						        
						        if(tableButtons[numberY][numberX].getStyle().equals("-fx-background-color: #db4c4c"))
								{
						        	temp = "1";
								}
								else if(tableButtons[numberY][numberX].getStyle().equals("-fx-background-color: #3888d8"))
								{
									temp = "2";
								}
								else if(tableButtons[numberY][numberX].getStyle().equals("-fx-background-color: #1a9922"))
								{
									temp = "3";
								}
								else if(tableButtons[numberY][numberX].getStyle().equals("-fx-background-color: #c69033"))
								{
									temp = "4";
								}
								else if(tableButtons[numberY][numberX].getStyle().equals("-fx-background-color: #474747"))
								{
									temp = "5";
								}
						        
						        temp = ""+temp+"|"+tableButtons[numberY][numberX].getText();
						        
						        ClipboardContent content = new ClipboardContent();
						        content.putString(tableButtons[numberY][numberX].getText());
						        content.putUrl(tableButtons[numberY][numberX].getStyle());
						        content.putRtf(temp);
						        
						        String coordinates = ""+numberX+","+numberY;
						        content.putHtml(coordinates);
						        
						        db.setContent(content);
							}
					        
					        event.consume();
						}
					}
				});
			}
		}
		
		//The layoutPane for the players hand at the bottom
		playerHand = new HBox(5);
		playerHand.setPadding(new Insets(10)); //Sets the spacing between the cards
		playerHand.setAlignment(Pos.BASELINE_CENTER); //Centers the card in the bottom middle
		
		//System.out.println("Size of hand: "+game.getHuman().getHand().sizeOfHand());
		
		//Adds the starting cards to the players hand
		for(int x=0;x<game.getHuman().getHand().sizeOfHand();x++)
		{
			playerHandButtons.add(new Button());
			//playerHandButtons[x].setText("x: "+x);
			playerHandButtons.get(x).setMinSize(50, 80);
			
			final int number = x;
			//Copies the color and text to a clipboard to be carried over and allows you to start dragging it
			playerHandButtons.get(x).setOnDragDetected(new EventHandler<MouseEvent>() 
			{
			    public void handle(MouseEvent event) 
			    {
			        Dragboard db = playerHandButtons.get(number).startDragAndDrop(TransferMode.ANY);
			        String temp = "";
			        
			        if(playerHandButtons.get(number).getStyle().equals("-fx-background-color: #db4c4c"))
					{
			        	temp = "1";
					}
					else if(playerHandButtons.get(number).getStyle().equals("-fx-background-color: #3888d8"))
					{
						temp = "2";
					}
					else if(playerHandButtons.get(number).getStyle().equals("-fx-background-color: #1a9922"))
					{
						temp = "3";
					}
					else if(playerHandButtons.get(number).getStyle().equals("-fx-background-color: #c69033"))
					{
						temp = "4";
					}
					else if(playerHandButtons.get(number).getStyle().equals("-fx-background-color: #474747"))
					{
						temp = "14";
					}
			        
			        
			        if(playerHandButtons.get(number).getText().equals("J"))
			        {
			        	recentlyPlayed.add(14);
			        	temp = ""+temp+"|14";
			        }
			        else
			        {
			        	recentlyPlayed.add(Integer.parseInt(playerHandButtons.get(number).getText()));
			        	temp = ""+temp+"|"+playerHandButtons.get(number).getText();
			        }
			        
			        
			        ClipboardContent content = new ClipboardContent();
			        content.putString(playerHandButtons.get(number).getText());
			        content.putUrl(playerHandButtons.get(number).getStyle());
			        content.putRtf(temp);
			        db.setContent(content);
			        
			        event.consume();
			    }
			});
		}
		
		setPlayerHand();

		for(int x=0;x<playerHandButtons.size();x++)
		{
			playerHand.getChildren().add(playerHandButtons.get(x));
		}
		
		//Creates the text console where it will ouput any necessary info
		console = new TextArea();
		console.setText("");
		console.setEditable(false); //Makes it not editable
		console.setMinSize(795, 100); //sets the size of the box
		console.setMaxSize(795, 100); //sets the size of the box
		
		//Creates the text console where it will ouput The score
		scoreConsole = new TextArea();
		scoreConsole.setText("Score: 0");
		scoreConsole.setEditable(false); //Makes it not editable
		scoreConsole.setMinSize(300, 100); //sets the size of the box
		scoreConsole.setMaxSize(300, 100); //sets the size of the box
		
		//Sets up the End Turn Button
		endTurnButton = new Button();
		endTurnButton.setText("End Turn");
		endTurnButton.setMinSize(100, 55);
		endTurnButton.setDisable(false);
		endTurnButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	for(int x=0;x<recentlyPlayed.size();x++)
		    	{
		    		System.out.println(recentlyPlayed.get(x));
		    	}
		    	
		    	console.setText(console.getText() + "---------------------------- \n");
		    	
		    	for(int x=0;x<playerHandButtons.size();x++)
		    	{
		    		playerHandButtons.get(x).setEffect(null);
		    	}
		    	
		    	//Change to send a message that players turn has ended
		    	//boolean hasWinner = false;
		    	checkMeld = new HandleJoker();
		    	boolean valid = true;
		    	ArrayList<ArrayList<Tile>> currentTable = current_table();
		    	ArrayList<Tile> xList = new ArrayList<Tile>();
		    	//System.out.println(currentTable);
		    	myloop:for (int i=0;i<currentTable.size();i++) {
		    		if(currentTable.get(i).size() < 3) {valid = false; break myloop;}
		    		for (int j=0;j<currentTable.get(i).size();j++) {
		    			if (currentTable.get(i).get(j).getColor().equals("J")) {
		    				currentTable.get(i).get(j).setJoker(true);
		    				if (j==0) {
		    					if (currentTable.get(i).get(j+1).getColor().equals((currentTable.get(i).get(j+2).getColor()))){
		    						currentTable.get(i).get(j).setJokerColor(currentTable.get(i).get(j+1).getColor());
		    						currentTable.get(i).get(j).setJokerPoint (currentTable.get(i).get(j+1).getNumber()-1);
		    					}
		    					else {
		    						if (currentTable.get(i).get(j+1).getNumber()==(currentTable.get(i).get(j+2).getNumber())){
		    							ArrayList<Tile> temp = new ArrayList<Tile>();
		    							temp = currentTable.get(i);
		    							temp.remove(0);
			    						xList=functions.getJokerSets(temp);
			    						currentTable.set(i, xList); //System.out.println(currentTable.get(i).get(j).getJokerPoint());
			    					}
		    					}
		    				}
		    				if (j==currentTable.get(i).size()-1) {
		    					if (currentTable.get(i).get(j-1).getColor().equals((currentTable.get(i).get(j-2).getColor()))){
		    						currentTable.get(i).get(j).setJokerColor(currentTable.get(i).get(j-1).getColor());
		    						currentTable.get(i).get(j).setJokerPoint (currentTable.get(i).get(j-1).getNumber()+1);
		    					}
		    					else {
		    						if (currentTable.get(i).get(j-1).getNumber()==(currentTable.get(i).get(j-2).getNumber())){
		    							ArrayList<Tile> temp = new ArrayList<Tile>();
		    							temp = currentTable.get(i);
		    							temp.remove(j);
			    						xList=functions.getJokerSets(temp);
			    						currentTable.set(i, xList); //System.out.println(currentTable.get(i).get(j).getJokerPoint());
			    					}
		    					}
		    				}
		    				
		    				
		    				if ((j!=currentTable.get(i).size()-1)&&(j!=0)) {
		    					if (currentTable.get(i).get(j-1).getColor().equals((currentTable.get(i).get(j+1).getColor()))){
		    						currentTable.get(i).get(j).setJokerColor(currentTable.get(i).get(j-1).getColor());
		    						currentTable.get(i).get(j).setJokerPoint (currentTable.get(i).get(j-1).getNumber()+1); 
		    						//System.out.println(currentTable.get(i).get(j).getJokerPoint());		
		    						}
		    					else {
		    						if (currentTable.get(i).get(j-1).getNumber()==(currentTable.get(i).get(j+1).getNumber())){
		    							ArrayList<Tile> temp = new ArrayList<Tile>();
		    							temp = currentTable.get(i);
		    							temp.remove(j);
			    						xList=functions.getJokerSets(temp);
			    						currentTable.set(i, xList); //System.out.println(currentTable.get(i).get(j).getJokerPoint());
			    					}
		    					}
		    				}
		    				//currentTable.get(i).get(j).setJokerColor(1);
		    				//currentTable.get(i).get(j).setJokerPoint(10);
		    			}
		    		}
		    	}
		    	System.out.println("REPORT TO USER: ---------------------");
		    	for(int i =0; i < currentTable.size();i++) {
		    		if(!checkMeld.isRun(currentTable.get(i)) && !checkMeld.isSet(currentTable.get(i))) {
		    			valid = false;
		    			playerScore = 0;
				    	System.out.println("YOUR MOVE IS INVALID\n");
				    	console.setText(console.getText() + "YOUR MOVE IS INVALID\n");
		    		}
		    	}


		    	if(!game.getHuman().getIsFirstMeldComplete()) {
		    		if(playerScore >= 30) { 
		    			valid = true;
		    		System.out.println("First play is success\n");
		    		}
		    		else {
		    			playerScore = 0;
		    			valid = false;
		    		System.out.println("PLAY YOUR FIRST INITIAL TURN ASAP\n");
		    		console.setText(console.getText() + "PLAY YOUR FIRST INITIAL TURN ASAP\n");
		    		}
		    	}
		    
		  
		    	if(valid) {
	    		int temp = 0;
		    	
		    	for(int x=0;x<recentlyPlayed.size();x++)
		    	{
		    		temp += recentlyPlayed.get(x);
		    	}
		    	
		    	scoreConsole.setText("Score: "+temp);
		    	
		    	game.getHuman().getTable().setTable(currentTable);
		    	game.getTable().setTable(game.getHuman().getTable().getTable());
		    	game.Announcement();
		    	lastMove = new Memento(game); 
		    	checkPlayerIsWinner();
		    	//console.clear()
		    	
		    	String report = "";
		    	for(int i = turnOfHuman; i < game.getPlayers().size();i++) 
		    	{
	    			game.getPlayers().get(i).getHand().sortTilesByColour();
		    		
		    		if(!game.getPlayers().get(i).getName().equals("Human") && game.getPlayers().get(i).play()) 
		    		{
		    			report += game.getPlayers().get(i).getName() + " played: ";
		    			report += game.getPlayers().get(i).return_report();
		    		}
		    		else if (!game.getPlayers().get(i).getName().equals("Human") && game.getDeck().getDeck().size() > 0) 
		    		{
			    		Tile t= game.getDeck().Draw();
			    		report += game.getPlayers().get(i).getName() + " drew: ";
			    		game.getPlayers().get(i).getHand().addTileToHand(t);
			    		report += t.toString() + "\n";
			    	}
		    		game.Announcement();
		    		lastMove = new Memento(game); 
		    	}
		    	timing = 120;
		    	prevString = report;
		    	console.setText(console.getText() + report);  
		    	updateTable();
		    	AisPlay(turnOfHuman,report);
		    	
		    	
		    	if(!played)
		    	{
		    		if(game.getDeck().getDeck().size() > 0)
		    			drawTile();
		    		else
		    			System.out.println("Out of tiles");
		    		
		    		updateHand();
		    	}
		    	else {
		    		game.getTable().addTableCounter();
		    		played=false;
		    	}
		    	checkAIIsWinner();
		    	
		    	if(playerScore>=30) 
		    	{
		    		game.getHuman().setIsfirstMeldComplete(true);
		    	}
		    	
		    	lightRecentlyPlayed();
		    	game.getTable().clearBool();
		    
		    }
		    else {
		    	updateTableAndHand();
		 
		    	if(game.getDeck().getDeck().size() > 0)
	    			drawTile();
		    	updateHand();
		    	game.Announcement();
		    	lastMove = new Memento(game);
		    	String report = "";
		    	for(int i = turnOfHuman; i < game.getPlayers().size();i++) 
		    	{
		    		report = "";
	    			game.getPlayers().get(i).getHand().sortTilesByColour();
		    		if(!game.getPlayers().get(i).getName().equals("Human") && game.getPlayers().get(i).play()) 
		    		{
		    			report += game.getPlayers().get(i).getName() + " played: ";
		    			report += game.getPlayers().get(i).return_report();
		    		}
		    		else if (!game.getPlayers().get(i).getName().equals("Human") && game.getDeck().getDeck().size() > 0) 
		    		{
			    		Tile t= game.getDeck().Draw();
			    		report += game.getPlayers().get(i).getName() + " drew: ";
			    		game.getPlayers().get(i).getHand().addTileToHand(t);
			    		report += t.toString() + "\n";
			    	}
		    		game.Announcement();
		    		lastMove = new Memento(game);  	
		    		prevString = report;
		    		console.setText(console.getText() + prevString);  
		    		prevString = "";
		    	}
		    	AisPlay(turnOfHuman,report);
		    	timing = 120;
		    	updateTable();
		    	updateHand();
		    	}
		    	recentlyPlayed.clear();
		    	console.end();
		    }
		});
		
		suggestions = new Button();
		suggestions.setText("Suggestion");
		suggestions.setMinSize(100, 55);
		suggestions.setDisable(false);
		suggestions.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	
		    	
		    	game.getHuman().play();
		    	ArrayList<Tile> suggestionTiles = game.getHuman().getSuggPlayList();
		    	
		    	for(int x=0;x<suggestionTiles.size();x++)
                {
                    if (suggestionTiles.get(x)==null) 
                    {
                    	Tile t = new Tile(14,14);
                    	suggestionTiles.set(x, t);
                    }
                }
		    	
		    	if (details==false) {
		    	System.out.println("Tiles Player should play are: ");
					
		    	for(int x=0;x<suggestionTiles.size();x++)
                {
                    System.out.println("color: "+suggestionTiles.get(x).getColor()+", Number: " +suggestionTiles.get(x).getNumber());
                }
		    	details=true;
		    	}
		    	else if (details==true) {
		    		details=false;
		    	}
		    	System.out.println("Press again to get details");
		    	
		    	
		    	for(int x=0;x<playerHandButtons.size();x++)
		    	{
		    		String color = "";
		    		
		    		if(playerHandButtons.get(x).getStyle().equals("-fx-background-color: #db4c4c"))
					{
						color = "R";
					}
					else if(playerHandButtons.get(x).getStyle().equals("-fx-background-color: #3888d8"))
					{
						color = "B";
					}
					else if(playerHandButtons.get(x).getStyle().equals("-fx-background-color: #1a9922"))
					{
						color = "G";
					}
					else if(playerHandButtons.get(x).getStyle().equals("-fx-background-color: #c69033"))
					{
						color = "O";
					} 
					else if(playerHandButtons.get(x).getStyle().equals("-fx-background-color: #474747"))
					{
						color = "J";
					}
		    		
		    		for(int y=0;y<suggestionTiles.size();y++)
		    		{
		    			if(suggestionTiles.get(y).getNumber() == 14 && playerHandButtons.get(x).getText().equals("J"))
		    			{
		    				DropShadow dropShadow = new DropShadow();
							dropShadow.setRadius(20.0);
							dropShadow.setColor(Color.PURPLE);
							playerHandButtons.get(x).setEffect(dropShadow);
							
							suggestionTiles.set(y, new Tile(5, 1));
		    			}
		    			else if(playerHandButtons.get(x).getText().equals("J"))
		    			{
		    				
		    			}
		    			else if(suggestionTiles.get(y).getNumber() == Integer.parseInt(playerHandButtons.get(x).getText()) && 
			    				color.equals(suggestionTiles.get(y).getColor()))
			    		{
			    			DropShadow dropShadow = new DropShadow();
							dropShadow.setRadius(20.0);
							dropShadow.setColor(Color.PURPLE);
							playerHandButtons.get(x).setEffect(dropShadow);
							
							suggestionTiles.set(y, new Tile(5, 1));
			    		}	
		    		}	
		    	} 
		    }
		});
		
		//Controls the button panel on the right of the player hand
		BorderPane controlSkeleton = new BorderPane();
		controlSkeleton.setTop(endTurnButton);
		controlSkeleton.setBottom(suggestions);
		
		//Creates the scroll pane to store the player hand tiles
		ScrollPane playerHandScroller = new ScrollPane();
		playerHandScroller.setContent(playerHand);
		playerHandScroller.setFitToWidth(true);
		playerHandScroller.setPrefHeight(110);
		playerHandScroller.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		//The layoutPane that seperated the Player's hand and the end turn button
		BorderPane bottomSkeleton = new BorderPane();
		bottomSkeleton.setCenter(playerHandScroller);
		bottomSkeleton.setRight(controlSkeleton);
		
		//Controls the top part of the screen with the consoles
		BorderPane topSkeleton = new BorderPane();
		topSkeleton.setLeft(console);
		topSkeleton.setRight(scoreConsole);
		
		//The layoutPane for the overarching skeleton (holds all the other layouts)
		BorderPane bigSkeleton = new BorderPane();
		bigSkeleton.setTop(topSkeleton); 
		bigSkeleton.setBottom(bottomSkeleton); 
		bigSkeleton.setCenter(tablePane); 
		
		rummiScene = new Scene(bigSkeleton);
		
		window.setScene(rummiScene);
		window.show();
	}
	
	public void setTurnOrder(int[] turns)
	{
		for(int x=0;x<turns.length;x++)
		{
			turnOrder.add(turns[x]);
		}
	}
	
	public ArrayList<Integer> getTurnOrder()
	{
		return turnOrder;
	}
	
	private boolean checkAIIsWinner() {
		Button OK_Button = new Button();
    	OK_Button.setText("OK");
    	OK_Button.setMinSize(100, 50);
    	OK_Button.setLayoutX(480);
    	OK_Button.setLayoutY(650);
    	OK_Button.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	window.close();
		    	
		    }
		});
    	
    	for(int i =0; i < game.getPlayers().size()-1;i++) {
    		if(game.getPlayers().get(i).getHand().sizeOfHand() == 0) {
    			InputStream mainImagePath = getClass().getResourceAsStream("Looser.jpg");
				Image mainImage = new Image(mainImagePath);
				mainImageNode = new ImageView(mainImage);
				mainImageNode.setX(200);
				mainImageNode.setY(200);
				AnchorPane mainScreen = new AnchorPane(mainImageNode,OK_Button);
				mainScreen.setMinSize(1095,	790);
				
				mainMenuScene = new Scene(mainScreen);
				window.setScene(mainMenuScene);
				window.show();
				System.out.println("YOU ARE THE LOOSER");
				return true;
    			
    		}
    	}
		return false;
	}
	
	private boolean checkPlayerIsWinner() {
		Button OK_Button = new Button();
    	OK_Button.setText("OK");
    	OK_Button.setMinSize(100, 50);
    	OK_Button.setLayoutX(480);
    	OK_Button.setLayoutY(650);
    	OK_Button.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	window.close();
		    	
		    }
		});
		if(game.getHuman().getHand().sizeOfHand() == 0) {
			InputStream mainImagePath = getClass().getResourceAsStream("Winner.jpg");
			Image mainImage = new Image(mainImagePath);
			mainImageNode = new ImageView(mainImage);
			mainImageNode.setX(200);
			mainImageNode.setY(200);
			AnchorPane mainScreen = new AnchorPane(mainImageNode,OK_Button);
			mainScreen.setMinSize(1095,	790);
			
			mainMenuScene = new Scene(mainScreen);
			window.setScene(mainMenuScene);
			window.show();
			System.out.println("YOU ARE THE WINNER");
		return true;	
		}
		return false;
	}
	
	private void lightRecentlyPlayed()
	{
		for(int x=0;x<20;x++)
		{
			for(int y=0;y<7;y++)
			{
				Boolean[][] isTableSet = game.getTable().getBool();
				
				if(isTableSet[x][y])
				{
					DropShadow dropShadow = new DropShadow();
					dropShadow.setRadius(10.0);
					dropShadow.setColor(Color.BLUE);
					tableButtons[x][y].setEffect(dropShadow);
				}
				else
				{
					tableButtons[x][y].setEffect(null);
				}
			}
		}
	}
	
	public void clearMainScreen()
	{
		mainScreen.getChildren().remove(mainImageNode);
		mainScreen.getChildren().remove(twoPlayer);
		mainScreen.getChildren().remove(threePlayer);
		mainScreen.getChildren().remove(fourPlayer);
		mainScreen.getChildren().remove(scenarios);
	}
	

	
	public void whoGoesFirst(final int maxPlayers)
	{
		aiType = new int[maxPlayers];
    	System.out.println(maxPlayers);
		//Sets up the AI 1 Button
		aiOne = new Button();
		aiOne.setText("AI 1");
		aiOne.setMinSize(100, 50);
		aiOne.setDisable(false);
		aiOne.setLayoutX(327);
		aiOne.setLayoutY(500);
		aiOne.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//System.out.println("Pressed ai 1 button");
		    	aiOne.setDisable(true);
		    	game.addPlayer(1);
		    	if(game.getPlayers().size() == maxPlayers) {
		    		game.deal();
		    		mainGame();
		    		AisPlay(turnOfHuman,prevString);
		    	}
		    }
		});
		
		//Sets up the AI 2 Button
		aiTwo = new Button();
		aiTwo.setText("AI 2");
		aiTwo.setMinSize(100, 50);
		aiTwo.setDisable(false);
		aiTwo.setLayoutX(437);
		aiTwo.setLayoutY(500);
		aiTwo.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//System.out.println("Pressed ai 2 button");
		    	aiTwo.setDisable(true);
		    	game.addPlayer(2);
		    	if(game.getPlayers().size() == maxPlayers) {
		    		game.deal();
		    		mainGame();
		    		AisPlay(turnOfHuman,prevString);
		    	}
		    }
		});
		
		//Sets up the AI 3 Button
		aiThree = new Button();
		aiThree.setText("AI 3");
		aiThree.setMinSize(100, 50);
		aiThree.setDisable(false);
		aiThree.setLayoutX(547);
		aiThree.setLayoutY(500);
		aiThree.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//System.out.println("Pressed ai 3 button");
		    	aiThree.setDisable(true);
		    	game.addPlayer(3);
		    	if(game.getPlayers().size() == maxPlayers) {
		    		game.deal();
		    		mainGame();
		    		AisPlay(turnOfHuman,prevString);
		    	}
		    }
		});
		
		//Sets up the AI 4 Button
		aiFour = new Button();
		aiFour.setText("AI 4");
		aiFour.setMinSize(100, 50);
		aiFour.setDisable(false);
		aiFour.setLayoutX(657);
		aiFour.setLayoutY(500);
		aiFour.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//System.out.println("Pressed ai 4 button");
		    	aiFour.setDisable(true);
		    	game.addPlayer(4);
		    	if(game.getPlayers().size() == maxPlayers) {
		    		game.deal();
		    		mainGame();	
		    		AisPlay(turnOfHuman,prevString);
		    	}
		    }
		});
		
		if(maxPlayers==2)
		{
			InputStream mainImagePath = getClass().getResourceAsStream("pickOne.png");
			Image mainImage = new Image(mainImagePath);
			mainImageNode = new ImageView(mainImage);
			mainImageNode.setX(442);
			mainImageNode.setY(100);
		}
		else if(maxPlayers==3)
		{
			InputStream mainImagePath = getClass().getResourceAsStream("pickTwo.png");
			Image mainImage = new Image(mainImagePath);
			mainImageNode = new ImageView(mainImage);
			mainImageNode.setX(442);
			mainImageNode.setY(100);
		}
		else if(maxPlayers==4)
		{
			InputStream mainImagePath = getClass().getResourceAsStream("pickThree.png");
			Image mainImage = new Image(mainImagePath);
			mainImageNode = new ImageView(mainImage);
			mainImageNode.setX(442);
			mainImageNode.setY(100);
		}
		else
		{
			System.out.print("Something went wrong in whoGoesFirst() in UI.java, maxPlayers = "+maxPlayers);
		}
		
		mainScreen.getChildren().add(aiOne);
		mainScreen.getChildren().add(aiTwo);
		mainScreen.getChildren().add(aiThree);
		mainScreen.getChildren().add(aiFour);
		mainScreen.getChildren().add(mainImageNode);
	}
	
	
	
	public void scenariosList()
	{
		//Sets up the Scenario 1 Button
		scenarioOne = new Button();
		scenarioOne.setText("Scenario 1");
		scenarioOne.setMinSize(100, 50);
		scenarioOne.setDisable(false);
		scenarioOne.setLayoutX(327);
		scenarioOne.setLayoutY(500);
		scenarioOne.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//partially working 
		    	
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	//still need to properly determine order of players 
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s1 = scenarioFactory.getScenario("s1");
		        game.Announcement();
		        game = s1.deal(game);
		    	setupGameRigging(); 

		    	game.getPlayers().remove(game.getHuman());
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//Have to make a seperate method to make turn order or just rig that as well
		    	//turnOrders = game.turnOrderAI(turnOrders); 
		    	playGameRigging(turnOrders);
		    }		    
		});
		
		//Sets up the Scenario 2 Button
		scenarioTwo = new Button();
		scenarioTwo.setText("Scenario 2");
		scenarioTwo.setMinSize(100, 50);
		scenarioTwo.setDisable(false);
		scenarioTwo.setLayoutX(437);
		scenarioTwo.setLayoutY(500);
		scenarioTwo.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	
		    }
		});
		
		//Sets up the Scenario 3 Button
		scenarioThree = new Button();
		scenarioThree.setText("Scenario 3");
		scenarioThree.setMinSize(100, 50);
		scenarioThree.setDisable(false);
		scenarioThree.setLayoutX(547);
		scenarioThree.setLayoutY(500);
		scenarioThree.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	
		    }
		});
		
		//Sets up the Scenario 4 Button
		scenarioFour = new Button();
		scenarioFour.setText("Scenario 4");
		scenarioFour.setMinSize(100, 50);
		scenarioFour.setDisable(false);
		scenarioFour.setLayoutX(657);
		scenarioFour.setLayoutY(500);
		scenarioFour.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	
		    }
		});
		
		InputStream mainImagePath = getClass().getResourceAsStream("pickOne.png");
		Image mainImage = new Image(mainImagePath);
		mainImageNode = new ImageView(mainImage);
		mainImageNode.setX(442);
		mainImageNode.setY(100);
		
		mainScreen.getChildren().add(scenarioOne);
		mainScreen.getChildren().add(scenarioTwo);
		mainScreen.getChildren().add(scenarioThree);
		mainScreen.getChildren().add(scenarioFour);
		mainScreen.getChildren().add(mainImageNode);
		
	}
	
	public void addPlayerTile(Tile tile)
	{
		//String color = tile.getColor();
		//int number = tile.getNumber();

		final int counter = playerHandButtons.size();
		
		playerHandButtons.add(new Button());
		playerHandButtons.get(counter).setMinSize(50, 80);

		//Copies the color and text to a clipboard to be carried over and allows you to start dragging it
		playerHandButtons.get(counter).setOnDragDetected(new EventHandler<MouseEvent>() 
		{
		    public void handle(MouseEvent event) 
		    {
		        Dragboard db = playerHandButtons.get(counter).startDragAndDrop(TransferMode.ANY);
		        String temp = "";
		        
		        if(playerHandButtons.get(counter).getStyle().equals("-fx-background-color: #db4c4c"))
				{
		        	temp = "1";
				}
				else if(playerHandButtons.get(counter).getStyle().equals("-fx-background-color: #3888d8"))
				{
					temp = "2";
				}
				else if(playerHandButtons.get(counter).getStyle().equals("-fx-background-color: #1a9922"))
				{
					temp = "3";
				}
				else if(playerHandButtons.get(counter).getStyle().equals("-fx-background-color: #c69033"))
				{
					temp = "4";
				}
				else if(playerHandButtons.get(counter).getStyle().equals("-fx-background-color: #474747"))
				{
					temp = "5";
				}
		        
		        temp = ""+temp+"|"+playerHandButtons.get(counter).getText();
		        
		        ClipboardContent content = new ClipboardContent();
		        content.putString(playerHandButtons.get(counter).getText());
		        content.putUrl(playerHandButtons.get(counter).getStyle());
		        content.putRtf(temp);
		        db.setContent(content);
		        
		        event.consume();
		    }
		});
	}
	
	public void setPlayerHand()
	{
		game.getHuman().getHand().sortTilesByColour();
		//System.out.println("Size of hand: "+game.getHuman().getHand().sizeOfHand());
		
		for(int x=0;x<game.getHuman().getHand().sizeOfHand();x++)
		{
			if(game.getHuman().getHand().getTile(x)!=null)
			{
				if(game.getHuman().getHand().getTile(x).getNumber() == 14)
				{
					playerHandButtons.get(x).setText("J");
				}
				else
				{
					playerHandButtons.get(x).setText(""+game.getHuman().getHand().getTile(x).getNumber());
				}
				
				//System.out.println("test"+game.getHuman().getHand().getTile(x).isJoker());
				
				if(game.getHuman().getHand().getTile(x).getColor().equals("R"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #db4c4c");
				}
				else if(game.getHuman().getHand().getTile(x).getColor().equals("B"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #3888d8");
				}
				else if(game.getHuman().getHand().getTile(x).getColor().equals("G"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #1a9922");
				}
				else if(game.getHuman().getHand().getTile(x).getColor().equals("O"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #c69033");
				} 
				else if(game.getHuman().getHand().getTile(x).isJoker())
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #474747");
				} 
				else
				{
					System.out.println("Something went wrong in setPlayerHand()");
					System.out.println("Color for tile "+x+" is set to "+game.getHuman().getHand().getTile(x).getColor());
				}
			}
		}
	}
	
	public boolean updateTable()
	{
		Table table = game.getTable();
		ArrayList<ArrayList<Tile>> t = table.getTable();
		for(int x=0;x<tableButtons[0].length;x++)
		{
			for(int y=0;y<tableButtons.length;y++)
			{
				tableButtons[y][x].setText(" ");
				tableButtons[y][x].setStyle(null);
				
			}
		}
		int x = 0;
		int y = 0;
		
		for(int i =0; i < t.size();i++) 
		{
			if(t.get(i).size() + y < 20) 
			{
				for(int u =0; u < t.get(i).size(); u++) 
				{
					String color = t.get(i).get(u).getColor();
					tableButtons[y][x].setText(""+t.get(i).get(u).getNumber());
					
					if(color.equals("R"))
						tableButtons[y][x].setStyle("-fx-background-color: #db4c4c");
					else if(color.equals("B"))
						tableButtons[y][x].setStyle("-fx-background-color: #3888d8");
					else if(color.equals("G"))
						tableButtons[y][x].setStyle("-fx-background-color: #1a9922");
					else if(color.equals("O"))
						tableButtons[y][x].setStyle("-fx-background-color: #c69033");
					else if(color.equals("J"))
						tableButtons[y][x].setStyle("-fx-background-color: #474747");
					y++;
				}
				y++;
			}
			else 
			{
				y = 0;
				x ++;
				for(int u =0; u < t.get(i).size(); u++) 
				{
					String color = t.get(i).get(u).getColor();
					tableButtons[y][x].setText(""+t.get(i).get(u).getNumber());
					
					if(color.equals("R"))
						tableButtons[y][x].setStyle("-fx-background-color: #db4c4c");
					else if(color.equals("B"))
						tableButtons[y][x].setStyle("-fx-background-color: #3888d8");
					else if(color.equals("G"))
						tableButtons[y][x].setStyle("-fx-background-color: #1a9922");
					else if(color.equals("O"))
						tableButtons[y][x].setStyle("-fx-background-color: #c69033");
					else if(color.equals("J"))
						tableButtons[y][x].setStyle("-fx-background-color: #474747");
					y++;
				}
				y++;
				
			}
		}
		return false;	
	}
	
	public void updateHand()
	{
		PlayerHand test = game.getHuman().getHand();
		playerHand.getChildren().clear();
		for(int x=0;x<game.getHuman().getHand().sizeOfHand();x++)
		{
			
			playerHandButtons.get(x).setText(""+test.getTile(x).getNumber());
			
			if(test.getTile(x).getColor().equals("R"))
			{
				playerHandButtons.get(x).setStyle("-fx-background-color: #db4c4c");
			}
			else if(test.getTile(x).getColor().equals("B"))
			{
				playerHandButtons.get(x).setStyle("-fx-background-color: #3888d8");
			}
			else if(test.getTile(x).getColor().equals("G"))
			{
				playerHandButtons.get(x).setStyle("-fx-background-color: #1a9922");
			}
			else if(test.getTile(x).getColor().equals("O"))
			{
				playerHandButtons.get(x).setStyle("-fx-background-color: #c69033");
			}
			else if(test.getTile(x).getColor().equals("J"))
			{
				playerHandButtons.get(x).setStyle("-fx-background-color: #474747");
			}
			else
			{
				System.out.println("Something went wrong in setPlayerHand()");
				System.out.println("Color for tile "+x+" is set to "+test.getTile(x).getColor());
			}
		}
		for(int x=0;x<game.getHuman().getHand().sizeOfHand();x++)
		{
			playerHand.getChildren().add(playerHandButtons.get(x));
		}
	}
	
	public void clearTile(int x, int y)
	{		
		tableButtons[y][x].setStyle("");
		tableButtons[y][x].setText("");
	}

	private ArrayList<ArrayList<Tile>> current_table(){
		ArrayList<ArrayList<Tile>> t = new ArrayList<ArrayList<Tile>>();
		int current_number = 0;
		int current_color = 0;
		ArrayList<Tile> meld = new ArrayList<Tile>();
		for(int x=0;x<7;x++){
			for(int y=0;y < 20;y++){
				if(!tableButtons[y][x].getText().equals("") && !tableButtons[y][x].getText().equals(" ")) 
				{		
					//System.out.println("Text: "+tableButtons[y][x].getText()+", Color: "+tableButtons[y][x].getStyle());
					
					if(tableButtons[y][x].getText().equals("14") || tableButtons[y][x].getText().equals("J"))
					{
						current_number = 14;
					}
					else
					{
						current_number = Integer.valueOf(tableButtons[y][x].getText());
					}
					
					current_color =  getColorFromStyle(tableButtons[y][x].getStyle());
				}
				
				if(y <= 19 && can_add(tableButtons[y][x].getText()) && can_add(tableButtons[y+1][x].getText())) 
				{ 
					meld.add(new Tile(current_color,current_number));
				}
				else if (can_add(tableButtons[y][x].getText()) && meld.size() > 0 ) 
				{
					meld.add(new Tile(current_color,current_number));
					t.add(meld);
					meld = new ArrayList<Tile>();
				}
				else if(can_add(tableButtons[y][x].getText())) {
					meld.add(new Tile(current_color,current_number));
					t.add(meld);
					meld = new ArrayList<Tile>();
				}
		
			}
		}
		return t;
	}
	
	public boolean can_add(String t) {
		if(t.equals(" ") || t.equals(""))
			return false;
		return true;
	}
	
	public int getColorFromStyle(String text) {
		if(text.contains("db4c4c"))
			return 1;
		else if(text.contains("3888d8"))
			return 2;
		else if(text.contains("1a9922"))
			return 3;
		else if(text.contains("474747"))
			return 5;
		else
			return 4;			
	}
	
	public void drawTile()
	{
		game.getHuman().getPlayedList();
		PlayerHand hand = game.getHuman().getHand();
		Tile tile = game.getDeck().Draw();
		
		hand.addTileToHand(tile);
		hand.sortTilesByColour();
		addPlayerTile(tile);
	}
	
	private void updateTableAndHand() 
	{
		game.getTable().setTable(lastMove.getStateTable().getTable());
		game.getHuman().getHand().setPlayerHand(lastMove.getStateHumanHand().getTiles());		    
	}
	
	public void setupGameRigging() {
		TilePane tablePane = new TilePane();
		tablePane.setPrefRows(7); //Sets the row length to 7
		tablePane.setPrefColumns(20); //Sets the column length to 20
		tablePane.setMaxSize(1100, 1000); //Makes the resize not squish the table
		tablePane.setHgap(5);
		tablePane.setVgap(5);
		ObservableList<Node> list = tablePane.getChildren();
		//Adds all of the table buttons onto the table
		for(int x=0;x<tableButtons[0].length;x++)
		{
			for(int y=0;y<tableButtons.length;y++)
			{
				tableButtons[y][x] = new Button();
				tableButtons[y][x].setText(" ");
				tableButtons[y][x].setMinSize(50, 80);
				list.addAll(tableButtons[y][x]);
				
				//Shows the button as droppable if it isn't from itself
			}
		}

		//Creates the text console where it will ouput any necessary info
		console = new TextArea();
		console.setText("");
		console.setEditable(false); //Makes it not editable
		console.setMinSize(1095, 100); //sets the size of the box
		console.setMaxSize(1095, 100); //sets the size of the box


		//The layoutPane that seperated the Player's hand and the end turn button
		BorderPane bottomSkeleton = new BorderPane();
		bottomSkeleton.setCenter(playerHand);
		bottomSkeleton.setRight(endTurnButton);

		//The layoutPane for the overarching skeleton (holds all the other layouts)
		BorderPane bigSkeleton = new BorderPane();
		bigSkeleton.setTop(console); 
		bigSkeleton.setBottom(bottomSkeleton); 
		bigSkeleton.setCenter(tablePane); 

		rummiScene = new Scene(bigSkeleton);
    	updateTable();

		window.setScene(rummiScene);
		window.show();
	}
	
	public void playGameRigging(int [] turnOrders) {
		setTurnOrder(turnOrders);
		
		System.out.println("\nUI Class");
		for(int x=0;x<turnOrders.length;x++)
		{
			System.out.println(turnOrders[x]);
		}
		System.out.print("\n");
		
		//int x=0;
		
		//while(turnOrders[x] != 0)
		for(int x = 0; x < 4; x++)
		{
			System.out.println("--------------------------- "+ turnOrders[x]);
			
	    	//Change to send a message that players turn has ended

	    	game.Announcement();
	    	//console.clear();

			game.getPlayers().get(turnOrders[x]-1).getHand().sortTilesByColour();
			
			if(game.getPlayers().get(turnOrders[x]-1).play()) 
			{
				prevString += game.getPlayers().get(turnOrders[x]-1).getName() +  " played: ";
		    	prevString += game.getPlayers().get(turnOrders[x]-1).return_report();
			}
			else if (game.getDeck().getDeck().size() > 0) 
			{
	    		//Tile t= game.getDeck().Draw();
	    		prevString += game.getPlayers().get(turnOrders[x]-1).getName() + " drew: ";
	    		//game.getPlayers().get(turnOrders[x]).getHand().addTileToHand(t);
	    		//prevString += t.toString() + "\n";
	    	}
			game.Announcement();
	    	
	    	console.setText(console.getText() + prevString);  
	    	prevString = "\n";
	    	this.updateTable();
	    	
	    		if(game.getPlayers().get(turnOrders[x]-1).getHand().sizeOfHand() == 0) 
	    			System.out.println("Winner: "+ turnOrders[x]); 
	    	
	   	    game.getTable().clearBool();
	    	
		}	
	}	   
	private void AisPlay(int index,String report)
	{
		prevString = "";
		
		for(int i = 0; i < index;i++) 
    	{
    		report = "";
			game.getPlayers().get(i).getHand().sortTilesByColour();
    		
    		if(!game.getPlayers().get(i).getName().equals("Human") && game.getPlayers().get(i).play()) 
    		{
    			report += game.getPlayers().get(i).getName() + " played: ";
    			report += game.getPlayers().get(i).return_report();
    		}
    		else if (!game.getPlayers().get(i).getName().equals("Human") && game.getDeck().getDeck().size() > 0) 
    		{
	    		Tile t= game.getDeck().Draw();
	    		report += game.getPlayers().get(i).getName() + " drew: ";
	    		game.getPlayers().get(i).getHand().addTileToHand(t);
	    		report += t.toString() + "\n";
	    	}
    		

    		prevString = report;
        	console.setText(console.getText() + prevString); 
        	prevString = "";
    		game.Announcement();
    	}
		game.Announcement();
		lastMove = new Memento(game); 
    	updateTable();
	}
}