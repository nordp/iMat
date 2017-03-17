package Main;

import BackendExtension.ProductContainer;
import BackendMediators.CustomerHandler;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import Controllers.*;
import ListCells.CartElement;
import ListCells.EditableCartElementFactory;
import Main.SequenceHandler;
import Utility.Util;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.Duration;
import se.chalmers.ait.dat215.project.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import BackendExtension.*;

import javax.swing.*;

public class IMatController implements Initializable, ShoppingCartListener{

    @FXML Parent lightbox;
    @FXML Parent productGrid;
    @FXML ProductGridController productGridController;

    @FXML ListView<ShoppingItem> currentCartList;
    @FXML ToggleButton editToggle;
    @FXML Label sumLabel;

    @FXML Button accountButton;
    @FXML Button savedCartsButton;
    @FXML Button historyButton;
    @FXML Button checkoutButton;

    @FXML Pane shadow, shadow1, shadow2, shadow3;
    @FXML LightboxController lightboxController;

    @FXML TextField searchField;
    @FXML Accordion products_accordion;

    @FXML Pane lightBoxHelpPane;

    @FXML Label nextLabel;
    @FXML Label previousLabel;

    @FXML Button nextButton;
    @FXML Button backButton;

    @FXML AnchorPane startPage;

    private List<Product> testList;
    IStoreHandler store = new StoreHandler();
    List<ProductParentCategory> parentCategories;
    SequenceHandler sequenceHandler;

    private static IMatController instance;

    public static IMatController getInstance(){
        return instance;
    }

    public IMatController(){
        instance = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sequenceHandler = SequenceHandler.getInstance(this, nextButton, backButton, nextLabel, previousLabel);
        store = StoreHandler.getInstance();

        store.addShoppingCartListener(this);
        shoppingCartChanged(null);

        currentCartList.setCellFactory(new EditableCartElementFactory(currentCartList.widthProperty().subtract(4),editToggle.isSelected()));
        currentCartList.getItems().addAll(store.getCurrentShoppingCart());


        lightbox.setVisible(false);
        lightboxController.addShadow(shadow);
        lightboxController.addShadow(shadow1);
        lightboxController.addShadow(shadow2);
        lightboxController.addShadow(shadow3);

        products_accordion.getPanes().get(0).setOnMouseClicked(a -> favoritesClicked());

        parentCategories = ProductContainer.getInstance().getParentCategories();
        int i = 0;
        for(ProductParentCategory cat : parentCategories)
        {
            ProductContainer products = ProductContainer.getInstance();
            TitledPane pane = new TitledPane();
            pane.setId(String.valueOf(i++));
            pane.setFont(new Font(16));
            pane.setText(ProductContainer.getInstance().TranslateToSwedish(cat.toString()));
            List<ProductSubCategory> subCats = products.getSubCategories( products.getParentCategory(cat.toString()));
            FlowPane grid = new FlowPane();
            grid.setVgap(4);
            grid.setHgap(4);
           // grid.prefHeight(300);
            int nButton = 0;
            double height = 0;
            for(ProductSubCategory subCat : subCats){
                nButton++;
                Button b = new Button(ProductContainer.getInstance().TranslateToSwedish(subCat.toString()));
                b.setOnMouseClicked(sub -> categoryClicked(new ProductCategory_(null,subCat)));
                grid.getChildren().add(b);
            }
            pane.setContent(grid);
            pane.setOnMouseClicked(sup -> categoryClicked(new ProductCategory_(cat,null)));
            products_accordion.getPanes().add(pane);
            setcheckoutButtonStatus();
        }



    }
    public void enableButtons(){
        historyButton.setDisable(false);
        savedCartsButton.setDisable(false);
        accountButton.setDisable(false);
        editToggle.setDisable(false);
    }

    private void favoritesClicked()
    {
        productGridController.fillGrid("\uD83D\uDDA4 Favoriter", IMatDataHandler.getInstance().favorites());
    }

    @FXML private void myAccountClicked(){
        nextAndBackButtonsActive(false);
        lightboxController.myAccount();
        editToggle.setDisable(true);
        helpPaneVisible(false);
    }

    @FXML private void shoppingListsClicked(){
        nextAndBackButtonsActive(false);
        ShoppingListsController.getInstance().updateList();
        lightboxController.shoppingLists();
        editToggle.setDisable(true);
        helpPaneVisible(false);
    }

    public void nextAndBackButtonsActive(boolean active) {
        if(nextButton != null && backButton != null) {
            nextButton.setVisible(active);
            backButton.setVisible(active);
        }
    }

    @FXML public void historyClicked(){
        nextAndBackButtonsActive(false);
        HistoryController.getInstance().updateList();
        lightboxController.history();
        editToggle.setDisable(true);
        helpPaneVisible(false);
    }

    @FXML private void shadowClicked() {
        lightboxController.close();
        sequenceHandler.setCategoriesActive(true);
        sequenceHandler.setCheckoutActive(false);
        enableButtons();
    }

