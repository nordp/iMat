package Controllers;

import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import ListCells.CartElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.*;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.out;

/**
 * Created by gustav on 2017-02-23.
 */
public class CartController implements ShoppingCartListener, Initializable{
    private final int SORT_BY_PRICE = 0;
    private final int SORT_BY_ALPHABETICAL_ORDER = 1;
    private final int SORT_BY_SUBCATEGORY = 2;
    @FXML ListView cartList;
    @FXML Button sortFromButton;
    @FXML ComboBox<String> sortByCB;
    IStoreHandler handler;

    public CartController(){
        handler = new StoreHandler();
        handler.addShoppingCartListener(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ShoppingItem> list = FXCollections.observableList(handler.getCurrentShoppingCart());
        cartList.setItems(list);
        cartList.setCellFactory(param -> new CartElement());
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
            cartList.setItems(FXCollections.observableList(handler.getCurrentShoppingCart()));
    }

    public void onComboBoxClicked(ActionEvent event) { //TODO Borde uppdatera oavsett om man klickar eller använder andra sätt att välja.
        int selectedIndex = sortByCB.getSelectionModel().getSelectedIndex();
        //Way to select direction.
        switch (selectedIndex){
            case SORT_BY_PRICE:
                return;
            case SORT_BY_ALPHABETICAL_ORDER:
                return;
            case SORT_BY_SUBCATEGORY:
                return;
            default:
                out.println("Inte ett möjligt val.");
                return;
        }
    }
}
