import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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

public class Ui extends Application implements Observer
{
	Stage window;
	
	Scene mainMenuScene;
	Scene rummiScene;
	
	Button startButton;
	Button endTurnButton;
	
	Button[][] tableButtons = new Button[20][7];
	Button[] playerHandButtons = new Button[14];
	
	TextArea console;
	
	HBox playerHand;
	
	static GameMaster game = new GameMaster();	
	
	public static void main(String [] args) 
	{
		game.dealInitialHand();
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
		window.setTitle("Rummikub");
		
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
				//Shows the button as droppable if it isnt from itself
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
				        Dragboard db = event.getDragboard();
				        boolean successText = false;
				        boolean successColor = false;
				        
				        if(db.getRtf().length()==3)
				        {
				        	Tile temp = new Tile(Integer.parseInt(db.getRtf().substring(0, 1)), Integer.parseInt(db.getRtf().substring(2, 3)));
				        	game.getHuman().getHand().removeTile(temp);
				        }
				        else
				        {
				        	Tile temp = new Tile(Integer.parseInt(db.getRtf().substring(0, 1)), Integer.parseInt(db.getRtf().substring(2, 4)));
				        	game.getHuman().getHand().removeTile(temp);
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
				        
				        event.setDropCompleted(successText && successColor);
				        event.consume();
				     }
				});
				
				//Lights up the button green if it is possible to drop the tile here
				tableButtons[y][x].setOnDragEntered(new EventHandler<DragEvent>() 
				{
				    public void handle(DragEvent event) 
				    {
				    	System.out.println(tableButtons[numberY][numberX].getStyle().length());
				    	
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
				
				tableButtons[y][x].setOnDragDetected(new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event) 
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
					        db.setContent(content);
					        
					        tableButtons[numberY][numberX].setStyle("");
					        tableButtons[numberY][numberX].setText("");
						}
				        
				        event.consume();
					}
				});
			}
		}
		
		//The layoutPane for the players hand at the bottom
		playerHand = new HBox(5);
		playerHand.setPadding(new Insets(10)); //Sets the spacing between the cards
		playerHand.setAlignment(Pos.BASELINE_CENTER); //Centers the card in the bottom middle
		
		//Adds the starting cards to the players hand
		for(int x=0;x<playerHandButtons.length;x++)
		{
			playerHandButtons[x] = new Button();
			//playerHandButtons[x].setText("x: "+x);
			playerHandButtons[x].setMinSize(50, 80);
			
			final int number = x;
			//Copies the color and text to a clipboard to be carried over and allows you to start dragging it
			playerHandButtons[x].setOnDragDetected(new EventHandler<MouseEvent>() 
			{
			    public void handle(MouseEvent event) 
			    {
			        Dragboard db = playerHandButtons[number].startDragAndDrop(TransferMode.ANY);
			        String temp = "";
			        
			        if(playerHandButtons[number].getStyle().equals("-fx-background-color: #db4c4c"))
					{
			        	temp = "1";
					}
					else if(playerHandButtons[number].getStyle().equals("-fx-background-color: #3888d8"))
					{
						temp = "2";
					}
					else if(playerHandButtons[number].getStyle().equals("-fx-background-color: #1a9922"))
					{
						temp = "3";
					}
					else if(playerHandButtons[number].getStyle().equals("-fx-background-color: #c69033"))
					{
						temp = "4";
					}
			        
			        temp = ""+temp+"|"+playerHandButtons[number].getText();
			        
			        ClipboardContent content = new ClipboardContent();
			        content.putString(playerHandButtons[number].getText());
			        content.putUrl(playerHandButtons[number].getStyle());
			        content.putRtf(temp);
			        db.setContent(content);
			        
			        event.consume();
			    }
			});
		}
		
		setPlayerHand();

		for(int x=0;x<playerHandButtons.length;x++)
		{
			playerHand.getChildren().add(playerHandButtons[x]);
		}
		
		//Creates the text console where it will ouput any necessary info\
		console = new TextArea();
		console.setText("Player's turn.");
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
		    	//testTable();
		    	aiTurn();
		    	updateTable();
		    	
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
		
		InputStream mainImagePath = getClass().getResourceAsStream("TitleImage.png");
		Image mainImage = new Image(mainImagePath);
		ImageView mainImageNode = new ImageView(mainImage);
		mainImageNode.setX(300);
		mainImageNode.setY(100);
		
		//Sets up the End Turn Button
		startButton = new Button();
		startButton.setText("Start Turn");
		startButton.setMinSize(100, 50);
		startButton.setDisable(false);
		startButton.setLayoutX(480);
		startButton.setLayoutY(500);
		startButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    	window.setScene(rummiScene);
		    	
		    }
		});
		
		AnchorPane mainScreen = new AnchorPane(mainImageNode, startButton);
		mainScreen.setMinSize(1095,	790);
		
		mainMenuScene = new Scene(mainScreen);
		
		InputStream iconImagePath = getClass().getResourceAsStream("iconImage.png");
		Image iconImage = new Image(iconImagePath);
		
		window.getIcons().add(iconImage);
		window.setScene(mainMenuScene);
		window.show();
		
		
	}
	
	public void updateConsole(String output)
	{
		console.setText(output);
	}
	
	public String getConsoleText()
	{
		return console.getText();
	}
	
	public void addPlayerTile(Tile tile)
	{
		String color = tile.getColor();
		int number = tile.getNumber();
		
		Button newTile = new Button();
		newTile.setText(color+""+number);
		newTile.setMinSize(50, 100);
		playerHand.getChildren().add(newTile);		
	}
	
	public void setPlayerHand()
	{
		PlayerHand test = game.getHuman().getHand();
		
		for(int x=0;x<14;x++)
		{
			playerHandButtons[x].setText(""+test.getTile(x).getNumber());
			
			if(test.getTile(x).getColor().equals("R"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #db4c4c");
			}
			else if(test.getTile(x).getColor().equals("B"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #3888d8");
			}
			else if(test.getTile(x).getColor().equals("G"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #1a9922");
			}
			else if(test.getTile(x).getColor().equals("O"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #c69033");
			}
			else
			{
				System.out.println("Something went wrong in setPlayerHand()");
				System.out.println("Color for tile "+x+" is set to "+test.getTile(x).getColor());
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
	
	public void updateTable()
	{
		Table table = game.getTable();
		ArrayList<ArrayList<Tile>> t = table.getTable();
		
		int x = 0;
		int y = 0;
		
		for(int i =0; i < t.size();i++) {
			if(t.get(i).size() + y < 20) {
				for(int u =0; u < t.get(i).size(); u++) {
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
			else {
				y = 0;
				x ++;
				for(int u =0; u < t.get(i).size(); u++) {
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
			
	}
	
	public void updateHand()
	{
		PlayerHand test = game.getHuman().getHand();
		playerHand.getChildren().clear();
		
		for(int x=0;x<game.getHuman().getHand().sizeOfHand();x++)
		{
			playerHandButtons[x].setText(""+test.getTile(x).getNumber());
			
			if(test.getTile(x).getColor().equals("R"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #db4c4c");
			}
			else if(test.getTile(x).getColor().equals("B"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #3888d8");
			}
			else if(test.getTile(x).getColor().equals("G"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #1a9922");
			}
			else if(test.getTile(x).getColor().equals("O"))
			{
				playerHandButtons[x].setStyle("-fx-background-color: #c69033");
			}
			else
			{
				System.out.println("Something went wrong in setPlayerHand()");
				System.out.println("Color for tile "+x+" is set to "+test.getTile(x).getColor());
			}
		}
		for(int x=0;x<game.getHuman().getHand().sizeOfHand();x++)
		{
			playerHand.getChildren().add(playerHandButtons[x]);
		}
	}
	
	public void aiTurn()
	{
		Boolean temp = game.getAI().play();
		if(temp)
		{
			System.out.println("Updated Table");
		}
		else
		{
			System.out.println("Drew a card");
		}		
	}

	public void update(Observable o, Object arg) 
	{
		
	}
}
