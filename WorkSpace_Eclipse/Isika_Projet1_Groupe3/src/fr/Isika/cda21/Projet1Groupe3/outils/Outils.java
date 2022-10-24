package fr.isika.cda21.Projet1Groupe3.outils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.isika.cda21.Projet1Groupe3.application.ConstantesDAppli;
import fr.isika.cda21.Projet1Groupe3.entites.Stagiaire;

public class Outils {

	// Affiche une liste de stagiaires à la console
	public static void afficherListe(List<Stagiaire> liste) {
		for (Stagiaire stag : liste) {
			System.out.println(stag);
		}
	}

	// Normalise l'écriture des prénoms pour les recherches
	public static String prenomNormalise(String prenom) {			// Faire une classe PrenomNormalise ?
		String res=prenom.replace("é", "e");
		res = res.replace("è", "e");
		res = res.replace("ê", "e");
		res = res.replace("ë", "e");
		res = res.replace("à", "a");
		res = res.replace("â", "a");
		res = res.replace("ä", "a");
		res = res.replace("î", "i");
		res = res.replace("ï", "i");
		res = res.replace("ô", "o");
		res = res.replace("ö", "o");
		res = res.replace("û", "u");
		res = res.replace("ü", "u");
		res = res.replace("ÿ", "y");
		return res.toLowerCase();
	}
	
	// Lit un fichier binaire comme s'il ne contenait que des int et l'affiche à la console
	public static void afficherFichBinInt(RandomAccessFile raf)
	{
		try {
			System.out.println("Lecture du fichier .bin mode 'int'");
			raf.seek(0);
			for (int k=0; k<raf.length()/4; k++) {
				System.out.print(raf.readInt()+" ");
				if ((k+1)%(ConstantesDAppli.TAILLE_BLOC/4)==0) {
					System.out.println();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
