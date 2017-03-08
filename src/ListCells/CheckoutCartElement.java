package ListCells;

import BackendMediators.StoreHandler;
import Utility.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.xml.soap.Text;
import java.io.IOException;

import static java.lang.System.out;

/**
 * Created by gustav on 2017-02-24.
 */
public class CheckoutCartElement extends ListCell<ShoppingItem> {
    @FXML
    Label productName;
    @FXML
    Label pricePerUnit;
    @FXML
    GridPane grid;
    @FXML
    Label totalPrice;
    @FXML
    Label amountLabel;
    private ShoppingItem item;
    FXMLLoader mLLoader;

    public CheckoutCartElement() {
    }

    @Override
    public void updateItem(ShoppingItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || (item == null)) {
            setGraphic(null);
            return;
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/layouts/product_element_checkout.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.item = item;
            productName.setText(item.getProduct().getName());
            pricePerUnit.setText(String.valueOf((int) item.getProduct().getPrice()) + item.getProduct().getUnit());
            totalPrice.setText(String.valueOf((int) item.getTotal()) + " kr");
            amountLabel.setText(String.valueOf((int) item.getAmount()) + " st");
            setGraphic(grid);
            setText(null);
        }
    }
}
