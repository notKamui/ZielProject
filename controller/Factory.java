package controller;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import model.Ennemy;
import javafx.scene.layout.Pane;
import model.Map;
import model.MathDataBuilder;
import model.Player;
import model.World;

class Factory {

    static World initWorld() {
        World world = new World();

        Ennemy n = new Ennemy(80*6, 80*3, world);
        Player player = new Player(MathDataBuilder.TILESIZE * 65, MathDataBuilder.TILESIZE * 4);
        Map map = new Map();

        World world = new World();
        world.setPlayer(player);
        world.setMap(map);
        world.setEnnemy(n);
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
        /*playerBox.setPrefSize(MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1]);
        playerBox.setMinSize(MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1]);
        playerBox.setMaxSize(MathDataBuilder.PLAYERDIM[0], MathDataBuilder.PLAYERDIM[1]);*/
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
