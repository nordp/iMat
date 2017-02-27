package BackendMediators;

import BackendExtension.CustomerListener;
import se.chalmers.ait.dat215.project.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustav on 2017-02-23.
 */
public class CustomerHandler implements ICustomerHandler {
    IMatDataHandler handler = IMatDataHandler.getInstance();
    List<CustomerListener> listeners = new ArrayList<>();

    @Override
    public boolean isFirstRun(){
        return handler.isFirstRun();
    }

    @Override
    public Customer getCustomer() {
        return handler.getCustomer();
    }

    @Override
    public CreditCard getSavedCreditCard() {
        return handler.getCreditCard();
    }

    @Override
    public void addFavorite(Product product) {
        handler.addFavorite(product);
    }

    @Override
    public void addFavorite(int productID) {
        addFavorite(handler.getProduct(productID));
    }

    @Override
    public void addCustomerListener(CustomerListener cls) {
        listeners.add(cls);
    }

    public void fireCustomerChangedEvent(Customer customer){
        for (CustomerListener customerListener : listeners){
            customerListener.customerInfoChanged(getCustomer(),getSavedCreditCard());
        }
    }

    @Override
    public void removeFavorite(Product product) {
        handler.removeFavorite(product);
    }

    @Override
    public void removeFavorite(int productID) {
        removeFavorite(handler.getProduct(productID));
    }

    @Override
    public boolean isFavorite(Product product) {
        return handler.isFavorite(product);
    }

    @Override
    public boolean isFavorite(int productID) {
        return isFavorite(handler.getProduct(productID));
    }

    @Override
    public List<Product> getFavorites() {
        return handler.favorites();
    }
}
