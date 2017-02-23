package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-02-23.
 */
public class DeliveryController implements ISubCheckoutController{
    @FXML TextField nameTF;
    @FXML TextField addressTF;
    @FXML TextField postcodeTF;
    @FXML TextField cityTF;
    @FXML TextArea miscInfoTA;


    @Override
    public void nextPaneSelected() {

    }

    @Override
    public void previousPaneSelected() {

    }

    @Override
    public void paneLinkClicked() {

    }
}
