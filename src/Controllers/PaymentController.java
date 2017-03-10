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
        if(customerHandler.isFirstRun()){
            cardPane.setVisible(false);
            invoicePane.setVisible(false);
        } else if (customerHandler.isDirectPaymentSelcted()){
            invoicePane.setVisible(false);
            cardPayment.selectedProperty().setValue(true);
        } else {
            cardPane.setVisible(false);
            invoicePayment.selectedProperty().setValue(true);
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
        customerInfoChanged();
    }
    private void updateButtonsAndLinks(){
        SequenceHandler.getInstance().setInputValid(isInputValid());
        updateValid();
    }
    @FXML private void paymentChosen(ActionEvent event) {
        invoicePane.setVisible(event.getSource() == invoicePayment);
        cardPane.setVisible(event.getSource() == cardPayment);
        customerHandler.directPaymentSelected(event.getSource() == cardPayment);
        updateButtonsAndLinks();
    }
    private void updateValid() {
        if(cardFirstFour.getText().length()==4){
            cardFirstFour.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
        }
        else{
            cardFirstFour.setStyle(("-fx-border-color: red ; -fx-border-width: 2px ;"));
        }
        if(cardSecondFour.getText().length()==4){
            cardSecondFour.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
        }
        else{
            cardSecondFour.setStyle(("-fx-border-color: red ; -fx-border-width: 2px ;"));
        }
        if(cardThirdFour.getText().length()==4){
            cardThirdFour.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
        }
        else{
            cardThirdFour.setStyle(("-fx-border-color: red ; -fx-border-width: 2px ;"));
        }
        if(cardFourthFour.getText().length()==4){
            cardFourthFour.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
        }
        else{
            cardFourthFour.setStyle(("-fx-border-color: red ; -fx-border-width: 2px ;"));
        }
        if(cardName.getText().isEmpty()){
            cardName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
            cardName.setStyle(("-fx-border-color: green ; -fx-border-width: 2px ;"));
        }
        if(CVCCode.getText().length() != 3){
            CVCCode.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
            CVCCode.setStyle(("-fx-border-color: green ; -fx-border-width: 2px ;"));
        }
        if(validMonth.getSelectionModel().isEmpty()){
            validMonth.setStyle(("-fx-border-color: red ; -fx-border-width: 2px ;"));
        }
        else {
            validMonth.setStyle(("-fx-border-color: green ; -fx-border-width: 2px ;"));
        }
        if(validYear.getSelectionModel().isEmpty()){
            validYear.setStyle(("-fx-border-color: red ; -fx-border-width: 2px ;"));
        }
        else {
            validYear.setStyle(("-fx-border-color: green ; -fx-border-width: 2px ;"));
        }
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
        cardFirstFour.setText(customerHandler.getCardFour(0));
        cardSecondFour.setText(customerHandler.getCardFour(1));
        cardThirdFour.setText(customerHandler.getCardFour(2));
        cardFourthFour.setText(customerHandler.getCardFour(3));
        validYear.getSelectionModel().select(customerHandler.getValidYear());
        validMonth.getSelectionModel().select(customerHandler.getValidMonth());
        System.out.println("month set to " + customerHandler.getValidMonth());
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

    @Override
    public void receivedActive() {
        updateButtonsAndLinks();
    }
}
