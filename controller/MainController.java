package controller;


import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Player;
import model.World;
import model.TileType.Sky;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ScrollPane;
import javafx.event.*;
import model.Item;
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
    private int newId=-1;
   	private int oldId=-2;
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
	
	//Slot d'inventaire
	@FXML
    private ImageView slot0;
	
	@FXML
    private ImageView slot1;

    @FXML
    private ImageView slot2;

    @FXML
    private ImageView slot3;

    @FXML
    private ImageView slot4;

    @FXML
    private ImageView slot5;

    @FXML
    private ImageView slot6;

    @FXML
    private ImageView slot7;

    @FXML
    private ImageView slot8;

    @FXML
    private ImageView slot9;


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
            try {
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
                BufferedImage buffImg = ImageIO.read(new File(url));
                Image img = SwingFXUtils.toFXImage(buffImg, null);
                ImageView tile = new ImageView(img);
                
                tile.setOnMousePressed(
						new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								newId = paneMap.getChildrenUnmodifiable().indexOf(tile);
								oldId = newId;
							}
						});
                
                tile.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
						newId = paneMap.getChildrenUnmodifiable().indexOf(tile);
						oldId = newId;
                    } 
                });
                
                tile.setOnMouseReleased(
						new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								newId = -1;
								
							}
						});
                
                paneMap.getChildren().add(tile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scrollPaneMap.addEventFilter(MouseEvent.DRAG_DETECTED , new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scrollPaneMap.startFullDrag();
            }
        });
        
        scrollPaneMap.addEventFilter(KeyEvent.KEY_PRESSED,  event -> {
                     String code = event.getCode().toString();
                            if (!input.contains(code))
                                input.add(code);
                            event.consume();
                        });

        scrollPaneMap.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });
  
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
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.033),
                (ev -> {
                	if(newId!= oldId || newId == -1) {
                		digTimer= 0;
                	}
                	else {
                		digTimer++;
                		if(digTimer==60) { //depend de la case ?
                    		this.world.getMap().updateMap(newId, 's');
                			digTimer = 0;
                    	}
                	}
                	
                    this.world.getPlayer().jumpAnim();
                    this.world.getPlayer().gravity();
                    this.world.getPlayer().readInput(input);
                    playerBox.setRotate(this.world.getPlayer().getDirection());
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}
