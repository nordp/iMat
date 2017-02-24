package Controllers;

import ListCells.ProductElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Phnor on 2017-02-24.
 */
public class ProductGridController {
    @FXML private FlowPane productGrid;

    @FXML public void fillGrid(List<Product> products){
        productGrid.getChildren().removeAll();

        for (Product product : products){
            AnchorPane element = new ProductElement(product);
            productGrid.getChildren().add(element);
            //Add products to flowpane in the shape of product_element.
        }
    }

}
