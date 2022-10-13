package fr.Isika.cda21.Projet1Groupe3.application;
	
import fr.Isika.cda21.Projet1Groupe3.scenes.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class TestsGraphiques extends Application {
	@Override
	public void start(Stage stage) {
		try {
			stage.setScene(new LoginScene(stage));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		
		Stage endStage = new Stage();
		Scene endScene = new Scene(new Label("bouh"),50,50);
		endStage.setScene(endScene);
		endStage.show();
		
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
	
}
