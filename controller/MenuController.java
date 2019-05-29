package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController extends Application implements Initializable{

	@FXML
	void exitButton(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void playButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
	        URL url;
			url = new File("src/view/main.fxml").toURI().toURL();
			loader.setLocation(url);
	        BorderPane root = new BorderPane();
	        root = loader.load();
	        Stage primaryStage = new Stage();
	        Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Ziel");
            primaryStage.setScene(scene);
            primaryStage.show();
            root.requestFocus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Button b = (Button)event.getSource();
		Scene menuScene = b.getScene();
		menuScene.getWindow().hide();
	}

	@FXML
	private ImageView logo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 try {
	            FXMLLoader loader = new FXMLLoader();
	            URL url = new File("src/view/main.fxml").toURI().toURL();
	            loader.setLocation(url);
	            BorderPane root = new BorderPane();
	            root = loader.load();
	            Scene scene = new Scene(root);
	            primaryStage.setResizable(false);
	            primaryStage.setTitle("Ziel");
	            primaryStage.setScene(scene);
	            primaryStage.show();
	            root.requestFocus();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

}
