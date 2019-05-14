package controller;


import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Joueur;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ScrollPane;
import java.awt.*;
import model.Map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javax.imageio.ImageIO;


public class MainController implements Initializable{
  
  private Map map;
  private Joueur j;

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="scrollPaneMap"
  private ScrollPane scrollPaneMap; // Value injected by FXMLLoader

  @FXML // fx:id="paneMap"
  private TilePane paneMap; // Value injected by FXMLLoader

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
	
	//Methode permettant de recuperer la node du joueur dans le pane a par son nom (Trouver un moyen plus simple)
	public Node recupPerso(String n) {
		for(int node=0; node < pane.getChildren().size(); node++) {
			if(pane.getChildren().get(node).getId().equals(n))
				return pane.getChildren().get(node);
		}
		return null;
	}

	//Initialize qui va CHANGER quand on aura un personnage 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Rectangle joueur = new Rectangle();
		joueur.setStroke(Color.BLACK);
		joueur.setScaleX(50);
		joueur.setScaleY(75);
		joueur.setId("joueur");
		pane.getChildren().add(joueur);		
    
    map = new Map();

    paneMap.setPrefWidth(80*map.getWidth());
    paneMap.setPrefHeight(80*map.getHeight());
    int i; for(i = 0; i < this.map.getMap().size(); i++) {
      try {
        BufferedImage buffImg = ImageIO.read(new File(this.map.getTileAt(i).getURL()));
        Image img = SwingFXUtils.toFXImage(buffImg, null);
        paneMap.getChildren().add(new ImageView(img));
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
	}
}
