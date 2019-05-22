package controller;

import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Player;
import model.Tile;
import model.World;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ScrollPane;
import javafx.event.*;
import model.Item;
import model.Map;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;


public class MainController implements Initializable {

	ArrayList<String> input = new ArrayList<String>();
	private Timeline gameLoop;
	private World world;
	private int newId = -1;
	private int oldId = -2;
	private int digTimer = 0;

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

	private boolean isInRange(int cursorX, int cursorY) {
		int playerX = world.getPlayer().coordXProperty().get() + 40;
		int playerY = world.getPlayer().coordYProperty().get() + 40;
		if (playerX - world.getPlayer().getRange() <= cursorX && cursorX <= playerX + world.getPlayer().getRange()
				&& playerY - world.getPlayer().getRange() <= cursorY
				&& cursorY <= playerY + world.getPlayer().getRange()) {
			return true;
		}
		return false;
	}

	private Image getImage(int i) {
		String url = "src/resources/tiles/";
		switch (this.world.getMap().getTileAt(i).getCharCode()) {
		case 'g':
			url += "ground/groundTop.png";
			break;
		case 's':
			url += "sky.png";
			break;
		default:
			url += "void.png";
			break;
		}
		return new Image("file:" + url);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.world = new World(new Player(80 * 2, 80 * 5), new Map());

		playerBox = new ImageView("file:src/resources/sprites/mario.png");
		playerBox.setRotationAxis(new Point3D(0, 1, 0));
		playerBox.translateXProperty().bind(this.world.getPlayer().coordXProperty());
		playerBox.translateYProperty().bind(this.world.getPlayer().coordYProperty());
		paneOverworld.getChildren().add(playerBox);

		paneMap.setPrefWidth(80 * this.world.getMap().getWidth());
		paneMap.setPrefHeight(80 * this.world.getMap().getHeight());
		int i;
		for (i = 0; i < this.world.getMap().getTileMap().size(); i++) {
			ImageView tile = new ImageView(this.getImage(i));

			tile.setOnMousePressed(e -> handlePressed(e));
			tile.setOnMouseDragEntered(e -> handlePressed(e));
			tile.setOnMouseReleased(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					newId = -1;

				}
			});

			paneMap.getChildren().add(tile);
		}
    
		scrollPaneMap.addEventFilter(MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				scrollPaneMap.startFullDrag();
			}
		});

		scrollPaneMap.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			String code = event.getCode().toString();
			if (!input.contains(code))
				input.add(code);
			event.consume();
		});

		scrollPaneMap.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});
		startGame();
		gameLoop.play();
	}

	public void handlePressed(MouseEvent e) {

		int cursorX = (int) e.getSceneX();
		int cursorY = (int) e.getSceneY() - 100;
		if (isInRange(cursorX, cursorY)) {
			if (e.isPrimaryButtonDown()) {
				newId = paneMap.getChildrenUnmodifiable().indexOf(e.getSource());
				oldId = newId;
			} else if (e.isSecondaryButtonDown()) {
				if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {

				}
			}
		}
		// graphical changes on map
		this.world.getMap().getTileMap().addListener(new ListChangeListener<Tile>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Tile> change) {
				while (change.next()) {
					if (change.wasReplaced()) {
						ImageView img = (ImageView) paneMap.getChildren().get(change.getFrom());
						img.setImage(getImage(change.getFrom()));

   
  //-----Gestion de L'inventaire-------
        this.world.getPlayer().getInventory().returnInventory().addListener(new ListChangeListener<Item>() {
			  @Override
			  public void onChanged(ListChangeListener.Change<? extends Item> change) {
				  while(change.next()) {
				  	if(change.wasRemoved()) {
						
				   	}
					
				  	if(change.wasAdded()) {
						
				  	}
					
					if(change.wasReplaced()) {

					}
				}
			}
		});

        startGame();
        gameLoop.play();
    }

        
        private void startGame() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.033), (ev -> {
			scrollPaneMap.requestFocus();

			if (newId != oldId || newId == -1) {
				digTimer = 0;
			} else {
				digTimer++;
				if (digTimer == this.world.getMap().getTileAt(newId).getDurability()) {
					this.world.getMap().updateMap(newId, 's');
					digTimer = 0;
				}
			}
			this.world.getPlayer().jumpAnim();
			this.world.getPlayer().gravity();
			this.world.getPlayer().readInput(input);
			playerBox.setRotate(this.world.getPlayer().getDirection());
		}));
		gameLoop.getKeyFrames().add(kf);
	}

}
