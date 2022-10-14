package fr.Isika.cda21.Projet1Groupe3.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.Isika.cda21.Projet1Groupe3.entites.Stagiaire;
import fr.Isika.cda21.Projet1Groupe3.gestionABR.Noeud;
import fr.Isika.cda21.Projet1Groupe3.gestionABR.NoeudBin;

public class Tests {
	
	public static void main(String[] args) {
		
		try {
			//NoeudBin.creerFichierBinDepuisFichierTxt();
			RandomAccessFile  raf =new RandomAccessFile(ConstantesDAppli.getNomFihierBin(), "rw");
			System.out.println(NoeudBin.lireNoeudBinAIndex(13, raf));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stagiaire jerome = new Stagiaire ("Vallin", "Jérôme", "69", "CDA21", 2022);
		
				
	}

}
