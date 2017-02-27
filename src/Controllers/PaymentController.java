package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import BackendMediators.*;
import se.chalmers.ait.dat215.project.CreditCard;

/**
 * Created by gustav on 2017-02-23.
 */
public class PaymentController{
    @FXML RadioButton DeliveryPayment;
    @FXML TextField cardFirstFour;
    @FXML TextField cardSecondFour;
    @FXML TextField cardThirdFour;
    @FXML TextField cardFourthFour;
    @FXML ComboBox<Integer> validYear;
    @FXML ComboBox<Integer> validMonth;
    @FXML TextField CVCCode;
    CustomerHandler handler = new CustomerHandler();

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
