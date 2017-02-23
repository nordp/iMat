package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import BackendMediators.CustomerHandler;
import se.chalmers.ait.dat215.project.Customer;

/**
 * Created by gustav on 2017-02-23.
 */
public class DeliveryController implements ISubCheckoutController{
    @FXML TextField nameTF;
    @FXML TextField addressTF;
    @FXML TextField postcodeTF;
    @FXML TextField cityTF;
    @FXML TextArea miscInfoTA;
    CustomerHandler handler = new CustomerHandler();


    @Override
    public void focusReceived() {
        Customer customer = handler.getCustomer();
        nameTF.setText(customer.getFirstName() + " " + customer.getLastName());
        addressTF.setText(customer.getAddress());
        postcodeTF.setText(customer.getPostCode());
      //  cityTF.setText(customer.get); Storing a city is not possible with the current backend.
    }
}
