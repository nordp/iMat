package BackendExtension;

/**
 * Created by miktor on 2017-02-24.
 */
public enum ProductParentCategory {
    Meat (0),
    Vegetables (1),
    Bread(2),
    Dairy(3),
    Drinks(4),
    Uncatigorized(100);


    int this_value = 0;
    ProductParentCategory(int value)
    {
        this_value = value;
    }

    int getValue(){return this_value;}
}
