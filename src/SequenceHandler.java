import javafx.scene.control.Button;

/**
 * Created by gustav on 2017-02-27.
 */
public class SequenceHandler {
    private int MAX_CHECKOUT_VALUE = 3;
    private int MAX_CATEGORY_VALUE = 6;
    private int checkoutIndex = 0;
    private int categoryIndex = 0;
    private boolean checkoutActive = false;

    public void setCategoriesActive(boolean categoriesActive) {
        this.categoriesActive = categoriesActive;
    }

    boolean categoriesActive = true;
    IMatController iMatController;
    Button next;
    Button back;
    SequenceHandler(IMatController iMatController, Button next, Button back){
        this.iMatController = iMatController;
        this.next = next;
        this.back = back;
    }
    public void nextButton(){
        if(checkoutActive){
            if(checkoutIndex<MAX_CHECKOUT_VALUE) {
                iMatController.nextCheckout();
                checkoutIndex++;
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
    }

    public void updateButtonStatus(){
        next.setDisable(!isNextButtonActive());
        back.setDisable(!isBackButtonActive());
    }
    public void previousButton(){
        if(checkoutActive){
            checkoutIndex--;
        }
        else{
            categoryIndex--;
        }
    }
    public void setCheckoutIndex(int index){
        checkoutIndex = index;
    }
    public void setCategoriesIndex(int index){
        categoryIndex = index;
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
