package BackendMediators;

import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.Product;

import java.util.List;

/**
 * Created by gustav on 2017-02-22.
 */
    public interface ICustomerHandler {
    Customer getCustomer();
    CreditCard getSavedCreditCard();
    void addFavorite(Product product);
    void addFavorite(int productID);
    void removeFavorite(Product product);
    void removeFavorite(int productID);
    boolean isFavorite(Product product);
    boolean isFavorite(int productID);
    List<Product> getFavorites();
    //List<> //Method to return a favorites shopping cart.

}
