package controller;

import javafx.collections.ListChangeListener;
import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.DynamicObject;
import model.MathDataBuilder;

import java.util.HashMap;
import java.util.Map;

public class DynamicListener implements ListChangeListener<DynamicObject> {
    Pane paneOverworld;
    Map<DynamicObject, ImageView> dynImgMap;

    public DynamicListener(Pane pane) {
        paneOverworld = pane;
        dynImgMap = new HashMap<>();
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends DynamicObject> change) {
        while (change.next()) {
            DynamicObject dynamicObject;
            if (change.wasAdded()) {
                dynamicObject = change.getAddedSubList().get(0);
                ImageView dynamicBox = new ImageView(Factory.idToUrl.get(dynamicObject.getId()));
                dynamicBox.setFitWidth(MathDataBuilder.ITEMSIZE);
                dynamicBox.setFitHeight(MathDataBuilder.ITEMSIZE);
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
            }
        }
    }
}
