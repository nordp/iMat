package BackendExtension;

import BackendMediators.CustomerHandler;
import BackendMediators.StoreHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.ISTORE;
import se.chalmers.ait.dat215.project.*;

/**
 * Created by miktor on 2017-02-24.
 */
public class ProductContainer {

    HashMap<Product, ProductCategory_> productMap = new HashMap<>();

    public ProductContainer()
    {
        loadProductTranslations();
    }

    public List<Product> searchProducts(String name)
    {
        name = name.toLowerCase();
        ProductParentCategory par = ProductParentCategory.valueOf(name);
        ProductSubCategory sub = ProductSubCategory.valueOf(name);
        ProductCategory_ cat = new ProductCategory_(par,sub);
        List<Product> output = getProductsFromCategory(cat);

        // CopyPaste from
        for(Map.Entry<Product, ProductCategory_> entry : productMap.entrySet())
        {
            Product p = entry.getKey();
            String productName = p.getName().toLowerCase();
            if(productName.indexOf(name) > -1) {
                output.add(p);
            }
        }

        return output;
    }

    public List<Product> getProductsFromCategory(ProductCategory_ category)
    {
        List<Product> output = new ArrayList<>();
        for(Map.Entry<Product, ProductCategory_> entry : productMap.entrySet())
        {
            if(category.parentCategory == entry.getValue().parentCategory || category.subCategory == entry.getValue().subCategory)
            {
                output.add(entry.getKey());
            }
        }
        return output;
    }

    public ProductCategory_ getCategory(int id)
    {
        return getCategory(IMatDataHandler.getInstance().getProduct(id));
    }

    public ProductCategory_ getCategory(Product p)
    {
        return productMap.get(p);
    }

    private void loadProductTranslations()
    {
        IMatDataHandler handler = IMatDataHandler.getInstance();
        List<Product> products = handler.getProducts();
        // Initialize the map with empty valuyes so that there will not occur a nullptr exception later if categorys is missing from a product.
        for(Product p : products){
            productMap.put(p, new ProductCategory_(0,0));
        }

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));
            try {
                String line = br.readLine();

                while (line != null) {

                    line = br.readLine();
                }
            }
            catch (Exception ex) {}
            finally {
                br.close();
            }

        }
        catch (Exception ex) {}
    }

}
