package fr.Isika.cda21.Projet1Groupe3.application;

public abstract class ConstantesDAppli {
	
	private static final String nomFihierTxt = "src/mesFichiers/STAGIAIRES.DON";
	private static final String nomFihierBin = "src/mesFichiers/STAGIAIRES.bin";
	//private static final String nomFihierTxt = "src/mesFichiers/FichierTest.DON";
	
	public static final int TAILLE_MAX_NOM = 25;      //50 octets
	public static final int TAILLE_MAX_PRENOM = 20;   //40 octets
	public static final int TAILLE_MAX_DEP = 2;		  // 4 octets
	public static final int TAILLE_MAX_PROMO = 11;    //22 octets
	//public static final int TAILLE_MAX_ANNEE_F = 1; // 4 octets
	
	public static final int TAILLE_NOEUD_BIN = 128; //120 + 4 + 4
	
	
	

	public static String getNomFihierTxt() {
		return nomFihierTxt;
	}
	
	public static String getNomFihierBin() {
		return nomFihierBin;
	}

	public static int getTailleMaxNom() {
		return TAILLE_MAX_NOM;
	}

	public static int getTailleMaxPrenom() {
		return TAILLE_MAX_PRENOM;
	}

	public static int getTailleMaxDep() {
		return TAILLE_MAX_DEP;
	}

	public static int getTailleMaxPromo() {
		return TAILLE_MAX_PROMO;
	}

	public static int getTailleNoeudBin() {
		return TAILLE_NOEUD_BIN;
	}


}
