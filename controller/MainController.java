package controller;


import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Player;
import model.World;
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
	ArrayList<String> input = new ArrayList<String>();
	private Timeline gameLoop;
	private World world;


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
    private Pane paneOverworld;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.world = new World(new Player(80*2, 80*5),new Map());

		playerBox = new ImageView("file:src/ressources/sprites/mario.png");
		playerBox.setRotationAxis(new Point3D(0, 1, 0));
		playerBox.translateXProperty().bind(this.world.getPlayer().coordXProperty());
		playerBox.translateYProperty().bind(this.world.getPlayer().coordYProperty());
		paneOverworld.getChildren().add(playerBox);

		paneMap.setPrefWidth(80 * this.world.getMap().getWidth());
		paneMap.setPrefHeight(80 * this.world.getMap().getHeight());
		int i;
		for (i = 0; i < this.world.getMap().getTileMap().size(); i++) {
			try {
				BufferedImage buffImg = ImageIO.read(new File(this.world.getMap().getTileAt(i).getURL()));
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
                    this.world.getPlayer().jumpAnim();
					this.world.getPlayer().gravity();
					this.world.getPlayer().readInput(input);
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}


}
