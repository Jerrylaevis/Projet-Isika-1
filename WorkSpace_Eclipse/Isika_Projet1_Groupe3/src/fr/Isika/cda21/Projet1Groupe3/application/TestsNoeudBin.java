package fr.Isika.cda21.Projet1Groupe3.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import fr.Isika.cda21.Projet1Groupe3.gestionABR.NoeudBin;
import fr.Isika.cda21.Projet1Groupe3.nonUtilises.ListeStagiaires;
import fr.Isika.cda21.Projet1Groupe3.nonUtilises.Noeud;
import fr.Isika.cda21.Projet1Groupe3.outils.Outils;

public class TestsNoeudBin {
	
	public static void main(String[] args) {
		
		try {
			NoeudBin.creerFichierBinDepuisFichierTxt();
			RandomAccessFile  raf =new RandomAccessFile(ConstantesDAppli.getNomFichierBin(), "rw");
			//System.out.println(NoeudBin.lireNoeudBinAIndex(13, raf));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Stagiaire jerome = new Stagiaire ("Vallin", "Jérôme", "69", "CDA21", 2022);
		
		// ********************* AJOUTS POSTERIEURS A V2.1 **********************
		
		
		
		System.out.println();
		try {
			RandomAccessFile  raf =new RandomAccessFile(ConstantesDAppli.getNomFichierBin(), "rw");
			
			//for (int k=0; k<raf.length()/4; k++) {
			//	System.out.println(raf.readInt()+" ");
			//}
			
			Outils.afficherListe(NoeudBin.listeGND(raf, 0, new ArrayList<>()));
			System.out.println();
			System.out.println("Résultat de la recherche :");
			Outils.afficherListe(NoeudBin.recherchePrenom("Rosie", raf, 0, new ArrayList<>()));
			
			System.out.println();
			System.out.println("Résultat de la recherche partielle :");
			Outils.afficherListe(NoeudBin.recherchePrenomPartiel("P", raf, 0, new ArrayList<>()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
