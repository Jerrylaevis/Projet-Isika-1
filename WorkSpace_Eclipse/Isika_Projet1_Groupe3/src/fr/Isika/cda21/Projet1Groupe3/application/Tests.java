package fr.Isika.cda21.Projet1Groupe3.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import fr.Isika.cda21.Projet1Groupe3.entites.Stagiaire;
import fr.Isika.cda21.Projet1Groupe3.gestionABR.Noeud;

public class Tests {
	
	public static void main(String[] args) {
	
		Noeud annuaireABR = Noeud.creerABRDepuisFichierTxt();
		System.out.println(annuaireABR.toString()); 
		
		
		/*giaire jerome = new Stagiaire ("Vallin", "Jérôme", 69, "CDA21", 2022);
		Stagiaire mila = new Stagiaire ("Keuro", "Mila", 34, "CDA21", 2022);
		Stagiaire marie = new Stagiaire ("Briere", "Marie", 75, "CDA21", 2022);
		Stagiaire joachim = new Stagiaire ("Ouafo", "Joachim", 75, "CDA21", 2022);
		
		
		Noeud racineJerome = new Noeud (jerome, null, null);
		racineJerome.ajouterNoeud(joachim);
		racineJerome.ajouterNoeud(marie);
		racineJerome.ajouterNoeud(mila);
	
	
		System.out.println(racineJerome.toString());
	
		
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}

}
