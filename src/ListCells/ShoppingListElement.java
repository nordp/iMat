package ListCells;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    FXMLLoader loader;
    List<ShoppingItem> shoppingList;
    @FXML ListView itemList;
    public ShoppingListElement(List<ShoppingItem> shoppingList) {
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
        itemList.setCellFactory(param -> new CartElement());
    }
    public TitledPane getBackgroundPane(){
        return backgroundPane;
    }
}
