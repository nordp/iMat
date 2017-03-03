package ListCells;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * Created by Phnor on 2017-03-03.
 */
public class EditableCartElementFactory implements Callback<ListView<ShoppingItem>, ListCell<ShoppingItem>> {
    private DoubleBinding bindProperty;
    private boolean editable;

    public EditableCartElementFactory(DoubleBinding bindProperty, boolean editable){
        this.bindProperty = bindProperty;
        this.editable = editable;
    }

    @Override
    public ListCell<ShoppingItem> call(ListView<ShoppingItem> param) {
        CartElement lc = new CartElement();
        lc.prefWidthProperty().bind(bindProperty);
        if (editable){
            lc.removeButton.setPrefWidth(30);
        } else {
            lc.removeButton.setPrefWidth(0);
        }
        return lc;
    }

}
