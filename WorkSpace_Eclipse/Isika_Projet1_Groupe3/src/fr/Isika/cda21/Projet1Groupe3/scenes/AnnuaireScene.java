// Authors : Marie Briere, Joachim Ouafo

package fr.Isika.cda21.Projet1Groupe3.scenes;

import fr.Isika.cda21.Projet1Groupe3.entites.Stagiaire;
import fr.Isika.cda21.Projet1Groupe3.objetsGraphiques.PersoButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnnuaireScene extends Scene {
	
	//---------------ATTRIBUTS-----------------
		public BorderPane root2;
		public GridPane formulaire;
		public VBox listeStagiaire;
		public HBox buttons;
		public Stage stage;
		//Ajouter la liste stagiaire
		//Titres
		public Label titreFormulaire;
		public Label titreListe;
		//Labels formulaire
		public Label nom;
		public Label prenom;
		public Label departement;
		public Label promo;
		public Label anneePromo;
		public Label alertAjout; //Label qui s'affiche quand on souhaite ajouter un stagiaire mais qu'il manque des infos
		//TextFields formulaire
		public TextField textNom;
		public TextField textPrenom;
		public TextField textDep; 
		public TextField textPromo; 
		public TextField textAnnee; //-> rendre 4 chiffres obligatoires
		//BOUTONS
		//public ChoiceBox btnDepartement; -> choix liste département ?
		//public DatePicker btnDate; -> à voir ?
		public Button btnRetour;
		public Button btnRechercher;
		public Button btnAjouter;
		public Button btnImprimer;
		public Button btnSupprimer;
		public Button btnModifier;
		//Liste observable
		public ObservableList<Stagiaire> observableList;
		public TableView<Stagiaire> table;
		
	//--------------CONSTRUCTEUR---------------
		public AnnuaireScene(Stage stage) { //ajouter la liste Stagiaire en paramètres
		super(new BorderPane(),1200,750);
			
		//PARTIE GAUCHE = FORMULAIRE
		formulaire = new GridPane();	
		
		//1ere ligne : BTNRETOUR/TITRE
		btnRetour = new PersoButton("Retour");
		titreFormulaire = new Label ("FORMULAIRE D'ENREGISTREMENT");
		formulaire.addRow(0, btnRetour,titreFormulaire);
		
		//ACTION BTNRETOUR -> LOGINSCENE
		btnRetour.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				stage.setScene(new LoginScene(stage));
			}
		});
		
		//2eme ligne : NOM et PRENOM
		nom = new Label ("Nom :");
		textNom = new TextField();
		prenom = new Label ("Prénom :");
		textPrenom = new TextField();
		formulaire.addRow(1, nom,textNom, prenom, textPrenom);
		
		//3eme ligne : DEPARTEMENT
		departement = new Label ("Département :");	
		//btnDepartement = new ChoiceBox<String>(); -> ajouter une liste de département	?
		textDep = new TextField();
		formulaire.addRow(2, departement, textDep);
		
		//4eme ligne : PROMO + ANNEE
		promo = new Label ("Promotion : ");
		textPromo = new TextField();
		anneePromo = new Label ("Année :");
		textAnnee = new TextField();
		formulaire.addRow(3, promo, textPromo, anneePromo, textAnnee);
		
		//5eme ligne : BTN RECHERCHER ET AJOUTER
		btnRechercher = new PersoButton("Rechercher");
		btnAjouter = new PersoButton("Ajouter");
		formulaire.addRow(4, btnRechercher, btnAjouter);
		
		root2 = ((BorderPane)this.getRoot());
		root2.setLeft(formulaire);
		formulaire.setPadding(new Insets(0,50,0,0));
		
		//ACTIONS BTN AJOUTER ET RECHERCHER
		//AJOUTER : alerte sur obligation de remplir les champs
				//border rouge si textField = ""
				//affiche la fenêtre de confirmation si tous les champs sont remplis
		
		//RECHERCHER : lien vers la méthode rechercher et affiche la liste à jour
					//modifier la tableView 
		
		//PARTIE DROITE = TABLEVIEW
		listeStagiaire = new VBox();
		titreListe = new Label ("LISTE DES STAGIAIRES");

		//2eme ligne : Tableview
		//A COMPLETER AVEC LA LISTE STAGIAIRES !
		observableList = FXCollections.observableArrayList();
		table = new TableView<>(observableList);
		
		TableColumn <Stagiaire, String> colNom = new TableColumn<>("Nom");
		//colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		TableColumn <Stagiaire, String> colPrenom = new TableColumn<>("Prénom");
		//colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		TableColumn <Stagiaire, String> colDep = new TableColumn<>("Département");
		//colDep.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		TableColumn <Stagiaire, String> colPromo = new TableColumn<>("Promotion");
		//colPromo.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));
		TableColumn <Stagiaire, String> colAnnee = new TableColumn<>("Année de promotion");
		//colPromo.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));
		
		table.getColumns().addAll(colNom,colPrenom,colDep,colPromo,colAnnee);
		table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
		table.setPrefSize(500, 650);
	
		//sous le tableau : Boutons
		buttons = new HBox();
		btnImprimer = new PersoButton ("Imprimer");
		btnSupprimer = new PersoButton ("Supprimer");
		btnModifier = new PersoButton ("Modifier");
		buttons.getChildren().addAll(btnImprimer, btnSupprimer, btnModifier);
		
		listeStagiaire.getChildren().addAll(titreListe, table, buttons);
		listeStagiaire.setPadding(new Insets(0,20,0,0));
		
		listeStagiaire.setPrefWidth(1000);
		root2.setCenter(listeStagiaire);
		
	}
		

}