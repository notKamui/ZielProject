package controller;


import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Player;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ScrollPane;

import model.Map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javax.imageio.ImageIO;


public class MainController implements Initializable {

    private Map map;
    private Player player;

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
                this.player.move(0, -10);
                break;
            case S:
                this.player.move(0, 10);
                break;
            case D:
                this.player.move(10, 0);
                break;
            case Q:
                this.player.move(-10, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.player = new Player(10, 10);
        ImageView playerView = new ImageView("file:src/ressources/sprites/mario.png");
        playerView.translateXProperty().bind(this.player.coordXProperty());
        playerView.translateYProperty().bind(this.player.coordYProperty());
        pane.getChildren().add(playerView);

        map = new Map();

        paneMap.setPrefWidth(80 * map.getWidth());
        paneMap.setPrefHeight(80 * map.getHeight());
        int i;
        for (i = 0; i < this.map.getMap().size(); i++) {
            try {
                BufferedImage buffImg = ImageIO.read(new File(this.map.getTileAt(i).getURL()));
                Image img = SwingFXUtils.toFXImage(buffImg, null);
                paneMap.getChildren().add(new ImageView(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
