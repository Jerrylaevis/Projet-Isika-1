// Authors : Rocio Keuro, Jérôme Vallin

package fr.Isika.cda21.Projet1Groupe3.entites;

public class Stagiaire {
	
	// ******************** ATTRIBUTS *******************
	
	private String nom;
	private String prenom;
	private int dep;
	private String promo;
	private int anneeF;
	
	// **************** CONSTRUCTEURS **********************
	
	public Stagiaire(String nom, String prenom, int dep, String promo, int anneeF) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dep = dep;
		this.promo = promo;
		this.anneeF = anneeF;
	}

	// **************** METHODES SPECIFIQUES **********************
	
	// Comparaison de stagiaires. Clés : Nom puis Prénom puis Dpmt puis Formation (!! pas année car redondant avec promo)
	public int compareTo(Stagiaire stagiaireAComparer) {
		if (this.nom.compareTo(stagiaireAComparer.getNom()) == 0) { 
			if (this.prenom.compareTo(stagiaireAComparer.getPrenom()) == 0) {
				if (this.dep==stagiaireAComparer.getDep()) {
					return this.promo.compareTo(stagiaireAComparer.getPromo()); 
				}
				else {
					return this.dep-stagiaireAComparer.getDep();
				}
			}
			else {
				return this.prenom.compareTo(stagiaireAComparer.getPrenom());
			}
		}
		else {
			return this.nom.compareTo(stagiaireAComparer.getNom());
		}
	}
	
	
	
	@Override
	public String toString() {
		return prenom+" "+nom+", département "+dep+", de la promo "+promo+" ("+anneeF+")";
	}

	
	
	
	
	// *************** GETTERS ET SETTERS *******************
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public int getAnneeF() {
		return anneeF;
	}


	public void setAnneeF(int anneeF) {
		this.anneeF = anneeF;
	}
	
	
	

}