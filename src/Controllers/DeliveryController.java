package Controllers;

import Main.SequenceHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import BackendExtension.CustomerListener;
import Utility.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import BackendMediators.CustomerHandler;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by gustav on 2017-02-23.
 */
public class DeliveryController implements Initializable, CustomerListener, ActivePaneListener{
    @FXML TextField nameTF;
    @FXML TextField addressTF;
    @FXML TextField postcodeTF;
    @FXML TextField cityTF;
    @FXML TextArea miscInfoTA;
    @FXML Label twoDays;
    @FXML Label threeDays;
    @FXML Label fourDays;
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
    CustomerHandler handler = CustomerHandler.getInstance();
    Label[] dayLabels = new Label[3];
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dayLabels[0] = twoDays;
        dayLabels[1] = threeDays;
        dayLabels[2] = fourDays;
        handler.addCustomerListener(this);
        customerInfoChanged();
        Util.setNumericTextField(postcodeTF);
        nameTF.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        addressTF.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        postcodeTF.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());
        cityTF.textProperty().addListener((observable, oldValue, newValue) -> updateButtonsAndLinks());

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
        updateDayLabels();
    }

    private void updateDayLabels() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Today's date
        c.add(Calendar.DATE, 1);
        for(int i = 0; i<3; i++) {
            c.add(Calendar.DATE, 1); // Adds i days
            SimpleDateFormat format = new SimpleDateFormat("EEEE");
            Date day = c.getTime();
            String s = format.format(day);
            s = s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase() + ":";
            System.out.println(dayLabels[i]);
            dayLabels[i].setText(s);
        }
    }

    private void updateButtonsAndLinks(){
        SequenceHandler.getInstance().setInputValid(isInputValid());
    }
    @FXML private void saveText(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(nameTF)) {
            if (nameTF.getText().contains(" ") && nameTF.getText().trim().length() > 2) {
                String[] names = nameTF.getText().split(" ");
                handler.setFirstName(names[0]);
                handler.setLastName(names[1]);
            } else {
                handler.setFirstName(nameTF.getText());
                handler.setLastName("");
            }
        } else if (actionEvent.getSource().equals(addressTF)) {
            handler.setAddress(addressTF.getText());
        } else if (actionEvent.getSource().equals(postcodeTF)) {
            handler.setPostCode(postcodeTF.getText());
        } else if (actionEvent.getSource().equals(cityTF)) {
            handler.setPostAddress(cityTF.getText());
        }
    }

    @FXML private void toggleButtonPressed(ActionEvent event){
        ToggleButton pressedButton = (ToggleButton) event.getSource();
        // Så hackigt det ens kan bli
        int dayFromNow = Integer.parseInt(pressedButton.getId());
        String s[] = pressedButton.getText().split("-");
        int hour = Integer.parseInt(s[0]);
        Date day = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.add(Calendar.DATE, dayFromNow);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        day = c.getTime();
        System.out.println(day);
        handler.setDeliveryDate(day);
        updateButtonsAndLinks();
    }

    public boolean isInputValid(){
        return (Util.isInputValid(nameTF, addressTF, postcodeTF, cityTF) && !(t0_0.getToggleGroup().getSelectedToggle() == null));
    }

    @Override
    public void customerInfoChanged() {
        nameTF.setText(handler.getFirstName() + " " + handler.getLastName());
        addressTF.setText(handler.getAddress());
        postcodeTF.setText(handler.getPostCode());
        cityTF.setText(handler.getPostAddress());
    }
    @Override
    public void receivedActive() {
        updateAvailableTimes();
        updateButtonsAndLinks();
    }

    private void updateAvailableTimes() {
        Date time = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        System.out.println(c.get(Calendar.HOUR_OF_DAY));
        if(c.get(Calendar.HOUR_OF_DAY)>=9){
            t0_0.setDisable(true);
        }
        if(c.get(Calendar.HOUR_OF_DAY)>=12){
            t1_0.setDisable(true);
        }
        if(c.get(Calendar.HOUR_OF_DAY)>=15){
            t2_0.setDisable(true);
        }
        if(c.get(Calendar.HOUR_OF_DAY)>=18){
            t3_0.setDisable(true);
        }
    }
}
