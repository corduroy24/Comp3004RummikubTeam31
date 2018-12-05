import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
	Button humanOne;
	Button humanTwo;
	Button humanThree;
	
	Button endTurnButton;
	Button suggestions;
	
	Button scenarios;
	Button scenarioOne;
	Button scenarioTwo;
	Button scenarioThree;
	Button scenarioFour;
	Button scenarioFive;
	Button scenarioSix;
	Button scenarioSeven;
	Button scenarioEight;
	Button scenarioNine;
	Button scenarioTen;
	Button scenarioEleven;
	Button scenarioTwelve;
	Button scenarioThirteen;
	Button scenarioFourteen;
	Button scenarioFifteen;
	Button scenarioSixteen;
	Button scenarioSeventeen;
	Button scenarioEighteen;
	Button scenarioNineteen;
	Button scenarioTwenty; 
	Button scenarioTwentyOne; 


	
	
	Button AiOnly;
	boolean isTimerOn=false;
	Button timerButton;
	Timer timer;
	boolean details=false;
	Button[][] tableButtons = new Button[20][7];
	
	ArrayList<Button> playerHandButtons = new ArrayList<Button>();
	
	TextArea console;
	TextArea scoreConsole;
	
	HBox playerHand;
	
	ImageView mainImageNode;
	ImageView secondImageNode;
	
	Boolean played = false;
	
	String prevString = "";
	boolean OutOfTime = false;
	int numPlayers = 0;
	int[] aiType;
	ArrayList<Integer> turnOrder = new ArrayList<Integer>();
	ArrayList<Integer> recentlyPlayed = new ArrayList<Integer>();
	
	private GameMaster game;
	private HandleJoker checkMeld;
	private Memento lastMove;
	private int turnOfHuman = 0;
	private Player pointer = new Player("pointer",999,new HumanPlayerStrategy());
	
	private boolean humans = false;
	
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
		    	clearMainScreen();
		    	whoGoesFirst(2);
		    	mainScreen.getChildren().remove(AiOnly);
		    	
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
		    	mainScreen.getChildren().remove(AiOnly);
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
		    	mainScreen.getChildren().remove(AiOnly);
		    }
		});
		
		//Sets up Scenario Button
		AiOnly = new Button();
		AiOnly.setText("AiOnly");
		AiOnly.setMinSize(100, 50);
		AiOnly.setDisable(false);
		AiOnly.setLayoutX(390);
		AiOnly.setLayoutY(600);
		AiOnly.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	whoGoesFirst(4);
		    	game.getPlayers().remove(0);
		    	mainScreen.getChildren().remove(AiOnly);
		    	mainScreen.getChildren().remove(humanOne);
				mainScreen.getChildren().remove(humanTwo);
				mainScreen.getChildren().remove(humanThree);
				mainScreen.getChildren().remove(mainImageNode);
		    }
		});

		//Sets up Scenario Button
		scenarios = new Button();
		scenarios.setText("Scenarios");
		scenarios.setMinSize(100, 50);
		scenarios.setDisable(false);
		scenarios.setLayoutX(500);
		scenarios.setLayoutY(600);
		scenarios.setOnAction(new EventHandler<ActionEvent>() 
			{
				   public void handle(ActionEvent e) 
				   {
				   	clearMainScreen();
				   	mainScreen.getChildren().remove(AiOnly);
				   	scenariosList();
				   }
		});
		
		
		
		
		//Sets up the timer button
		timerButton = new Button();
		timerButton.setText("Timer On");
		timerButton.setMinSize(100, 50);
		timerButton.setDisable(false);
		timerButton.setLayoutX(615);
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
		
		mainScreen = new AnchorPane(mainImageNode, twoPlayer, threePlayer, fourPlayer, scenarios,timerButton,AiOnly);

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
		pointer = game.getHuman();
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
						    	if(game.getDeck().getDeck().size() > 2) {
						    		drawTile(game.getPlayers().get(turnOfHuman));
					    			drawTile(game.getPlayers().get(turnOfHuman));
					    			drawTile(game.getPlayers().get(turnOfHuman));
						    	}
						    	else 
						    		for(int i =0; i < game.getDeck().getDeck().size();i++)
						    			drawTile(game.getPlayers().get(turnOfHuman));
						    	updateHand(pointer);
						    	lastMove = new Memento(game);
						    	game.Announcement();
						    	String report = "";
						    	loop: for(int i = turnOfHuman; i < game.getPlayers().size();i++) 
						    	{
					    			game.getPlayers().get(i).getHand().sortTilesByColour();
						    		if(!game.getPlayers().get(i).IsHuman() && game.getPlayers().get(i).play()) 
						    		{
						    			report += game.getPlayers().get(i).getName() + " played: ";
						    			report += game.getPlayers().get(i).return_report();
						    			game.getTable().setTable(game.getPlayers().get(i).getTable().getTable());
						    			game.Announcement();
						    			lastMove = new Memento(game);
						    		}
						    		else if (!game.getPlayers().get(i).IsHuman() && game.getDeck().getDeck().size() > 0) 
						    		{
							    		Tile t= game.getDeck().Draw();
							    		report += game.getPlayers().get(i).getName() + " drew: ";
							    		AiAddTile(game.getPlayers().get(i),t);
							    		report += t.toString() + "\n";
							    	}
						    		lastMove = new Memento(game);
						    		prevString = report;
						    		console.setText(console.getText() + prevString);  
						    	}
						    	AisPlay(turnOfHuman,report);
						    	updateTableAndHand();
						    	updateTable();
						    	updateHand(pointer);	
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
                            pointer.getHand().removeTile(temp);
                            pointer.addScore(temp.getNumber());
                        }
				        else if(db.getRtf().length()==5)
				        {
				        	Tile temp = new Tile(Integer.parseInt(db.getRtf().substring(0, 2)), Integer.parseInt(db.getRtf().substring(3, 5)));
				        	pointer.getHand().removeTile(temp);
				        	pointer.addScore(temp.getNumber());
				        }
                        else
                        {
                            Tile temp = new Tile(Integer.parseInt(db.getRtf().substring(0, 1)), Integer.parseInt(db.getRtf().substring(2, 4)));
                            pointer.getHand().removeTile(temp);
                            pointer.addScore(temp.getNumber());
                        }
                        
                        updateHand(pointer);
				        
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
						if(pointer.getIsFirstMeldComplete())
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
		for(int x=0;x<pointer.getHand().sizeOfHand();x++)
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
			    	playerHandButtons.get(number).setEffect(null);
			    	
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
		    			pointer.setScore(0);
				    	System.out.println("YOUR MOVE IS INVALID\n");
				    	console.setText(console.getText() + "YOUR MOVE IS INVALID\n");
		    		}
		    	}


		    	if(!pointer.getIsFirstMeldComplete()) {
		    		if(pointer.getScore() >= 30) { 
		    			valid = true;
		    		System.out.println("First play is success\n");
		    		}
		    		else {
		    			pointer.setScore(0);
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
			    	
			    	pointer.getTable().setTable(currentTable);
			    	game.getTable().setTable(pointer.getTable().getTable());
			    	game.Announcement();
			    	lastMove = new Memento(game); 
			    	checkPlayerIsWinner();
			    	//console.clear()
			    	
			    	String report = "";
			    	loop:for(int i = turnOfHuman; i < game.getPlayers().size();i++) 
			    	{
		    			game.getPlayers().get(i).getHand().sortTilesByColour();
			    		
			    		if(!game.getPlayers().get(i).IsHuman() && game.getPlayers().get(i).play()) 
			    		{
			    			report += game.getPlayers().get(i).getName() + " played: ";
			    			report += game.getPlayers().get(i).return_report();
			    			game.getTable().setTable(game.getPlayers().get(i).getTable().getTable());
			    			game.Announcement();
			    			lastMove = new Memento(game);
			    		}
			    		else if (!game.getPlayers().get(i).IsHuman() && game.getDeck().getDeck().size() > 0) 
			    		{
				    		Tile t= game.getDeck().Draw();
				    		report += game.getPlayers().get(i).getName() + " drew: ";
				    		report +=AiAddTile(game.getPlayers().get(i),t) + "\n";
				    	}
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
			    			drawTile(game.getPlayers().get(turnOfHuman));
			    		else
			    			System.out.println("Out of tiles");
			    		
			    		updateHand(pointer);
			    	}
			    	else {
			    		game.getTable().addTableCounter();
			    		played=false;
			    	}
			    	checkAIIsWinner();
			    	
			    	if(pointer.getScore()>=30) 
			    	{
			    		pointer.setIsfirstMeldComplete(true);
			    	}
			    	if(humans) {
			    		if(turnOfHuman == 0) turnOfHuman = 1;
			    		else if(turnOfHuman == 3) turnOfHuman = 0;
			    		else turnOfHuman = (int) turnOfHuman%(game.getPlayers().size())+1;
			    		pointer = game.getPlayers().get(turnOfHuman);
			    		System.out.println("CURRENT PLAYER'S HAND IS: " + pointer.getName() + " " + pointer.getId());
			    		updateHand(pointer);
			    	}
			    }
			    else 
			    {
			    	updateTableAndHand();
			 
			    	if(game.getDeck().getDeck().size() > 0) {
		    			drawTile(game.getPlayers().get(turnOfHuman));
			    	}
			    	updateHand(pointer);
			    	game.Announcement();
			    	lastMove = new Memento(game);
			    	String report = "";
			    	loop: for(int i = turnOfHuman; i < game.getPlayers().size();i++) 
			    	{
			    		report = "";
		    			game.getPlayers().get(i).getHand().sortTilesByColour();
			    		if(!game.getPlayers().get(i).IsHuman()&& game.getPlayers().get(i).play()) 
			    		{
			    			report += game.getPlayers().get(i).getName() + " played: ";
			    			report += game.getPlayers().get(i).return_report();
			    			game.getTable().setTable(game.getPlayers().get(i).getTable().getTable());
			    			game.Announcement();
			    			lastMove = new Memento(game);
			    		}
			    		else if (!game.getPlayers().get(i).IsHuman() && game.getDeck().getDeck().size() > 0) 
			    		{
				    		Tile t= game.getDeck().Draw();
				    		report += game.getPlayers().get(i).getName() + " drew: ";
				    		report +=AiAddTile(game.getPlayers().get(i),t) + "\n";
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
			    	if(humans) {
			    		if(turnOfHuman == 0) turnOfHuman = 1;
			    		else if(turnOfHuman == 3) turnOfHuman = 0;
			    		else turnOfHuman = (int) turnOfHuman%(game.getPlayers().size())+1;
			    		pointer = game.getPlayers().get(turnOfHuman);
			    		System.out.print("WHAT");
			    		System.out.println("CURRENT PLAYER'S HAND IS: " + pointer.getName() + " " + pointer.getId());
			    		updateHand(pointer);
			    	}
	    		}
		    	
		    	lightRecentlyPlayed();
		    	game.getTable().clearBool();
		    	
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
		    	pointer.play();
		    	ArrayList<Tile> suggestionTiles = pointer.getSuggPlayList();
		    	
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
		    	
		    	String consoleText = "\nSuggested Tiles: ";
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
		    				consoleText += suggestionTiles.get(y).getColor() + suggestionTiles.get(y).getNumber() + ", ";
		    			}
		    			else if(playerHandButtons.get(x).getText().equals("J"))
		    			{
		    				
		    			}
		    			else if(suggestionTiles.get(y).getNumber() == Integer.parseInt(playerHandButtons.get(x).getText()) && 
			    				color.equals(suggestionTiles.get(y).getColor()))
			    		{
		    				consoleText += suggestionTiles.get(y).getColor() + suggestionTiles.get(y).getNumber() + ", ";
			    		}	
			    		
		    		}	
		    	} 
		    	consoleText+="\n";
		    	console.setText(console.getText()+consoleText);
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
		if(pointer.getHand().sizeOfHand() == 0) {
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
		mainScreen.getChildren().remove(timerButton);
	}
	
	public void whoGoesFirst(final int maxPlayers)
	{
		aiType = new int[maxPlayers];
    	//System.out.println(maxPlayers);
    	
		//Sets up the first human Button
		humanOne = new Button();
		humanOne.setText("Human");
		humanOne.setMinSize(100, 50);
		humanOne.setDisable(false);
		humanOne.setLayoutX(377);
		humanOne.setLayoutY(560);
		humanOne.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	humanOne.setDisable(true);
		    	game.addPlayer(5);
		    	if(game.getPlayers().size() == maxPlayers) {
		    		game.deal();
		    		check();
		    		mainGame();
		    		AisPlay(turnOfHuman,prevString);
		    	}
		    }
		});
		
		//Sets up the second human Button
		humanTwo = new Button();
		humanTwo.setText("Human");
		humanTwo.setMinSize(100, 50);
		humanTwo.setDisable(false);
		humanTwo.setLayoutX(487);
		humanTwo.setLayoutY(560);
		humanTwo.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	humanTwo.setDisable(true);
		    	game.addPlayer(6);
		    	if(game.getPlayers().size() == maxPlayers) {
		    		game.deal();
		    		check();
		    		mainGame();
		    		AisPlay(turnOfHuman,prevString);
		    	}
		    }
		});
		
		//Sets up the third human Button
		humanThree = new Button();
		humanThree.setText("Human");
		humanThree.setMinSize(100, 50);
		humanThree.setDisable(false);
		humanThree.setLayoutX(597);
		humanThree.setLayoutY(560);
		humanThree.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	humanThree.setDisable(true);
		    	game.addPlayer(7);
		    	if(game.getPlayers().size() == maxPlayers) {
		    		game.deal();
		    		check();
		    		mainGame();
		    		AisPlay(turnOfHuman,prevString);
		    	}
		    }
		});
		
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
		mainScreen.getChildren().add(humanOne);
		mainScreen.getChildren().add(humanTwo);
		mainScreen.getChildren().add(humanThree);
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
		    	
		    	clearMainScreen();
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
		    	
		    	//turn orders are rigged 
		    	playGameRigging(s1, s1.getTurnOrder());
		    }		    
		});
		
		//Sets up the Scenario 2 Button
		scenarioTwo = new Button();
		scenarioTwo.setText("complex");
		scenarioTwo.setMinSize(100, 50);
		scenarioTwo.setDisable(false);
		scenarioTwo.setLayoutX(437);
		scenarioTwo.setLayoutY(500);
		scenarioTwo.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s2 = scenarioFactory.getScenario("s2");
		        game.Announcement();
		        game = s2.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 
		    	playGameRigging(s2, turnOrders);
		    }
		});
		
		//Sets up the Scenario 3 Button
		scenarioThree = new Button();
		scenarioThree.setText("simple 1");
		scenarioThree.setMinSize(100, 50);
		scenarioThree.setDisable(false);
		scenarioThree.setLayoutX(547);
		scenarioThree.setLayoutY(500);
		scenarioThree.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s3 = scenarioFactory.getScenario("s3");
		        game.Announcement();
		        game = s3.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 
		    	playGameRigging(s3, turnOrders);
		    }
		});
		
		//Sets up the Scenario 4 Button
		scenarioFour = new Button();
		scenarioFour.setText("Simple 2");
		scenarioFour.setMinSize(100, 50);
		scenarioFour.setDisable(false);
		scenarioFour.setLayoutX(657);
		scenarioFour.setLayoutY(500);
		scenarioFour.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s4 = scenarioFactory.getScenario("s4");
		        game.Announcement();
		        game = s4.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 
		    	playGameRigging(s4, turnOrders);
		    }
		});
		//Sets up the Scenario 5 Button
		scenarioFive = new Button();
		scenarioFive.setText("Strategy 1-1");
		scenarioFive.setMinSize(100, 50);
		scenarioFive.setDisable(false);
		scenarioFive.setLayoutX(217);
		scenarioFive.setLayoutY(600);
		scenarioFive.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	/*int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;*/
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s5 = scenarioFactory.getScenario("s5");
		        game.Announcement();
		        game = s5.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 
		    	playGameRigging(s5, s5.getTurnOrder());
		    }
		});
		
		//Sets up the Scenario 6 Button
		scenarioSix = new Button();
		scenarioSix.setText("Strategy 1-2");
		scenarioSix.setMinSize(100, 50);
		scenarioSix.setDisable(false);
		scenarioSix.setLayoutX(327);
		scenarioSix.setLayoutY(600);
		scenarioSix.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s6 = scenarioFactory.getScenario("s6");
		        game.Announcement();
		        game = s6.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s6, turnOrders);
		    }
		});
		
		//Sets up the Scenario 7 Button
		scenarioSeven = new Button();
		scenarioSeven.setText("Strategy 1-3");
		scenarioSeven.setMinSize(100, 50);
		scenarioSeven.setDisable(false);
		scenarioSeven.setLayoutX(437);
		scenarioSeven.setLayoutY(600);
		scenarioSeven.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s7 = scenarioFactory.getScenario("s7");
		        game.Announcement();
		        game = s7.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s7, turnOrders);
		    }
		});
		
		//Sets up the Scenario 8 Button
		scenarioEight = new Button();
		scenarioEight.setText("Strategy 2-1");
		scenarioEight.setMinSize(100, 50);
		scenarioEight.setDisable(false);
		scenarioEight.setLayoutX(547);
		scenarioEight.setLayoutY(600);
		scenarioEight.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s8 = scenarioFactory.getScenario("s8");
		        game.Announcement();
		        game = s8.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s8, turnOrders);
		    }
		});
		
		//Sets up the Scenario 9 Button
		scenarioNine = new Button();
		scenarioNine.setText("Strategy 2-2");
		scenarioNine.setMinSize(100, 50);
		scenarioNine.setDisable(false);
		scenarioNine.setLayoutX(657);
		scenarioNine.setLayoutY(600);
		scenarioNine.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s9 = scenarioFactory.getScenario("s9");
		        game.Announcement();
		        game = s9.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s9, turnOrders);
		    }
		});
	
		//Sets up the Scenario 10 Button
		scenarioTen = new Button();
		scenarioTen.setText("Strategy 2-3");
		scenarioTen.setMinSize(100, 50);
		scenarioTen.setDisable(false);
		scenarioTen.setLayoutX(767);
		scenarioTen.setLayoutY(600);
		scenarioTen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s10 = scenarioFactory.getScenario("s10");
		        game.Announcement();
		        game = s10.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s10, turnOrders);
		    }
		});
		
		//Sets up the Scenario 11 Button
		scenarioEleven = new Button();
		scenarioEleven.setText("Strategy 2-4");
		scenarioEleven.setMinSize(100, 50);
		scenarioEleven.setDisable(false);
		scenarioEleven.setLayoutX(217);
		scenarioEleven.setLayoutY(700);
		scenarioEleven.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s11 = scenarioFactory.getScenario("s11");
		        game.Announcement();
		        game = s11.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s11, turnOrders);
		    }
		});
		
		//Sets up the Scenario 12 Button
		scenarioTwelve = new Button();
		scenarioTwelve.setText("Strategy 3-1");
		scenarioTwelve.setMinSize(100, 50);
		scenarioTwelve.setDisable(false);
		scenarioTwelve.setLayoutX(327);
		scenarioTwelve.setLayoutY(700);
		scenarioTwelve.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s12 = scenarioFactory.getScenario("s12");
		        game.Announcement();
		        game = s12.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s12, turnOrders);
		    }
		});
		//Sets up the Scenario 13 Button
		scenarioThirteen = new Button();
		scenarioThirteen.setText("Strategy 3-2");
		scenarioThirteen.setMinSize(100, 50);
		scenarioThirteen.setDisable(false);
		scenarioThirteen.setLayoutX(437);
		scenarioThirteen.setLayoutY(700);
		scenarioThirteen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s13 = scenarioFactory.getScenario("s13");
		        game.Announcement();
		        game = s13.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s13, turnOrders);
		    }
		});
		
		//Sets up the Scenario 14 Button
		scenarioFourteen = new Button();
		scenarioFourteen.setText("Strategy 3-3");
		scenarioFourteen.setMinSize(100, 50);
		scenarioFourteen.setDisable(false);
		scenarioFourteen.setLayoutX(547);
		scenarioFourteen.setLayoutY(700);
		scenarioFourteen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s14 = scenarioFactory.getScenario("s14");
		        game.Announcement();
		        game = s14.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s14, turnOrders);
		    }
		});
		
		//Sets up the Scenario 15 Button
		scenarioFifteen = new Button();
		scenarioFifteen.setText("Strategy 3-4");
		scenarioFifteen.setMinSize(100, 50);
		scenarioFifteen.setDisable(false);
		scenarioFifteen.setLayoutX(657);
		scenarioFifteen.setLayoutY(700);
		scenarioFifteen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s15 = scenarioFactory.getScenario("s15");
		        game.Announcement();
		        game = s15.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s15, turnOrders);
		    }
		});
		
		//Sets up the Scenario 16 Button
		scenarioSixteen = new Button();
		scenarioSixteen.setText("Strategy 3-5");
		scenarioSixteen.setMinSize(100, 50);
		scenarioSixteen.setDisable(false);
		scenarioSixteen.setLayoutX(767);
		scenarioSixteen.setLayoutY(700);
		scenarioSixteen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s16 = scenarioFactory.getScenario("s16");
		        game.Announcement();
		        game = s16.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s16, turnOrders);
		    }
		});
		
		//Sets up the Scenario 17 Button
		scenarioSeventeen = new Button();
		scenarioSeventeen.setText("Joker 1");
		scenarioSeventeen.setMinSize(100, 50);
		scenarioSeventeen.setDisable(false);
		scenarioSeventeen.setLayoutX(117);
		scenarioSeventeen.setLayoutY(400);
		scenarioSeventeen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s17 = scenarioFactory.getScenario("s17");
		        game.Announcement();
		        game = s17.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s17, turnOrders);
		    }
		});
		
		//Sets up the Scenario 18 Button
		scenarioEighteen= new Button();
		scenarioEighteen.setText("Joker 2");
		scenarioEighteen.setMinSize(100, 50);
		scenarioEighteen.setDisable(false);
		scenarioEighteen.setLayoutX(227);
		scenarioEighteen.setLayoutY(400);
		scenarioEighteen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s18 = scenarioFactory.getScenario("s18");
		        game.Announcement();
		        game = s18.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s18, turnOrders);
		    }
		});
		
		//Sets up the Scenario 19 Button
		scenarioNineteen = new Button();
		scenarioNineteen.setText("Joker 3");
		scenarioNineteen.setMinSize(100, 50);
		scenarioNineteen.setDisable(false);
		scenarioNineteen.setLayoutX(337);
		scenarioNineteen.setLayoutY(400);
		scenarioNineteen.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s19 = scenarioFactory.getScenario("s19");
		        game.Announcement();
		        game = s19.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s19, turnOrders);
		    }
		});
		
		//Sets up the Scenario 20 Button
		scenarioTwenty = new Button();
		scenarioTwenty.setText("Strategy 4");
		scenarioTwenty.setMinSize(100, 50);
		scenarioTwenty.setDisable(false);
		scenarioTwenty.setLayoutX(447);
		scenarioTwenty.setLayoutY(400);
		scenarioTwenty.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s20 = scenarioFactory.getScenario("s20");
		        game.Announcement();
		        game = s20.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s20, turnOrders);
		    }
		});
		scenarioTwentyOne = new Button();
		scenarioTwentyOne.setText("Strategy 4-2");
		scenarioTwentyOne.setMinSize(100, 50);
		scenarioTwentyOne.setDisable(false);
		scenarioTwentyOne.setLayoutX(600);
		scenarioTwentyOne.setLayoutY(400);
		scenarioTwentyOne.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	clearMainScreen();
		    	int maxPlayers  = 4; 
		    	int [] turnOrders = new int[maxPlayers];
		    	turnOrders[0] = 1;
		    	turnOrders[1] = 2;
		    	turnOrders[2] = 3;
		    	turnOrders[3] = 4;
		        ScenarioFactory scenarioFactory = new ScenarioFactory();
		        Scenario s21 = scenarioFactory.getScenario("s21");
		        game.Announcement();
		        game = s21.deal(game);
		        
		    	setupGameRigging(); 
		    	game.getPlayers().remove(game.getHuman());
		    	
		    	
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		    	
		    	//turn orders are rigged 

		    	playGameRigging(s21, turnOrders);
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
		mainScreen.getChildren().add(scenarioFive);
		mainScreen.getChildren().add(scenarioSix);
		mainScreen.getChildren().add(scenarioSeven);
		mainScreen.getChildren().add(scenarioEight);
		mainScreen.getChildren().add(scenarioNine);
		mainScreen.getChildren().add(scenarioTen);
		mainScreen.getChildren().add(scenarioEleven);
		mainScreen.getChildren().add(scenarioTwelve);
		mainScreen.getChildren().add(scenarioThirteen);
		mainScreen.getChildren().add(scenarioFourteen);
		mainScreen.getChildren().add(scenarioFifteen);
		mainScreen.getChildren().add(scenarioSixteen);
		mainScreen.getChildren().add(scenarioSeventeen);
		mainScreen.getChildren().add(scenarioEighteen);
		mainScreen.getChildren().add(scenarioNineteen);
		mainScreen.getChildren().add(scenarioTwenty);
		mainScreen.getChildren().add(scenarioTwentyOne);





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
		pointer.getHand().sortTilesByColour();
		//System.out.println("Size of hand: "+game.getHuman().getHand().sizeOfHand());
		
		for(int x=0;x<pointer.getHand().sizeOfHand();x++)
		{
			if(pointer.getHand().getTile(x)!=null)
			{
				if(pointer.getHand().getTile(x).getNumber() == 14)
				{
					playerHandButtons.get(x).setText("J");
				}
				else
				{
					playerHandButtons.get(x).setText(""+pointer.getHand().getTile(x).getNumber());
				}
				
				//System.out.println("test"+game.getHuman().getHand().getTile(x).isJoker());
				
				if(pointer.getHand().getTile(x).getColor().equals("R"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #db4c4c");
				}
				else if(pointer.getHand().getTile(x).getColor().equals("B"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #3888d8");
				}
				else if(pointer.getHand().getTile(x).getColor().equals("G"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #1a9922");
				}
				else if(pointer.getHand().getTile(x).getColor().equals("O"))
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #c69033");
				} 
				else if(pointer.getHand().getTile(x).isJoker())
				{
					playerHandButtons.get(x).setStyle("-fx-background-color: #474747");
				} 
				else
				{
					System.out.println("Something went wrong in setPlayerHand()");
					System.out.println("Color for tile "+x+" is set to "+pointer.getHand().getTile(x).getColor());
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
	
	public void updateHand(Player p)
	{
		PlayerHand test = p.getHand();
		playerHand.getChildren().clear();
		for(int x=0;x<pointer.getHand().sizeOfHand();x++)
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
		for(int x=0;x<pointer.getHand().sizeOfHand();x++)
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
				try {
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
				}}
				catch(ArrayIndexOutOfBoundsException exception) {}
		
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
	
	public void drawTile(Player p)
	{
		p.getPlayedList();
		PlayerHand hand = p.getHand();
		Tile tile = game.getDeck().Draw();
		
		hand.addTileToHand(tile);
		hand.sortTilesByColour();
		addPlayerTile(tile);
	}
	
	private void updateTableAndHand() 
	{
		game.getTable().setTable(lastMove.getStateTable().getTable());
		pointer.getHand().setPlayerHand(lastMove.getStateHumanHand().getTiles());		    
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
	
	public void playGameRigging(Scenario s, int [] turnOrders) {
		setTurnOrder(turnOrders);

		int stop = s.getMaxPlayers(); 
		System.out.println(stop);
		System.out.println("\nUI Class");
		for(int x=0;x<turnOrders.length;x++)
			System.out.println(turnOrders[x]);
	
		System.out.print("\n");
		int x =0; 
		int round = 0; 
		for(int i = 0; i < s.getNumTurns(); i++) {

			System.out.println("--------------------------- "+ turnOrders[x]);
			
	    	game.Announcement();
	    	
			game.getPlayers().get(turnOrders[x]-1).getHand().sortTilesByColour();
			game.getPlayers().get(turnOrders[x]-1).getHand().HandReader(); 

			if(game.getPlayers().get(turnOrders[x]-1).play()) 
			{				
				prevString += game.getPlayers().get(turnOrders[x]-1).getName() +  " played: ";
		    	prevString += game.getPlayers().get(turnOrders[x]-1).return_report();
			}
			else if (game.getDeck().getDeck().size() > 0) 
			{
	    		prevString += game.getPlayers().get(turnOrders[x]-1).getName() + " drew: ";
	    		switch(round) {
		    		case 0:  game = s.secondTurn(game, turnOrders[x]); break; 
		    		case 1:  game = s.thirdTurn(game, turnOrders[x]); break; 
		    		default: System.out.println("Scenario finished ");
	    		}
	    		/*if(round == 0)
	    			game = s.secondTurn(game, turnOrders[x]);
	    		else if (round == 1)
	    			game = s.thirdTurn(game, turnOrders[x]);
	    		else 
	    			System.out.println("Scenario finished ");*/
	    	}
			game.Announcement();
	    	
	    	console.setText(console.getText() + prevString);  
	    	prevString = "\n";

	    	this.updateTable();
	    	
	    		if(game.getPlayers().get(turnOrders[x]-1).getHand().sizeOfHand() == 0) 
	    			System.out.println("Winner: "+ turnOrders[x]); 
	    	
	   	    game.getTable().clearBool();
	    	x++;
	    	
	   	    if(x==stop) {
	   	    	x=0; 
	   	    	round++;
	   	    }
		}	
	}	   
	private void AisPlay(int index,String report)
	{
		prevString = "";
		report = "";
		loop: for(int i = 0; i < index;i++) 
    	{
			game.getPlayers().get(i).getHand().sortTilesByColour();
    		
    		if(!game.getPlayers().get(i).IsHuman() && game.getPlayers().get(i).play()) 
    		{
    			report += game.getPlayers().get(i).getName() + " played: ";
    			report += game.getPlayers().get(i).return_report();
    			game.getTable().setTable(game.getPlayers().get(i).getTable().getTable());
    			game.Announcement();
    		}
    		else if (!game.getPlayers().get(i).IsHuman() && game.getDeck().getDeck().size() > 0) 
    		{
	    		Tile t= game.getDeck().Draw();
	    		report += game.getPlayers().get(i).getName() + " drew: ";
	    		report += AiAddTile(game.getPlayers().get(i),t) + "\n";
	    	}

    	}
		prevString = report;
    	console.setText(console.getText() + prevString); 
    	prevString = "";
		game.Announcement();
		lastMove = new Memento(game);
    	updateTable();
	}
	private String AiAddTile(Player p, Tile t) {
		String out = "";
		if(p.getHand().containJoker() && t.isJoker()) {
			game.getDeck().add(t);
			while(true) {
			int random = (int) (Math.random()*game.getDeck().getDeck().size());
			Tile T = game.getDeck().getTile(random);
			if(!T.isJoker()) {
				p.getHand().addTileToHand(T);
				out += T.toString();
				return out;
				}
			}
		}
		p.getHand().addTileToHand(t);
		out += t.toString();
		return out;
	}
	private void check() {
		int check = 0; 
		for(int i =0; i < game.getPlayers().size();i++) {
			if(game.getPlayers().get(i).IsHuman())check++;
		}
		if(check == game.getPlayers().size()) humans = true;
		
	}
}