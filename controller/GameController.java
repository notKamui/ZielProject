package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Craft;
import model.DynamicObject;
import model.EnemyType.Gargoyle;
import model.EnemyType.Skeleton;
import model.ItemPlaceableType.BlockDirt;
import model.ItemUsableType.Shovel;
import model.MathDataBuilder;
import model.Tile;
import model.World;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    ArrayList<String> input = new ArrayList<>();
    private Timeline gameLoop;
    private boolean gameLoopIsPaused = false;
    private World world;
    private MouseEvent lastEvent = null;
    private Craft craft;

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
    private Pane playerBox;

    @FXML
    private Pane paneOverworld;

    //Slot d'inventaire
    @FXML
    private HBox quickInventory;

    @FXML
    private Pane pauseMenu;
    
    @FXML
    private Pane craftMenu;


    @FXML
    void quitPauseMenu(ActionEvent event) {
        Platform.exit();
    }

    private void cameraUpdate() {
        int width = (int) scrollPaneMap.getWidth();
        int height = (int) scrollPaneMap.getHeight();

        paneOverworld.setTranslateX(width / 2 - this.world.getPlayer().coordXProperty().get() - 40);
        paneOverworld.setTranslateY(height / 2 - this.world.getPlayer().coordYProperty().get() - 40);
    }


    private Image getImage(int i) {
        String url = "src/resources/tiles/";
        switch (this.world.getMap().getTileAt(i).getCharCode()) {
            case 'D':
                url += "dirt.png";
                break;
            case 'd':
                url += "dirtBG.png";
                break;
            case 'B':
                url += "brick.png";
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

    private void startGame() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.033), (ev -> {
            scrollPaneMap.requestFocus();
            if (lastEvent != null && lastEvent.isPrimaryButtonDown()) {
                ImageView test = (ImageView) lastEvent.getSource();
                Point2D coords = test.localToParent(lastEvent.getX(), lastEvent.getY());
                this.world.getPlayer().getInventory().getInventoryContent().get(this.world.getPlayer().getInventory().getIndex()).action((int) coords.getX(), (int) coords.getY());
            }

            this.world.getPlayer().setInput(input);
            this.world.getPlayer().act();
            
            for (DynamicObject object : this.world.getDynamicObjects()) {
                object.act();
            }

            //System.out.println(this.world.getMap().getTileAt(MathDataBuilder.coordToIndexTile(65, 5)).getDistance());
            this.cameraUpdate();
        }));
        gameLoop.getKeyFrames().add(kf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pauseMenu.setVisible(false);
        craftMenu.setVisible(false);
        craft = new Craft();
        
        this.world = Factory.initWorld();
        this.world.getPlayer().directionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                                Number newValue) {
                if ((int) newValue == 0)
                    playerBox.getChildren().get(0).setTranslateX(0);
                else
                    playerBox.getChildren().get(0).setTranslateX(-16);
            }
        });
        this.playerBox = Factory.initPlayerView(this.world.getPlayer().coordXProperty(), this.world.getPlayer().coordYProperty());
        this.playerBox.setMouseTransparent(true);
        paneOverworld.getChildren().add(playerBox);
        paneMap.setPrefWidth(MathDataBuilder.TILESIZE * this.world.getMap().getWidth());
        paneMap.setPrefHeight(MathDataBuilder.TILESIZE * this.world.getMap().getHeight());
        int i;
        for (i = 0; i < this.world.getMap().getTileMap().size(); i++) {
            ImageView tile = new ImageView(this.getImage(i));
            tile.setPickOnBounds(true);
            tile.setOnMousePressed(e -> handlePressed(e));
            tile.setOnMouseDragEntered(e -> handlePressed(e));
            tile.setOnMouseReleased(e -> handlePressed(e));
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
            if (code.equals("ESCAPE")) {
                if (!gameLoopIsPaused) {
                    gameLoop.pause();
                    gameLoopIsPaused = true;
                    quickInventory.setVisible(false);
                    pauseMenu.setVisible(true);
                } else {
                    gameLoop.play();
                    gameLoopIsPaused = false;
                    quickInventory.setVisible(true);
                    pauseMenu.setVisible(false);
                }
            }
            if (code.equals("I")) {
                if (!gameLoopIsPaused) {
                    gameLoop.pause();
                    gameLoopIsPaused = true;
                    craftMenu.setVisible(true);
                    fillTheCraft();
                } else {
                    gameLoop.play();
                    gameLoopIsPaused = false;
                    craftMenu.setVisible(false);
                }
            }
            event.consume();
        });

        scrollPaneMap.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

        // graphical changes on map
        this.world.getMap().getTileMap().addListener(new ListChangeListener<Tile>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Tile> change) {
                while (change.next()) {
                    if (change.wasReplaced()) {
                        ImageView img = (ImageView) paneMap.getChildren().get(change.getFrom());
                        img.setImage(getImage(change.getFrom()));
                    }
                }
            }
        });

        // inventory slot selection
        for (int slot = 0; slot < this.quickInventory.getChildren().size(); slot++) {
            Pane pane = (Pane) quickInventory.getChildren().get(slot);

            pane.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    world.getPlayer().getInventory().setIndexProperty(quickInventory.getChildrenUnmodifiable().indexOf(e.getSource()));
                }
            });
        }
        this.world.getPlayer().getInventory().getIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                                Number newValue) {
                Pane p = (Pane) quickInventory.getChildren().get((int) oldValue);
                Circle c = (Circle) p.getChildren().get(0);
                c.setFill(RadialGradient.valueOf("focus-angle 0.0deg, focus-distance 0.0% , center 50.0% 50.0%, radius 69.04761904761905%, 0xffffffff 0.0%, 0x3b3b3bf4 100.0%"));

                p = (Pane) quickInventory.getChildren().get((int) newValue);
                c = (Circle) p.getChildren().get(0);
                c.setFill(RadialGradient.valueOf("focus-angle 0.0deg, focus-distance 0.0% , center 50.0% 50.0%, radius 50%, 0xffffffff 0.0%, 0x322e2e 100.0%"));
            }
        });
        this.world.getDynamicObjects().addListener(new DynamicListener(paneOverworld));

        // inventory listener
        this.world.getPlayer().getInventory().getInventoryContent().addListener(new InventoryListener(quickInventory));
        this.world.getPlayer().getInventory().addItem(new Shovel(1));
        this.world.getPlayer().getInventory().addItem(new BlockDirt(0, 0));
        
        world.getDynamicObjects().add(new Gargoyle(MathDataBuilder.TILESIZE*61, MathDataBuilder.TILESIZE*0));
        //world.getDynamicObjects().add(new Skeleton(MathDataBuilder.TILESIZE*66, MathDataBuilder.TILESIZE*4));
        
        startGame();
        gameLoop.play();
    }

    public void handlePressed(MouseEvent e) {
        lastEvent = e;
    }
    
    private void fillTheCraft() {
    	ArrayList<Integer> nbOfItemCraftable = craft.getIdRecipeCraftable();
    	
    	int recipeSup = 0;
    	for(int recipe = 0; recipe < nbOfItemCraftable.size(); recipe++) {
    		Pane p = new Pane();
    		Rectangle r = new Rectangle(80, 80);
    		if(craft.isCraftable(nbOfItemCraftable.get(recipe))) {
    			r.setFill(Color.YELLOW);
    			p.setOnMousePressed(new EventHandler<MouseEvent>() {
        			public void handle(MouseEvent e) {
        				Pane pane = (Pane)e.getSource();
        				Rectangle t = (Rectangle)pane.getChildren().get(0);
        				t.setFill(Color.BLACK);
        				Text txt = (Text)pane.getChildren().get(1);
        				craft.craft(Integer.parseInt(txt.getText()));
        				fillTheCraft();
        			}
    			});
    		}
    		else {
    			r.setFill(Color.GRAY);
    		}
    		Text t = new Text(Integer.toString(nbOfItemCraftable.get(recipe)));
    		p.getChildren().add(r);
    		p.getChildren().add(t);
    		craftMenu.getChildren().add(p);
    		p.toFront();
    		if(recipe == 0) {
    			p.setLayoutY(1*100);
    		}
    		p.setLayoutY((recipe+1)*100);
    		if(recipe > 4) {
    			p.setLayoutX(200);
    			p.setLayoutY((recipeSup+1)*100);
    			recipeSup++;
    		}
    		else {
    			p.setLayoutX(100);
    		}
    	}
    	
    }


}
