package ListCells;

import BackendMediators.CustomerHandler;
import BackendMediators.StoreHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;

import static java.lang.System.out;


/**
 * Created by gustav on 2017-02-24.
 */
public class ProductElement extends AnchorPane {

    private FXMLLoader loader;
    @FXML private ImageView icon;
    @FXML private ImageView heartIcon;
    @FXML private TextField amountField;
    @FXML private Label productPrice;
    @FXML private Label productUnit;
    @FXML private Label unitPrice;
    @FXML private AnchorPane backgroundPane;
    @FXML private Label productName;
    @FXML private Button addToCartButton;
    @FXML private Button addAmount;
    @FXML private Button removeAmount;

    private StoreHandler storeHandler = new StoreHandler();
    private CustomerHandler customerHandler = new CustomerHandler();

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
            productPrice.setText(String.valueOf(p.getPrice()));

            addToCartButton.setOnAction(params-> storeHandler.addToCart(new ShoppingItem(p, Double.parseDouble(amountField.getText()))));

            addAmount.setOnAction(params -> {
                Double amount = Double.parseDouble(amountField.getText());
                amount+=1;
                amountField.setText(amount.toString());
            });
            removeAmount.setOnAction(params ->{
                Double amount = Double.parseDouble(amountField.getText());
                amount-=1;
                amount = Math.max(amount, 0);
                amountField.setText(amount.toString());
            });
        }
        public void invertIcon(ActionEvent event){

        }
        public AnchorPane getBackgroundPane(){
            return backgroundPane;
        }
    }