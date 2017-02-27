package ListCells;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.xml.soap.Text;
import java.io.IOException;

import static java.lang.System.out;

/**
 * Created by gustav on 2017-02-24.
 */
public class CheckoutCartElement extends ListCell<ShoppingItem>{
    @FXML Label productName;
    @FXML Label pricePerUnit;
    @FXML GridPane grid;
    @FXML Label totalPrice;
    @FXML TextField amountTF;
    private ShoppingItem item;
    FXMLLoader mLLoader;
    public CheckoutCartElement(){
    }
    @Override
    public void updateItem(ShoppingItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || (item == null)) {
            setGraphic(null);
            return;
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/layouts/product_element_checkout.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.item = item;
            productName.setText(item.getProduct().getName());
            pricePerUnit.setText(String.valueOf((int)item.getProduct().getPrice()) + item.getProduct().getUnit());
            totalPrice.setText(String.valueOf((int)item.getTotal()) + " kr");
            amountTF.setText(String.valueOf((int)item.getAmount()));
            setGraphic(grid);
            setText(null);
        }
    }

    public void onRemove(ActionEvent event) {   //Should be changed to only handle integers. the listeners should also be looked over.
        Double amount = Double.parseDouble(amountTF.getText());
        amount-=1;
        amountTF.setText(amount.toString());
        valueChanged(null);
    }

    public void onAdd(ActionEvent event) {
        out.println("pressed");
        Double amount = Double.parseDouble(amountTF.getText());
        amount+=1;
        amountTF.setText(amount.toString());
        valueChanged(null);
    }
    @FXML private void valueChanged(ActionEvent event){
        if(Double.parseDouble(amountTF.getText())<=0){
            amountTF.setText("0");
        }
        item.setAmount(Double.parseDouble(amountTF.getText()));
        totalPrice.setText(String.valueOf(item.getTotal()));
    }
}
