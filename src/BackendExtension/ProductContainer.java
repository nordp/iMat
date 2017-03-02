package BackendExtension;

import BackendMediators.CustomerHandler;
import BackendMediators.StoreHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.ISTORE;
import se.chalmers.ait.dat215.project.*;

/**
 * Created by miktor on 2017-02-24.
 * Since the backend uses the stupid singleton pattern i will use that aswell to make this app really quick and dirty.
 */
public class ProductContainer {


    private static ProductContainer m_container = null;

    private HashMap<Product, ProductCategory_> productMap = new HashMap<>();
    private HashMap<ProductParentCategory, HashSet<ProductSubCategory>> categoryMapping = new HashMap<>();



    private ProductContainer()
    {
        loadProductTranslations();
    }

    public static ProductContainer getInstance()
    {
        if(m_container == null)
        {
            m_container = new ProductContainer();
        }
        return m_container;
    }

    public List<ProductParentCategory> getParentCategories()
    {
        List<ProductParentCategory> output = new ArrayList<>();
        for(Map.Entry<ProductParentCategory, HashSet<ProductSubCategory>> entry : categoryMapping.entrySet())
        {
            output.add(entry.getKey());
        }
        java.util.Collections.sort(output);

        return output;
    }

    public List<ProductSubCategory> getSubCategories(ProductParentCategory cat)
    {
        List<ProductSubCategory> output = new ArrayList<>();
        for(ProductSubCategory c : categoryMapping.get(cat))
        {
            output.add(c);
        }
        java.util.Collections.sort(output);

        return output;
    }

    public ProductParentCategory getParentCategory(String s)
    {
        ProductParentCategory parentCategory = null;
        try
        {
            parentCategory = ProductParentCategory.valueOf(s);
        } catch (Exception ex){parentCategory = ProductParentCategory.NO_CATEGORY;}

        return parentCategory;
    }

    public ProductSubCategory getSubCategory(String s)
    {
        ProductSubCategory subCategory = null;
        try
        {
            subCategory = ProductSubCategory.valueOf(s);
        } catch (Exception ex) {subCategory = ProductSubCategory.NO_CATEGORY;}

        return subCategory;
    }

    public List<Product> searchProducts(String name)
    {
        name = name.toUpperCase();

        ProductParentCategory par = getParentCategory(name);
        ProductSubCategory sub = getSubCategory(name);
        ProductCategory_ cat = new ProductCategory_(par,sub);
        List<Product> output = getProductsFromCategory(cat);

        // CopyPaste from
        for(Map.Entry<Product, ProductCategory_> entry : productMap.entrySet())
        {
            Product p = entry.getKey();
            String productName = p.getName().toUpperCase();
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


    public String TranslateToSwedish(String name)
    {
        switch (name)
        {
            case "MEAT": return "Kött";
            case "GREEN": return "Grönt";
            case "BREAD": return "Bröd";
            case "DAIRY": return "Mejeri";
            case "DRINKS": return "Drycker";
            case "PANTRY": return "Skafferi";
            case "CANDY": return "Godis";
            case "BEEF": return "Biff";
            case "CHICKEN": return "Kyckling";
            case "FISH": return "Fisk";
            case "VEGETABLE": return "Grönsaker";
            case "BERRY": return "Bär";
            case "CITRUS": return "Citrus";
            case "CABBAGE": return "Kål";
            case "FRUIT": return "Frukt";
            case "ROOT": return "Rötter";
            case "POD": return "Balj";
            case "POTATO": return "Potatis";
            case "HERB": return "Ört";
            case "EXOTIC": return "Exotisk";
            case "NUTS": return "Nötter";
            case "MELONS": return "Meloner";
            case "SOFT": return "Mjuka";
            case "HARD": return "Hårda";
            case "CHEESE": return "Ost";
            case "MILK": return "Mjölk";
            case "EGG": return "Ägg";
            case "JUICE": return "Juice";
            case "SODA": return "Soda";
            case "WATER": return "Vatten";
            case "TEA": return "Te";
            case "PASTA": return "Pasta";
            case "RICE": return "Ris";
            case "BAKING": return "Bak";
            case "SWEET": return "Sött";
            case "SNACKS": return "Snacks";
            case "ALL": return "Alla";
            default: return "";
        }
    }

    public String TranslateToEnglish(String name)
    {
        switch (name)
        {
            case "Kött": return "MEAT";
            case "Grönt": return "GREEN";
            case "Bröd": return "BREAD";
            case "Mejeri": return "DAIRY";
            case "Drycker": return "DRINKS";
            case "Skafferi": return "PANTRY";
            case "Godis": return "CANDY";
            case "Biff": return "BEEF";
            case "Kyckling": return "CHICKEN";
            case "Fisk": return "FISH";
            case "Grönsaker": return "VEGETABLE";
            case "Bär": return "BERRY";
            case "Citrus": return "CITRUS";
            case "Kål": return "CABBAGE";
            case "Frukt": return "FRUIT";
            case "Rötter": return "ROOT";
            case "Balj": return "POD";
            case "Potatis": return "POTATO";
            case "Ört": return "HERB";
            case "Exotisk": return "EXOTIC";
            case "Nötter": return "NUTS";
            case "Meloner": return "MELONS";
            case "Mjuka": return "SOFT";
            case "Hårda": return "HARD";
            case "Ost": return "CHEESE";
            case "Mjölk": return "MILK";
            case "Ägg": return "EGG";
            case "Juice": return "JUICE";
            case "Soda": return "SODA";
            case "Vatten": return "WATER";
            case "Te": return "TEA";
            case "Pasta": return "PASTA";
            case "Ris": return "RICE";
            case "Bak": return "BAKING";
            case "Sött": return "SWEET";
            case "Snacks": return "SNACKS";
            case "Alla": return "ALL";
            default: return "";
        }
    }

    private void loadProductTranslations()
    {
        IMatDataHandler handler = IMatDataHandler.getInstance();
        List<Product> products = handler.getProducts();
        // Initialize the map with empty values so that there will not occur a nullptr exception later if categorys is missing from a product.
        for(Product p : products){
        productMap.put(p, new ProductCategory_(0,0));
    }

        try
        {
            File f = new File("resources/Categories").getAbsoluteFile();
            BufferedReader br = new BufferedReader(new FileReader("src/resources/Categories"));
            try {
                String line = br.readLine();

                while (line != null) {
                    String[] args = line.split(";");
                    int idx = Integer.parseInt(args[0]);
                    String parentCat = args[1];
                    String subCat = args[2];
                    String name_notused = args[3];
                    ProductParentCategory parentCategory = getParentCategory(parentCat);
                    ProductSubCategory subCategory = getSubCategory(subCat);

                    productMap.put(handler.getProduct(idx), new ProductCategory_(parentCategory, subCategory));

                    // We need to map what sub categories belongs to what parent category.
                    if(!categoryMapping.containsKey(parentCategory))
                    {
                        categoryMapping.put(parentCategory, new HashSet<>());
                    }
                    categoryMapping.get(parentCategory).add(subCategory);

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
