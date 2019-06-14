package controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.DynamicObject;
import model.Enemy;
import model.MathDataBuilder;

import java.util.HashMap;
import java.util.Map;

public class DynamicListener implements ListChangeListener<DynamicObject> {
    Pane paneOverworld;
    Map<DynamicObject, ImageView> dynImgMap;
    Map<DynamicObject, Rectangle> dynBarMap;


    public DynamicListener(Pane pane) {
        paneOverworld = pane;
        dynImgMap = new HashMap<>();
        dynBarMap = new HashMap<>();
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends DynamicObject> change) {
        while (change.next()) {
            DynamicObject dynamicObject;
            if (change.wasAdded()) {
                dynamicObject = change.getAddedSubList().get(0);
                ImageView dynamicBox = new ImageView(Factory.idToUrl.get(dynamicObject.getId()));
                if (dynamicObject.getId() >= 0 && dynamicObject.getId() <= 100) {
                    dynamicBox.setFitWidth(MathDataBuilder.ITEMSIZE);
                    dynamicBox.setFitHeight(MathDataBuilder.ITEMSIZE);
                }
                if(dynamicObject.getId() >= 900) {
                	Enemy enemy = (Enemy) dynamicObject;
                	Rectangle healthBar = new Rectangle(-5,-10 , 60, 5);
                	healthBar.setFill(Color.RED);
                	healthBar.translateXProperty().bind(enemy.coordXProperty());
                	healthBar.translateYProperty().bind(enemy.coordYProperty());
                	healthBar.toFront();
                	paneOverworld.getChildren().add(healthBar);
                	enemy.getHpProperty().addListener(new ChangeListener<Number>() {
                			public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                                Number newValue) {
                				healthBar.setWidth((double) newValue /enemy.getMaxHp()*60);
            }});
                    dynBarMap.put(dynamicObject, healthBar);


                }
                dynamicBox.translateXProperty().bind(dynamicObject.coordXProperty());
                dynamicBox.translateYProperty().bind(dynamicObject.coordYProperty());
                dynamicBox.setRotationAxis(new Point3D(0, 1, 0));
                dynamicBox.rotateProperty().bind(dynamicObject.directionProperty());
                dynamicBox.setMouseTransparent(true);
                paneOverworld.getChildren().add(dynamicBox);
                dynImgMap.put(dynamicObject, dynamicBox);
            }
            if (change.wasRemoved()) {
                dynamicObject = change.getRemoved().get(0);
                paneOverworld.getChildren().remove(dynImgMap.get(dynamicObject));
                paneOverworld.getChildren().remove(dynBarMap.get(dynamicObject));
                
            }
        }
    }
}
