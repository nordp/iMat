package Controllers;

import BackendExtension.ProductContainer;
import BackendExtension.ProductParentCategory;
import BackendExtension.ProductSubCategory;
import BackendMediators.StoreHandler;
import ListCells.ProductElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.*;

/**
 * Created by Phnor on 2017-02-24.
 */
public class ProductGridController{


    HashMap<Product, ProductElement> productElementMap = new HashMap<>();
    @FXML private FlowPane productGrid;

    public ProductGridController()
    {

    }

    @FXML public void fillGrid(List<Product> products){
        productGrid.getChildren().clear();
        for(Product product: products) {
            ProductElement p = new ProductElement(product);
            productGrid.getChildren().add(p.getBackgroundPane());
        }
    }

}
