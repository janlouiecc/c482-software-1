package wgu.softwareproject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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
    @FXML
    public TableView<Product> mainProductsTable;
    @FXML
    public TableColumn<Product, Integer> mainProductIdColumn;
    @FXML
    public TableColumn<Product, String> mainProductNameColumn;
    @FXML
    public TableColumn<Product, Integer> mainProductInventoryColumn;
    @FXML
    public TableColumn<Product, Double> mainProductPriceColumn;
    @FXML
    public TextField partSearchField;
    @FXML
    public TextField productSearchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPartsTable.setItems(Inventory.getAllParts());
        mainPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        mainPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        mainPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        mainPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

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
        stage.setResizable(false);
        stage.show();
    }

    public void modifyPart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyPartView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void deletePart() {
        Part selectedPart = mainPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No item selected.");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setHeaderText("Deleting " + selectedPart.getPartName());
        alert.setContentText("Are you sure you want to delete this selection?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.getAllParts().remove(selectedPart);
        }
    }

    public void getPartSearchResults() {
        String search = partSearchField.getText();
        ObservableList<Part> parts = Inventory.lookupPart(search);

        if (parts.size() == 0) {
            try {
                int partId = Integer.parseInt(search);
                Part part = Inventory.lookupPart(partId);
                if (part != null) {
                    parts.add(part);
                }
            } catch (NumberFormatException ignored) {}
        }

        mainPartsTable.setItems(parts);
    }

    public void addProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProductView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void modifyProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyProductView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void getProductSearchResults() {
        String search = productSearchField.getText();
        ObservableList<Product> products = Inventory.lookupProduct(search);

        if (products.size() == 0) {
            try {
                int productId = Integer.parseInt(search);
                Product product = Inventory.lookupProduct(productId);
                if (product != null) {
                    products.add(product);
                }
            } catch (NumberFormatException ignored) {}
        }

        mainProductsTable.setItems(products);
    }
    
    public void exit() {
        Platform.exit();
        System.exit(0);
    }
}