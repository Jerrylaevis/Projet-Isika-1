package fr.isika.cda21.Projet1Groupe3.application;
	
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import fr.isika.cda21.Projet1Groupe3.gestionAbrBinaire.AbrBinaire;
import fr.isika.cda21.Projet1Groupe3.scenes.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static RandomAccessFile raf;
	public static AbrBinaire arbre ;
	
	
	@Override
	
	public void start(Stage stage) {
		
		try {
			//stage.getIcons().add(new Image ("logo.png"));
			stage.setTitle("ANNUAIRE DES STAGIAIRES");
			stage.setScene(new LoginScene(stage, arbre, raf));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			arbre = new AbrBinaire();
			raf = new RandomAccessFile(ConstantesDAppli.getNomFichierBin(), "rw");
			arbre.creerFichierBinDepuisFichierTxt(raf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		launch(args);
	}
}