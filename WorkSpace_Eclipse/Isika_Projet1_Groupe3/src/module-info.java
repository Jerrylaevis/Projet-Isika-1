module Isika_Projet1_Groupe3 {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;
	
	opens fr.isika.cda21.Projet1Groupe3.application to javafx.graphics, javafx.fxml, javafx.base;
	opens fr.isika.cda21.Projet1Groupe3.entites to javafx.graphics, javafx.fxml, javafx.base;
}
