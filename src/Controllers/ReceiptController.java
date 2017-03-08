package Controllers;

import BackendMediators.CustomerHandler;
import BackendMediators.ICustomerHandler;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import Main.IMatController;
import Main.SequenceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import sun.java2d.pipe.SpanShapeRenderer;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import static Utility.Util.calcSum;
import static Utility.Util.formatDate;
import static Utility.Util.formatTime;

/**
 * Created by Phnor on 2017-02-28.
 */
public class ReceiptController implements Initializable, ActivePaneListener{

    @FXML Label dateLabel;
    @FXML Label sumLabel;
    @FXML Label timeLabel;
    @FXML ListView receiptList;
    @FXML Button saveButton;
    ICustomerHandler customerHandler = CustomerHandler.getInstance();
    IStoreHandler storeHandler = StoreHandler.getInstance();
    ObservableList<ShoppingItem> dispList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML private void saveShoppingList(){
        Order order = storeHandler.getLastOrder();
        customerHandler.addShoppingList(formatDate(order.getDate()) + " " + formatTime(order.getDate()), order.getItems());
        saveButton.setText("Inköpslista sparad");
        saveButton.setDisable(true);
    }
    public void resetSaveButton(){
        saveButton.setDisable(false);
        saveButton.setText("Spara inköpslista");
    }
    @FXML private void toHistory(){
        IMatController.getInstance().historyClicked();
    }

    @FXML private void print(){

    }

    @Override
    public void receivedActive() {
        storeHandler.placeOrder();
        storeHandler.clearCurrentShoppingCart();
        Order lastOrder = storeHandler.getLastOrder();
        sumLabel.setText(calcSum(lastOrder.getItems()) + "");

        dateLabel.setText(formatDate(lastOrder.getDate()));
        timeLabel.setText(formatTime(lastOrder.getDate()));
        List<ShoppingItem> list = lastOrder.getItems();
        dispList = FXCollections.observableList(list);
        receiptList.setItems(dispList);
        receiptList.setCellFactory(param -> new ListCells.CheckoutCartElement());
    }
}
