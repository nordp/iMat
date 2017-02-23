import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class IMatController implements Initializable {

    @FXML Parent popupPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popupPane.setVisible(false);
    }

    @FXML private void openMyAccount(){

    }

    @FXML private void categoryClicked(){ //Bör ta en kategori som indata.
    }

    @FXML private void openShoppingLists(){

    }

    @FXML private void openHistory(){

    }

    @FXML private void toCheckout(){

    }

    @FXML private void toHome(){

    }

    @FXML private void searchPerformed(){

    }

    @FXML private void nextPressed(){

    }

    @FXML private void backPressed(){

    }
}
