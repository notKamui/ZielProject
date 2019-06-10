package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.*;

class Factory {

    static World initWorld() {
        World world = new World();

        Player player = new Player(MathDataBuilder.TILESIZE * 65, MathDataBuilder.TILESIZE * 4);
        Map map = new Map();

        world.setPlayer(player);
        world.setMap(map);
        MathDataBuilder.setWorld(world);

        return world;
    }

    static Pane initPlayerView(IntegerProperty coordXProperty, IntegerProperty coordYProperty) {

        ImageView sprite = new ImageView("file:src/resources/sprites/player.png");
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
    	ImageView enemyBox = new ImageView("file:src/resources/sprites/mario.png");
    	enemyBox.setRotationAxis(new Point3D(0, 1, 0));
    	enemyBox.translateXProperty().bind(coordXproperty);
    	enemyBox.translateYProperty().bind(coordYProperty);
    	
    	return enemyBox;
    }
}
