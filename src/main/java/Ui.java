import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Ui extends Application implements EventHandler<ActionEvent>, Observer
{
	Stage window;
	
	Scene mainMenuScene;
	StackPane mainMenu;
	
	Scene rummiScene;
	StackPane playBoard;
	
	Button startButton;
	Button endTurnButton;
	
	Button[][] tableButtons = new Button[20][7];
	Button[] playerHandButtons = new Button[14];
	
	TextArea console;
	
	HBox playerHand;
	
	Boolean tracing = true;
	
	/*
	public static void main(String [] args) 
	{
		launch(args);
	}
	*/
	
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
		window.setTitle("Rummikub");
		
		//The layoutPane for the gridded table in the center
		TilePane tablePane = new TilePane();
		tablePane.setPrefRows(7); //Sets the row length to 7
		tablePane.setPrefColumns(20); //Sets the column length to 20
		tablePane.setMaxWidth(1000); //Makes the resize not squish the table
		tablePane.setMinWidth(1000); //Makes the resize not squish the table
		ObservableList<Node> list = tablePane.getChildren();
		
		//Adds all of the table buttons onto the table
		for(int x=0;x<tableButtons[0].length;x++)
		{
			for(int y=0;y<tableButtons.length;y++)
			{
				tableButtons[y][x] = new Button();
				tableButtons[y][x].setText("x: "+x+"\ny: "+y);
				tableButtons[y][x].setMinSize(50, 100);
				list.addAll(tableButtons[y][x]);
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
			playerHandButtons[x].setMinSize(50, 100);
			
			
			
		}
		
		//Remove once the main class sends in the hand
		PlayerHand test1 = new PlayerHand("test1");
		setPlayerHand(test1);
		
		
		//
		//Testing Events
		//You can drag and drop the first player card to the first table card and it carries over number and color
		//
		playerHandButtons[0].setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        Dragboard db = playerHandButtons[0].startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putString(playerHandButtons[0].getText());
		        content.putUrl(playerHandButtons[0].getStyle());
		        db.setContent(content);
		        
		        event.consume();
		    }
		});
		tableButtons[0][0].setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != tableButtons[0][0] &&
		                event.getDragboard().hasString()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		tableButtons[0][0].setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean successText = false;
		        boolean successColor = false;
		        if (db.hasString()) {
		        	tableButtons[0][0].setText(db.getString());
		        	successText = true;
		        }
		        if (db.hasUrl()) {
		        	tableButtons[0][0].setStyle(db.getUrl());
		        	successColor = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(successText && successColor);
		        
		        event.consume();
		     }
		});
		
		
		
		
		
		
		
		
		
		
		
		
		for(int x=0;x<playerHandButtons.length;x++)
		{
			playerHand.getChildren().add(playerHandButtons[x]);
		}
		
		//Creates the text console where it will ouput any necessary info\
		console = new TextArea();
		console.setText("This is where it displays current players turn as well as what the AI did on their turn.\nCan also display score");
		console.setEditable(false); //Makes it not editable
		console.setMinHeight(100); //sets the size of the box
		console.setMaxHeight(100); //sets the size of the box
		
		//Sets up the End Turn Button
		endTurnButton = new Button();
		endTurnButton.setText("End Turn");
		endTurnButton.setMinSize(100, 100);
		endTurnButton.setDisable(false);
		
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
	}
	
	public void handle(ActionEvent event) 
	{
		//The listener for the starting button
		if(event.getSource() == startButton)
		{
			if(tracing) System.out.println("Start Button Pressed");
			//window.setScene(rummiScene);
		}
		
		if(event.getSource() == endTurnButton)
		{
			if(tracing) System.out.println("End Turn Button Pressed");
			//window.setScene(rummiScene);
		}
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
	
	public void setPlayerHand(PlayerHand hand)
	{
		Deck deck = new Deck();
		deck.Shuffle();
		
		PlayerHand test = new PlayerHand("test");
		test.drawFirst14(deck);
		
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
				System.out.print("Something went wrong in setPlayerHand();");
			}
		}
	}

	public void update(Observable o, Object arg) 
	{
		
	}
}
