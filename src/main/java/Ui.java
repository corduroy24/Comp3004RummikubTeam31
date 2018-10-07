import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.Button;
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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

public class Ui extends Application implements EventHandler<ActionEvent>
{
	Stage window;
	
	Scene mainMenuScene;
	StackPane mainMenu;
	
	Scene rummiScene;
	StackPane playBoard;
	
	Button startButton;
	Button testButton;
	
	Button[][] tableButtons = new Button[12][12];
	Button[] playerHandButtons = new Button[14];
	
	Boolean tracing = true;
	public static void main(String [] args) 
	{
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
		window.setTitle("Rummikub");
		
		//The layoutPane for the gridded table in the center
		TilePane tablePane = new TilePane();
		tablePane.setPrefRows(12); //Sets the row length to 12
		tablePane.setPrefColumns(12); //Sets the column length to 12
		ObservableList<Node> list = tablePane.getChildren();
		
		//Adds all of the table buttons onto the table
		for(int x=0;x<tableButtons.length;x++)
		{
			for(int y=0;y<tableButtons[0].length;y++)
			{
				tableButtons[x][y] = new Button();
				tableButtons[x][y].setText("x: "+x+", y: "+y+"\nTable\nCards");
				list.addAll(tableButtons[x][y]);
			}
		}
		
		
		//The layoutPane for the players hand at the bottom
		HBox playerHand = new HBox(5);
		playerHand.setPadding(new Insets(10)); //Sets the spacing between the cards
		playerHand.setAlignment(Pos.BASELINE_CENTER); //Centers the card in the bottom middle
		
		//Adds the starting cards to the players hand
		for(int x=0;x<playerHandButtons.length;x++)
		{
			playerHandButtons[x] = new Button();
			playerHandButtons[x].setText("x: "+x+",\nPlayer\nCards\nGo\nHere");
			playerHand.getChildren().add(playerHandButtons[x]);
		}
		
		//Creates the text console where it will ouput any necessary info
		TextField console = new TextField();
		console.setText("This is where it displays current players turn as well as what the AI did on their turn.");
		console.setEditable(false); //Makes it not editable
		console.setMinHeight(100); //sets the size of the box
		console.setAlignment(Pos.BASELINE_LEFT); //Centers the text to the bottom left
		
		//The layoutPane for the overarching skeleton (holds all the other layouts)
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(console); 
		borderPane.setBottom(playerHand); 
		borderPane.setCenter(tablePane); 
		
		rummiScene = new Scene(borderPane);
		
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
		
		if(event.getSource() == testButton)
		{
			if(tracing) System.out.println("Test Button Pressed");
			//window.setScene(rummiScene);
		}
	}
}
