package Controllers;

import BackendExtension.CustomerListener;
import Main.SequenceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import BackendMediators.*;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.ShoppingCartListener;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationController implements CustomerListener, ShoppingCartListener, Initializable, ActivePaneListener{
    @FXML TextArea deliveryInfoTA;
    @FXML TextField cardNumberTF;
    @FXML TextField cardNameTF;
    @FXML TextField validMonthTF;
    @FXML TextField validYearTF;
    @FXML Label sumLabel;
    @FXML Label itemsLabel;
    @FXML Label deliveryDayLabel;
    @FXML Label deliveryTimeLabel;
    CustomerHandler customerHandler;
    StoreHandler storeHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        storeHandler = new StoreHandler();
        customerHandler = CustomerHandler.getInstance();
        storeHandler.addShoppingCartListener(this);
        customerHandler.addCustomerListener(this);
        shoppingCartChanged(null);
        customerInfoChanged();
    }

    public void changeDeliveryClicked(ActionEvent event) {
        //TODO
    }

    public void changePaymentClicked(ActionEvent event) {
        //TODO
        // something about Opening paymentPane and returning to Confirmation on nextButton clicked.
        // How should Previous Button be handled in this case.
    }

    @Override
    public void customerInfoChanged() {
        StringBuilder deliveryInfo = new StringBuilder("namn: ");
        deliveryInfo.append(customerHandler.getFirstName() + " " + customerHandler.getLastName() + "\nAdress: " +
        customerHandler.getAddress() + "\nPostkod: " + customerHandler.getPostCode() + "\nPostAdress: " + customerHandler.getPostAddress());
        deliveryInfoTA.setText(deliveryInfo.toString());    // Färgen här blir helt fel.
        cardNumberTF.setText("****");
        for (int i = 1; i<3; i++) {
            cardNumberTF.appendText(" " + customerHandler.getCardFour(i));
        }
        cardNumberTF.appendText(" ****");
        validMonthTF.setText(String.valueOf(customerHandler.getValidMonth()));
        validYearTF.setText(String.valueOf(customerHandler.getValidYear()));
        cardNameTF.setText(customerHandler.getCardHolder());

        //TODO Set labels to reflect current info
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        sumLabel.setText((int)storeHandler.getCartPrice() + " kr");
        itemsLabel.setText(storeHandler.getCurrentShoppingCart().size() + " st");
    }

    @Override
    public void receivedActive() {
    }
    // A Method that handels the next button clicked should be implemented. Interface or SuperClass?
}
