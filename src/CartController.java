import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.out;

/**
 * Created by gustav on 2017-02-16.
 */
public class CartController implements Initializable{

    @FXML AnchorPane pane;
    @FXML Button nextButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML protected void nextButtonPressed(ActionEvent event){


    }
}
