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

public class HistoryController implements Initializable {

    @FXML Accordion orderAccordion;
    IStoreHandler storeHandler = StoreHandler.getInstance();
    private static HistoryController instance;
    public static HistoryController getInstance(){
        if(instance == null){
            instance = new HistoryController();
        }
        return instance;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        updateList();
    }
    public void updateList(){
        orderAccordion.getPanes().clear();
        List<Order> orders = storeHandler.getOrders();
            for (Order currentOrder : orders) {
                orderAccordion.getPanes().add(new HistoryElement(currentOrder).getBackgroundPane());
            }
    }
}