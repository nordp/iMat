package BackendMediators;

import BackendExtension.CustomerListener;
import javafx.fxml.FXML;
import se.chalmers.ait.dat215.project.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * Created by gustav on 2017-02-23.
 */
public class CustomerHandler implements ICustomerHandler {
    IMatDataHandler handler = IMatDataHandler.getInstance();
    private static CustomerHandler instance;
    List<CustomerListener> listeners = new ArrayList<>();
    HashMap<String, List<ShoppingItem>> shoppingLists = new HashMap<String, List<ShoppingItem>>();
    List<String> keys = new LinkedList<>();
    private boolean directPayment;
    private Date deliveryDate;
    private CustomerHandler()
    {
        try {
            FileInputStream fileIn = new FileInputStream("shoppinglists.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            shoppingLists = (HashMap<String, List<ShoppingItem>>)in.readObject();
            in.close();
            fileIn.close();
            FileInputStream fileInputStream = new FileInputStream("shoppingListsIndexes.dat");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            keys = (List<String>)inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        }catch(Exception ex) {
            System.out.println("an error occured while reading data");
            // Filenotfound, memory ex blabla we cant do anything anyway so just continue.
        }
    }

    public static CustomerHandler getInstance(){
        if (instance == null){
            instance = new CustomerHandler();
        }
        return instance;
    }
    public void directPaymentSelected(boolean directPayment){
        this.directPayment = directPayment;
    }
    public boolean isDirectPaymentSelcted(){
        return directPayment;
    }
    @Override
    public boolean isFirstRun(){
        return handler.isFirstRun();
    }

    @Override
    public void setDeliveryDate(Date date) {
        deliveryDate = date;
    }

    @Override
    public Date getDeliveryDate() {
        return deliveryDate;
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

    public void addShoppingList(String name, List<ShoppingItem> list)
    {
        keys.add(name);
        shoppingLists.put(name, list);
    }
    public String getKey(int index){
        System.out.println(keys);
        return keys.get(index);
    }
    public void removeShoppingList(String name)
    {
        shoppingLists.remove(name);
    }

    public List<ShoppingItem> GetShoppingList(String name)
    {
        return shoppingLists.get(name);
    }

    public HashMap<String, List<ShoppingItem>> getShoppingLists()
    {
        return shoppingLists;
    }

    public void shutDown()
    {
        System.out.println("Saving n shoppinglists, n=" + shoppingLists.size());
        try
        {
            System.out.println("saving shoppinglists");
            FileOutputStream fout =
                    new FileOutputStream("shoppinglists.dat") ;
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(shoppingLists);
            out.close();
            fout.close();
            FileOutputStream fileOutputStream =
                    new FileOutputStream("ShoppingListsIndexes.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.flush();
            outputStream.writeObject(keys);
            outputStream.close();
            fileOutputStream.close();
        }
        catch (Exception redundantName){
            System.out.println("could not read files. ");
        }
    }

}
