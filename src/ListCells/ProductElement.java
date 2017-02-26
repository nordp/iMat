package ListCells;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by gustav on 2017-02-24.
 */
public class ProductElement extends AnchorPane {

    @FXML public ImageView icon;
    @FXML public ImageView heartIcon;
    @FXML public TextField amountField;
    @FXML public Label productName;
    @FXML public Label productPrice;
    @FXML public Label productUnit;
    @FXML public Label unitPrice;

    private void setValues(Product product) {
        //TODO
        productName.setText("Namn");
        productPrice.setText("50kr");
        productUnit.setText("enhet");
        unitPrice.setText("15kr/liter");

    }
    @FXML
    private void invertIcon(){
        //Todo invert icon when hovering
    }

    public ProductElement() {}

    public ProductElement(Product product) {
    //TODO Load product_element.fxml
    }
}