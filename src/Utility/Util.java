package Utility;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by Phnor on 2017-03-01.
 */
public class Util {
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
            System.out.println("formated");
            return String.format("%d", amount.intValue());
        } else {
            return String.format("%s",amount.doubleValue());
        }
    }
}
