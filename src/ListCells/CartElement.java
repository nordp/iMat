package ListCells;

import BackendMediators.CustomerHandler;
import BackendMediators.StoreHandler;
import Controllers.CartController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.IntSummaryStatistics;

import static java.lang.System.load;
import static java.lang.System.out;

/**
 * Created by gustav on 2017-02-24.
 */
public class CartElement extends ListCell<ShoppingItem>{
    @FXML Label productName;
    @FXML Label pricePerUnit;
    @FXML GridPane grid;
    @FXML Label totalPrice;
    @FXML Label amountTF;
    private ShoppingItem item;
    FXMLLoader mLLoader;
    public CartElement(){
    }
    @Override
    public void updateItem(ShoppingItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
            return;
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/layouts/cart_element.fxml"));
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
            amountTF.setText(String.valueOf(item.getAmount()) + item.getProduct().getUnitSuffix());
            setGraphic(grid);
        }
    }

    public void onRemove(ActionEvent event) {   //Should be changed to only handle integers. the listeners should also be looked over.
        String nr = amountTF.getText().replaceAll(item.getProduct().getUnitSuffix(),"");
        Double amount = Double.parseDouble(nr);
        amount-=1;
        amount = Math.max(0,amount);
        amountTF.setText(amount.toString() + item.getProduct().getUnitSuffix());
        valueChanged(null);
    }

    public void onAdd(ActionEvent event) {
        String nr = amountTF.getText().replaceAll(item.getProduct().getUnitSuffix().toString(),"");
        Double amount = Double.parseDouble(nr);
        amount+=1;
        amountTF.setText(amount.toString() + item.getProduct().getUnitSuffix());
        valueChanged(null);
    }
    @FXML private void valueChanged(ActionEvent event){
        String nr = amountTF.getText().replaceAll(item.getProduct().getUnitSuffix().toString(),"");
        // How could this happend?
        /*if(Double.parseDouble(amountTF.getText())<=0){
            amountTF.setText("0");
        }*/
        item.setAmount(Double.parseDouble(nr));
        totalPrice.setText(String.valueOf(item.getTotal() + " Kr"));
    }
}
