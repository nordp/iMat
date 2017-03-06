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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ConfirmationController implements CustomerListener, ShoppingCartListener, Initializable, ActivePaneListener{
    @FXML TextArea deliveryInfoTA;
    @FXML TextField cardNumberTF1;
    @FXML TextField cardNameTF1;
    @FXML TextField validMonthTF1;
    @FXML TextField validYearTF1;
    @FXML Label sumLabel1;
    @FXML Label itemsLabel1;
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
        if(customerHandler.isDirectPaymentSelcted()) {
            cardNumberTF1.setText("****");
            for (int i = 1; i < 3; i++) {
                cardNumberTF1.appendText(" " + customerHandler.getCardFour(i));
            }
            cardNumberTF1.appendText(" ****");
            validMonthTF1.setText(String.valueOf(customerHandler.getValidMonth()));
            validYearTF1.setText(String.valueOf(customerHandler.getValidYear()));
            cardNameTF1.setText(customerHandler.getCardHolder());
        }
        //TODO Set labels to reflect current info
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        sumLabel1.setText((int)storeHandler.getCartPrice() + " kr");
        itemsLabel1.setText(storeHandler.getCurrentShoppingCart().size() + " st");
    }

    @Override
    public void receivedActive() {
        Date day = customerHandler.getDeliveryDate();
        Date current = new Date();
        Calendar currentDay = Calendar.getInstance();
        currentDay.setTime(current);
        Calendar deliveryDay = Calendar.getInstance();
        deliveryDay.setTime(day);
        if(deliveryDay.get(Calendar.DATE) == currentDay.get(Calendar.DATE)){
            deliveryDayLabel.setText("Idag");
        }
        else if(deliveryDay.get(Calendar.DATE) == currentDay.get(Calendar.DATE)+1){
            deliveryDayLabel.setText("Imorgon");
        }
        else{
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            deliveryDayLabel.setText(dayFormat.format(day));
        }
        String time =  deliveryDay.get(Calendar.HOUR_OF_DAY)
                + "-" + (deliveryDay.get(Calendar.HOUR_OF_DAY)+3);
        deliveryTimeLabel.setText(time);
    }
    // A Method that handels the next button clicked should be implemented. Interface or SuperClass?
}
