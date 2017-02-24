package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        if(!(customer.getFirstName().isEmpty() && customer.getLastName().isEmpty())) {
            nameTF.setText(customer.getFirstName() + " " + customer.getLastName());
        }
        addressTF.setText(customer.getAddress());
        postcodeTF.setText(customer.getPostCode());
      //  cityTF.setText(customer.get); Storing a city is not possible with the current backend.
    }

    @Override
    public void focusLost() {
        Customer customer = handler.getCustomer();
        String name[] = nameTF.getText().split(" ");
        String address = addressTF.getText();
        String postcode = postcodeTF.getText();
        if(name.length<0) {
            customer.setFirstName(name[0]);
        }
        if(name.length>1) {
            customer.setLastName(name[1]);
        }
        customer.setAddress(address);
        customer.setPostCode(postcode);
    }
}
