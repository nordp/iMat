package BackendExtension;

/**
 * Created by miktor on 2017-02-24.
 */
public enum ProductSubCategory {
    Beef(0),
    Milk(1),// going to remove these ints.....-...,
    Uncatigorized(100);


    int this_value = 0;
    ProductSubCategory(int value)
    {
        this_value = value;
    }

    int getValue(){return this_value;}
}
