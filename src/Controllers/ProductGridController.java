package Controllers;

import BackendExtension.ProductContainer;
import BackendExtension.ProductParentCategory;
import BackendExtension.ProductSubCategory;
import BackendMediators.StoreHandler;
import ListCells.ProductElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.*;

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

        ProductContainer prodContainer = ProductContainer.getInstance();

        HashMap<ProductParentCategory, HashMap<ProductSubCategory ,List<Product>>> productFromCategory = new HashMap<>();
        for(Product p : products)
        {
            ProductParentCategory parentCategory = prodContainer.getCategory(p).parentCategory;
            ProductSubCategory subCategory = prodContainer.getCategory(p).subCategory;

            if(!productFromCategory.containsKey(parentCategory))
            {
                productFromCategory.put(parentCategory, new HashMap<>());
            }
            if(!productFromCategory.get(parentCategory).containsKey(subCategory))
            {
                productFromCategory.get(parentCategory).put(subCategory, new ArrayList<>());
            }

            productFromCategory.get(parentCategory).get(subCategory).add(p);
        }

        for(Map.Entry<ProductParentCategory, HashMap<ProductSubCategory, List<Product>>> entry0 : productFromCategory.entrySet())
        {
            // Add the parent category text
            productGrid.getChildren().add(new Label(entry0.getKey().toString()));
            for(Map.Entry<ProductSubCategory, List<Product>> entry1 : productFromCategory.get(entry0.getKey()).entrySet())
            {
                productGrid.getChildren().add(new Label(entry1.getKey().toString()));
                for(Product p : entry1.getValue())
                {
                    productGrid.getChildren().add(productElementMap.get(p));
                }
            }
        }

        for (Product product : products){

        }
    }

}
