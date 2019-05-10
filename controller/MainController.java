package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Joueur;

public class MainController implements Initializable{
	
	private Joueur j;
	
	@FXML
    private Pane pane;
	
	@FXML 
    void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
        case Z:
        	Rectangle n = (Rectangle) recupPerso("joueur");
        	n.setTranslateY(n.getTranslateY()-15);
        	break;
        case S:
        	Rectangle n2 = (Rectangle) recupPerso("joueur");
        	n2.setTranslateY(n2.getTranslateY()+15);
            break;
        case D:
        	Rectangle n3 = (Rectangle) recupPerso("joueur");
        	n3.setTranslateX(n3.getTranslateX()+15);
        	break;
        case Q:
        	Rectangle n4 = (Rectangle) recupPerso("joueur");
        	n4.setTranslateX(n4.getTranslateX()-15);
            break;
        default:
            break;
        }
    }
	
	public Node recupPerso(String n) {
		for(int node=0; node < pane.getChildren().size(); node++) {
			if(pane.getChildren().get(node).getId().equals(n))
				return pane.getChildren().get(node);
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Rectangle joueur = new Rectangle();
		joueur.setStroke(Color.BLACK);
		joueur.setScaleX(50);
		joueur.setScaleY(75);
		joueur.setId("joueur");
		pane.getChildren().add(joueur);		
	}
}
