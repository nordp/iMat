package Controllers;

import BackendExtension.ProductContainer;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import ListCells.CartElement;
import ListCells.CheckoutCartElement;
import Main.SequenceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.*;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.out;
import static java.lang.System.setOut;

/**
 * Created by gustav on 2017-02-23.
 */
public class CartController implements ShoppingCartListener, Initializable, ActivePaneListener{
    private final int SORT_BY_PRICE = 0;
    private final int SORT_BY_TOTAL_PRICE = 1;
    private final int SORT_BY_ALPHABETICAL_ORDER = 2;
    private final int SORT_BY_SUBCATEGORY = 3;
    @FXML ListView cartList;
    @FXML Label sumLabel;
    @FXML Button sortFromButton;
    @FXML ComboBox<String> sortByCB;
    boolean ascSort = true;
    IStoreHandler handler;

    public CartController(){
        handler = new StoreHandler();
        handler.addShoppingCartListener(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingCartChanged(null);
        sortFromButton.setOnAction(a ->
        {
            ascSort = !ascSort;
            onComboBoxClicked();
        });
        sortByCB.getItems().addAll(new String[]{"Jämförpris","Pris", "A-Ö", "Kategori"});
        ObservableList<ShoppingItem> list = FXCollections.observableList(handler.getCurrentShoppingCart());
        cartList.setItems(list);
        cartList.setCellFactory(param -> new CheckoutCartElement());
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
            cartList.setItems(FXCollections.observableList(handler.getCurrentShoppingCart()));
            sumLabel.setText((int)handler.getCartPrice() + " kr");

    }

    public void onComboBoxClicked() { //TODO Borde uppdatera oavsett om man klickar eller använder andra sätt att välja.
        int selectedIndex = sortByCB.getSelectionModel().getSelectedIndex();
        //Way to select direction.
        switch (selectedIndex){
            case SORT_BY_PRICE:
            {
                ObservableList<ShoppingItem> list = cartList.getItems();
                Collections.sort(list, new Comparator<ShoppingItem>() {
                    @Override
                    public int compare(ShoppingItem o1, ShoppingItem o2) {
                        int val = o1.getProduct().getPrice() > o2.getProduct().getPrice() ? -1 : (o1.getProduct().getPrice() == o2.getProduct().getPrice() ? 0 : 1);
                        return ascSort ? val : -val;
                    }
                });
            }
            return;
            case SORT_BY_TOTAL_PRICE:
            {
                ObservableList<ShoppingItem> list = cartList.getItems();
                Collections.sort(list, new Comparator<ShoppingItem>() {
                    @Override
                    public int compare(ShoppingItem o1, ShoppingItem o2) {
                        int val =  o1.getTotal() > o2.getTotal() ? -1 : (o1.getTotal() == o2.getTotal() ? 0 : 1);
                        return ascSort ? val : -val;
                    }
                });
            }
            return;
            case SORT_BY_ALPHABETICAL_ORDER:
            {
                ObservableList<ShoppingItem> list = cartList.getItems();
                Collections.sort(list, new Comparator<ShoppingItem>() {
                    @Override
                    public int compare(ShoppingItem o1, ShoppingItem o2) {
                        int val =  o1.getProduct().getName().compareToIgnoreCase(o2.getProduct().getName());
                        return ascSort ? val : -val;
                    }
                });
            }
                return;
            case SORT_BY_SUBCATEGORY:
            {
                ObservableList<ShoppingItem> list = cartList.getItems();
                Collections.sort(list, new Comparator<ShoppingItem>() {
                    @Override
                    public int compare(ShoppingItem o1, ShoppingItem o2) {
                        int val =  ProductContainer.getInstance().getCategory(o1.getProduct()).subCategory.toString().compareToIgnoreCase(
                                ProductContainer.getInstance().getCategory(o2.getProduct()).subCategory.toString());
                        return ascSort ? val : -val;
                    }
                });
            }
                return;
            default:
                out.println("Inte ett möjligt val.");
                return;
        }
    }

    @Override
    public void receivedActive() {
        System.out.println("cartActive");
    }
}
