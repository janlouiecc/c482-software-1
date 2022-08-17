package wgu.softwareproject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    public TableView<Part> mainPartsTable;
    @FXML
    public TableColumn<Part, Integer> mainPartIdColumn;
    @FXML
    public TableColumn<Part, String> mainPartNameColumn;
    @FXML
    public TableColumn<Part, Integer> mainPartInventoryColumn;
    @FXML
    public TableColumn<Part, Double> mainPartPriceColumn;
    public TableView<Product> mainProductsTable;
    public TableColumn<Product, Integer> mainProductIdColumn;
    public TableColumn<Product, String> mainProductNameColumn;
    public TableColumn<Product, Integer> mainProductInventoryColumn;
    public TableColumn<Product, Double> mainProductPriceColumn;

    public void initialize() {
        mainPartsTable.setItems(Inventory.getAllParts());
        mainPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        mainProductsTable.setItems(Inventory.getAllProducts());
        mainProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        mainProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        mainProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        mainProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
    }

    public void addPart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPartView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modifyPart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyPartView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProductView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modifyProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyProductView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit() {
        Platform.exit();
        System.exit(0);
    }
}