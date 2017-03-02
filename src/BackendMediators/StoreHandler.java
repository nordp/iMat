package BackendMediators;

import BackendExtension.ProductCategory_;
import BackendExtension.ProductContainer;
import javafx.scene.image.Image;
import se.chalmers.ait.dat215.project.*;

import java.util.Date;
import java.util.List;

public class StoreHandler implements IStoreHandler{
    private static IStoreHandler instance;

    IMatDataHandler handler = IMatDataHandler.getInstance();

    public static IStoreHandler getInstance(){if(instance == null) {
        instance = new StoreHandler();
        //instance.init();
    }
        return instance;}

    @Override
    public void addToCart(ShoppingItem product) {
        // fulhack :p
        boolean found = false;
        for(ShoppingItem item : handler.getShoppingCart().getItems())
        {
            if(item.getProduct().equals(product.getProduct()))
            {
                found = true;
                handler.getShoppingCart().removeItem(item);
                item.setAmount(item.getAmount() + product.getAmount());
                handler.getShoppingCart().addItem(item);
                break;
            }
        }

        if(!found)
        {
            handler.getShoppingCart().addItem(product);
        }
    }

    @Override
    public void addShoppingCartListener(ShoppingCartListener scl) {
        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(scl);
    }

    @Override
    public ProductCategory_ getCategory(Product product) {
        return ProductContainer.getInstance().getCategory(product);
    }

    @Override
    public ProductCategory_ getCategory(int productID) {
        return ProductContainer.getInstance().getCategory(productID);
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
    public Order getOrder(int index) {
        return handler.getOrders().get(index);
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
    public List<Product> getProductsFromCategories(ProductCategory_ category) {
        return ProductContainer.getInstance().getProductsFromCategory(category);
    }

    @Override
    public List<Product> getProductsFromSearch(String search) {
        return ProductContainer.getInstance().searchProducts(search);
    }

    @Override
    public List<Product> getAllProducts() {
        return handler.getProducts();
    }

    @Override
    public void shutDown() {
        handler.shutDown();
    }

    @Override
    public void clearCurrentShoppingCart() {
        handler.getShoppingCart().clear();
    }

    @Override
    public Date getDate(Order order) {
        return order.getDate();
    }

    @Override
    public Date getDate(int orderIndex) {
        return getDate(handler.getOrders().get(orderIndex));
    }

    @Override
    public boolean hasImage(Product product) {
        return handler.hasImage(product);
    }

    @Override
    public boolean hasImage(int productID) {
        return hasImage(handler.getProduct(productID));
    }

    @Override
    public Image getImage(Product product) {
        return handler.getFXImage(product);
    }

    @Override
    public Image getImage(int productID) {
        return getImage(handler.getProduct(productID));
    }

    @Override
    public Image getImage(Product product, int width, int height) {
        return handler.getFXImage(product, width, height);
    }

    @Override
    public Image getImage(int productID, int width, int height) {
        return getImage(handler.getProduct(productID), width, height);
    }
}
