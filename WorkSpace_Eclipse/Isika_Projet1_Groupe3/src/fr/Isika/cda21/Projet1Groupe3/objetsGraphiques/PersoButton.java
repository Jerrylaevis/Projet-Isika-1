package fr.isika.cda21.Projet1Groupe3.objetsGraphiques;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class PersoButton extends Button {
	
	public PersoButton(String text){
		super(text);
		//ou super(); avec this.setText(text);
		this.setFont(Font.font("Century Gothic",12));
		this.setStyle("-fx-focus-color: orange; -fx-faint-focus-color: transparent"); 
	}

}