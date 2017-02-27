package Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import BackendMediators.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.EventListener;
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
    @FXML TextField cardName;
    CustomerHandler customerHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerHandler = new CustomerHandler();
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

    private void setNextTextFieldOn4Chars(TextField a, TextField b)
    {
        a.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length() == 4)
                {
                    b.requestFocus();
                }
            }
        });
    }

    private void setNumericTextField(TextField field)
    {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML private void paymentChosen(ActionEvent event) {
        invoicePane.setVisible(event.getSource() == invoicePayment);
        cardPane.setVisible(event.getSource() == cardPayment);
        System.out.println("clicked");
    }

    public boolean IsInputValid()
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
