package Controllers;

import BackendMediators.CustomerHandler;
import BackendMediators.ICustomerHandler;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import ListCells.HistoryElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-03-06.
 */
public class ShoppingListsController implements Initializable{
    private static ShoppingListsController instance;
    private @FXML Accordion itemAccordion;
    private IStoreHandler storeHandler = StoreHandler.getInstance();
    private ICustomerHandler customerHandler = CustomerHandler.getInstance();
    public static ShoppingListsController getInstance(){
        if(instance == null){
            instance = new ShoppingListsController();
        }
        return instance;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        updateList();
    }
    public void updateList(){
        itemAccordion.getPanes().clear();
        HashMap <String, List<ShoppingItem>> shoppingLists = customerHandler.getShoppingLists();
        for (int i = 0; i<shoppingLists.size(); i++) {
            System.out.println(customerHandler.getKey(i));
            List<ShoppingItem> list = shoppingLists.get(customerHandler.getKey(i));
            itemAccordion.getPanes().add(new ListCells.ShoppingListElement(list).getBackgroundPane());
        }
    }
}
