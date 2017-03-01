package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Phnor on 2017-02-28.
 */
public class ReceiptController implements Initializable, ActivePaneListener{

    @FXML Label dateLabel;
    @FXML Label sumLabel;
    @FXML Label timeLabel;
    @FXML ListView receiptList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML private void saveShoppingList(){

    }

    @FXML private void print(){

    }

    @Override
    public void receivedActive() {

    }
}
