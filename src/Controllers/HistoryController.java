package Controllers;

import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import ListCells.HistoryElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import se.chalmers.ait.dat215.project.Order;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-03-03.
 */
public class HistoryController implements Initializable {

    @FXML
    Accordion orderAccordion;
    IStoreHandler storeHandler = StoreHandler.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Order> orders = storeHandler.getOrders();
        for (Order currentOrder : orders) {
            orderAccordion.getPanes().add(new HistoryElement(currentOrder).getBackgroundPane());
        }
    }
}