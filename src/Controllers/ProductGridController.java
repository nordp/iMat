package Controllers;

import BackendMediators.StoreHandler;
import ListCells.ProductElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Phnor on 2017-02-24.
 */
public class ProductGridController {


    HashMap<Product, ProductElement> productElementMap = new HashMap<>();

    public ProductGridController()
    {
        List<Product> products = StoreHandler.getInstance().getAllProducts();
        for(Product p : products)
        {
            ProductElement element = new ProductElement(p);
            productElementMap.put(p, element);
        }
    }


    @FXML private FlowPane productGrid;

    @FXML public void fillGrid(List<Product> products){
        System.out.println("fillgrid");
        productGrid.getChildren().clear();

        for (Product product : products){
            productGrid.getChildren().add(productElementMap.get(product));
        }
    }

}
