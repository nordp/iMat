import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class CheckoutController implements Initializable{

    public final static int CART = 1;
    public final static int DELIVERY = 2;
    public final static int PAYMENT = 3;
    public final static int CONFIRMATION = 4;
    private int active = 1;

        @FXML AnchorPane pane;
        @FXML Button nextButton;
        @FXML Hyperlink cartLink;
        @FXML Hyperlink deliveryLink;
        @FXML Hyperlink confirmationLink;
        @FXML Hyperlink paymentLink;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
        }

        private void changePaneContent(int paneInt){
            Node activeNode = null;
            try {
                switch (paneInt) {
                    case 1:
                        activeNode = FXMLLoader.load(getClass().getResource("layouts/cart.fxml"));
                        break;
                    case 2:
                        activeNode = FXMLLoader.load(getClass().getResource("layouts/delivery.fxml"));
                        break;
                    case 3:
                        activeNode = FXMLLoader.load(getClass().getResource("layouts/payment.fxml"));
                        break;
                    case 4:
                        activeNode = FXMLLoader.load(getClass().getResource("layouts/confirmation.fxml"));
                        break;
                    default:
                        return;
                }
            }
            catch (IOException IO){
                IO.printStackTrace();
                return;
            }
            pane.getChildren().clear();
            pane.getChildren().add(activeNode);
        }
        @FXML protected void nextButtonPressed(ActionEvent event){
            out.println("next pane active");
            changePaneContent(active++);
        }
        @FXML protected void backButtonClicked(ActionEvent event){
            out.println("previous pane active");
            changePaneContent(active--);
        }
        @FXML protected void linkClicked(ActionEvent event){
            Object source = event.getSource();
            Hyperlink link = (Hyperlink) source;    // Alternative way: use ID set to 1, 2, 3, 4.
            if(source.equals(cartLink)){
                active = 1;
            }
            else if(source.equals(deliveryLink)){
                active = 2;
            }
            else if(source.equals(paymentLink)){
                active = 3;
            }
            else if(source.equals(confirmationLink)){
                active = 4;
            }
            else{
                out.println("not a viable link");
            }
            changePaneContent(active);
        }
    }
