package Controllers;

import Main.SequenceHandler;
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
    public final static int RECEIPT = 5;

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
        @FXML Parent receipt;

        @FXML CartController cartController;
        @FXML DeliveryController deliveryController;
        @FXML PaymentController paymentController;
        @FXML ConfirmationController confirmationController;
        @FXML ReceiptController receiptController;
        List<ActivePaneListener> controllerList = new ArrayList<>();
        List<Parent> parentList = new ArrayList<>();
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            parentList.add(cart);
            parentList.add(delivery);
            parentList.add(payment);
            parentList.add(confirmation);
            parentList.add(receipt);
            controllerList.add(cartController);
            controllerList.add(deliveryController);
            controllerList.add(paymentController);
            controllerList.add(confirmationController);
            controllerList.add(receiptController);
            changePaneContent(0);
        }

        public void changePaneContent(int paneIndex){
            for(int i = 0; i<parentList.size(); i++){
                parentList.get(i).setVisible(false);
            }
            parentList.get(paneIndex).setVisible(true);
            controllerList.get(paneIndex).receivedActive();
        }

        @FXML protected void nextButtonPressed(){
            active++;
            // Temporary to avoid crashes.
            active = active%5;
            changePaneContent(active);
        }

        @FXML protected void backButtonPressed(){
            active--;
            active = Math.floorMod(active, 5);
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
            SequenceHandler.getInstance().setCheckoutIndex(active);
            SequenceHandler.getInstance().updateButtonStatus();
            changePaneContent(active);
        }
    }
