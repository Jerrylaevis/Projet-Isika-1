// Authors : Marie Briere, Rocio Keuro, Joachim Ouafo, Jérôme Vallin

package fr.Isika.cda21.Projet1Groupe3.gestionABR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.Isika.cda21.Projet1Groupe3.application.ConstantesDAppli;
import fr.Isika.cda21.Projet1Groupe3.entites.Stagiaire;

public abstract class NoeudBin {
	
	// *************** ATTRIBUTS*************
	// int tailleBloc ?
	
	// *************** METHODES SPECIFIQUES*************

	// Crée un ABR sous forme de fichier binaire à partir des infos du fichier .txt
	public static void creerFichierBinDepuisFichierTxt() {
		File annuaireTxt = new File(ConstantesDAppli.getNomFihierTxt());
		
		try {
			RandomAccessFile  annuaireBin =new RandomAccessFile(ConstantesDAppli.getNomFihierBin(), "rw");
			FileReader fr = new FileReader(annuaireTxt);
			BufferedReader br = new BufferedReader(fr);
			
			
			while(br.ready()) {
				String nom = br.readLine().toUpperCase();
				String prenom = br.readLine();
				String dep = br.readLine();
				String promo = br.readLine();
				int anneeF = Integer.parseInt(br.readLine());
				br.readLine();						// pour passer la ligne *
				
				Stagiaire nouveauStagiaire = new Stagiaire(nom, prenom, dep, promo, anneeF);
				
				ajouterNoeudBin(0, nouveauStagiaire, annuaireBin);
			}
			
			br.close();
			fr.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// Demande au bloc situé à 'index' de placer le Stagiaire 'stagAAjouter'. Métjode récursive.
	public static void ajouterNoeudBin(int index, Stagiaire stagAAjouter, RandomAccessFile raf) {
		
		try {
			int indexNouveauNoeudBin = (int)raf.length()/ConstantesDAppli.TAILLE_NOEUD_BIN;
			
			if (indexNouveauNoeudBin==0) {
				ecrireNoeudBinAIndex(stagAAjouter, 0, -1, -1, raf);
			}
			else {
				
				Stagiaire stagiaireDuNoeudALire = lireNoeudBinAIndex(index, raf);
				int indexFilsG = raf.readInt();
				int indexFilsD = raf.readInt();
				if (stagAAjouter.compareTo(stagiaireDuNoeudALire)==0) {
					System.out.println("Stagiaire déjà dans l'annuaire");
				}
				else if (stagAAjouter.compareTo(stagiaireDuNoeudALire)<0) {
					if (indexFilsG==-1) {
						ecrireNoeudBinAIndex(stagAAjouter, indexNouveauNoeudBin, -1, -1, raf);
						raf.seek(raf.getFilePointer()-8);
						raf.writeInt(indexNouveauNoeudBin);
					}
					else {
						ajouterNoeudBin(indexFilsG, stagAAjouter, raf);
					}
				}
				else {
					if (indexFilsD==-1) {
						ecrireNoeudBinAIndex(stagAAjouter, indexNouveauNoeudBin, -1, -1, raf);
						raf.seek(raf.getFilePointer()-4);
						raf.writeInt(indexNouveauNoeudBin);
					}
					else {
						ajouterNoeudBin(indexFilsD, stagAAjouter, raf);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Ecris un bloc dans le fichier bin, bloc contenant un Stagiaire puis les index de FilsG et de FilsD. 
	public static void ecrireNoeudBinAIndex(Stagiaire stagAAjouter, int index, int indexFG, int indexFD, RandomAccessFile raf) {
		try {
			raf.seek(index*ConstantesDAppli.TAILLE_NOEUD_BIN);
			raf.writeChars(stagAAjouter.nomLong());
			raf.writeChars(stagAAjouter.prenomLong());
			raf.writeChars(stagAAjouter.depLong());
			raf.writeChars(stagAAjouter.promoLong());
			raf.writeInt(stagAAjouter.getAnneeF());
			raf.writeInt(indexFG);
			raf.writeInt(indexFD);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Lis les attributs Stagiaire d'un bloc du fichier .bin, celui en position 'index'
	public static Stagiaire lireNoeudBinAIndex(int index, RandomAccessFile raf) {
		
		Stagiaire res = new Stagiaire();
		try {
			raf.seek(index*ConstantesDAppli.TAILLE_NOEUD_BIN);
			res.setNom(lireString(ConstantesDAppli.TAILLE_MAX_NOM, raf));
			res.setPrenom(lireString(ConstantesDAppli.TAILLE_MAX_PRENOM, raf));
			res.setDep(lireString(ConstantesDAppli.TAILLE_MAX_DEP, raf));
			res.setPromo(lireString(ConstantesDAppli.TAILLE_MAX_PROMO, raf));
			res.setAnneeF(raf.readInt());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// Lis une String dans le fichier bin et se positionne au début de la donnée suivante. TailleMax varie selon l'attribut à lire
	public static String lireString(int tailleMax, RandomAccessFile raf) {
 		String res = "";
		for (int j=0; j<tailleMax; j++) {
			char c;
			try {
				c = raf.readChar();
				if (c!='*') res+=c;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	// Recherche un Stagaire dans le fichier .bin
	
}
	
	