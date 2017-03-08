package Controllers;

import Main.SequenceHandler;
import Utility.Util;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class CheckoutController implements Initializable{

    public final static int CART = 1;
    public final static int DELIVERY = 2;
    public final static int PAYMENT = 3;
    public final static int CONFIRMATION = 4;
    public final static int RECEIPT = 5;
    private int lastActive = 0;
    private int active = 0;

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
        private static CheckoutController checkout;
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            checkout = this;
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

        private void fadeOutTransition(boolean toLeft){
            int offset = -50;
            if(!toLeft) {
                offset = 50;
            }
                TranslateTransition transition = new TranslateTransition(Duration.millis(200), parentList.get(lastActive));
                transition.setFromX(parentList.get(lastActive).getLayoutX());
                transition.setToX(parentList.get(lastActive).getLayoutX() + offset);
                transition.play();
                Util.fadeOut(200, 0, parentList.get(lastActive));
        }
        private void fadeInTransition(boolean toLeft){
        int offset = 50;
        if(!toLeft){
            offset = -50;
        }
            TranslateTransition transition2 = new TranslateTransition(Duration.millis(200), parentList.get(active));
            transition2.setToX(parentList.get(active).getLayoutX());
            transition2.setFromX(parentList.get(active).getLayoutX() + offset);
            transition2.play();
            Util.fadeIn(200, 0, parentList.get(active));
        }
        public void changePaneContent(int paneIndex){
            boolean toLeft = paneIndex>lastActive;
            fadeOutTransition(toLeft);
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    for(Parent p : parentList){
                        p.setVisible(false);
                    }
                    parentList.get(paneIndex).setVisible(true);
                    active = paneIndex;
                    fadeInTransition(toLeft);
                    t.cancel();
                    t.purge();
                }
            }, 200);
            controllerList.get(paneIndex).receivedActive();
            lastActive = active;
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

    public void resetButtons() {
            receiptController.resetSaveButton();
    }
}
