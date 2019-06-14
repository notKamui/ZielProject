package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Map;
import model.MathDataBuilder;
import model.Player;
import model.World;

import java.util.HashMap;

class Factory {
    public static java.util.Map<Integer, String> idToUrl = new HashMap<>();
    public static void initIDToURL() {
        idToUrl.clear();
        String rootUrl = "file:src/resources/";
        idToUrl.put(0, rootUrl + "tiles/void.png");
        idToUrl.put(1, rootUrl + "tiles/dirt.png");
        idToUrl.put(15, rootUrl + "tiles/dirtBG.png");
        idToUrl.put(2, rootUrl + "tiles/stone.png");
        idToUrl.put(3, rootUrl + "tiles/wood.png");
        idToUrl.put(4, rootUrl + "tiles/brick.png");
        idToUrl.put(5, rootUrl + "tiles/sky.png");

        idToUrl.put(100, rootUrl + "sprites/shovel.png");
        idToUrl.put(101, rootUrl + "sprites/pickaxe.png");

        idToUrl.put(700, rootUrl + "sprites/player/player_idle.gif");

        idToUrl.put(900, rootUrl + "sprites/skeleton.png");
        idToUrl.put(901, rootUrl + "sprites/gargoyle.gif");
    }

    static World initWorld() {
        initIDToURL();
        World world = new World();

        Player player = new Player(MathDataBuilder.TILESIZE * 65, MathDataBuilder.TILESIZE * 4);
        Map map = new Map();

        world.setPlayer(player);
        world.setMap(map);
        MathDataBuilder.setWorld(world);

        return world;
    }

    static Pane initPlayerView(IntegerProperty coordXProperty, IntegerProperty coordYProperty) {

        ImageView sprite = new ImageView("file:src/resources/sprites/player_idle.gif");
        sprite.setRotationAxis(new Point3D(0, 1, 0));
        sprite.rotateProperty().bind(MathDataBuilder.world().getPlayer().directionProperty());
        sprite.setMouseTransparent(true);

        Pane playerBox = new Pane();
        playerBox.translateXProperty().bind(coordXProperty);
        playerBox.translateYProperty().bind(coordYProperty);
        playerBox.getChildren().add(sprite);


        return playerBox;
    }

    static ImageView initEnemyView(IntegerProperty coordXproperty, IntegerProperty coordYProperty) {
        ImageView enemyBox = new ImageView("file:src/resources/sprites/gargoyle.png");
        enemyBox.setRotationAxis(new Point3D(0, 1, 0));
        enemyBox.translateXProperty().bind(coordXproperty);
        enemyBox.translateYProperty().bind(coordYProperty);

        return enemyBox;
    }
}
