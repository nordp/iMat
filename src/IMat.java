import BackendMediators.CustomerHandler;
import BackendMediators.StoreHandler;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ResourceBundle;

public class IMat extends Application {

    public static void main(String[] args) {
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                StoreHandler.getInstance().shutDown();
                CustomerHandler.getInstance().shutDown();
            }
        }));
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("layouts/main_window.fxml"), bundle);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("layouts/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("iMat");
    }
}
