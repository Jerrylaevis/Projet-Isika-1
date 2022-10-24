package fr.isika.cda21.Projet1Groupe3.gestionAbrBinaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda21.Projet1Groupe3.application.ConstantesDAppli;
import fr.isika.cda21.Projet1Groupe3.entites.Stagiaire;
import fr.isika.cda21.Projet1Groupe3.outils.Outils;

public class AbrBinaire {

	Bloc racine;
	
	// ********** CONSTRUCTEURS ***********
	public AbrBinaire() {
		this.racine = null;
	}
	
	// ********** METHODES SPECIFIQUES ***********
	
	// Ajoute le bloc 'blocAAjouter' à l'arbre : détermine son index et demande à la racine de trouver sa place dans l'ABR
	public void ajouterStagiaire(Stagiaire stagAAjouter, RandomAccessFile raf) {
		
		try {
			if (this.racine==null) { 		// arbre vide -> créer racine
				this.racine = new Bloc(0, -1, stagAAjouter, -1, -1, -1);
				racine.ecrireBloc(raf);			//   ???  Peut-on et faut-il effacer le fichier ???
				//System.out.println("Ajout de la racine");	// pour debug
			}
			else {
				this.racine=this.racine.lireBlocAIndex(0, raf);		// MaJ des enfants de la racine (dommage de le faire à chaque fois, peut-on trouver mieux ?)
				int indexNouveauBloc = (int)raf.length()/ConstantesDAppli.TAILLE_BLOC;  	// détermination de l'index
				Bloc blocAAjouter = new Bloc(indexNouveauBloc, -1, stagAAjouter, -1, -1, -1);
				this.racine.ajouterBloc(blocAAjouter, raf);									// on demande au bloc racine de placer le nouveau bloc
				//System.out.println("Ajout du bloc "+stagAAjouter.getNom()+" à l'index "+indexNouveauBloc);	// pour debug
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Crée un ABR sous forme de fichier binaire à partir des infos du fichier .txt
	public void creerFichierBinDepuisFichierTxt(RandomAccessFile annuaireBin) {
		File annuaireTxt = new File(ConstantesDAppli.getNomFichierTxt());		// fichier à lire
		try {
			FileReader fr = new FileReader(annuaireTxt);
			BufferedReader br = new BufferedReader(fr);
			
			while(br.ready()) {
				String nom = br.readLine().toUpperCase();				// lecture des attributs du Stagiaire
				String prenom = br.readLine();
				String dep = br.readLine();
				String promo = br.readLine();
				int anneeF = Integer.parseInt(br.readLine());
				br.readLine();											// pour passer la ligne *
				
				Stagiaire nouveauStagiaire = new Stagiaire(nom, prenom, dep, promo, anneeF);    // crée le nouveau stagiaire
				this.ajouterStagiaire(nouveauStagiaire, annuaireBin);	// ajout du nouveau stagiaire à l'ABR
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Lance la lecture du fichier .bin en parcours GND pour créer une liste dans l'ordre alphabétique
	public List<Stagiaire> listeGND(RandomAccessFile raf) {
		if (this.racine==null) {
			return new ArrayList<>();  // ou null ? La liste vide permet d'utiliser un 'for each'
		}
		else {
			return this.racine.listeGND(raf, 0, new ArrayList<>());
		}
	}

	// Lance la lecture du fichier .bin en parcours GND pour créer une liste des stagiaires de nom égal à 'nom'
	public List<Stagiaire> rechercheNom(String nom, RandomAccessFile raf) {
		if (this.racine==null) {
			return new ArrayList<>();  // ou null ? La liste vide permet d'utiliser un 'for each'
		}
		else {
			return this.racine.rechercheNom(nom.toUpperCase(), raf, 0, new ArrayList<>());
		}
	}

	// Lance la lecture du fichier .bin en parcours GND pour créer une liste des stagiaires de nom commençant par 'nom'
	public List<Stagiaire> rechercheNomPartiel(String nom, RandomAccessFile raf) {
		if (this.racine==null) {
			return new ArrayList<>();  // ou null ? La liste vide permet d'utiliser un 'for each'
		}
		else {
			return this.racine.rechercheNomPartiel(nom.toUpperCase(), raf, 0, new ArrayList<>());
		}
	}

	// Lance la lecture du fichier .bin en parcours GND pour créer une liste des stagiaires de prénom égal à (ou commençant par) 'prenom'
	public List<Stagiaire> recherchePrenom(String prenom, RandomAccessFile raf, boolean partiel) {
		if (this.racine==null) {
			return new ArrayList<>();  // ou null ? La liste vide permet d'utiliser un 'for each'
		}
		else {
			return this.racine.recherchePrenom(Outils.prenomNormalise(prenom), raf, 0, new ArrayList<>(), partiel);
		}
	}



	@Override
	public String toString() {
		if (this.racine==null) {
			return "Arbre vide";
		} else {
		return "Arbre de racine : "+this.racine.toString();
		}
	}

	
	// ********** Getters et Setters ***********
	public Bloc getRacine() {
		return racine;
	}

	public void setRacine(Bloc racine) {
		this.racine = racine;
	}
	
}
