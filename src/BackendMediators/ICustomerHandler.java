package BackendMediators;

import BackendExtension.CustomerListener;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;

import java.util.List;

/**
 * Created by gustav on 2017-02-22.
 */
    public interface ICustomerHandler {
    void addFavorite(Product product);
    void addFavorite(int productID);
    void addCustomerListener(CustomerListener cls);
    void removeFavorite(Product product);
    void removeFavorite(int productID);
    boolean isFavorite(Product product);
    boolean isFavorite(int productID);
    boolean isFirstRun();
    List<Product> getFavorites();
    //List<> //Method to return a favorites shopping cart.

}
