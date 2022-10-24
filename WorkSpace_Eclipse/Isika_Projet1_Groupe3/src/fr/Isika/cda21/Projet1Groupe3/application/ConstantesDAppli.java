package fr.isika.cda21.Projet1Groupe3.application;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class ConstantesDAppli {
	
	private static final String NOM_FICHIER_BIN = "src/mesFichiers/STAGIAIRES.bin";
	private static final String NOM_FICHIER_BIN_DATE = "src/mesFichiers/Annuaire_"+LocalDate.now().toString()+"_"+LocalTime.now().getHour()+"H"+LocalTime.now().getMinute()+".bin";
	private static final String NOM_FICHIER_TXT = "src/mesFichiers/STAGIAIRES.DON";
	//private static final String NOM_FICHIER_TXT = "src/mesFichiers/FichierTest.DON";
	//private static final String NOM_FICHIER_TXT = "src/mesFichiers/FichierTestDoublons.DON";
	
	// int index									     4 octets
	// int pere										     4 octets
	
	public static final int TAILLE_MAX_NOM = 25;      //50 octets
	public static final int TAILLE_MAX_PRENOM = 20;   //40 octets
	public static final int TAILLE_MAX_DEP = 2;		  // 4 octets
	public static final int TAILLE_MAX_PROMO = 11;    //22 octets
	// int anneeF										 4 octets
	
	// int filsG									  // 4 octets
	// int filsD									  // 4 octets
	// int indexDoublon								  // 4 octets
	
	public static final int TAILLE_NOEUD_BIN = 128; // 120 + 4 + 4
	public static final int TAILLE_BLOC = 140; 		// 4 + 4 + 120 + 4 + 4 + 4
	
	

	public static String getNomFichierTxt() {
		return NOM_FICHIER_TXT;
	}
	
	public static String getNomFichierBin() {
		return NOM_FICHIER_BIN;
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

	public static String getNomFichierBinDate() {
		return NOM_FICHIER_BIN_DATE;
	}


}