    @FXML private void toCheckout(){
        lightboxController.checkout();
        sequenceHandler.setCheckoutActive(true);
        sequenceHandler.setCategoriesActive(false);
        disableButtons();
        helpPaneVisible(true);
        nextAndBackButtonsActive(true);
    }

    private void disableButtons() {
        accountButton.setDisable(true);
        savedCartsButton.setDisable(true);
        historyButton.setDisable(true);
        editToggle.setDisable(true);
    }

    @FXML private void toHome(){
        lightboxController.close();
        sequenceHandler.setCheckoutActive(false);
        sequenceHandler.setCategoriesActive(true);
        productGridController.fillGrid("",null);
        sequenceHandler.setCategoriesIndex(0);
        products_accordion.getExpandedPane().setExpanded(false);
        setWelcomeScreenVisible(true);
        helpPaneVisible(false);
        nextAndBackButtonsActive(true);
    }

    @FXML private void categoryClicked(ProductCategory_ cat) { //Bör ta en kategori som indata.
        // Lets assume that the TPane is collapsed, what should happend? nothing of the below i guess so we could just return to fix this nullptr exc,
        // Maybe we want to clear the grid aswell and make something happend in the sequenceHandler, check this out.
        if(products_accordion.getExpandedPane() == null) return;
        productGridController.fillGrid(ProductContainer.getInstance().TranslateToSwedish(cat.toString()),store.getProductsFromCategories(cat));
        sequenceHandler.setCategoriesIndex((Integer.parseInt(products_accordion.getExpandedPane().getId())+1));     // Detta skapar en nullpointer när kategorin redan är vald.
        sequenceHandler.setCategoriesActive(true);
        sequenceHandler.setCheckoutActive(false);
    }

    @FXML private void searchPerformed()
    {
        productGridController.fillGrid("Sökresultat",store.getProductsFromSearch(searchField.getText()));
        sequenceHandler.setCategoriesActive(false);
        sequenceHandler.setCheckoutActive(false);
    }

    @FXML private void nextPressed(ActionEvent e){
        sequenceHandler.nextButton();
    }

    public void nextCategory(int categoryIndex) {
        productGridController.fillGrid(ProductContainer.getInstance().TranslateToSwedish(parentCategories.get(categoryIndex-1).toString()),store.getProductsFromCategories(new ProductCategory_(parentCategories.get(categoryIndex-1), null)));
        products_accordion.setExpandedPane(products_accordion.getPanes().get(categoryIndex));
    }

    @FXML private void backPressed(ActionEvent event){
        sequenceHandler.previousButton();
    }

    @FXML private void setCartEditable(ActionEvent event){
        currentCartList.setCellFactory(new EditableCartElementFactory(currentCartList.widthProperty().subtract(4),editToggle.isSelected()));
        if (editToggle.isSelected()){
            editToggle.setText("Klar");
        } else {
            editToggle.setText("Ändra");
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        sumLabel.setText((int)store.getCartPrice() + " kr");
        if (cartEvent==null)
                return;
        if (cartEvent.isAddEvent()){
            currentCartList.getItems().add(cartEvent.getShoppingItem());
        } else {
            if(store.getCurrentShoppingCart().size() == 0){
                currentCartList.getItems().clear();
            } else {
                currentCartList.getItems().remove(cartEvent.getShoppingItem());
            }
        }
        setcheckoutButtonStatus();
    }

    private void setcheckoutButtonStatus() {
        if(currentCartList.getItems().isEmpty()){
            checkoutButton.setDisable(true);
        }
        else{
            checkoutButton.setDisable(false);
        }
    }

    public void setCheckoutPane(int activePane) {
        lightboxController.checkoutController.changePaneContent(activePane);
    }

    public void previousCheckout(){
        lightboxController.previousCheckoutPaneSelected();
    }

    public void previousCategory(int categoryIndex) {
        productGridController.fillGrid(ProductContainer.getInstance().TranslateToSwedish(parentCategories.get(categoryIndex-1).toString()), store.getProductsFromCategories(new ProductCategory_(parentCategories.get(categoryIndex-1), null)));
        products_accordion.setExpandedPane(products_accordion.getPanes().get(categoryIndex));
}

    public void nextCheckout() {
        lightboxController.nextCheckoutPaneSelected();
    }
    protected void closeWindow(){
        System.out.println("closing");
        lightboxController.close();
        resetCheckout();
    }
    protected void resetCheckout(){
        lightboxController.resetCheckout();
    }

    public void setWelcomeScreenVisible(boolean value) {
        startPage.setVisible(value);
    }

    public void helpPaneVisible(boolean visible) {
        System.out.println("visible " + visible);
    if(lightboxController != null) {
        lightBoxHelpPane.setVisible(visible);
        if(visible){
            Util.fadeIn(300, 0, lightBoxHelpPane);
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), lightBoxHelpPane);
            System.out.println(lightBoxHelpPane.getLayoutY());
            translateTransition.setFromY(25);
            translateTransition.setToY(0);
            translateTransition.play();
            System.out.println(translateTransition.getToY());
        }
        else{
           // Util.fadeOut(300, 0, lightBoxHelpPane);
        }
    }
    }
}