package ListCells;

import BackendMediators.CustomerHandler;
import BackendMediators.StoreHandler;
import Controllers.CartController;
import Controllers.CheckoutController;
import Utility.Util;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
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
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.out;


/**
 * Created by gustav on 2017-02-24.
 */
public class ProductElement extends AnchorPane {

    private FXMLLoader loader;
    @FXML private ImageView icon;
    @FXML private ImageView heartIcon;
    @FXML private ImageView noHeartIcon;
    @FXML private TextField amountField;
    @FXML private Label productPrice;
    @FXML private Label unitPrice;
    @FXML private AnchorPane backgroundPane;
    @FXML private Label productName;
    @FXML private Button addToCartButton;
    @FXML private Button addAmount;
    @FXML private Button removeAmount;
    @FXML private Button addToFavoritesBtn;
    @FXML private StackPane addedToCartOverlay;
    private StoreHandler storeHandler = new StoreHandler();
    private CustomerHandler customerHandler = CustomerHandler.getInstance();
    private boolean isFavourite;

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
        productPrice.setText(Util.format(p.getPrice()) + "kr");
        unitPrice.setText(Util.format(p.getPrice()) + p.getUnit());

        addToCartButton.setOnAction(params-> {
            storeHandler.addToCart(new ShoppingItem(p, Double.parseDouble(amountField.getText())));
            amountField.setText("1");
            productPrice.setText(Util.format(p.getPrice()) + "kr");
            CartController.getInstance().shoppingCartChanged(null);
            addedToCartOverlay.setVisible(true);
            addedToCartOverlay.setDisable(false);
            Util.fadeIn(200, 0, addedToCartOverlay);
            Util.fadeOut(200, 800, addedToCartOverlay);
            Timer t = new Timer();
            t.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            addedToCartOverlay.setVisible(false);
                            addedToCartOverlay.setDisable(true);
                            t.cancel();
                            t.purge();
                        }
                    },1200
            );
        });
        addAmount.setOnAction(params -> {
            Double amount = Double.parseDouble(amountField.getText());
            amount += 1;
            amountField.setText(Util.format(amount));
            productPrice.setText(Util.format(amount*p.getPrice()) + "kr");
        });
        removeAmount.setOnAction(params ->{
            Double amount = Double.parseDouble(amountField.getText());
            amount-=1;
            amount = Math.max(amount, 0);
            amountField.setText(Util.format(amount));
            productPrice.setText(Util.format(amount*p.getPrice()) + "kr");
        });
        addToFavoritesBtn.setOnAction(q ->
        {
            if (!isFavourite) {
                customerHandler.addFavorite(p);
            } else {
                customerHandler.removeFavorite(p);
            }
            isFavourite = !isFavourite;
            updateHeart();
        });
        isFavourite = customerHandler.isFavorite(p.getProductId());
        addToFavoritesBtn.setOnMouseEntered(q -> invertIcon());
        addToFavoritesBtn.setOnMouseExited(q -> updateHeart());
        updateHeart();
    }
    public void updateHeart() { heartIcon.setVisible(isFavourite); }
    public void invertIcon(){
        heartIcon.setVisible(!isFavourite);
    }
    public AnchorPane getBackgroundPane(){
        return backgroundPane;
    }
}