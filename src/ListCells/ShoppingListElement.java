package ListCells;

import BackendMediators.CustomerHandler;
import BackendMediators.ICustomerHandler;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

/**
 * Created by gustav on 2017-03-06.
 */
public class ShoppingListElement {
    @FXML TitledPane backgroundPane;
    @FXML Label listName;
    @FXML Button addToCartButton;
    FXMLLoader loader;
    List<ShoppingItem> shoppingList;
    @FXML ListView itemList;
    ICustomerHandler customerHandler = CustomerHandler.getInstance();
    IStoreHandler storeHandler = StoreHandler.getInstance();
    public ShoppingListElement(List<ShoppingItem> shoppingList, int listIndex) {
        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("/layouts/shopping_list_element.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.shoppingList = shoppingList;
        ObservableList<ShoppingItem> list = FXCollections.observableList(shoppingList);
        itemList.setItems(list);
        itemList.setCellFactory(param -> new SavedCartsElementsController());
        listName.setText(customerHandler.getKey(listIndex));
        addToCartButton.setOnAction(event -> addListToCart());
    }
    private void addListToCart(){       // ska detta ta bort allting i varukorgen?
        for(ShoppingItem item : shoppingList) {
            storeHandler.addToCart(item);
        }
    }
    public TitledPane getBackgroundPane(){
        return backgroundPane;
    }
}
