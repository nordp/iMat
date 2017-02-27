package ListCells;

import BackendMediators.StoreHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;


/**
 * Created by gustav on 2017-02-24.
 */
public class ProductElement extends AnchorPane {

    private FXMLLoader loader;
    @FXML private ImageView icon;
    @FXML private TextField amountField;
    @FXML private Label productPrice;
    @FXML private Label productUnit;
    @FXML private Label unitPrice;
    @FXML private AnchorPane backgroundPane;
    @FXML private Label productName;
    @FXML private Button addToCartButton;

    private StoreHandler storeHandler = new StoreHandler();

        public ProductElement(se.chalmers.ait.dat215.project.Product p) {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/ListCells/product_element.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productName.setText(p.getName());
            icon.setImage(storeHandler.getImage(p.getProductId()));
            addToCartButton.setOnAction(params->{storeHandler.addToCart(new ShoppingItem(p, Double.parseDouble(amountField.getText())));});
        }
        public void invertIcon(ActionEvent event){

        }
        public AnchorPane getBackgroundPane(){
            return backgroundPane;
        }
    }