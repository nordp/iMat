package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class CheckoutController implements Initializable{

    public final static int CART = 1;
    public final static int DELIVERY = 2;
    public final static int PAYMENT = 3;
    public final static int CONFIRMATION = 4;
    private int active = 0;
    private int lastActive = 0;

        @FXML AnchorPane pane;
        @FXML Button nextButton;
        @FXML Hyperlink cartLink;
        @FXML Hyperlink deliveryLink;
        @FXML Hyperlink confirmationLink;
        @FXML Hyperlink paymentLink;

        @FXML Parent cart;
        @FXML Parent delivery;
        @FXML Parent payment;
        @FXML Parent confirmation;
        @FXML CartController cartController;
        @FXML DeliveryController deliveryController;
        @FXML PaymentController paymentController;
        @FXML ConfirmationController confirmationController;
        List<Parent> parentList = new ArrayList<>();
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            parentList.add(cart);
            parentList.add(delivery);
            parentList.add(payment);
            parentList.add(confirmation);
            changePaneContent(0);
        }

        public void changePaneContent(int paneIndex){
            for(int i = 0; i<parentList.size(); i++){
                parentList.get(i).setVisible(false);
            }
            parentList.get(paneIndex).setVisible(true);
        }

        @FXML protected void nextButtonPressed(ActionEvent event){
            active++;
            // Temporary to avoid crashes.
            active = active%4;
            changePaneContent(active);
        }

        @FXML protected void backButtonPressed(ActionEvent event){
            active--;
            active = Math.floorMod(active, 4);
            changePaneContent(active);
        }

        @FXML protected void linkClicked(ActionEvent event){
            Object source = event.getSource();
            if(source.equals(cartLink)){
                active = 0;
            }
            else if(source.equals(deliveryLink)){
                active = 1;
            }
            else if(source.equals(paymentLink)){
                active = 2;
            }
            else if(source.equals(confirmationLink)){
                active = 3;
            }
            else{
                out.println("not a viable link");
            }
            changePaneContent(active);
        }
    }
