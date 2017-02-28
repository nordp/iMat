package Main;

import javafx.scene.control.Button;

/**
 * Created by gustav on 2017-02-27.
 */
public class SequenceHandler {
    private int MAX_CHECKOUT_VALUE = 3;
    private int MAX_CATEGORY_VALUE = 6;
    private int checkoutIndex = 0;
    private int categoryIndex = -1; //TODO Is it wise to use instance values since it is possible to use different means of navigating?
    private boolean checkoutActive = false;
    private static Main.SequenceHandler instance = null;

    public static SequenceHandler getInstance(){
        if(instance == null) {
            return new SequenceHandler();
        }
        return instance;
    }

    private SequenceHandler(){}
    public void setCategoriesActive(boolean categoriesActive) {
        this.categoriesActive = categoriesActive;
        updateButtonStatus();
    }

    boolean categoriesActive = true;
    IMatController iMatController;
    Button next;
    Button back;
    protected SequenceHandler(IMatController iMatController, Button next, Button back){
        this.iMatController = iMatController;
        this.next = next;
        this.back = back;
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
        updateButtonStatus();
    }
    public void setCheckoutActive(boolean checkoutActive){
        this.checkoutActive = checkoutActive;
        updateButtonStatus();
    }

    public void updateButtonStatus(){
        if(next!= null && back != null) {
            next.setDisable(!isNextButtonActive());
            back.setDisable(!isBackButtonActive());
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
    }
    public void setCheckoutIndex(int index){
        checkoutIndex = index;
    }
    public void setCategoriesIndex(int index){
        categoryIndex = index;
        updateButtonStatus();
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
}
