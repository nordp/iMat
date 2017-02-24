package Controllers;

/**
 * Created by gustav on 2017-02-23.
 */
public interface ISubCheckoutController {
    /*
        Interface that handles the notifying the different subCheckoutControllers when they are
        in view, and gives them the responsibility to handle when next button is Enabled.
     */
    void focusReceived();
    void focusLost();
}
