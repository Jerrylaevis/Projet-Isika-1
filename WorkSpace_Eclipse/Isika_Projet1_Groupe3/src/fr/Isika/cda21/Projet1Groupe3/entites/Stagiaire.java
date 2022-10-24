// Authors : Rocio Keuro, Jérôme Vallin

package fr.isika.cda21.Projet1Groupe3.entites;

import fr.isika.cda21.Projet1Groupe3.application.ConstantesDAppli;

public class Stagiaire {
	
	// ******************** ATTRIBUTS *******************
	
	private String nom;
	private String prenom;
	private String dep;
	private String promo;
	private int anneeF;
	
	// **************** CONSTRUCTEURS **********************
	
	public Stagiaire(String nom, String prenom, String dep, String promo, int anneeF) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dep = dep;
		this.promo = promo;
		this.anneeF = anneeF;
	}
	
	public Stagiaire() {
		
	}

	// **************** METHODES SPECIFIQUES **********************
	
	// Comparaison de stagiaires. Clés : Nom puis Prénom puis Dpmt puis Formation (!! pas année car redondant avec promo)
	public int compareTo(Stagiaire stagiaireAComparer) {
		if (this.nom.compareTo(stagiaireAComparer.getNom()) == 0) { 
			if (this.prenom.compareTo(stagiaireAComparer.getPrenom()) == 0) {
				if (this.dep.compareTo(stagiaireAComparer.getDep())==0) {
					return this.promo.compareTo(stagiaireAComparer.getPromo()); 
				}
				else {
					return this.dep.compareTo(stagiaireAComparer.getDep());
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
		//return prenom+" "+nom+"  ";
	}

	// *************** MISE AU BON FORMAT *******************
	
	public String nomLong() {
		String res = this.nom;
		if (res.length()>ConstantesDAppli.TAILLE_MAX_NOM) {
				res.substring(0, ConstantesDAppli.TAILLE_MAX_NOM);
		}
		for (int i=this.nom.length();i<ConstantesDAppli.TAILLE_MAX_NOM;i++) {
			res+="*";
		}
		return res;
	}
	
	public String prenomLong() {
		String prenomLong = prenom;
		if (prenomLong.length()>ConstantesDAppli.TAILLE_MAX_PRENOM) {
			prenomLong.substring(0, ConstantesDAppli.TAILLE_MAX_PRENOM);
	}
		for(int i = prenom.length(); i < ConstantesDAppli.TAILLE_MAX_PRENOM; i++) {
			prenomLong +="*";
		}
		return prenomLong;
	}
	
	public String depLong() {
		String depLong = dep;
		if (depLong.length()>ConstantesDAppli.TAILLE_MAX_DEP) {
			depLong.substring(0, ConstantesDAppli.TAILLE_MAX_DEP);
	}
		for(int i = dep.length(); i < ConstantesDAppli.TAILLE_MAX_DEP; i++) {
			depLong += "*";
		}
		return depLong;
	}
	
	public String promoLong() {	
		String promoLong =promo;
		if (promoLong.length()>ConstantesDAppli.TAILLE_MAX_PROMO) {
			promoLong.substring(0, ConstantesDAppli.TAILLE_MAX_PROMO);
	}
		for(int i = promo.length(); i < ConstantesDAppli.TAILLE_MAX_PROMO; i++) {
			promoLong += "*";
		}
		
		return promoLong;
	}
	
	// ********************* AJOUTS POSTERIEURS A V2.1 **********************
	
	
	
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

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
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