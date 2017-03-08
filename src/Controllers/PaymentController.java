package Controllers;

import Main.SequenceHandler;
import BackendExtension.CustomerListener;
import Utility.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import BackendMediators.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

import static Utility.Util.*;

/**
 * Created by gustav on 2017-02-23.
 */
public class PaymentController implements Initializable, CustomerListener, ActivePaneListener{
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
    @FXML TextField cardName;
    CustomerHandler customerHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardFirstFour.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        cardSecondFour.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        cardThirdFour.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        cardFourthFour.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        validYear.valueProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        validMonth.valueProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        CVCCode.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        cardName.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());

        customerHandler = CustomerHandler.getInstance();
        customerHandler.addCustomerListener(this);
        customerInfoChanged();
        if(true){ //TODO REPLACE WITH customerHandler.isFirstRun()
            cardPane.setVisible(false);
            invoicePane.setVisible(false);
        }

        for(int i = 16; i < 27; i++)
        {
            validYear.getItems().add(i);
        }
        for(int i = 1; i < 13; i++)
        {
            validMonth.getItems().add(i);
        }

        setNumericTextField(cardFirstFour);
        setNumericTextField(cardSecondFour);
        setNumericTextField(cardThirdFour);
        setNumericTextField(cardFourthFour);

        setNextTextFieldOn4Chars(cardFirstFour, cardSecondFour);
        setNextTextFieldOn4Chars(cardSecondFour, cardThirdFour);
        setNextTextFieldOn4Chars(cardThirdFour, cardFourthFour);
        setNextTextFieldOn4Chars(cardFourthFour, cardName);
    }
    private void updateButtonsAndLinks(){
        SequenceHandler.getInstance().setInputValid(isInputValid());
    }
    @FXML private void paymentChosen(ActionEvent event) {
        invoicePane.setVisible(event.getSource() == invoicePayment);
        cardPane.setVisible(event.getSource() == cardPayment);
        customerHandler.directPaymentSelected(event.getSource() == cardPayment);
        updateButtonsAndLinks();
    }

    public boolean isInputValid()
    {
        if(invoicePayment.getToggleGroup().getSelectedToggle() == cardPayment)
        {
            //
            if(cardFirstFour.getText().length() == 4 &&
                    cardFirstFour.getText().length() == 4 &&
                    cardFirstFour.getText().length() == 4 &&
                    cardFirstFour.getText().length() == 4 &&
                    cardName.getText().length() > 2 &&
                    CVCCode.getText().length() == 3 &&
                    validYear.getSelectionModel().getSelectedItem() != null &&
                    validMonth.getSelectionModel().getSelectedItem() != null)
            {
                return true;
            }

            // The user needs to put an input to all fields: we can make this better later.
            return false;
        }
        else if(invoicePayment.getToggleGroup().getSelectedToggle() == invoicePayment)
        {
            // The user will pay when the delivery arrives
            return true;
        }
        else
        {
            // The message to the user is; Pick a payment method.
            return false;
        }
    }


    @Override
    public void customerInfoChanged() {
        System.out.println("Payment received");
        cardFirstFour.setText(customerHandler.getCardFour(0));
        cardSecondFour.setText(customerHandler.getCardFour(1));
        cardThirdFour.setText(customerHandler.getCardFour(2));
        cardFourthFour.setText(customerHandler.getCardFour(3));
        validYear.getSelectionModel().select(customerHandler.getValidMonth());
        validMonth.getSelectionModel().select(customerHandler.getValidMonth());
        CVCCode.setText(customerHandler.getSecurityCode() + "");
        cardName.setText(customerHandler.getCardHolder());
    }
    @FXML private void saveInfo(ActionEvent event){
        customerHandler.setCardNumber(cardFirstFour.getText() + cardSecondFour.getText() + cardThirdFour.getText() + cardFourthFour.getText());
        customerHandler.setHoldersName(cardName.getText());
        if (!CVCCode.getText().equals("")){
        customerHandler.setVerificationCode(Integer.valueOf(CVCCode.getText()));
    }
        customerHandler.setValidMonth(validMonth.getSelectionModel().getSelectedIndex());
        customerHandler.setValidYear(validYear.getSelectionModel().getSelectedIndex());
        customerHandler.fireCustomerChangedEvent();
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


    @Override
    public void receivedActive() {
        updateButtonsAndLinks();
    }
    //TODO Implmenet save method.
}
