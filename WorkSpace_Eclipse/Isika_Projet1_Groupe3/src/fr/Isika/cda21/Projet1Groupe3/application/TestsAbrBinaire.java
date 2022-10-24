package fr.isika.cda21.Projet1Groupe3.application;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda21.Projet1Groupe3.gestionAbrBinaire.AbrBinaire;
import fr.isika.cda21.Projet1Groupe3.outils.Outils;

public class TestsAbrBinaire {
	

	public static void main(String[] args) {
		
		long t1, t2;

		try {
			RandomAccessFile  raf =new RandomAccessFile(ConstantesDAppli.getNomFichierBinDate(), "rw");
			
			AbrBinaire monABR = new AbrBinaire();
			
			/*// test de la méthode ajouterStagiaire()
				// pour racine
			Stagiaire newStag = new Stagiaire ("Vallin", "Jérôme", "69", "CDA21", 2022);
			System.out.println("Stagiaire créé : "+newStag);
			monABR.ajouterStagiaire(newStag, raf);
			System.out.println("ABR : "+monABR);
			System.out.println();
			
				// pour bloc interne
			newStag = new Stagiaire ("Keuro", "Mila", "34", "CDA21", 2022);
			System.out.println("Stagiaire créé : "+newStag);
			monABR.ajouterStagiaire(newStag, raf);
			System.out.println("ABR : "+monABR);
			System.out.println("Racine lue : "+monABR.getRacine().lireBlocAIndex(0, raf));
			System.out.println();*/
			
			//test de la méthode creerFichierBinDepuisFichierTxt() dans classe AbrBinaire (avec listes de doublons)
			monABR.creerFichierBinDepuisFichierTxt(raf);
			System.out.println();
			//Outils.afficherFichBinInt(raf);
			
			//test de la méthode listeGND() dans classe AbrBinaire
			//System.out.println("Annuaire complet :");
			//Outils.afficherListe(monABR.listeGND(raf));
			System.out.println();
			
			//test de la méthode rechercheNom() dans classe AbrBinaire
			System.out.println("Résultat de la recherche sur le nom 'POTIN' avec AbrBinaire.rechercheNom() :");
			Outils.afficherListe(monABR.rechercheNom("POTIN", raf));
			System.out.println();
			System.out.println("Résultat de la recherche sur le nom 'Potin' avec AbrBinaire.rechercheNom() :");
			t1 = System.nanoTime();
			Outils.afficherListe(monABR.rechercheNom("Potin", raf));
			t2 = System.nanoTime();
			System.out.println("temps : "+(t2-t1));
			System.out.println();
			System.out.println("Résultat de la recherche avec le nom partiel 'Potin' avec AbrBinaire.rechercheNomPartiel() :");
			t1 = System.nanoTime();
			Outils.afficherListe(monABR.rechercheNomPartiel("Potin", raf));
			t2 = System.nanoTime();
			System.out.println();
			System.out.println("temps : "+(t2-t1));
			
			//test de la méthode recherchePrenom() dans classe AbrBinaire
			System.out.println("Résultat de la recherche sur le prénom 'Anne' avec AbrBinaire.recherchePrenom() :");
			Outils.afficherListe(monABR.recherchePrenom("Anne", raf, false));
			System.out.println();
			System.out.println("Résultat de la recherche sur le prénom 'anne' avec AbrBinaire.recherchePrenom() :");
			Outils.afficherListe(monABR.recherchePrenom("anne", raf, false));
			System.out.println();
			System.out.println("Résultat de la recherche avec le prénom partiel 'da' avec AbrBinaire.recherchePrenom() :");
			Outils.afficherListe(monABR.recherchePrenom("da", raf, true));
			System.out.println();
			
			//test de la méthode prenomNormalise()
			System.out.println("Résultat de la recherche avec le prénom 'Jérôme' avec AbrBinaire.recherchePrenom() :");
			Outils.afficherListe(monABR.recherchePrenom("Jérôme", raf, false));
			System.out.println();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
