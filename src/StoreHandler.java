import javafx.scene.image.Image;
import se.chalmers.ait.dat215.project.*;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by gustav on 2017-02-22.
 */
public class StoreHandler implements IStoreHandler{
    IMatDataHandler handler = IMatDataHandler.getInstance();
    Order order = new Order();

    @Override
    public void addToCart(ShoppingItem product, int amount) {
        for(int i = 1; i<=amount; i++) {
            handler.getShoppingCart().addItem(product);
        }
    }

    @Override
    public void addToCart(ShoppingItem product) {
        addToCart(product, 1);
    }

    @Override
    public ProductCategory getCategory(Product product) {
        return product.getCategory();
    }

    @Override
    public ProductCategory getCategory(int productID) {
        return getCategory(handler.getProduct(productID));
    }

    @Override
    public List<ShoppingItem> getCurrentShoppingCart() {
        return handler.getShoppingCart().getItems();
    }

    @Override
    public List<Order> getOrders() {
        return handler.getOrders();
    }

    @Override
    public void getOrder(int index) {
        handler.getOrders().get(index);
    }

    @Override
    public void placeOrder() {
        handler.placeOrder();
    }

    @Override
    public double getProductPrice(Product product) {
        return product.getPrice();
    }

    @Override
    public double getProductPrice(int productID) {
        return handler.getProducts().get(productID).getPrice();
    }

    @Override
    public double getCartPrice() {
        return handler.getShoppingCart().getTotal();
    }

    @Override
    public double getCartPrice(Order order) {
        List<ShoppingItem> products = order.getItems();
        double totalPrice = 0;
        for(ShoppingItem s: products) {
            totalPrice += s.getProduct().getPrice();
        }
        return totalPrice;
    }

    @Override
    public List<Product> getProductsFromCategories(ProductCategory category) {
        return null;
    }

    @Override
    public List<Product> getProductsFromSearch(String search) {
        return null;
    }

    @Override
    public void shutDown() {

    }

    @Override
    public void clearCurrentShoppingCart() {

    }

    @Override
    public Date getDate(Order order) {
        return null;
    }

    @Override
    public Date getDate(int orderIndex) {
        return null;
    }

    @Override
    public boolean hasImage(Product product) {
        return false;
    }

    @Override
    public boolean hasImage(int productID) {
        return false;
    }

    @Override
    public Image getImage(Product product) {
        return null;
    }

    @Override
    public Image getImage(int productID) {
        return null;
    }

    @Override
    public Image getImage(Product product, int width, int height) {
        return null;
    }

    @Override
    public Image getImage(int productID, int width, int height) {
        return null;
    }
}
