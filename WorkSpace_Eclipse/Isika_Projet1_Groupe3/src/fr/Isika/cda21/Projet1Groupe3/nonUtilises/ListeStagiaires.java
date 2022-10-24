package fr.Isika.cda21.Projet1Groupe3.nonUtilises;

import java.util.ArrayList;
import java.util.List;

import fr.Isika.cda21.Projet1Groupe3.entites.Stagiaire;

public class ListeStagiaires extends ArrayList {    // Comment ça se constuit une ArrayList ??

	private List<Stagiaire> values;
	
	// ************** Constructeurs ***********************

	public ListeStagiaires(List<Stagiaire> liste) {
		super();
		this.values = liste;
		
	}
	
	public ListeStagiaires() {
		super();
		this.values = new ArrayList<>();
		
	}
	
	// ************ Méthodes spécifiques *******************
	
	// Affiche la liste à la console
	public void afficheListe() {
		for (Stagiaire stag : this.values) {
			System.out.println(stag);
		}
	}
	
	
	
	// ************** Getters et Setters *******************

	public List<Stagiaire> getValues() {
		return values;
	}

	public void setValues(List<Stagiaire> values) {
		this.values = values;
	}
	
	
	
}
