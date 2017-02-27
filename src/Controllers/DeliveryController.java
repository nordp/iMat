package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import BackendMediators.CustomerHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import se.chalmers.ait.dat215.project.Customer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-02-23.
 */
public class DeliveryController implements Initializable {
    @FXML TextField nameTF;
    @FXML TextField addressTF;
    @FXML TextField postcodeTF;
    @FXML TextField cityTF;
    @FXML TextArea miscInfoTA;
    @FXML ToggleButton t0_0;
    @FXML ToggleButton t1_0;
    @FXML ToggleButton t2_0;
    @FXML ToggleButton t3_0;
    @FXML ToggleButton t1_1;
    @FXML ToggleButton t2_1;
    @FXML ToggleButton t3_1;
    @FXML ToggleButton t0_1;
    @FXML ToggleButton t0_2;
    @FXML ToggleButton t1_2;
    @FXML ToggleButton t2_2;
    @FXML ToggleButton t3_2;
    @FXML ToggleButton t0_3;
    @FXML ToggleButton t1_3;
    @FXML ToggleButton t2_3;
    @FXML ToggleButton t3_3;
    @FXML ToggleButton t0_4;
    @FXML ToggleButton t1_4;
    @FXML ToggleButton t2_4;
    @FXML ToggleButton t3_4;
    CustomerHandler handler = new CustomerHandler();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ToggleGroup toggleGroup = new ToggleGroup();
        t0_0.setToggleGroup(toggleGroup);
        t1_0.setToggleGroup(toggleGroup);
        t2_0.setToggleGroup(toggleGroup);
        t3_0.setToggleGroup(toggleGroup);
        t1_1.setToggleGroup(toggleGroup);
        t2_1.setToggleGroup(toggleGroup);
        t3_1.setToggleGroup(toggleGroup);
        t0_1.setToggleGroup(toggleGroup);
        t0_2.setToggleGroup(toggleGroup);
        t1_2.setToggleGroup(toggleGroup);
        t2_2.setToggleGroup(toggleGroup);
        t3_2.setToggleGroup(toggleGroup);
        t0_3.setToggleGroup(toggleGroup);
        t1_3.setToggleGroup(toggleGroup);
        t2_3.setToggleGroup(toggleGroup);
        t3_3.setToggleGroup(toggleGroup);
        t0_4.setToggleGroup(toggleGroup);
        t1_4.setToggleGroup(toggleGroup);
        t2_4.setToggleGroup(toggleGroup);
        t3_4.setToggleGroup(toggleGroup);
    }

    public boolean IsInputValid()
    {
        if(nameTF.getText().length() <= 0)
        {
            // Prompt user to enter his/her name.
            return false;
        }
        if(addressTF.getText().length() <= 0)
        {
            // Prompt user to enter the address.
            return false;
        }
        if(postcodeTF.getText().length() != 5)
        {
            // I hope every postal code is 5 long. Promt user to enter postcode here
            return false;
        }
        if(cityTF.getText().length() <= 0)
        {
            // Prompt user to enter the city
            return false;
        }
        if(t0_0.getToggleGroup().getSelectedToggle() == null)
        {
            // Prompt user to select a time/date for the delivery
            return false;
        }
        return true;
    }

}
