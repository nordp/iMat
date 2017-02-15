/**
 * Created by Phnor on 2017-02-15.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class IMat extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("layouts/main_window.fxml"), bundle);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
