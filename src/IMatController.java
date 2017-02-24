import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import Controllers.*;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IMatController implements Initializable {

    @FXML Parent lightbox;
    @FXML Parent productGrid;
    @FXML ProductGridController productGridController;

    @FXML Pane shadow, shadow1, shadow2, shadow3;
    @FXML LightboxController lightboxController;

    @FXML TextField searchField;

    private List<Product> testList;


    private IStoreHandler store;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lightbox.setVisible(false);
        lightboxController.addShadow(shadow);
        lightboxController.addShadow(shadow1);
        lightboxController.addShadow(shadow2);
        lightboxController.addShadow(shadow3);
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

    @FXML private void toCheckout(){    }

    @FXML private void toHome(){ lightboxController.close();  }

    @FXML private void categoryClicked(){ //Bör ta en kategori som indata.
    }

    @FXML private void searchPerformed(){ productGridController.fillGrid(store.getProductsFromSearch(searchField.getText())); }

    @FXML private void nextPressed(){    }

    @FXML private void backPressed(){    }
}
