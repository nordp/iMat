package Controllers;

import BackendExtension.ProductContainer;
import BackendExtension.ProductParentCategory;
import BackendExtension.ProductSubCategory;
import BackendMediators.StoreHandler;
import ListCells.ProductElement;
import Main.IMatController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
        List<Product> products = StoreHandler.getInstance().getAllProducts();
        for(Product p : products)
        {
            ProductElement element = new ProductElement(p);
            productElementMap.put(p, element);
        }
    }

    @FXML public void fillGrid(String header, List<Product> products){


        productGrid.getChildren().clear();

        ProductContainer prodContainer = ProductContainer.getInstance();

        Label gridHeader = new Label(header);
        gridHeader.setFont(new Font(20));
        gridHeader.setPrefWidth(600);
        gridHeader.alignmentProperty().setValue(Pos.TOP_CENTER);
        productGrid.getChildren().add(gridHeader);
        IMatController.getInstance().setWelcomeScreenVisible(false);
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
            Label parentLabel = new Label(ProductContainer.getInstance().TranslateToSwedish(entry0.getKey().toString()));
            parentLabel.alignmentProperty().setValue(Pos.CENTER);
            parentLabel.setFont(new Font(24));
            parentLabel.textAlignmentProperty().setValue(TextAlignment.CENTER);
            parentLabel.prefWidthProperty().bind(productGrid.widthProperty());
            //productGrid.getChildren().add(parentLabel);

            for(Map.Entry<ProductSubCategory, List<Product>> entry1 : productFromCategory.get(entry0.getKey()).entrySet())
            {
                // Add the sub category text
                Label subLabel = new Label(ProductContainer.getInstance().TranslateToSwedish(entry1.getKey().toString()));
                subLabel.alignmentProperty().setValue(Pos.TOP_LEFT);
                subLabel.setFont(new Font(18));
                subLabel.textAlignmentProperty().setValue(TextAlignment.CENTER);
                productGrid.getChildren().add(subLabel);
                subLabel.prefWidthProperty().bind(productGrid.widthProperty());

                for(Product p : entry1.getValue())
                {
                    productGrid.getChildren().add(productElementMap.get(p).getBackgroundPane());
                }
            }
        }

    }

}
