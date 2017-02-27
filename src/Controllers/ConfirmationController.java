package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import BackendMediators.*;

public class ConfirmationController{
    @FXML TextArea deliveryInfoTA;
    @FXML TextField cardNumberTF;
    @FXML TextField cardNameTF;
    @FXML TextField validMonthTF;
    @FXML TextField validYearTF;
    @FXML Label sumLabel;
    CustomerHandler customerHandler = new CustomerHandler();
    StoreHandler storeHandler = new StoreHandler();

    public void changeDeliveryClicked(ActionEvent event) {

    }

    public void changePaymentClicked(ActionEvent event) {
        // something about Opening paymentPane and returning to Confirmation on nextButton clicked.
        // How should Previous Button be handled in this case.
    }
    // A Method that handels the next button clicked should be implemented. Interface or SuperClass?
}
