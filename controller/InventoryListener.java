package controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Item;

public class InventoryListener implements ListChangeListener<Item> {
    private HBox quickInventory;

    public InventoryListener(HBox container) {
        this.quickInventory = container;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Item> change) {
        while (change.next()) {
            // TODO optimiser tout ca oulah

            int id = 0;
            int id2 = 0;
            if (change.getAddedSubList().size() != 0)
                id = change.getAddedSubList().get(0).getId();
            if (change.getRemoved().size() != 0)
                id2 = change.getRemoved().get(0).getId();

            if (change.wasRemoved()) {
                if (id2 != 0) {
                    Pane pane = (Pane) quickInventory.getChildren().get(change.getFrom());
                    ImageView img = (ImageView) pane.getChildren().get(1);
                    img.setImage(null);
                    change.getFrom();
                }
            }

            if (change.wasAdded()) {
                if (id != 0) {
                    boolean isAdded = false;
                    for (int slot = 0; !isAdded && slot < quickInventory.getChildren().size(); slot++) {
                        Pane pane = (Pane) quickInventory.getChildren().get(slot);
                        ImageView img = (ImageView) pane.getChildren().get(1);
                        if (null == img.getImage()) {
                            img.setImage(new Image("file:src/resources/tiles/ground/groundTop.png"));
                            //img.setImage(new Image("file:src/resources/item/"+id+".png"));
                            isAdded = true;
                        }
                    }
                }
            }
        }
    }
}
