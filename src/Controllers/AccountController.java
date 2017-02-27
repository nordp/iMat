package Controllers;

import BackendExtension.CustomerListener;
import BackendMediators.CustomerHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
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
    @FXML private ComboBox<String> monthExp;
    @FXML private ComboBox<String> yearExp;

    CustomerHandler customerHandler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         customerHandler = new CustomerHandler();
         customerHandler.addCustomerListener(this);
         customerInfoChanged(customerHandler.getCustomer(),customerHandler.getSavedCreditCard());
    }

    @FXML private void saveText(ActionEvent actionEvent) {
        Customer customer = customerHandler.getCustomer();
        CreditCard card = customerHandler.getSavedCreditCard();
        if (actionEvent.getSource().equals(nameTF)) {
            if (nameTF.getText().contains(" ")) {
                String[] names = nameTF.getText().split(" ");
                customer.setFirstName(names[0]);
                customer.setLastName(names[1]);
            } else {
                customer.setFirstName(nameTF.getText());
                customer.setLastName("");
            }
        } else if (actionEvent.getSource().equals(addressTF)) {
            customer.setAddress(addressTF.getText());
        } else if (actionEvent.getSource().equals(postCodeTF)) {
            customer.setPostCode(postCodeTF.getText());
        } else if (actionEvent.getSource().equals(cityTF)) {
            customer.setPostAddress(cityTF.getText());
        } else if (actionEvent.getSource().equals(firstFour)) {
            card.setCardNumber(firstFour.getText() + card.getCardNumber().substring(4, 15));
        } else if (actionEvent.getSource().equals(secondFour)) {
            card.setCardNumber(card.getCardNumber().substring(0, 3) + secondFour.getText() + card.getCardNumber().substring(8, 15));
        } else if (actionEvent.getSource().equals(thirdFour)) {
            card.setCardNumber(card.getCardNumber().substring(0, 7) + thirdFour.getText() + card.getCardNumber().substring(12, 15));
        } else if (actionEvent.getSource().equals(lastFour)) {
            card.setCardNumber(card.getCardNumber().substring(0, 11) + lastFour.getText());
        } else if (actionEvent.getSource().equals(cardHolderTF)) {
            card.setHoldersName(cardHolderTF.getText());
        } else if (actionEvent.getSource().equals(cvcTF)) {
            card.setVerificationCode(Integer.parseInt(cvcTF.getText()));
        } else if (actionEvent.getSource().equals(monthExp)) {
            card.setValidMonth(Integer.parseInt(monthExp.getSelectionModel().getSelectedItem()));
        } else if (actionEvent.getSource().equals(yearExp)){
            card.setValidYear(Integer.parseInt(yearExp.getSelectionModel().getSelectedItem()));
        }
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());
    }

    @Override
    public void customerInfoChanged(Customer customer, CreditCard card) {
        nameTF.setText(customer.getFirstName() + " " + customer.getLastName());
        addressTF.setText(customer.getAddress());
        postCodeTF.setText(customer.getPostCode());
        cityTF.setText(customer.getPostAddress());

        if (card.getCardNumber().length()<16) { return; }
        firstFour.setText(card.getCardNumber().substring(0,3));
        firstFour.setText(card.getCardNumber().substring(4,7));
        firstFour.setText(card.getCardNumber().substring(8,11));
        firstFour.setText(card.getCardNumber().substring(12,15));
    }
}
