import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.lang.System.out;

public class CheckoutController implements Initializable{

    public final static int CART = 1;
    public final static int DELIVERY = 2;
    public final static int PAYMENT = 3;
    public final static int CONFIRMATION = 4;
    private int active = 0;

        @FXML AnchorPane pane;
        @FXML Button nextButton;
        @FXML Hyperlink cartLink;
        @FXML Hyperlink deliveryLink;
        @FXML Hyperlink confirmationLink;
        @FXML Hyperlink paymentLink;

        @FXML AnchorPane confirmationPane;
        @FXML AnchorPane paymentPane;
        @FXML AnchorPane deliveryPane;
        @FXML AnchorPane cartPane;
        private final java.util.List<AnchorPane> activePane = new ArrayList();

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            activePane.add(cartPane);
            activePane.add(deliveryPane);
            activePane.add(paymentPane);
            activePane.add(confirmationPane);
            changePaneContent(0);
        }

        public void getCurrentController(){
            activePane.get(active);
            FXMLLoader loader = new FXMLLoader();
            loader.setClassLoader(cartPane.getClass().getClassLoader());
            out.println(loader.getLocation());
        }
        private void changePaneContent(int paneIndex){
            try {
                for (int i = 0; i < activePane.size(); i++) {
                    activePane.get(i).setVisible(false);
                }
                activePane.get(paneIndex).setVisible(true);
            }
            catch (IndexOutOfBoundsException exception){
                exception.printStackTrace();
                active = 0;     //Temporary
                changePaneContent(active);
            }
        }

        @FXML protected void nextButtonPressed(ActionEvent event){
            active++;
            active = active%4;
            out.println(active);
            changePaneContent(active);
        }

        @FXML protected void backButtonPressed(ActionEvent event){
            active--;
            active = active%4;
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
