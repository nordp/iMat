package BackendMediators;

import BackendExtension.CustomerListener;
import se.chalmers.ait.dat215.project.*;

import java.util.List;

/**
 * Created by gustav on 2017-02-22.
 */
    public interface ICustomerHandler {
    void addFavorite(Product product);
    void addFavorite(int productID);
    void addCustomerListener(CustomerListener cls);
    void addShoppingList(String name, List<ShoppingItem> list);
    void removeShoppingList(String name);
    void removeFavorite(Product product);
    void removeFavorite(int productID);
    boolean isFavorite(Product product);
    boolean isFavorite(int productID);
    boolean isFirstRun();
    List<Product> getFavorites();
    //List<> //Method to return a favorites shopping cart.

}
