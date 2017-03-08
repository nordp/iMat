package ListCells;

import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import Utility.Util;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by gustav on 2017-03-08.
 */
public class SavedCartsElementsController  extends ListCell<ShoppingItem> {
    @FXML
    Label productName;
    @FXML Label pricePerUnit;
    @FXML
    GridPane grid;
    @FXML Label totalPrice;
    @FXML Label amountTF;
    @FXML Label unitLabel;
    @FXML Button addToCartButton;
    @FXML
    Button removeButton;
    IStoreHandler storeHandler = new StoreHandler();
    FXMLLoader mLLoader;
    public SavedCartsElementsController(){
        if (mLLoader == null) {
            mLLoader = new FXMLLoader(getClass().getResource("/ListCells/saved_carts_elements.fxml"));
            mLLoader.setController(this);

            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setGraphic(grid);
    }

    @Override
    public void updateItem(ShoppingItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
          //  removeButton.setOnAction(e -> );   Update the file to reflect that an object was deleted from list.
            productName.setText(item.getProduct().getName());
            pricePerUnit.setText(Util.format(item.getProduct().getPrice()));
            addToCartButton.setOnAction(param -> addToCartClicked(item));
            unitLabel.setText(item.getProduct().getUnit());
            amountTF.setText(Util.format(item.getAmount()));
            totalPrice.setText(Util.format(item.getTotal()) + " kr");
            setGraphic(grid);
        }
    }

    private void addToCartClicked(ShoppingItem item) {
        storeHandler.addToCart(item);
    }
}
