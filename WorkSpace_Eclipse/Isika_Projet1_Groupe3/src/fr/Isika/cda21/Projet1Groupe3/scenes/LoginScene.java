// Authors : Marie Briere, Joachim Ouafo

package fr.Isika.cda21.Projet1Groupe3.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginScene extends Scene {

	//---------------ATTRIBUTS-----------------
	public Label espaceAdmin;
	public Label id;
	public TextField textId;
	public Label mdp;
	public PasswordField textMdp;
	public Button valider;
	public Button acceder;
	public GridPane root;
	public Stage stage;
	public Label alert;
	public final String ID_ADMIN = "admin";
	public final String MDP_ADMIN = "0000";

	//--------------CONSTRUCTEUR---------------
	public LoginScene(Stage stage) {
		super(new GridPane(), 600, 500);
		acceder = new Button("ACCEDER A L'ANNUAIRE");
		espaceAdmin = new Label("ESPACE ADMINISTRATEUR");
		id = new Label("Identifiant :");
		textId = new TextField();
		textId.setPromptText("Votre identifiant");
		mdp = new Label("Mot de passe :");
		textMdp = new PasswordField();
		textMdp.setPromptText("Votre mot de passe");
		valider = new Button("Valider");
		alert = new Label("");

		root = ((GridPane)this.getRoot());
		root.addRow(1, acceder);
		root.addRow(3, espaceAdmin);
		root.addRow(4, id, textId);
		root.addRow(5, mdp, textMdp);
		root.addRow(6, valider);
		root.addRow(7,alert);

		acceder.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				stage.setScene(new AnnuaireScene(stage));
			}
		});
		
		valider.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				if (textId.getText().equals("") || textMdp.getText().equals("")) {
					alert.setText("Veuillez remplir les champs");
				} else if ((textId.getText().equals(ID_ADMIN)) || (textMdp.getText().equals(MDP_ADMIN))) {
					stage.setScene(new AnnuaireScene(stage));
				} else { 
					//à modifier en fonction de la classe 2e scène
					alert.setText("ID ou MDP incorrect");
				}
				};
		});
	}
}
