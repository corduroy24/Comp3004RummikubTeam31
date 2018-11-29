import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.Button;
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
	Button scenario1; 
	
	Button[][] tableButtons = new Button[20][7];
	//Boolean[][] isTableSet = new Boolean[20][7];
	
	ArrayList<Button> playerHandButtons = new ArrayList<Button>();
	
	TextArea console;
	
	HBox playerHand;
	
	ImageView mainImageNode;
	ImageView secondImageNode;
	
	Boolean played = false;
	
	String prevString = "";
	
	int playerScore = 0;
	int numPlayers = 0;
	int[] aiType;;
	
	private GameMaster game;
	private HandleJoker checkMeld;
	private Memento lastMove;
	
	
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
		
		//Sets up the 4 player Button
				/*scenario1 = new Button();
				scenario1.setText("Scenario 1");
				scenario1.setMinSize(100, 50);
				scenario1.setDisable(false);
				scenario1.setLayoutX(610);
				scenario1.setLayoutY(200);
				scenario1.setOnAction(new EventHandler<ActionEvent>() 
				{
				    public void handle(ActionEvent e) 
				    {
				    	int maxPlayers  = 4; 
				    	clearMainScreen();
						aiType = new int[maxPlayers];
						aiType[0] = 1;
						aiType[1] = 2;
						aiType[2] = 3;
						aiType[3] = 4;
					    ScenarioFactory scenarioFactory = new ScenarioFactory();

					    Scenario s1 = scenarioFactory.getScenario("s1");
					    game = s1.deal(game);

			    		int[] temp = game.turnOrder(aiType);
			    		game.getPlayers().remove(game.getHuman());
			    		
					   //game.getAI().getHand().HandReader();
					   
					  // game.getAI().play();
			    		mainGame(temp);		   
			    		
				    }
				});*/

		
		mainScreen = new AnchorPane(mainImageNode, twoPlayer, threePlayer, fourPlayer);
		//mainScreen = new AnchorPane(mainImageNode, twoPlayer, threePlayer, fourPlayer, scenario1);

		mainScreen.setMinSize(1095,	790);
		
		mainMenuScene = new Scene(mainScreen);
		
		InputStream iconImagePath = getClass().getResourceAsStream("iconImage.png");
		Image iconImage = new Image(iconImagePath);
		
		window.getIcons().add(iconImage);
		window.setScene(mainMenuScene);
		window.show();
		
		
	}
	
	//turnOrder goes by ai numbers and the player is listed as 10
	public void mainGame(int[] turnOrder)
	{	lastMove= new Memento(game);
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
				
				//Turns the button into the button that was dropped on it
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
		
		//Adds the starting cards to the players hand
		for(int x=0;x<14;x++)
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
			        
			        temp = ""+temp+"|"+playerHandButtons.get(number).getText();
			        
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
		
		//Creates the text console where it will ouput any necessary info\
		console = new TextArea();
		console.setText("");
		console.setEditable(false); //Makes it not editable
		console.setMinSize(1095, 100); //sets the size of the box
		console.setMaxSize(1095, 100); //sets the size of the box
		
		//Sets up the End Turn Button
		endTurnButton = new Button();
		endTurnButton.setText("End Turn");
		endTurnButton.setMinSize(100, 100);
		endTurnButton.setDisable(false);
		
		endTurnButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	//Change to send a message that players turn has ended
		    	//boolean hasWinner = false;
		    	checkMeld = new HandleJoker();
		    	ArrayList<ArrayList<Tile>> currentTable = current_table();
		    	boolean valid = true;
		    	for(int i =0; i < currentTable.size();i++) {
		    		if(!checkMeld.isRun(currentTable.get(i)) && !checkMeld.isSet(currentTable.get(i))) {
		    			valid = false;
		    		}
		    	}
		    	if(valid) {
		    	game.getHuman().getTable().setTable(currentTable);
		    	game.getTable().setTable(game.getHuman().getTable().getTable());
		    	game.Announcement();
		    	checkPlayerIsWinner();
		    	console.clear();
		    	for(int i =0; i < game.getPlayers().size();i++) 
		    	{
		    		game.getPlayers().get(i).getHand().sortTilesByColour();
		    		
		    		if(!game.getPlayers().get(i).getName().equals("Human")&& game.getPlayers().get(i).play()) 
		    		{
		    			prevString += game.getPlayers().get(i).getName() +  " play: \n";
				    	prevString += game.getPlayers().get(i).return_report();
		    		}
		    		else if (!game.getPlayers().get(i).getName().equals("Human")&& game.getDeck().getDeck().size() > 0) 
		    		{
			    		Tile t= game.getDeck().Draw();
			    		prevString += game.getPlayers().get(i).getName() + "draw: \n";
			    		game.getPlayers().get(i).getHand().addTileToHand(t);
			    		prevString += t.toString() + "\n";
			    	}
		    		game.Announcement();
		    		lastMove = new Memento(game);
		    	}
		    	console.setText(prevString);  
		    	prevString = "";
		    	updateTable();
		    	
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
		    	updateTable();
		    	updateHand();
		    
		    
		    	if(game.getDeck().getDeck().size() > 0)
	    			drawTile();
		    	updateHand();
		    	lastMove = new Memento(game);
		    	System.out.println(game.getHuman().getHand().sizeOfHand());
		    
		    	}
		    	
		    }

			private void updateTableAndHand() {
				// TODO Auto-generated method stub
				game.getTable().setTable(lastMove.getStateTable().getTable());
				game.getHuman().getHand().setPlayerHand(lastMove.getStateHumanHand().getTiles());
				
			}
		});
		
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
		
		window.setScene(rummiScene);
		window.show();
		
		aiPlayFirst(turnOrder);
	}
	
	public void aiPlayFirst(int[] turnOrder)
	{
		System.out.println("\nUI Class");
		for(int x=0;x<turnOrder.length;x++)
		{
			System.out.println(turnOrder[x]);
		}
		System.out.print("\n");
		
		int x=0;
		
		while(turnOrder[x] != 0)
		{
			System.out.println("--------------------------- "+ turnOrder[x]);
			
	    	//Change to send a message that players turn has ended
	    	//boolean hasWinner = false;
	    	game.getHuman().getTable().setTable(current_table());
	    	game.getTable().setTable(game.getHuman().getTable().getTable());
	    	game.Announcement();
	    	checkPlayerIsWinner();
	    	//console.clear();

    		game.getPlayers().get(turnOrder[x]).getHand().sortTilesByColour();
    		
    		if(game.getPlayers().get(turnOrder[x]).play()) 
    		{
    			prevString += game.getPlayers().get(turnOrder[x]).getName() +  " play: \n";
		    	prevString += game.getPlayers().get(turnOrder[x]).return_report();
    		}
    		else if (game.getDeck().getDeck().size() > 0) 
    		{
	    		Tile t= game.getDeck().Draw();
	    		prevString += game.getPlayers().get(turnOrder[x]).getName() + "draw: \n";
	    		game.getPlayers().get(turnOrder[x]).getHand().addTileToHand(t);
	    		prevString += t.toString() + "\n";
	    	}
    		game.Announcement();
	    	
	    	console.setText(console.getText() + prevString);  
	    	prevString = "";
	    	updateTable();
	    	
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
	    	
	    	x++;
	    }
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
    	
    	for(int i =0; i < game.getPlayers().size();i++) {
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
    	/*
		if(game.getAI().getHand().sizeOfHand() == 0 || game.getAI2().getHand().sizeOfHand() == 0
			|| game.getAI3().getHand().sizeOfHand() == 0)  {
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
		*/
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
	}
	
	public void whoGoesFirst(final int maxPlayers)
	{
		aiType = new int[maxPlayers];
		
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
		    	aiType[numPlayers] = 1;
		    	numPlayers++;
		    	if(numPlayers==maxPlayers-1)
		    	{
		    		int[] temp = game.dealInitialHand(aiType);
		    		mainGame(temp);
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
		    	aiType[numPlayers] = 2;
		    	numPlayers++;
		    	if(numPlayers==maxPlayers-1)
		    	{
		    		int[] temp = game.dealInitialHand(aiType);
		    		mainGame(temp);
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
		    	aiType[numPlayers] = 3;
		    	numPlayers++;
		    	if(numPlayers==maxPlayers-1)
		    	{
		    		int[] temp = game.dealInitialHand(aiType);
		    		mainGame(temp);
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
		    	aiType[numPlayers] = 4;
		    	numPlayers++;
		    	if(numPlayers==maxPlayers-1)
		    	{
		    		int[] temp = game.dealInitialHand(aiType);
		    		mainGame(temp);
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
	
	public void updateConsole(String output)
	{
		prevString = prevString+output;
		console.setText(prevString);
		
	}
	
	public String getConsoleText()
	{
		return console.getText();
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
		PlayerHand test = game.getHuman().getHand();
		test.sortTilesByColour();
		
		for(int x=0;x<14;x++)
		{
			if(test.getTile(x)!=null)
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
				else
				{
					System.out.println("Something went wrong in setPlayerHand()");
					System.out.println("Color for tile "+x+" is set to "+test.getTile(x).getColor());
				}
			}
		}
	}
	
	public void testTable()
	{
		Table table = game.getTable();
		
		Tile test1 = new Tile(2, 3);
		table.setTile(1, 4, test1);
		
		Tile test2 = new Tile(1, 4);
		table.setTile(15, 2, test2);
		
		Tile test3 = new Tile(4, 11);
		table.setTile(8, 6, test3);
		
		game.setTable(table);
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
				if(!tableButtons[y][x].getText().equals("") && !tableButtons[y][x].getText().equals(" ")) {
					current_number = Integer.valueOf(tableButtons[y][x].getText());
					current_color =  getColorFromStyle(tableButtons[y][x].getStyle());
				}
				
				if(y <= 19 && can_add(tableButtons[y][x].getText()) && can_add(tableButtons[y+1][x].getText())) { 
					meld.add(new Tile(current_color,current_number));
				}
				else if (can_add(tableButtons[y][x].getText()) && meld.size() > 0 ) {
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
	
	
}
