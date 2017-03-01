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
    private static CustomerHandler instance;
    List<CustomerListener> listeners = new ArrayList<>();

    private CustomerHandler(){}

    public static CustomerHandler getInstance(){
        if (instance == null){
            instance = new CustomerHandler();
        }
        return instance;
    }

    @Override
    public boolean isFirstRun(){
        return handler.isFirstRun();
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

    public void fireCustomerChangedEvent(){
        for (CustomerListener customerListener : listeners){
            customerListener.customerInfoChanged();
        }
        System.out.println("Customer info updated");
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

    public void setFirstName(String firstName) {
        handler.getCustomer().setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        handler.getCustomer().setLastName(lastName);
    }

    public void setAddress(String address) {
        handler.getCustomer().setAddress(address);
    }

    public void setPostCode(String postCode) {
        handler.getCustomer().setPostCode(postCode);
    }

    public void setPostAddress(String postAddress) {
        handler.getCustomer().setPostAddress(postAddress);
    }

    public void setCardNumber(String cardNumber) {
        handler.getCreditCard().setCardNumber(cardNumber);
    }

    public void setHoldersName(String holdersName) {
        handler.getCreditCard().setHoldersName(holdersName);
    }

    public void setVerificationCode(int verificationCode) {
        handler.getCreditCard().setVerificationCode(verificationCode);
    }

    public void setValidMonth(int validMonth) {
        handler.getCreditCard().setValidMonth(validMonth);
    }

    public void setValidYear(int validYear) {
        handler.getCreditCard().setValidYear(validYear);
    }

    public String getCardNumber() { return handler.getCreditCard().getCardNumber(); }

    public String getCardFour(int i) {
        String number = handler.getCreditCard().getCardNumber();
        if (number == null || number.equals("")) {
            return "";
        }
        return number.substring(i*4,i*4+4);
    }

    public String getFirstName() {
        return handler.getCustomer().getFirstName();
    }

    public String getLastName() {
        return handler.getCustomer().getLastName();
    }

    public String getAddress() {
        return handler.getCustomer().getAddress();
    }

    public String getPostCode() {
        return handler.getCustomer().getPostCode();
    }

    public String getPostAddress() {
        return handler.getCustomer().getPostAddress();
    }

    public int getValidYear() {
        return handler.getCreditCard().getValidYear();
    }

    public int getValidMonth() {
        return handler.getCreditCard().getValidMonth();
    }

    public int getSecurityCode() {
        return handler.getCreditCard().getVerificationCode();
    }

    public String getCardHolder() {
        return handler.getCreditCard().getHoldersName();
    }


}
