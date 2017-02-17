import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable{

        @FXML AnchorPane pane;
        @FXML Button nextButton;
        @FXML Label delivery;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            try {
                pane.getChildren().removeAll();
                pane.getChildren().add(FXMLLoader.load(getClass().getResource("layouts/cart.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML protected void nextButtonPressed(ActionEvent event){


        }
    }
