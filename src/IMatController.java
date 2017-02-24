import Controllers.LightboxController;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class IMatController implements Initializable {

    @FXML Parent lightbox;
    @FXML LightboxController lightboxController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lightbox.setVisible(false);
    }

    @FXML private void myAccountClicked(){
        lightboxController.myAccount();

    }

    @FXML private void shoppingListsClicked(){
        lightboxController.shoppingLists();
    }

    @FXML private void historyClicked(){
        lightboxController.history();
    }

    @FXML private void toCheckout(){

    }

    @FXML private void toHome(){

    }

    @FXML private void categoryClicked(){ //Bör ta en kategori som indata.

    }

    @FXML private void searchPerformed(){

    }

    @FXML private void nextPressed(){

    }

    @FXML private void backPressed(){

    }
}
