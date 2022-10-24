package fr.isika.cda21.Projet1Groupe3.gestionAbrBinaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import fr.isika.cda21.Projet1Groupe3.application.ConstantesDAppli;
import fr.isika.cda21.Projet1Groupe3.entites.Stagiaire;
import fr.isika.cda21.Projet1Groupe3.outils.Outils;

public class Bloc {

	int index;
	int pere;
	private Stagiaire cle;		// Serait-il intéressant d'ajouter le rang ?
	private int filsG;
	private int filsD;
	private int indexDoublon;
	
	// ********** CONSTRUCTEURS ***********
	public Bloc(int index, int pere, Stagiaire cle, int filsG, int filsD, int indexDoublon) {
		this.index = index;
		this.pere = pere;
		this.cle = cle;
		this.filsG = filsG;
		this.filsD = filsD;
		this.indexDoublon = indexDoublon;
	}
	
	
	// ********** METHODES SPECIFIQUES ***********
	
	// Ecrit un bloc dans le fichier bin (à la position this.index)
	public void ecrireBloc(RandomAccessFile raf) {
		try {
			raf.seek(this.index*ConstantesDAppli.TAILLE_BLOC);
			raf.writeInt(this.index);
			raf.writeInt(this.pere);
			raf.writeChars(this.cle.nomLong());
			raf.writeChars(this.cle.prenomLong());
			raf.writeChars(this.cle.depLong());
			raf.writeChars(this.cle.promoLong());
			raf.writeInt(this.cle.getAnneeF());
			raf.writeInt(this.filsG);
			raf.writeInt(this.filsD);
			raf.writeInt(this.indexDoublon);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Met à jour le filsG du bloc courant dans le fichier 
	public void ecrireFilsG(int nouvelIndex, RandomAccessFile raf) {
		try {
			raf.seek((this.index+1)*ConstantesDAppli.TAILLE_BLOC-12);
			raf.writeInt(nouvelIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Met à jour le filsD du bloc courant dans le fichier 
		public void ecrireFilsD(int nouvelIndex, RandomAccessFile raf) {
			try {
				raf.seek((this.index+1)*ConstantesDAppli.TAILLE_BLOC-8);
				raf.writeInt(nouvelIndex);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Met à jour le filsD du bloc courant dans le fichier 
		public void ecrireIndexDoublon(int nouvelIndex, RandomAccessFile raf) {
			//System.out.println("Bloc appelant ecrire index doublon : "+this);
			//System.out.println("nouvel index : "+nouvelIndex);
			try {
				raf.seek((this.index+1)*ConstantesDAppli.TAILLE_BLOC-4);
				raf.writeInt(nouvelIndex);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Lit une String dans le fichier bin et se positionne au début de la donnée suivante. TailleMax varie selon l'attribut à lire
		public static String lireString(int tailleMax, RandomAccessFile raf) { // à mettre dans une classe à part ?
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
		
	// Lit un bloc du fichier .bin, celui en position 'index'
	public Bloc lireBlocAIndex(int index, RandomAccessFile raf) {
		
		Stagiaire stag = new Stagiaire();
		Bloc res = new Bloc(index, -1, stag, -2, -2, -2);
		try {
			raf.seek(index*ConstantesDAppli.TAILLE_BLOC);
			res.index=raf.readInt();
			res.pere=raf.readInt();
			res.cle.setNom(lireString(ConstantesDAppli.TAILLE_MAX_NOM, raf));
			res.cle.setPrenom(lireString(ConstantesDAppli.TAILLE_MAX_PRENOM, raf));
			res.cle.setDep(lireString(ConstantesDAppli.TAILLE_MAX_DEP, raf));
			res.cle.setPromo(lireString(ConstantesDAppli.TAILLE_MAX_PROMO, raf));
			res.cle.setAnneeF(raf.readInt());
			res.filsG=raf.readInt();
			res.filsD=raf.readInt();
			res.indexDoublon=raf.readInt();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// Ajoute un bloc dans la liste des doublons de 'blocAAjouter.getCle.getNom'. 'blocAAjouter' contient déjà son Index, la méthode doit établir les liens suivant/précédant
	public void ajouterDansListeDoublons(Bloc blocAAjouter, RandomAccessFile raf) {
		//System.out.println("Bloc appelant ajouterDansListeDoublon : "+this);
		if (this.indexDoublon==-1) {
			this.ecrireIndexDoublon(blocAAjouter.getIndex(), raf);  	// MaJ de l'index doublon du bloc appelant
			blocAAjouter.setPere(this.index);				 			// MaJ du père du bloc à ajouter
			blocAAjouter.ecrireBloc(raf);								// Ecriture du bloc dans le fichier .bin
		}
		else {
			lireBlocAIndex(this.indexDoublon, raf).ajouterDansListeDoublons(blocAAjouter, raf); // on lui refile le boulot d'ajouter à la LISTE
		}
	}
	
	// Charge le bloc_appelant de placer le bloc 'blocAAjouter' (ie MaJ d'un des fils du père). Méthode récursive. 'blocAAjouter' contient déjà son Index, la méthode doit établir les liens fils/père
	public void ajouterBloc(Bloc blocAAjouter, RandomAccessFile raf) {
		//System.out.println("bloc appelant : "+lireBlocAIndex(this.index, raf));	//pour debug
		if (blocAAjouter.cle.compareTo(this.cle)==0) {			 		// stagiaire déjà dans l'annuaire
			System.out.println("Stagiaire déjà dans l'annuaire");
		}
		else if (blocAAjouter.cle.getNom().equals(this.cle.getNom())) { // il y a deja un stagiaire ayant ce nom
			if (this.indexDoublon==-1) {									// il n'y a pas encore de doublon
				this.ecrireIndexDoublon(blocAAjouter.getIndex(), raf);  		// MaJ du doublon du bloc appelant
				blocAAjouter.setPere(this.index);				 				// MaJ du père du bloc à ajouter
				blocAAjouter.ecrireBloc(raf);									// écriture du bloc dans le fichier
			}
			else {														// il y déjà un doublon
				lireBlocAIndex(this.indexDoublon, raf).ajouterDansListeDoublons(blocAAjouter, raf); // on lui refile le boulot d'ajouter à la LISTE
			}
		}
		else if (blocAAjouter.cle.getNom().compareTo(this.cle.getNom())<0) {// on part à gauche
			if (this.filsG==-1) {												// il n'y a personne
				this.ecrireFilsG(blocAAjouter.getIndex(), raf);  					// MaJ du filsG du bloc appelant
				blocAAjouter.setPere(this.index);				 					// MaJ du père du bloc à ajouter
				blocAAjouter.ecrireBloc(raf);										// écriture du bloc dans le fichier
			}
			else {																// il y a quelqu'un
				lireBlocAIndex(this.filsG, raf).ajouterBloc(blocAAjouter, raf); 	// on lui refile le boulot
			}
		}
		else {																	// on part à droite
			if (this.filsD==-1) {													// il n'y a personne
				this.ecrireFilsD(blocAAjouter.getIndex(), raf);  						// MaJ du filsD du bloc appelant						
				blocAAjouter.setPere(this.index);										// MaJ du père du bloc à ajouter
				blocAAjouter.ecrireBloc(raf);											// écriture du bloc dans le fichier
			}
			else {																	// il y a quelqu'un
			lireBlocAIndex(this.filsD, raf).ajouterBloc(blocAAjouter, raf);				// on lui refile le boulot
			}
		}
		
	}
	
	// Lit le fichier .bin en parcours GND pour créer une liste dans l'ordre alphabétique, pour le sous-arbre commençant au bloc 'index'
	public List<Stagiaire> listeGND(RandomAccessFile raf, int index, List<Stagiaire> liste) {
		Bloc blocALire = lireBlocAIndex(index, raf);
		//System.out.println("Je suis le bloc "+blocALire.getCle().getNom());
		if (blocALire.getFilsG()!=-1) {
			listeGND(raf, blocALire.getFilsG(), liste); 	// s'il y a un FilsG, on crée sa liste
		}
		liste.add(blocALire.cle);							// on ajoute le Stagiaire en cours
		if (blocALire.indexDoublon!=-1) {					// s'il y a des doublons
			Bloc blocDoublon = lireBlocAIndex(index, raf);					//
			while (blocDoublon.indexDoublon!=-1) {							//					Dans une méthode dédiée ?
				blocDoublon = lireBlocAIndex(blocDoublon.indexDoublon, raf);// on les ajoute 
				liste.add(blocDoublon.cle);									//
			}
		}
		if (blocALire.getFilsD()!=-1) {
			listeGND(raf, blocALire.getFilsD(), liste);		// s'il y a un FilsD, on crée sa liste (qui est ajoutée à la liste)
		}
		return liste;
	}
		
	// Crée une liste de tous les stagiaires dont le nom est 'nom' (recherche dichotomique, parcours DNG). Rem : 'nom' est mis en UpperCase dans la méthode appelante de AbrBinaire
	public List<Stagiaire> rechercheNom(String nom, RandomAccessFile raf, int index, List<Stagiaire> liste) {
		Bloc blocATester = lireBlocAIndex(index, raf);					// on récupère les infos du bloc en cours
		String nomATester = blocATester.getCle().getNom();
		if (nom.compareTo(nomATester)<0 && blocATester.getFilsG()!=-1) { 	// si nom cherché < nom du bloc  ET  il y a un fils gauche
			rechercheNom(nom, raf, blocATester.getFilsG(), liste);		// on cherche à gauche
		}
		if (nom.equals(nomATester)) {										// si nom cherché = nom du bloc
			liste.add(blocATester.getCle());									// on ajoute le stagiaire du bloc à la liste
			if (blocATester.indexDoublon!=-1) {									// s'il y a des doublons
				Bloc blocDoublon = lireBlocAIndex(index, raf);						//
				while (blocDoublon.indexDoublon!=-1) {								//
					blocDoublon = lireBlocAIndex(blocDoublon.indexDoublon, raf);	// on les ajoute
					liste.add(blocDoublon.cle);										//
				}
			}
		}
		else if (blocATester.getFilsD()!=-1) {								// si nom cherché > nom du bloc  ET  il y a un fils droit
			rechercheNom(nom, raf, blocATester.getFilsD(), liste);		// on cherche à droite
		}
		return liste;
	}
	
	// Crée une liste de tous les stagiaires dont le nom commence par 'nom' (recherche dichotomique jusqu'au premier noeud favorable (=> parcours complet dans le ca le + défavorable), parcours DNG). Rem : 'nom' est mis en UpperCase dans la méthode appelante de AbrBinaire
	public List<Stagiaire> rechercheNomPartiel(String nom, RandomAccessFile raf, int index, List<Stagiaire> liste) {
		Bloc blocATester = lireBlocAIndex(index, raf);					// on récupère les infos du bloc en cours
		String nomATester = blocATester.getCle().getNom();
		if (nom.length()<nomATester.length()) {			// si recherche sur le début du nom
			nomATester = nomATester.substring(0, nom.length());			// on ne garde que le début du nomATester
		}
		if (nom.compareTo(nomATester)<0 && blocATester.getFilsG()!=-1) { 	// si nom cherché < nom du bloc  ET  il y a un fils gauche
			rechercheNomPartiel(nom, raf, blocATester.getFilsG(), liste);		// on cherche à gauche
		}
		if (nom.equals(nomATester)) {										// si nom cherché = nom du bloc
			if (blocATester.getFilsG()!=-1) {									// si filsG
				rechercheNomPartiel(nom, raf, blocATester.getFilsG(), liste);		//on cherche à gauche 	(pour noms partiels !)
			}
			liste.add(blocATester.getCle());									// on ajoute le stagiaire du bloc à la liste
			if (blocATester.indexDoublon!=-1) {									// s'il y a des doublons
				Bloc blocDoublon = lireBlocAIndex(index, raf);						//
				while (blocDoublon.indexDoublon!=-1) {								//
					blocDoublon = lireBlocAIndex(blocDoublon.indexDoublon, raf);	// on les ajoute
					liste.add(blocDoublon.cle);										//
				}
			}
			if (blocATester.getFilsD()!=-1) {									// si filsD
				rechercheNomPartiel(nom, raf, blocATester.getFilsD(), liste);		//on cherche à droite 	(pour noms partiels !)
			}
		}
		else if (blocATester.getFilsD()!=-1) {								// si nom cherché > nom du bloc  ET  il y a un fils droit
			rechercheNomPartiel(nom, raf, blocATester.getFilsD(), liste);		// on cherche à droite
		}
		return liste;
	}
	
	// Crée une liste de tous les stagiaires dont le prénom est (ou commence par) 'prenom' (recherche complète, parcours DNG). Rem : 'prenom' doit être normalisé
	public List<Stagiaire> recherchePrenom(String prenom, RandomAccessFile raf, int index, List<Stagiaire> liste, boolean partiel) {
		Bloc blocATester = lireBlocAIndex(index, raf);					// on récupère les infos du bloc en cours
		String prenomATester = Outils.prenomNormalise(blocATester.getCle().getPrenom());
		if (partiel && prenom.length()<prenomATester.length()) {				// si recherche sur le début du nom
			prenomATester = prenomATester.substring(0, prenom.length());		// on ne garde que le début du prenomATester
		}
		if (blocATester.getFilsG()!=-1) {										// s'il y a un fils gauche
			recherchePrenom(prenom, raf, blocATester.getFilsG(), liste, partiel);	// on cherche à gauche
		}
		if (prenom.equals(prenomATester)) {										// si prénom cherché = prénom du bloc
			liste.add(blocATester.getCle());										// on ajoute le stagiaire du bloc à la liste
		}
		if (blocATester.indexDoublon!=-1) {
			Bloc blocDoublon = lireBlocAIndex(index, raf);
			while (blocDoublon.indexDoublon!=-1) {											//
				blocDoublon = lireBlocAIndex(blocDoublon.indexDoublon, raf);				//
				prenomATester = Outils.prenomNormalise(blocDoublon.getCle().getPrenom());	//
				if (partiel && prenom.length()<prenomATester.length()) {					// on teste aussi les éventuels doublons
					prenomATester = prenomATester.substring(0, prenom.length());			//
				}																			//
				if (prenom.equals(prenomATester)) {											//
				liste.add(blocDoublon.cle);													//
				}
			}
		}
		if (blocATester.getFilsD()!=-1) {										// s'il y a un fils droit
			recherchePrenom(prenom, raf, blocATester.getFilsD(), liste, partiel);	// on cherche à droite
		}
		return liste;
	}
	
		

	@Override
	public String toString() {
		return "Stagiaire : "+this.cle.toString()+" / Index : "+this.index+" / Index du père : "+this.pere+" / Index du fils G : "+this.filsG+" / Index du fils D : "+this.filsD+" / Index doublon : "+this.indexDoublon;
	}

	// ********** Getters et Setters ***********
	
		public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public int getFilsG() {
		return filsG;
	}

	public void setFilsG(int filsG) {
		this.filsG = filsG;
	}

	public int getFilsD() {
		return filsD;
	}

	public void setFilsD(int filsD) {
		this.filsD = filsD;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getPere() {
		return pere;
	}
	
	public void setPere(int pere) {
		this.pere = pere;
	}

	public int getIndexDoublon() {
		return indexDoublon;
	}

	public void setIndexDoublon(int indexDoublon) {
		this.indexDoublon = indexDoublon;
	}

	
}
