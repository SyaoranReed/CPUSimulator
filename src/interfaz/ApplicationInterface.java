package interfaz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ApplicationInterface extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("./MainInterface.fxml"));
		Pane root = loader.load();
		Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(root.getPrefHeight());
		primaryStage.setMinWidth(root.getPrefWidth());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
