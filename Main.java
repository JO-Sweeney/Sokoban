package GridTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application{
	
	private Game game;
	private Stage mWindow;
	private BorderPane mBorderPane;
	private HBox mMenu;
	private Button restartButton;
	private Button nextLevelButton;
	private Button previousLevelButton;
	private int levelNo;
	private Scene scene;
	private Scene winScene;
	private Text winText;
	public MapImporter mMapImporter;
	
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		levelNo = 1;
		mMapImporter = new MapImporter();
		mWindow = window;
		setUI();
		mWindow.show();
	}
	
	public void setUI() {
		
		mMenu = new HBox();
		mBorderPane = new BorderPane();
		restartButton = new Button("Restart Level");
		previousLevelButton = new Button("Previous Level");
		nextLevelButton = new Button("Next Level");
		game = new Game(mMapImporter.getMap(levelNo));
		winText = new Text();
		winText.setText("Level Complete!");
		
		
		restartButton.setOnAction(e -> {
			setUI();
		});
		
		previousLevelButton.setOnAction(e -> {
			if(levelNo > 1) {
				levelNo--; 
			}
			setUI();
		});
		
		nextLevelButton.setOnAction(e -> {
			if(levelNo < 5) {
				levelNo++;
			}
			setUI();
		});
		
		mMenu.getChildren().add(restartButton);
		mMenu.getChildren().add(previousLevelButton);
		mMenu.getChildren().add(nextLevelButton);
		
		mBorderPane.setTop(mMenu);
		mBorderPane.setCenter(game.getGrid());									//center of borderpane is whatever game returns as grid
		mBorderPane.setBottom(game.getMoveDisplay());						//bottom of borderpane is whatever game returns as text - moveNo
		
		
		mBorderPane.setAlignment(game.getMoveDisplay(), Pos.CENTER);
		mBorderPane.setAlignment(mMenu, Pos.CENTER);
		
		
		scene = new Scene(mBorderPane);
		
		scene.setOnKeyPressed(e -> {														//Src = https://stackoverflow.com/questions/33224161/how-do-i-run-a-function-on-a-specific-key-press-in-javafx
		    if (e.getCode() == KeyCode.W) {
		    	game.moveObject("up");
		    	game.updateMoveDisplay();
		    	if(game.getWin()) {
		    		mBorderPane.setCenter(winText);
		    	}else {
		    	game.updateGrid();
		    	}
		    }else if(e.getCode() == KeyCode.A) {
		    	game.moveObject("left");
		    	game.updateMoveDisplay();
		    	if(game.getWin()) {
		    		mBorderPane.setCenter(winText);
		    	}else {
		    	game.updateGrid();
		    	}
		    }else if(e.getCode() == KeyCode.S) {
		    	game.moveObject("down");
		    	game.updateMoveDisplay();
		    	if(game.getWin()) {
		    		mBorderPane.setCenter(winText);
		    	}else {
		    	game.updateGrid();
		    	}
		    }else if(e.getCode() == KeyCode.D) {
		    	game.moveObject("right");
		    	game.updateMoveDisplay();
		    	if(game.getWin()) {
		    		mBorderPane.setCenter(winText);
		    	}else {
		    	game.updateGrid();
		    	}
		    }
		});
		
		mWindow.setScene(scene);
	}

}
