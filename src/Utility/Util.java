package Utility;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Phnor on 2017-03-01.
 */
public class Util {
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Util(){}

    public static void setNextTextFieldOn4Chars(TextField a, TextField b)
    {
        a.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length() == 4)
                {
                    b.requestFocus();
                }
            }
        });
    }

    public static void setNumericTextField(TextField field)
    {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public static boolean isInputValid(TextField name, TextField address, TextField postCode, TextField city)
    {
        if(name.getText().length() <= 0)
        {
            // Prompt user to enter his/her name.
            return false;
        }
        if(address.getText().length() <= 0)
        {
            // Prompt user to enter the address.
            return false;
        }
        if(postCode.getText().length() != 5)
        {
            // I hope every postal code is 5 long. Promt user to enter postcode here
            return false;
        }
        if(city.getText().length() <= 0)
        {
            // Prompt user to enter the city
            return false;
        }
        return true;
    }

    public static String format(Double amount) {
        if (amount.doubleValue() == amount.intValue()){
            return String.format("%d", amount.intValue());
        } else {
            return String.format("%s",amount.doubleValue());
        }
    }

    public static String formatTime(Date time){
        return timeFormat.format(time);
    }

    public static String formatDate(Date date){
        return dateFormat.format(date);
    }

    public static int calcSum(List<ShoppingItem> list){
        int sum = 0;
        for (ShoppingItem item : list){
            sum += item.getTotal();
        }
        return sum;
    }
    public static void fadeIn(double time, double delay, Node node){
        FadeTransition transition = new FadeTransition(Duration.millis(time), node);
        transition.setDelay(Duration.millis(delay));
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        transition.play();
    }
    public static void fadeOut(double time, double delay, Node node){
        FadeTransition t = new FadeTransition(Duration.millis(time), node);
        t.setDelay(new Duration(delay));
        t.setToValue(0.0);
        t.setFromValue(1.0);
        t.play();
    }
}
