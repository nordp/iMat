package Controllers;

/**
 * Created by Phnor on 2017-02-24.
 */
import Main.IMatController;
import Main.SequenceHandler;
import Utility.Util;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.IMatDataHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LightboxController implements Initializable{
    @FXML AnchorPane root;
    @FXML Parent history;
    @FXML Parent shoppingLists;
    @FXML Parent myAccount;
    @FXML Parent checkout;
    @FXML public CheckoutController checkoutController;
    private static LightboxController controller;

    List<Parent> panes;
    List<Pane> shadows;
    private LightBoxEnum activeView = LightBoxEnum.NONE;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panes = new ArrayList<>();
        shadows = new ArrayList<>();
        controller = this;
        panes.add(history);
        panes.add(shoppingLists);
        panes.add(myAccount);
        panes.add(checkout);
        close();
    }

    protected static void closeWindow() {
        controller.close();
    }
    public void resetCheckout(){
        checkoutController.changePaneContent(0);
    }

    public LightBoxEnum getActiveView()
    {
        return activeView;
    }

    public void shoppingLists()
    {
        setView(shoppingLists);
        activeView = LightBoxEnum.SHOPPINGLIST;
    }

    public void checkout(){
        // if this should be opened in the lightbox, we might want to resize the checkout to reflect this.
        setView(checkout);
        activeView = LightBoxEnum.CHECKOUT;
    }
    public void nextCheckoutPaneSelected(){
        checkoutController.nextButtonPressed();
    }

    public void history(){
        setView(history);
        activeView = LightBoxEnum.HISTORY;
    }

    public void myAccount()
    {
        setView(myAccount);
        activeView = LightBoxEnum.MYACCOUNT;
    }

    private void setView(Parent parent){
        // Disable all other parents
        for (Parent p : panes){
            if (p!=parent){
                p.setVisible(false);
            }
        }

        //Toggle the lightbox with parent depending on parent shown
        boolean toggle = !parent.isVisible();
        root.setVisible(toggle);
        setShadows(toggle);
        parent.setVisible(toggle);
        if(!toggle){
            close();
        }
        else{
            SequenceHandler.getInstance().setCategoriesActive(false);
            SequenceHandler.getInstance().setCategoriesActive(false);
        }
        Util.fadeIn(300, 0, root);
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), root);
        translateTransition.setFromY(root.getLayoutY()+25);
        translateTransition.setByY(root.getLayoutY()+10);
        translateTransition.setToY(root.getLayoutY());
        translateTransition.play();
    }

    public void close(){
        for (Parent p : panes){
            p.setVisible(false);
        }
        root.setVisible(false);
        setShadows(false);
        activeView = LightBoxEnum.NONE;
        SequenceHandler.getInstance().setCheckoutActive(false);
        SequenceHandler.getInstance().setCategoriesActive(true);
        IMatController.getInstance().enableButtons();
    }

    private void setShadows(boolean bool){
        for (Pane shadow : shadows){
            shadow.setVisible(bool);
        }
    }

    public void addShadow(Pane shadow) {
        shadows.add(shadow);
    }

    public void previousCheckoutPaneSelected() {checkoutController.backButtonPressed();
    }
}
