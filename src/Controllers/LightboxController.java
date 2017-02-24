package Controllers;

/**
 * Created by Phnor on 2017-02-24.
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LightboxController implements Initializable{
    @FXML AnchorPane root;
    @FXML Parent history;
    @FXML Parent shoppingLists;
    @FXML Parent myAccount;

    List<Parent> panes;
    List<Pane> shadows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panes = new ArrayList<>();
        shadows = new ArrayList<>();
        panes.add(history);
        panes.add(shoppingLists);
        panes.add(myAccount);
        close();
    }

    public void shoppingLists(){
        setView(shoppingLists);

    }

    public void history(){
        setView(history);
    }

    public void myAccount(){
        setView(myAccount);
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

    }

    public void close(){
        for (Parent p : panes){
            p.setVisible(false);
        }
        root.setVisible(false);
        setShadows(false);
    }

    private void setShadows(boolean bool){
        for (Pane shadow : shadows){
            shadow.setVisible(bool);
        }
    }

    public void addShadow(Pane shadow) {
        shadows.add(shadow);
    }

}
