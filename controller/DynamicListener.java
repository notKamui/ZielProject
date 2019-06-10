package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.DynamicObject;
import model.MathDataBuilder;

public class DynamicListener implements ListChangeListener<DynamicObject> {
	Pane paneOverworld;
	Map<DynamicObject, ImageView> test;
	public DynamicListener(Pane pane) {
		paneOverworld = pane;
		test = new HashMap<>();
	}
    @Override
    public void onChanged(ListChangeListener.Change<? extends DynamicObject> change) {
        while (change.next()) {
        DynamicObject i;  
            if (change.wasAdded()) {
            	i = change.getAddedSubList().get(0);
            	ImageView itemBox = new ImageView("file:src/resources/sprites/mario.png");
            	itemBox.translateXProperty().bind(i.coordXProperty());
            	itemBox.translateYProperty().bind(i.coordYProperty());
            	itemBox.setRotationAxis(new Point3D(0, 1, 0));
                itemBox.rotateProperty().bind(i.directionProperty());
                itemBox.setMouseTransparent(true);
            	paneOverworld.getChildren().add(itemBox);
            	test.put(i, itemBox);
            }
            if(change.wasRemoved()) {
            	i= change.getRemoved().get(0);
            	paneOverworld.getChildren().remove(test.get(i));
            }
        }
    }
};
