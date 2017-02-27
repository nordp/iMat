package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import BackendMediators.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-02-23.
 */
public class PaymentController implements Initializable{
    @FXML RadioButton invoicePayment;
    @FXML RadioButton cardPayment;
    @FXML AnchorPane invoicePane;
    @FXML AnchorPane cardPane;
    @FXML TextField cardFirstFour;
    @FXML TextField cardSecondFour;
    @FXML TextField cardThirdFour;
    @FXML TextField cardFourthFour;
    @FXML ComboBox<Integer> validYear;
    @FXML ComboBox<Integer> validMonth;
    @FXML TextField CVCCode;
    CustomerHandler customerHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerHandler = new CustomerHandler();
        if(true){ //TODO REPLACE WITH customerHandler.isFirstRun()
            cardPane.setVisible(false);
            invoicePane.setVisible(false);
        }
    }


    @FXML private void paymentChosen(ActionEvent event) {
        invoicePane.setVisible(event.getSource() == invoicePayment);
        cardPane.setVisible(event.getSource() == cardPayment);
        System.out.println("clicked");
    }

    public RadioButton getInvoiceButton() {
        return invoicePayment;
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
