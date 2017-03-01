package Main;

import BackendExtension.ProductContainer;
import BackendMediators.CustomerHandler;
import BackendMediators.IStoreHandler;
import BackendMediators.StoreHandler;
import Controllers.*;
import ListCells.CartElement;
import Main.SequenceHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import se.chalmers.ait.dat215.project.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import BackendExtension.*;

public class IMatController implements Initializable, ShoppingCartListener{

    @FXML Parent lightbox;
    @FXML Parent productGrid;
    @FXML ProductGridController productGridController;

    @FXML ListView<ShoppingItem> currentCartList;
    @FXML Label sumLabel;

    @FXML Pane shadow, shadow1, shadow2, shadow3;
    @FXML LightboxController lightboxController;

    @FXML TextField searchField;
    @FXML Accordion products_accordion;

    @FXML Button nextButton;
    @FXML Button backButton;

    private List<Product> testList;
    IStoreHandler store = new StoreHandler();
    List<ProductParentCategory> parentCategories;
    SequenceHandler sequenceHandler;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sequenceHandler = new SequenceHandler(this, nextButton, backButton);
        store = StoreHandler.getInstance();

        store.addShoppingCartListener(this);
        shoppingCartChanged(null);

        currentCartList.setCellFactory(param -> new CartElement());
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
            pane.setText(cat.toString());
            List<ProductSubCategory> subCats = products.getSubCategories( products.getParentCategory(cat.toString()));
            FlowPane grid = new FlowPane();
            grid.setVgap(4);
            grid.setHgap(4);
            for(ProductSubCategory subCat : subCats){
                Button b = new Button(subCat.toString());
                b.setOnMouseClicked(sub -> categoryClicked(new ProductCategory_(null,subCat)));
                grid.getChildren().add(b);
            }
            pane.setContent(grid);
            pane.setOnMouseClicked(sup -> categoryClicked(new ProductCategory_(cat,null)));

            products_accordion.getPanes().add(pane);
        }


    }

    private void favoritesClicked()
    {
        productGridController.fillGrid("Favoriter", IMatDataHandler.getInstance().favorites());
    }

    @FXML private void myAccountClicked(){
        lightboxController.myAccount();
        sequenceHandler.setCheckoutActive(false);
        sequenceHandler.setCategoriesActive(false);}

    @FXML private void shoppingListsClicked(){
        lightboxController.shoppingLists();
        sequenceHandler.setCheckoutActive(false);
        sequenceHandler.setCategoriesActive(false);
    }

    @FXML private void historyClicked(){
        lightboxController.history();
        sequenceHandler.setCheckoutActive(false);
        sequenceHandler.setCategoriesActive(false);
    }

    @FXML private void shadowClicked() {
        lightboxController.close();
        sequenceHandler.setCategoriesActive(true);
        sequenceHandler.setCheckoutActive(false);
    }

    @FXML private void toCheckout(){
        lightboxController.checkout();
        sequenceHandler.setCheckoutActive(true);
        sequenceHandler.setCategoriesActive(false);
    }

    @FXML private void toHome(){ lightboxController.close();
        sequenceHandler.setCheckoutActive(false);
        sequenceHandler.setCategoriesActive(true);
    }

    @FXML private void categoryClicked(ProductCategory_ cat) { //Bör ta en kategori som indata.
        productGridController.fillGrid(cat.toString(),store.getProductsFromCategories(cat));
        sequenceHandler.setCategoriesIndex(Integer.parseInt(products_accordion.getExpandedPane().getId()));
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
         productGridController.fillGrid(parentCategories.get(categoryIndex).toString(),store.getProductsFromCategories(new ProductCategory_(parentCategories.get(categoryIndex), null)));
    }

    @FXML private void backPressed(ActionEvent event){
        sequenceHandler.previousButton();
    }


    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        sumLabel.setText((int)store.getCartPrice() + " kr");
        if (cartEvent==null)
                return;
        if (cartEvent.isAddEvent()){
            currentCartList.getItems().add(cartEvent.getShoppingItem());
        } else {
            currentCartList.getItems().remove(cartEvent.getShoppingItem());
        }
    }

    public void setCheckoutPane(int activePane) {
        lightboxController.checkoutController.changePaneContent(activePane);
    }
    public void previousCheckout(){
        lightboxController.previousCheckoutPaneSelected();
    }

    public void previousCategory(int categoryIndex) {
        productGridController.fillGrid(parentCategories.get(categoryIndex).toString(), store.getProductsFromCategories(new ProductCategory_(parentCategories.get(categoryIndex), null)));
    }

    public void nextCheckout() {
        lightboxController.nextCheckoutPaneSelected();
    }
}