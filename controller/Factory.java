package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.*;

class Factory {

    static World initWorld() {
        World world = new World();

        Enemy n = new Enemy(MathDataBuilder.TILESIZE*61, MathDataBuilder.TILESIZE*4);
        Player player = new Player(MathDataBuilder.TILESIZE * 65, MathDataBuilder.TILESIZE * 4);
        Map map = new Map();

        world.setPlayer(player);
        world.setMap(map);
        world.setEnemy(n);
        MathDataBuilder.setWorld(world);

        return world;
    }

    static Pane initPlayerView(IntegerProperty coordXProperty, IntegerProperty coordYProperty) {

        ImageView sprite = new ImageView("file:src/resources/sprites/player.png");
        sprite.setRotationAxis(new Point3D(0, 1, 0));
        sprite.rotateProperty().bind(MathDataBuilder.world().getPlayer().directionProperty());


        Pane playerBox = new Pane();
        playerBox.translateXProperty().bind(coordXProperty);
        playerBox.translateYProperty().bind(coordYProperty);
        playerBox.getChildren().add(sprite);


        return playerBox;
    }
    
    static ImageView initEnnemyView(IntegerProperty coordXproperty, IntegerProperty coordYProperty) {
    	ImageView ennemyBox = new ImageView("file:src/resources/sprites/mario.png");
    	ennemyBox.setRotationAxis(new Point3D(0, 1, 0));
    	ennemyBox.translateXProperty().bind(coordXproperty);
    	ennemyBox.translateYProperty().bind(coordYProperty);
    	
    	return ennemyBox;
    }
}
