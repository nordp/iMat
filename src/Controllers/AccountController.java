package Controllers;

import BackendExtension.CustomerListener;
import BackendMediators.CustomerHandler;
import Utility.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Phnor on 2017-02-27.
 */
public class AccountController implements Initializable, CustomerListener {
    @FXML private TextField nameTF;
    @FXML private TextField addressTF;
    @FXML private TextField postCodeTF;
    @FXML private TextField cityTF;

    @FXML private TextField firstFour;
    @FXML private TextField secondFour;
    @FXML private TextField thirdFour;
    @FXML private TextField lastFour;
    @FXML private TextField cardHolderTF;
    @FXML private TextField cvcTF;
    @FXML private ComboBox<Integer> monthExp;
    @FXML private ComboBox<Integer> yearExp;

    CustomerHandler customerHandler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         customerHandler = CustomerHandler.getInstance();
         customerHandler.addCustomerListener(this);
         Util.setNumericTextField(firstFour);
         Util.setNumericTextField(secondFour);
         Util.setNumericTextField(thirdFour);
         Util.setNumericTextField(lastFour);
         Util.setNextTextFieldOn4Chars(firstFour, secondFour);
         Util.setNextTextFieldOn4Chars(secondFour, thirdFour);
         Util.setNextTextFieldOn4Chars(thirdFour, lastFour);
         Util.setNextTextFieldOn4Chars(lastFour, cardHolderTF);
         for(int i = 16; i < 27; i++)
         {
             yearExp.getItems().add(i);
         }
         for(int i = 1; i < 13; i++)
         {
             monthExp.getItems().add(i);
         }
         customerInfoChanged();
    }

    @FXML private void saveText() {
        if (nameTF.getText().contains(" ")) {
            String[] names = nameTF.getText().split(" ");
            if(names.length>0) {
                customerHandler.setFirstName(names[0]);
            }
            if(names.length>1) {
                customerHandler.setLastName(names[1]);
            }
        } else {
            customerHandler.setFirstName(nameTF.getText());
            customerHandler.setLastName("");
        }
        customerHandler.setAddress(addressTF.getText());
        customerHandler.setPostCode(postCodeTF.getText());
        customerHandler.setPostAddress(cityTF.getText());
        customerHandler.setCardNumber(firstFour.getText() + secondFour.getText() + thirdFour.getText() + lastFour.getText());
        customerHandler.setHoldersName(cardHolderTF.getText());
        if (!cvcTF.getText().equals("")){
            customerHandler.setVerificationCode(Integer.valueOf(cvcTF.getText()));
        }
        customerHandler.setValidMonth(monthExp.getSelectionModel().getSelectedIndex());
        customerHandler.setValidYear(yearExp.getSelectionModel().getSelectedIndex());
        customerHandler.fireCustomerChangedEvent();
    }
    @FXML private void saveButton(ActionEvent event){
        saveText();
        LightboxController.closeWindow();
    }
    @Override
    public void customerInfoChanged() {
        nameTF.setText(customerHandler.getFirstName() + " " + customerHandler.getLastName());
        addressTF.setText(customerHandler.getAddress());
        postCodeTF.setText(customerHandler.getPostCode());
        cityTF.setText(customerHandler.getPostAddress());

        if (customerHandler.getCardNumber().length()<16) { return; }
        firstFour.setText(customerHandler.getCardFour(0));
        secondFour.setText(customerHandler.getCardFour(1));
        thirdFour.setText(customerHandler.getCardFour(2));
        lastFour.setText(customerHandler.getCardFour(3));
    }
}
