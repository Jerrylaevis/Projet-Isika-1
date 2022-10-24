// Authors : Marie Briere, Joachim Ouafo

package fr.isika.cda21.Projet1Groupe3.scenes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.isika.cda21.Projet1Groupe3.entites.Departement;
import fr.isika.cda21.Projet1Groupe3.entites.Stagiaire;
import fr.isika.cda21.Projet1Groupe3.gestionAbrBinaire.AbrBinaire;
import fr.isika.cda21.Projet1Groupe3.objetsGraphiques.PersoButton;
import fr.isika.cda21.Projet1Groupe3.objetsGraphiques.PersoLabel;
import fr.isika.cda21.Projet1Groupe3.objetsGraphiques.PersoTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
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
		public TextField textPromo; 
		public TextField textAnnee; //-> rendre 4 chiffres obligatoires
		//BOUTONS
		public ChoiceBox btnDepartement; 
		public static List listeDepartement; //voir si peut être dans la classe dép
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
		public AnnuaireScene(Stage stage, AbrBinaire arbre, RandomAccessFile raf) { //ajouter la liste Stagiaire en paramètres
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
				stage.setScene(new LoginScene(stage,arbre,raf));
			}
		});
		//2eme ligne : NOM et PRENOM
		nom = new Label ("Nom :");
		textNom = new PersoTextField();
		prenom = new PersoLabel ("Prénom :");
		textPrenom = new PersoTextField();
		formulaire.addRow(1, nom,textNom, prenom, textPrenom);
		
		//3eme ligne : DEPARTEMENT
		departement = new PersoLabel ("Département :");
		//on récupère les départements dans une liste à partir d'un fichier txt
		listeDepartement = new ArrayList<>();
		btnDepartement = new ChoiceBox<String>();
		btnDepartement.setStyle("-fx-focus-color: orange; -fx-faint-focus-color: transparent");
		listerDep(); //lecture du fichier txt, récupération de la liste département et remplissage de la choice box	
		formulaire.addRow(2, departement, btnDepartement);
		
		//4eme ligne : PROMO + ANNEE
		promo = new PersoLabel ("Promotion : ");
		textPromo = new PersoTextField();
		anneePromo = new PersoLabel ("Année :");
		textAnnee = new PersoTextField();
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
		observableList = FXCollections.observableArrayList(arbre.listeGND(raf));
		table = new TableView<>(observableList);
		
		TableColumn <Stagiaire, String> colNom = new TableColumn<>("Nom");
		colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		TableColumn <Stagiaire, String> colPrenom = new TableColumn<>("Prénom");
		colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		TableColumn <Stagiaire, String> colDep = new TableColumn<>("Département");
		colDep.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("dep"));
		TableColumn <Stagiaire, String> colPromo = new TableColumn<>("Promotion");
		colPromo.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));
		TableColumn <Stagiaire, String> colAnnee = new TableColumn<>("Année");
		colAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("anneeF"));
		
		table.getColumns().addAll(colNom,colPrenom,colDep,colPromo,colAnnee);
		table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
		table.setStyle("-fx-focus-color: orange; -fx-faint-focus-color: transparent");
		table.setPrefSize(500, 650);
	
		//sous le tableau : Boutons
		buttons = new HBox();
		btnImprimer = new PersoButton ("Imprimer");
		// Action sur le bouton Imprimer
		btnImprimer.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// Creation du printer job
				PrinterJob job = PrinterJob.createPrinterJob();
				// Montre la boite de dialogue
				boolean proceed = job.showPrintDialog(stage);
				// Si l'utilisateur clique sur imprimer dans la boite de dialogue
				if (proceed) {
					job.jobStatusProperty().addListener((observable, oldValue, newValue) -> {
					});
					// Imprime la zone texte
					boolean printed = job.printPage(table);
					if (printed) {
						job.endJob();
					}
				}
			}
		}); // fin action sur le bouton imprimer
		btnSupprimer = new PersoButton ("Supprimer");
		btnModifier = new PersoButton ("Modifier");
		
		//Action sur les boutons supprimer et modifier
		btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {	
				//vérifier si un stagiaire 
				alerteSupprimer();
			}
		});
		btnModifier.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				alerteModifier();
			}
		});
		
		buttons.getChildren().addAll(btnImprimer, btnSupprimer, btnModifier);
		
		affichageAdmin(); //AFFICHAGE ou MASQUAGE DES BOUTONS SUPP et MOD
		
		listeStagiaire.getChildren().addAll(titreListe, table, buttons);
		listeStagiaire.setPadding(new Insets(0,20,0,0));
		
		listeStagiaire.setPrefWidth(1000);
		root2.setCenter(listeStagiaire);
		
	}
		
	//--------------METHODES---------------
		public void affichageAdmin() {
			//si le user clique sur Acceder -> isAdmin = faux et les btn supprimer et modifier sont invisibles
			//si le user clique sur Valider -> isAdmin = vrai et les btn sont visibles
			if (LoginScene.isAdmin == false) {
				//table n'est pas éditable en mode visiteur
				btnSupprimer.setVisible(false);
				btnModifier.setVisible(false);
			}
		}
		
		public void listerDep() {
			try {
				FileReader fr = new FileReader("src/mesFichiers/departements.txt");
				BufferedReader br = new BufferedReader(fr);
				
				while(br.ready()) { //chaque ligne lue = new département qu'on ajoute à la liste
					String departement = br.readLine();
					Departement newDepartement = new Departement(departement);
					listeDepartement.add(newDepartement);
				}
				
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i<listeDepartement.size(); i++) {
				 btnDepartement.getItems().add(listeDepartement.get(i));
			 }
		}
		
		//les fenêtres d'alerte gèrent directement le lancement des actions lorsqu'on clique sur OK, et ferment la fenêtre dans les 2 cas (OK ou CANCEL)
		public void alerteSupprimer() {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Supprimer ?");
			alert.setHeaderText("Etes-vous sûr de vouloir supprimer le stagiaire ?");
			
			Optional<ButtonType> option = alert.showAndWait();
			
			if (option.get()==ButtonType.OK) {
				
				//lancer la méthode supprimer
			}
		}
		
		public void alerteModifier() {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Modifier ?");
			alert.setHeaderText("Etes-vous sûr de vouloir modifier le stagiaire ?");
			
			Optional<ButtonType> option = alert.showAndWait();
			
			if (option.get()==ButtonType.OK) {
				
				//lancer la méthode modifier
			}
		}
}