package BackendExtension;

import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

public class ProductCategory_ {
    public ProductParentCategory parentCategory;
    public ProductSubCategory subCategory;

    public ProductCategory_(ProductParentCategory parentCat, ProductSubCategory subCat)
    {
        this.parentCategory = parentCat;
        this.subCategory = subCat;
    }

    public ProductCategory_(int parentCatValue, int subCatValue)
    {
        ProductParentCategory[] parentValues = ProductParentCategory.values();

        ProductSubCategory[] subValues = ProductSubCategory.values();
        // not finished dont touch. will probably switch to strings instead....
    }

    @Override
    public String toString(){
        if (parentCategory!=null)
            return parentCategory.toString();
        else
            return "";
    }
}
