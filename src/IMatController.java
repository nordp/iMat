import Controllers.LightboxController;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class IMatController implements Initializable {

    @FXML Parent lightbox;
    @FXML Pane shadow;
    @FXML Pane shadow1;
    @FXML Pane shadow2;
    @FXML Pane shadow3;
    @FXML LightboxController lightboxController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lightbox.setVisible(false);
        lightboxController.addShadow(shadow);
        lightboxController.addShadow(shadow1);
        lightboxController.addShadow(shadow2);
        lightboxController.addShadow(shadow3);
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

    @FXML private void toHome(){    }

    @FXML private void categoryClicked(){ //Bör ta en kategori som indata.
    }

    @FXML private void searchPerformed(){    }

    @FXML private void nextPressed(){    }

    @FXML private void backPressed(){    }
}
