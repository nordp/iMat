import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;

/**
 * Created by gustav on 2017-02-23.
 */
public class PaymentController{
    @FXML RadioButton DeliveryPayment;
    @FXML private void cardPaymentClicked(ActionEvent event) {
        System.out.println("clicked");
    }

    public RadioButton getDeliveryPayment() {
        return DeliveryPayment;
    }
}
