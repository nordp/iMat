package BackendExtension;

/**
 * Created by miktor on 2017-02-24.
 */
// Uppercase ONLY
public enum ProductSubCategory {
    //Meat
    BEEF,
    CHICKEN,
    FISH,

    //Greens
    VEGETABLE,
    BERRY,
    CITRUS,
    CABBAGE,
    FRUIT,
    ROOT,
    POD,
    POTATO,
    HERB,
    EXOTIC,
    NUTS, //Some nuts are baking, some are fruits, some are snacks.
    MELONS,

    //Bread
    SOFT,
    HARD,

    //Dairy
    CHEESE,
    MILK, //Milk-products
    EGG, //Only one product?

    //Drinks, only bottled ones.
    JUICE,
    SODA,
    WATER,

    //Pantry. Allt till skafferiet
    TEA, //Kaffe och The
    PASTA,
    RICE,
    BAKING, //Torrvaror / Bakartiklar

    //Candy
    SWEET,
    SNACKS,

    ALL, //to be used to adress all items in parent category

    NO_CATEGORY;
}
