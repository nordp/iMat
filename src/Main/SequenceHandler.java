package Main;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.Stack;

public class SequenceHandler {
    private Label nextButtonLabel;
    private int MAX_CHECKOUT_VALUE = 4;
    private int MAX_CATEGORY_VALUE = 6;
    private int checkoutIndex = 0;
    private int categoryIndex = 0; //TODO Is it wise to use instance values since it is possible to use different means of navigating?
    private boolean checkoutActive = false;
    private static Main.SequenceHandler instance = null;
    private boolean inputValid = false;
    String[] categories = {"", "Kött", "Grönt", "Bröd", "Mejeri", "Dricka","Skafferi", "Godis", ""};
    String[] checkout = {"", "Varukorg", "Leverans", "Betalning", "Granskning", "Bekräfta Köp", "Stäng"};
    private Label previousButtonLabel;

    public static SequenceHandler getInstance(){
        if(instance == null) {
            return new SequenceHandler();
        }
        return instance;
    }
    protected static SequenceHandler getInstance(IMatController iMatController, Button next, Button back, Label nextButtonLabel, Label previousButtonLabel){
        if(instance == null || instance.iMatController == null){
            instance =  new SequenceHandler(iMatController, next, back, nextButtonLabel, previousButtonLabel);
        }
        return instance;
    }

    private SequenceHandler(){}
    public void setCategoriesActive(boolean categoriesActive) {
        this.categoriesActive = categoriesActive;
        updateButtonStatus();
        updateButtonText();
    }

    boolean categoriesActive = true;
    IMatController iMatController;
    Button next;
    Button back;
    private SequenceHandler(IMatController iMatController, Button next, Button back, Label nextButtonLabel, Label previousButtonLabel){
        this.iMatController = iMatController;
        this.next = next;
        this.back = back;
        this.nextButtonLabel = nextButtonLabel;
        this.previousButtonLabel = previousButtonLabel;
        instance = this;
        updateButtonStatus();
    }
    public void nextButton(){
        if(checkoutActive){
            if(checkoutIndex<MAX_CHECKOUT_VALUE) {
                checkoutIndex++;
                iMatController.nextCheckout();
            }
        }
        else{
            if(categoryIndex<MAX_CATEGORY_VALUE) {
                categoryIndex++;
                iMatController.nextCategory(categoryIndex);
            }
        }
        updateButtonText();
        updateButtonStatus();
    }
    public void setCheckoutActive(boolean checkoutActive){
        this.checkoutActive = checkoutActive;
        updateButtonStatus();
        updateButtonText();
    }

    public void updateButtonStatus(){
        if(next!= null && back != null) {
            next.setDisable(!isNextButtonActive());
            back.setDisable(!isBackButtonActive());
            if(checkoutActive && (checkoutIndex == 1 || checkoutIndex == 2)){
                next.setDisable(!inputValid);
            }
        }
    }
    public void previousButton(){
        if(checkoutActive){
            checkoutIndex--;
            iMatController.previousCheckout();
        }
        else{
            categoryIndex--;
            iMatController.previousCategory(categoryIndex);
        }
        updateButtonStatus();
        updateButtonText();
    }
    public void setCheckoutIndex(int index){
        checkoutIndex = index;
        updateButtonText();
    }
    public void setCategoriesIndex(int index){
        categoryIndex = index;
        updateButtonStatus();
        updateButtonText();
    }
    private boolean isNextButtonActive(){

        if(checkoutActive){
            if(checkoutIndex<MAX_CHECKOUT_VALUE){
                return true;
            }
            return false;
        }
        if(categoriesActive){
            if(categoryIndex<MAX_CATEGORY_VALUE){
                return true;
            }
        }
        return false;
    }
    private boolean isBackButtonActive(){

            if(checkoutActive){
                if(checkoutIndex>0){
                    return true;
                }
                return false;
            }
            if(categoriesActive){
                if(categoryIndex>0){
                    return true;
                }
            }
            return false;
    }

    public void setInputValid(boolean inputValid) {
        this.inputValid = inputValid;
        updateButtonStatus();
    }
    private void updateButtonText(){
        if(nextButtonLabel != null && previousButtonLabel != null) {
            System.out.println(categoriesActive);
            if (categoriesActive) {
                nextButtonLabel.setText(categories[categoryIndex + 2]);
                previousButtonLabel.setText(categories[categoryIndex]);
            } else {
                nextButtonLabel.setText(checkout[checkoutIndex + 2]);
                previousButtonLabel.setText(checkout[checkoutIndex]);
            }
        }
    }
}
