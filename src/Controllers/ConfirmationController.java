package Controllers;

import com.sun.org.apache.bcel.internal.generic.LADD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-02-23.
 */
public class ConfirmationController implements Initializable{
    @FXML TextArea deliveryInfoTA;
    @FXML TextField cardNumberTF;
    @FXML TextField cardNameTF;
    @FXML TextField validMonthTF;
    @FXML TextField validYearTF;
    @FXML Label sumLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize all information that's been entered.
    }
    public void changeDeliveryClicked(ActionEvent event) {
        // something about Opening deliveryPane and returning to Confirmation on nextButton clicked.
    }

    public void changePaymentClicked(ActionEvent event) {
        // something about Opening paymentPane and returning to Confirmation on nextButton clicked.
        // How should Previous Button be handled in this case.
    }
    // A Method that handels the next button clicked should be implemented. Interface or SuperClass?
}
