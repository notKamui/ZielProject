package controller;


import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ScrollPane;
import javafx.event.*;
import model.Map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javax.imageio.ImageIO;


public class MainController implements Initializable {
    private KeyCode key = KeyCode.UNDEFINED;
    ArrayList<String> input = new ArrayList<String>();
    private Timeline gameLoop;
    private Map map;
    private Player player;

    @FXML
    private BorderPane root;

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
    private ImageView playerBox;


    @FXML
    void keyPressed(KeyEvent event) {
        key = event.getCode();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.player = new Player(80*2, 80*5);
        playerBox = new ImageView("file:src/ressources/sprites/mario.png");
        playerBox.setRotationAxis(new Point3D(0, 1, 0));
        playerBox.translateXProperty().bind(this.player.coordXProperty());
        playerBox.translateYProperty().bind(this.player.coordYProperty());
        pane.getChildren().add(playerBox);

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

        root.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        root.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });
        startGame();
        gameLoop.play();
    }

    private void startGame() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.033),
                (ev -> {
                    for (String key : input)
                        switch (key) {
                            case "Z":
                                this.player.move(0, -10);
                                break;
                            case "S":
                                this.player.move(0, 10);
                                break;
                            case "D":
                                this.player.move(10, 0);
                                playerBox.setRotate(0); // flip right
                                break;
                            case "Q":
                                this.player.move(-10, 0);
                                playerBox.setRotate(180); // flip left
                                break;
                            default:
                                break;
                        }
                    key = KeyCode.UNDEFINED;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}
