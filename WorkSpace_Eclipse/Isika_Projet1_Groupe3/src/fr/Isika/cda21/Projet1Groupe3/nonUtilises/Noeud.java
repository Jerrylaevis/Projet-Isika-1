// Authors : Rocio Keuro, Jérôme Vallin

package fr.Isika.cda21.Projet1Groupe3.nonUtilises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fr.Isika.cda21.Projet1Groupe3.application.ConstantesDAppli;
import fr.Isika.cda21.Projet1Groupe3.entites.Stagiaire;

public class Noeud {
	
	// *************** ATTRIBUTS*************
	
	private Stagiaire cle;
	private Noeud filsGauche;
	private Noeud filsDroit;
	
	// *************** CONSTRUCTEUR*************
	
	public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
		super();
		this.setCle(cle);
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}
	
	
	// *************** METHODES SPECIFIQUES*************
	
	
	public void ajouterNoeud (Stagiaire nouveauStag) {
		if (nouveauStag.compareTo(cle)==0) {
			System.out.println("Stagiaire déjà présent dans l'arbre");
		}
		else if (nouveauStag.compareTo(cle)>0) {
				if (this.filsDroit == null) {
					Noeud nouvelEmplacement = new Noeud(nouveauStag, null, null);
					this.filsDroit=nouvelEmplacement;
				}
				else {
					this.filsDroit.ajouterNoeud(nouveauStag);
				}
			}
		else {
			if (this.filsGauche == null) {
				Noeud nouvelEmplacement = new Noeud(nouveauStag, null, null);
				this.filsGauche=nouvelEmplacement;
			}
			else {
				this.filsGauche.ajouterNoeud(nouveauStag);
			}
		}
	}
	
	public static Noeud creerABRDepuisFichierTxt() {
		File annuaireTxt = new File(ConstantesDAppli.getNomFichierTxt());
		
		Noeud racine = null;
		
		try {
			FileReader fr = new FileReader(annuaireTxt);
			BufferedReader br = new BufferedReader(fr);
			
			String nom = br.readLine().toUpperCase();
			String prenom = br.readLine();
			String dep = br.readLine();
			String promo = br.readLine();
			int anneeF = Integer.parseInt(br.readLine());
			br.readLine();						// pour passer la ligne *
			
			Stagiaire stagiaireRoi = new Stagiaire(nom, prenom, dep, promo, anneeF);
			racine = new Noeud(stagiaireRoi, null, null);
			
			while(br.ready()) {
				nom = br.readLine().toUpperCase();
				prenom = br.readLine();
				dep = br.readLine();
				promo = br.readLine();
				anneeF = Integer.parseInt(br.readLine());
				br.readLine();
			
				Stagiaire nouveauStagiaire = new Stagiaire(nom, prenom, dep, promo, anneeF);
				racine.ajouterNoeud(nouveauStagiaire);
			}
			
			
			br.close();
			fr.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return racine;
	}
	
	
	
	

	// *************** TOSTRING RECURSIF POUR AFFICHER LA TOTALITE DE L'ARBRE*************
	
	public String toString() {
		
		String res = "";
		if (this.filsGauche!=null) {
		res = res + this.filsGauche.toString();//G
		}
		res = res + " " + this.cle.toString() + " ";//N
		if (this.filsDroit!=null) {
		res = res + this.filsDroit.toString();//D
		}
		return res;
		
	}
	
	
	// *************** GETTERS & SETTERS*************
	
		public Stagiaire getCle() {
			return cle;
		}
		
	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}
	
	public Noeud getFilsGauche() {
		return filsGauche;
	}
	
	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}
	
	public Noeud getFilsDroit() {
		return filsDroit;
	}
	
	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}
	
}