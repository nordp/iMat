package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import BackendMediators.CustomerHandler;
import se.chalmers.ait.dat215.project.Customer;

/**
 * Created by gustav on 2017-02-23.
 */
public class DeliveryController {
    @FXML TextField nameTF;
    @FXML TextField addressTF;
    @FXML TextField postcodeTF;
    @FXML TextField cityTF;
    @FXML TextArea miscInfoTA;
    CustomerHandler handler = new CustomerHandler();

}
