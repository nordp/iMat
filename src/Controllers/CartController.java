package Controllers;

import BackendMediators.StoreHandler;
import ListCells.CartElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;

import static java.lang.System.out;

/**
 * Created by gustav on 2017-02-23.
 */
public class CartController implements ISubCheckoutController{
    private final int SORT_BY_PRICE = 0;
    private final int SORT_BY_ALPHABETICAL_ORDER = 1;
    private final int SORT_BY_SUBCATEGORY = 2;
    @FXML ListView cartList;
    @FXML Button sortFromButton;
    @FXML ComboBox<String> sortByCB;
    StoreHandler handler = new StoreHandler();
    @Override
    public void focusReceived() {
        handler.addToCart(new ShoppingItem(handler.getProductsFromCategories(ProductCategory.BERRY).get(1)));
        out.println(handler.getCurrentShoppingCart().get(0).getProduct());
        ObservableList<ShoppingItem> list = FXCollections.observableList(handler.getCurrentShoppingCart());
        cartList.setItems(list);
        cartList.setCellFactory(param -> new CartElement());
        // cartList should be populated when this method is called.
        // the comboBox should be populated as well.
    }

    @Override
    public void focusLost() {

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
