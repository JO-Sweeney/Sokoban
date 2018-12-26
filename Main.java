package GridTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application{
	
	public Game game;
	public Stage mWindow;
	
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		
		mWindow = window;
		
		game = new Game();
		
		Scene scene = new Scene(game.getGrid());
		
		scene.setOnKeyPressed(e -> {														//Src = https://stackoverflow.com/questions/33224161/how-do-i-run-a-function-on-a-specific-key-press-in-javafx
		    if (e.getCode() == KeyCode.W) {
		    	game.moveObject("up");
		    	game.updateGrid();
		    }else if(e.getCode() == KeyCode.A) {
		    	game.moveObject("left");
		    	game.updateGrid();
		    }else if(e.getCode() == KeyCode.S) {
		    	game.moveObject("down");
		    	game.updateGrid();
		    }else if(e.getCode() == KeyCode.D) {
		    	game.moveObject("right");
		    	game.updateGrid();
		    }
		});
		
		window.setScene(scene);
		window.show();
	}

}
