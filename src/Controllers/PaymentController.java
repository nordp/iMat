package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-02-23.
 */
public class PaymentController implements ISubCheckoutController{
    @FXML RadioButton DeliveryPayment;
    @FXML TextField cardFirstFour;
    @FXML TextField cardSecondFour;
    @FXML TextField cardThirdFour;
    @FXML TextField cardFourthFour;
    @FXML ComboBox<Integer> validYear;
    @FXML ComboBox<Integer> validMonth;
    @FXML TextField CVCCode;

    @Override
    public void nextPaneSelected() {

    }

    @Override
    public void previousPaneSelected() {

    }

    @Override
    public void paneLinkClicked() {

    }

    @FXML private void paymentChosen(ActionEvent event) {
        System.out.println("clicked");
    }

    public RadioButton getDeliveryPayment() {
        return DeliveryPayment;
    }

    public TextField getCardFirstFour() {
        return cardFirstFour;
    }

    public TextField getCardSecondFour() {
        return cardSecondFour;
    }

    public TextField getCardThirdFour() {
        return cardThirdFour;
    }

    public TextField getCardFourthFour() {
        return cardFourthFour;
    }

    public ComboBox<Integer> getValidYear() {
        return validYear;
    }

    public ComboBox<Integer> getValidMonth() {
        return validMonth;
    }

    public TextField getCVCCode() {
        return CVCCode;
    }
}
