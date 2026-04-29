package breakout;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Breakout extends Application{
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Breakout");
        showTitleScreen();
        stage.show();
    }

    public static void showTitleScreen() {
        Text title = new Text("Breakout");
        title.setFont(new Font("Impact", 48));

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            BallWorld world = new BallWorld();
            Scene gameScene = new Scene(world, 800, 600);
            stage.setScene(gameScene);
            world.start();
        });
        
        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(title, playButton);

        Scene titleScene = new Scene(layout, 800, 600);
        stage.setScene(titleScene);
    }
}

