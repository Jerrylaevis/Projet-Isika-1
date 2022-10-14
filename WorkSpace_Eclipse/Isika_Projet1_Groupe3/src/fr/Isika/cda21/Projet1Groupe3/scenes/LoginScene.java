// Authors : Marie Briere, Joachim Ouafo

package fr.Isika.cda21.Projet1Groupe3.scenes;

import fr.Isika.cda21.Projet1Groupe3.objetsGraphiques.PersoButton;
import fr.Isika.cda21.Projet1Groupe3.objetsGraphiques.PersoHBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends Scene {

	//---------------ATTRIBUTS-----------------
	//Layout
	public VBox root;
	public HBox ligne1;
	public HBox ligne2;
	public HBox ligne3;
	public HBox ligne4;
	public HBox ligne5;
	public HBox ligne6;
	public Label espaceAdmin;
	public Label id;
	public TextField textId;
	public Label mdp;
	public PasswordField textMdp;
	public Button valider;
	public Button acceder;
	public Stage stage;
	public Label alert;
	public String idAdmin;
	public String mdpAdmin;

	//--------------CONSTRUCTEUR---------------
	public LoginScene(Stage stage) {
		super(new VBox(), 600, 500);
		acceder = new PersoButton("ACCEDER A L'ANNUAIRE");
		espaceAdmin = new Label("ESPACE ADMINISTRATEUR");
		id = new Label("Identifiant : ");
		textId = new TextField();
		textId.setPromptText("Votre identifiant");
		mdp = new Label("Mot de passe : ");
		textMdp = new PasswordField();
		textMdp.setPromptText("Votre mot de passe");
		alert = new Label("");
		//Ici pour modifier l'acces admin
		idAdmin = "Admin";
		mdpAdmin = "0000";
		valider = new PersoButton("Valider");
		
		//HBOX 
		ligne1 = new PersoHBox();
		ligne2 = new PersoHBox();
		ligne3 = new PersoHBox();
		ligne4 = new PersoHBox();
		ligne5 = new PersoHBox();
		ligne6 = new PersoHBox();
		ligne1.getChildren().add(acceder);
		ligne2.getChildren().add(espaceAdmin);
		ligne3.getChildren().addAll(id, textId);
		ligne4.getChildren().addAll(mdp, textMdp);
		ligne5.getChildren().add(valider);
		ligne6.getChildren().add(alert);
		
		root = ((VBox)this.getRoot());
		root.getChildren().addAll(ligne1,ligne2,ligne3,ligne4,ligne5,ligne6);
		
		//Mise en forme de la pane
		root.setPadding(new Insets(100,0,0,150));
		
		acceder.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				stage.setScene(new AnnuaireScene(stage));
			}
		});
		
		valider.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				if (textId.getText().equals("") || textMdp.getText().equals("")) {
					alert.setText("Veuillez remplir les champs");
				} else if ((!textId.getText().equals(idAdmin)) || (!textMdp.getText().equals(mdpAdmin))) {
					alert.setText("ID ou MDP incorrect");
				} else { 
					//à modifier en fonction de la classe 2e scène
					stage.setScene(new AnnuaireScene(stage));
				}
				};
		});
	}
}