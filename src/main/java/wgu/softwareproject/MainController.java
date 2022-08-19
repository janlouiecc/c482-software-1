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

/**
 * The Main controller class. 
 */
public class MainController implements Initializable {

    @FXML
    private TableView<Part> mainPartsTable;
    @FXML
    private TableColumn<Part, Integer> mainPartIdColumn;
    @FXML
    private TableColumn<Part, String> mainPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> mainPartInventoryColumn;
    @FXML
    private TableColumn<Part, Double> mainPartPriceColumn;
    @FXML
    private TableView<Product> mainProductsTable;
    @FXML
    private TableColumn<Product, Integer> mainProductIdColumn;
    @FXML
    private TableColumn<Product, String> mainProductNameColumn;
    @FXML
    private TableColumn<Product, Integer> mainProductInventoryColumn;
    @FXML
    private TableColumn<Product, Double> mainProductPriceColumn;
    @FXML
    private TextField partSearchField;
    @FXML
    private TextField productSearchField;

    private static Part partToModify = null;
    private static Product productToModify = null;

    /**
     * Opens the 'add part' form. 
     * This method is from the event of pushing the add button in the part table to open the 'add part' form.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void addPart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPartView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Opens the 'modify part' form. 
     * This method is from the event of pushing the modify button in the part table to open the 'modify part' form.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void modifyPart(ActionEvent event) throws IOException {
        Part selectedPart = mainPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No item selected.");
            alert.setContentText("Please select an item to modify.");
            alert.showAndWait();
            return;
        }
        partToModify = selectedPart;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyPartView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Deletes a part. 
     * This method deletes the highlighted part and ensures it is also deleted from inventory.
     */
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

        if (Inventory.deletePart(selectedPart)) {
            Alert confirmed = new Alert(Alert.AlertType.INFORMATION);
            confirmed.setTitle("Deleted");
            confirmed.setHeaderText("This part has been deleted.");
            confirmed.showAndWait();
        }
    }

    /**
     * Gets the search results in the parts table. 
     */
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

    /**
     * Opens the 'add product' form. 
     * This method is from the event of pushing the add button in the product table to open the 'add product' form.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void addProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProductView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Opens the 'modify product' form. 
     * This method is from the event of pushing the modify button in the product table to open the 'modify product' form.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void modifyProduct(ActionEvent event) throws IOException {
        Product selectedProduct = mainProductsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No item selected.");
            alert.setContentText("Please select an item to modify.");
            alert.showAndWait();
            return;
        }
        productToModify = selectedProduct;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyProductView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Deletes a product. 
     * This method deletes the highlighted product and ensures it is also deleted from inventory.
     */
    public void deleteProduct() {
        Product selectedProduct = mainProductsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No item selected.");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setHeaderText("Deleting " + selectedProduct.getProductName());
        alert.setContentText("Are you sure you want to delete this selection?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (selectedProduct.getAllAssociatedParts().isEmpty()) {
                Inventory.getAllProducts().remove(selectedProduct);
            } else {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Error");
                alert1.setHeaderText("Product has parts associated. Unable to delete.");
                alert1.setContentText("Please select a different item to delete or disassociate parts from product before deleting.");
                alert1.showAndWait();
                return;
            }
        }

        if (Inventory.deleteProduct(selectedProduct)) {
            Alert confirmed = new Alert(Alert.AlertType.INFORMATION);
            confirmed.setTitle("Deleted");
            confirmed.setHeaderText("This product has been deleted.");
            confirmed.showAndWait();
        }
    }

    /**
     * Gets the search results in the products table. 
     */
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

    /**
     * Exits the program. 
     */
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Program?");
        alert.setHeaderText("You are about to exit the program.");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     * Gets part to modify. 
     * Gets the highlighted part to modify and passes it to the modify part controller for data manipulation. 
     * @return the part to modify
     */
    public static Part getPartToModify() {
        return partToModify;
    }

    /**
     * Gets product to modify. 
     * Gets the highlighted product to modify and passes it to the modify product controller for data manipulation. 
     * @return the product to modify
     */
    public static Product getProductToModify() {
        return productToModify;
    }

    /**
     * Initializes what is shown in the main form.
     * This method overrides the initialize method in the Initializable interface and links the table view data with the inventory data for presentation.
     * @param url the URL
     * @param resourceBundle the Resource Bundle
     */
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

        mainPartIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        mainPartsTable.getSortOrder().add(mainPartIdColumn);

        mainProductIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        mainProductsTable.getSortOrder().add(mainProductIdColumn);

        mainPartsTable.sort();
        mainProductsTable.sort();
    }

}