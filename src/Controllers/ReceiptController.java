package Controllers;

import BackendMediators.CustomerHandler;
import BackendMediators.ICustomerHandler;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

/**
 * Created by Phnor on 2017-02-28.
 */
public class ReceiptController implements Initializable, ActivePaneListener{

    @FXML Label dateLabel;
    @FXML Label sumLabel;
    @FXML Label timeLabel;
    @FXML ListView receiptList;
    ICustomerHandler customerHandler = CustomerHandler.getInstance();
    IStoreHandler storeHandler = StoreHandler.getInstance();
    ObservableList<ShoppingItem> dispList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML private void saveShoppingList(){

    }

    @FXML private void print(){

    }

    @Override
    public void receivedActive() {
        storeHandler.placeOrder();
        Order lastOrder = storeHandler.getLastOrder();
        //sumLabel.setText(storeHandler.getLastOrder());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateLabel.setText(dateFormat.format(lastOrder.getDate()));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeLabel.setText(timeFormat.format(lastOrder.getDate()));
        List<ShoppingItem> list = storeHandler.getLastOrder().getItems();
        dispList = FXCollections.observableList(list);
        receiptList.setItems(dispList);
        receiptList.setCellFactory(param -> new ListCells.CheckoutCartElement());
    }
}
