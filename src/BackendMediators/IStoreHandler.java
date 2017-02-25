package BackendMediators;

import BackendExtension.ProductCategory_;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by gustav on 2017-02-22.
 */
    public interface IStoreHandler{
    void addToCart(ShoppingItem product, int amount);
    void addToCart(ShoppingItem product);
    ProductCategory_ getCategory(Product product);
    ProductCategory_ getCategory(int productID);
    List<ShoppingItem> getCurrentShoppingCart();
    List<Order> getOrders();
    void getOrder(int index);
    void placeOrder();
    double getProductPrice(Product product);
    double getProductPrice(int productID);
    double getCartPrice();
    double getCartPrice(Order order);
    List<Product> getProductsFromCategories(ProductCategory_ category);
    List<Product> getProductsFromSearch(String search);
    List<Product> getAllProducts();
    void shutDown();
    void clearCurrentShoppingCart();
    Date getDate(Order order);
    Date getDate(int orderIndex);
    boolean hasImage(Product product);
    boolean hasImage(int productID);
    Image getImage(Product product);
    Image getImage(int productID);
    Image getImage(Product product, int width, int height);
    Image getImage(int productID, int width, int height);
}