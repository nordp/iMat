package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import BackendMediators.*;

public class ConfirmationController implements ISubCheckoutController{
    @FXML TextArea deliveryInfoTA;
    @FXML TextField cardNumberTF;
    @FXML TextField cardNameTF;
    @FXML TextField validMonthTF;
    @FXML TextField validYearTF;
    @FXML Label sumLabel;
    CustomerHandler customerHandler = new CustomerHandler();
    StoreHandler storeHandler = new StoreHandler();

    @Override
    public void focusReceived() {
        // How should this even be handled? from backend, or should we save it in other fashion.
        // when focus is gained all previously entered and saved information should be displayed.
    }

    @Override
    public void focusLost() {

    }

    public void changeDeliveryClicked(ActionEvent event) {

    }

    public void changePaymentClicked(ActionEvent event) {
        // something about Opening paymentPane and returning to Confirmation on nextButton clicked.
        // How should Previous Button be handled in this case.
    }
    // A Method that handels the next button clicked should be implemented. Interface or SuperClass?
}
