package ListCells;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by gustav on 2017-02-24.
 */
public class ProductElement extends AnchorPane {

    @FXML public ImageView icon;
    @FXML public ImageView heartIcon;
    @FXML public TextField amountField;
    @FXML public Label productName;
    @FXML public Label productPrice;
    @FXML public Label productUnit;
    @FXML public Label unitPrice;

    private void setValues(Product product) {
        productName.setText(product.getName());
        productPrice.setText(Double.toString(product.getPrice()));
        productUnit.setText(product.getUnit());
        unitPrice.setText("NOT SET");

        icon.setImage(IMatDataHandler.getInstance().getFXImage(product));

    }
    @FXML
    private void invertIcon(){
        //Todo invert icon when hovering
    }

    public ProductElement() {}

    public ProductElement(Product product) {
    //TODO Load product_element.fxml
        Test();
        setValues(product);
    }

    public void Test()
    {
        prefWidth(150);
        prefHeight(300);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_RIGHT);
        grid.setLayoutX(89);
        grid.prefHeight(311);
        grid.prefWidth(600);
        getChildren().add(grid);


        // Col0
        ColumnConstraints columConstraints = new ColumnConstraints();
        columConstraints.hgrowProperty().set(Priority.SOMETIMES);
        columConstraints.setMaxWidth(127);
        columConstraints.setMinWidth(10);
        columConstraints.setPrefWidth(106.5);
        grid.getColumnConstraints().add(columConstraints);
        // Col1
        columConstraints = new ColumnConstraints();
        columConstraints.hgrowProperty().set(Priority.SOMETIMES);
        columConstraints.setMaxWidth(113);
        columConstraints.setMinWidth(10);
        columConstraints.setPrefWidth(14.5);
        grid.getColumnConstraints().add(columConstraints);
        // Col2
        columConstraints = new ColumnConstraints();
        columConstraints.hgrowProperty().set(Priority.SOMETIMES);
        columConstraints.setMaxWidth(95.5);
        columConstraints.setMinWidth(10);
        columConstraints.setPrefWidth(88.5);
        grid.getColumnConstraints().add(columConstraints);
        // Col3
        columConstraints = new ColumnConstraints();
        columConstraints.hgrowProperty().set(Priority.SOMETIMES);
        columConstraints.setMaxWidth(120.5);
        columConstraints.setMinWidth(10);
        columConstraints.setPrefWidth(91.5);
        grid.getColumnConstraints().add(columConstraints);
        // Row0
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.SOMETIMES);
        rowConstraints.setMaxHeight(79.5);
        rowConstraints.setMinHeight(10);
        rowConstraints.setPrefHeight(43.5);
        grid.getRowConstraints().add(rowConstraints);
        // Row1
        rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.SOMETIMES);
        rowConstraints.setMaxHeight(103.5);
        rowConstraints.setMinHeight(10);
        rowConstraints.setPrefHeight(36.5);
        grid.getRowConstraints().add(rowConstraints);
        // Row2
        rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.SOMETIMES);
        rowConstraints.setMaxHeight(117);
        rowConstraints.setMinHeight(10);
        rowConstraints.setPrefHeight(29);
        grid.getRowConstraints().add(rowConstraints);
        // Row3
        rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.SOMETIMES);
        rowConstraints.setMaxHeight(99.5);
        rowConstraints.setMinHeight(0);
        rowConstraints.setPrefHeight(42.5);
        grid.getRowConstraints().add(rowConstraints);

        HBox hbox = new HBox();
        hbox.setPrefWidth(200);
        hbox.setPrefHeight(100);
        grid.getChildren().add(hbox);
        grid.setRowIndex(hbox, 3);
        grid.setColumnIndex(hbox, 4);

        icon = new ImageView();
        icon.setFitHeight(100);
        icon.setFitWidth(100);
        icon.setPickOnBounds(true);
        icon.setPreserveRatio(true);
        //Image img = new Image("file:../../../DAT216-LAB/Lab2/RecipeSearch/src/resources/laxpaket-med-fankal-och-spenat.jpg");
        //icon.setImage(img);

        grid.getChildren().add(icon);
        grid.setRowIndex(icon, 1);
        grid.setHalignment(icon, HPos.RIGHT);
        grid.setValignment(icon, VPos.BOTTOM);

        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.prefWidth(51);
        label.prefHeight(25);
        label.setFont(new Font(18));
        label.setText("Antal");
        grid.getChildren().addAll(label);
        grid.setRowIndex(label, 2);
        grid.setHalignment(label, HPos.CENTER);
        grid.setValignment(label,VPos.BOTTOM);

        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.prefWidth(200);
        hbox.prefHeight(42);
        grid.getChildren().addAll(hbox);
        grid.setHalignment(hbox, HPos.CENTER);
        grid.setRowIndex(hbox, 3);
        grid.setValignment(hbox, VPos.TOP);
        Button btn = new Button();
        btn.setAlignment(Pos.CENTER);
        btn.setMnemonicParsing(false);
        btn.setPrefHeight(0);
        btn.setPrefWidth(26);

        Font f = new Font(12);
        btn.setFont(f);
        // Make the font System Bold somehow.
        btn.setText("-");
        hbox.getChildren().addAll(btn);
        hbox.setMargin(btn, new Insets(0,2,2,2));

        amountField = new TextField();
        amountField.setAlignment(Pos.CENTER);
        amountField.setPrefHeight(35);
        amountField.setPrefWidth(40);
        amountField.setFont(new Font(14));
        amountField.setText("1");
        hbox.getChildren().add(amountField);
        hbox.setMargin(amountField, new Insets(0,2,0,2));
        btn = new Button();
        btn.setAlignment(Pos.CENTER);
        btn.setMnemonicParsing(false);
        btn.setPrefHeight(25);
        btn.setPrefWidth(25);
        btn.setFont(new Font(12));
        btn.setText("+");
        hbox.getChildren().add(btn);
        hbox.setMargin(btn, new Insets(0,2,2,2));

        productName = new Label();
        productName.setFont(new Font(14));
        productName.setText("Varunamn");
        grid.getChildren().add(productName);
        grid.setColumnIndex(productName, 2);
        grid.setHalignment(productName, HPos.LEFT);
        grid.setValignment(productName, VPos.BOTTOM);

        unitPrice = new Label();
        unitPrice.setFont(new Font(14));
        unitPrice.setText("Kilopris");
        grid.getChildren().add(unitPrice);
        grid.setColumnIndex(unitPrice, 2);
        grid.setHalignment(unitPrice, HPos.LEFT);
        grid.setValignment(unitPrice, VPos.BOTTOM);
        grid.setRowIndex(unitPrice,1);

        productUnit = new Label();
        productUnit.setFont(new Font(14));
        productUnit.setText("500 g");
        grid.getChildren().add(productUnit);
        grid.setColumnIndex(productUnit, 3);
        grid.setHalignment(productUnit, HPos.LEFT);
        grid.setValignment(productUnit, VPos.BOTTOM);

        productPrice = new Label();
        productPrice.setFont(new Font(14));
        productPrice.setText("500 g");
        grid.getChildren().add(productPrice);
        grid.setColumnIndex(productPrice, 3);
        grid.setRowIndex(productPrice, 1);
        grid.setHalignment(productPrice, HPos.LEFT);
        grid.setValignment(productPrice, VPos.BOTTOM);

        btn = new Button();
        btn.setMnemonicParsing(false);
        btn.setPrefHeight(31);
        btn.setPrefWidth(37);

        heartIcon = new ImageView();
        heartIcon.setFitHeight(25);
        heartIcon.setFitWidth(25);
        heartIcon.setPickOnBounds(true);
        heartIcon.setPreserveRatio(true);
        heartIcon.setImage(new Image("resources/img/heart.png"));
        btn.setGraphic(heartIcon);

        grid.getChildren().add(btn);
        grid.setColumnIndex(btn, 3);
        grid.setHalignment(btn, HPos.RIGHT);
        grid.setValignment(btn, VPos.TOP);
        grid.setMargin(btn, new Insets(2,2,0,0));

        btn = new Button();
        btn.setMnemonicParsing(false);
        btn.setPrefHeight(35);
        btn.setPrefWidth(169);
        btn.setFont(new Font(14));
        btn.setText("Lägg i kundvagn");
        grid.getChildren().add(btn);
        grid.setColumnIndex(btn, 1);
        grid.setColumnSpan(btn, 3);
        grid.setHalignment(btn, HPos.CENTER);
        grid.setRowIndex(btn, 3);
        grid.setValignment(btn, VPos.CENTER);


    }
}