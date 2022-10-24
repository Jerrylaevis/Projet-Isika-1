// Authors : Marie Briere, Rocio Keuro, Joachim Ouafo, Jérôme Vallin

package fr.Isika.cda21.Projet1Groupe3.gestionABR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.Isika.cda21.Projet1Groupe3.application.ConstantesDAppli;
import fr.Isika.cda21.Projet1Groupe3.entites.Stagiaire;

public abstract class NoeudBin {
	
	// *************** ATTRIBUTS*************
	// int tailleBloc ?
	
	// *************** METHODES SPECIFIQUES*************

	// Crée un ABR sous forme de fichier binaire à partir des infos du fichier .txt
	public static void creerFichierBinDepuisFichierTxt() {
		File annuaireTxt = new File(ConstantesDAppli.getNomFichierTxt());
		
		try {
			RandomAccessFile  annuaireBin =new RandomAccessFile(ConstantesDAppli.getNomFichierBin(), "rw");
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
	
	// Demande au bloc situé à 'index' de placer le Stagiaire 'stagAAjouter'. Méthode récursive.
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
						raf.seek(raf.getFilePointer()-8);
						raf.writeInt(indexNouveauNoeudBin);
						ecrireNoeudBinAIndex(stagAAjouter, indexNouveauNoeudBin, -1, -1, raf);
					}
					else {
						ajouterNoeudBin(indexFilsG, stagAAjouter, raf);
					}
				}
				else {
					if (indexFilsD==-1) {
						raf.seek(raf.getFilePointer()-4);
						raf.writeInt(indexNouveauNoeudBin);
						ecrireNoeudBinAIndex(stagAAjouter, indexNouveauNoeudBin, -1, -1, raf);
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
	
	
	// Ecrit, à un index précis, un bloc dans le fichier bin, bloc contenant un Stagiaire puis les index de FilsG et de FilsD. 
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
	
	
	// Lit les attributs Stagiaire d'un bloc du fichier .bin, celui en position 'index'
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
	
	// Lit une String dans le fichier bin et se positionne au début de la donnée suivante. TailleMax varie selon l'attribut à lire
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
	
	// ********************  AJOUTS POSTERIEURS A V2.1  ***************************
	
	// Lit le fichier .bin en parcours GND pour créer une liste dans l'ordre alphabétique
	public static List<Stagiaire> listeGND(RandomAccessFile raf, int index, List<Stagiaire> liste) {
		try {
			Stagiaire stagAAjouter = lireNoeudBinAIndex(index, raf);
			int indexFilsG = raf.readInt();
			int indexFilsD = raf.readInt();
			if (indexFilsG!=-1) {
				listeGND(raf, indexFilsG, liste); 	// s'il y a un FilsG, on crée sa liste
			}
			liste.add(stagAAjouter);				// on ajoute le Stagiaire en cours
			if (indexFilsD!=-1) {
				listeGND(raf, indexFilsD, liste);	// s'il y a un FilsD, on crée sa liste (qui est ajoutée à la liste)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	// Crée une liste de tous les stagiaires dont le nom est 'nom' (recherche dichotomique, parcours DNG)
	public static List<Stagiaire> rechercheNom(String nom, RandomAccessFile raf, int index, List<Stagiaire> liste) {
		try {
			Stagiaire stagATester = lireNoeudBinAIndex(index, raf);
			int indexFilsG = raf.readInt();
			int indexFilsD = raf.readInt();
			if (nom.compareTo(stagATester.getNom())<0 && indexFilsG!=-1) {
				rechercheNom(nom, raf, indexFilsG, liste);
			}
			else if (nom.equals(stagATester.getNom())) {
				if (indexFilsG!=-1) {
					rechercheNom(nom, raf, indexFilsG, liste);
				}
				liste.add(stagATester);
				if (indexFilsD!=-1) {
					rechercheNom(nom, raf, indexFilsD, liste);
				}
			}
			else if (indexFilsD!=-1) {
				rechercheNom(nom, raf, indexFilsD, liste);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	// Crée une liste de tous les stagiaires dont le nom commence par 'nom' (recherche dichotomique, parcours DNG)
	public static List<Stagiaire> rechercheNomPartiel(String nom, RandomAccessFile raf, int index, List<Stagiaire> liste) {
		try {
			Stagiaire stagATester = lireNoeudBinAIndex(index, raf);
			int indexFilsG = raf.readInt();
			int indexFilsD = raf.readInt();
			String nomATester = stagATester.getNom();
			if (nom.length()<nomATester.length()) {
				nomATester = nomATester.substring(0, nom.length());
			}
			if (nom.compareTo(nomATester)<0 && indexFilsG!=-1) {
				rechercheNomPartiel(nom, raf, indexFilsG, liste);
			}
			else if (nom.equals(nomATester)) {
				if (indexFilsG!=-1) {
					rechercheNomPartiel(nom, raf, indexFilsG, liste);
				}
				liste.add(stagATester);
				if (indexFilsD!=-1) {
					rechercheNomPartiel(nom, raf, indexFilsD, liste);
				}
			}
			else if (indexFilsD!=-1) {
				rechercheNomPartiel(nom, raf, indexFilsD, liste);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
		// Crée une liste de tous les stagiaires dont le prénom est 'prenom' (recherche complète, parcours DNG)
		public static List<Stagiaire> recherchePrenom(String prenom, RandomAccessFile raf, int index, List<Stagiaire> liste) {
			try {
				Stagiaire stagATester = lireNoeudBinAIndex(index, raf);
				int indexFilsG = raf.readInt();
				int indexFilsD = raf.readInt();
				if (indexFilsG!=-1) {
					recherchePrenom(prenom, raf, indexFilsG, liste);
				}
				if (prenom.equals(stagATester.getPrenom())) {
					liste.add(stagATester);
				}
				if (indexFilsD!=-1) {
					recherchePrenom(prenom, raf, indexFilsD, liste);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return liste;
		}
		
		// Crée une liste de tous les stagiaires dont le prénom commence par 'prenom' (recherche complète, parcours DNG)
		public static List<Stagiaire> recherchePrenomPartiel(String prenom, RandomAccessFile raf, int index, List<Stagiaire> liste) {
			try {
				Stagiaire stagATester = lireNoeudBinAIndex(index, raf);
				int indexFilsG = raf.readInt();
				int indexFilsD = raf.readInt();
				String prenomATester = stagATester.getPrenom();
				if (prenom.length()<prenomATester.length()) {
					prenomATester = prenomATester.substring(0, prenom.length());
				}
				if (indexFilsG!=-1) {
					recherchePrenomPartiel(prenom, raf, indexFilsG, liste);
				}
				if (prenom.equals(prenomATester)) {
					liste.add(stagATester);
				}
				if (indexFilsD!=-1) {
					recherchePrenomPartiel(prenom, raf, indexFilsD, liste);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return liste;
		}
		
}
	
	