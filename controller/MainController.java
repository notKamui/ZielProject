package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import model.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javax.imageio.ImageIO;

public class MainController {
    private Map map;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tilePaneMap"
    private TilePane tilePaneMap; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        map = new Map();
        int i; for(i = 0; i < this.map.getMap().size(); i++) {
            try {
                BufferedImage buffImg = ImageIO.read(new File(this.map.getTileAt(i).getURL()));
                Image img = SwingFXUtils.toFXImage(buffImg, null);
                tilePaneMap.getChildren().add(new ImageView(img));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

    }
}
