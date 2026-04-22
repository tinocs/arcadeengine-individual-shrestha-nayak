package breakout;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Breakout extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Breakout");
		BorderPane root = new BorderPane();
		BallWorld bw = new BallWorld();
		root.setCenter(bw);
		Scene scene = new Scene(root);
		bw.start();
		stage.setScene(scene);
		stage.show();
	}

}
