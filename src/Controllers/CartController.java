package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void nextPaneSelected() {

    }

    @Override
    public void previousPaneSelected() {

    }

    @Override
    public void paneLinkClicked() {

    }

    public void onComboBoxClicked(ActionEvent event) {
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
                System.out.println("Inte ett möjligt val.");
                return;
        }
    }
}
