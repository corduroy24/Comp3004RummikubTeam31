import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Ui extends Application implements EventHandler<ActionEvent>
{
	Stage window;
	
	Scene mainMenuScene;
	StackPane mainMenu;
	
	Scene rummiScene;
	StackPane playBoard;
	
	Button startButton;
	
	Boolean tracing = true;
	public static void main(String [] args) 
	{
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception 
	{
		//Sets the default window and the text in the top left
		window = primaryStage;
		window.setTitle("Rummikub");
		
		//Creates the button that launches the game from the starting menu
		startButton = new Button();
		startButton.setText("Start Button");
		startButton.setOnAction(this);
		
		//Creates the pane for the main menu
		mainMenu = new StackPane();
		mainMenu.getChildren().add(startButton);
		
		//Creates the pane for the actual game
		playBoard = new StackPane();
		
		//Sets the size of the windows
		rummiScene = new Scene(playBoard, 1000, 700);
		mainMenuScene = new Scene(mainMenu, 800, 600);
		
		//Makes the window visible
		window.setScene(mainMenuScene);
		window.show();
	}
	public void handle(ActionEvent event) 
	{
		//The listener for the starting button
		if(event.getSource() == startButton)
		{
			if(tracing) System.out.println("Start Button Pressed");
			window.setScene(rummiScene);
		}
	}
}
