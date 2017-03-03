package ListCells;

import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import Utility.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustav on 2017-03-03.
 */
public class HistoryElement{
    private FXMLLoader loader;

    @FXML TitledPane backgroundPane;
    @FXML ListView itemList;
    @FXML Label orderNumberLabel;
    @FXML Label totalPriceLabel;
    @FXML Label dateLabel;
    IStoreHandler storeHandler = StoreHandler.getInstance();

    public HistoryElement(Order order){
        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("/layouts/history_element.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<ShoppingItem> tempList = order.getItems();
        ObservableList<ShoppingItem> list = FXCollections.observableList(tempList);
        itemList.setItems(list);
        itemList.setCellFactory(param -> new CartElement());
        orderNumberLabel.setText(String.valueOf(order.getOrderNumber()));
        dateLabel.setText(Util.formatDate(order.getDate()));
        totalPriceLabel.setText(String.valueOf(storeHandler.getTotalOrderPrice(order)));
    }

    public TitledPane getBackgroundPane() {
        return backgroundPane;
    }
}
