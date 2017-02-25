import BackendExtension.ProductContainer;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import Controllers.*;
import ListCells.CartElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import BackendExtension.*;
import BackendMediators.*;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class IMatController implements Initializable {

    @FXML Parent lightbox;
    @FXML Parent productGrid;
    @FXML ProductGridController productGridController;

    @FXML ListView<ShoppingItem> currentCartList;

    @FXML Pane shadow, shadow1, shadow2, shadow3;
    @FXML LightboxController lightboxController;

    @FXML TextField searchField;
    @FXML Accordion products_accordion;

    private List<Product> testList;
    StoreHandler handler = new StoreHandler();

    private IStoreHandler store;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lightbox.setVisible(false);
        lightboxController.addShadow(shadow);
        lightboxController.addShadow(shadow1);
        lightboxController.addShadow(shadow2);
        lightboxController.addShadow(shadow3);

        List<String> parentCategories = ProductContainer.getInstance().getParentCategories();
        for(String cat : parentCategories)
        {
            TitledPane pane = new TitledPane();
            pane.setFont(new Font(16));
            pane.setText(cat);
            List<String> subCats = ProductContainer.getInstance().getSubCategories( ProductContainer.getInstance().getParentCategory(cat));

            GridPane grid = new GridPane();
            grid.setVgap(4);
            grid.setPadding(new Insets(5, 5, 5, 5));
            for(int i = 0; i < subCats.size(); i++){
                grid.add(new Button(subCats.get(i)), 0, i);
            }
            pane.setContent(grid);

            products_accordion.getPanes().add(pane);
        }
        //new TitledPane();
       // products_accordion.getPanes().add()

        store = StoreHandler.getInstance();
    }

    @FXML private void myAccountClicked(){ lightboxController.myAccount();}

    @FXML private void shoppingListsClicked(){
        lightboxController.shoppingLists();
    }

    @FXML private void historyClicked(){
        lightboxController.history();
    }

    @FXML private void shadowClicked() { lightboxController.close(); }

    @FXML private void toCheckout(){lightboxController.checkout();    }

    @FXML private void toHome(){ lightboxController.close();  }

    @FXML private void categoryClicked(){ //Bör ta en kategori som indata.
    }
    @FXML private void searchPerformed()
    {
        productGridController.fillGrid(store.getProductsFromSearch(searchField.getText()));
    }

    @FXML private void nextPressed(){    }

    @FXML private void backPressed(){    }

    private void updateCurrentCart(ShoppingItem shoppingItem){  // this should be changed so size matches better
        currentCartList.getItems().add(shoppingItem);
        currentCartList.setCellFactory(param -> new CartElement());
    }
}