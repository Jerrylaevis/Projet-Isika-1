package fr.isika.cda21.Projet1Groupe3.objetsGraphiques;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class PersoLabel extends Label {
	
	public PersoLabel(String text){
		super(text);
		//ou super(); avec this.setText(text);
		this.setFont(Font.font("Century Gothic",12));
		this.setPadding(new Insets(5,10,0,10));
	}

}