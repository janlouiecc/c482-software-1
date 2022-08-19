package wgu.softwareproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FUTURE ENHANCEMENT. An enhancement that would extend the functionality of this class would be to take into account total inventory and match the amount of parts to be used with the total amount of products in the inventory.
 * This would allow companies to keep track of what parts are left even after being used in a product and would allow companies to know if there are enough parts for a product.
 * This is the Add Product controller class.
 */
public class AddProductController implements Initializable {

    @FXML
    private TableColumn<Part, Integer> addPartsAssociatedPartsIdColumn;
    @FXML
    private TableColumn<Part, String> addPartsAssociatedPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> addPartsAssociatedPartsInventoryColumn;
    @FXML
    private TableColumn<Part, Double> addPartsAssociatedPartsPriceColumn;
    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<Part> addProductPartsTable;
    @FXML
    private TableView<Part> addPartsAssociatedPartsTable;
    @FXML
    private TableColumn<Part, Integer> addProductPartIdColumn;
    @FXML
    private TableColumn<Part, String> addProductPartNameColumn;
    @FXML
    private TableColumn<Part, Double> addProductPartPriceColumn;
    @FXML
    private TableColumn<Part, Integer> addProductPartInventoryColumn;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField productInventoryTextField;
    @FXML
    private TextField productPriceTextField;
    @FXML
    private TextField productMaxTextField;
    @FXML
    private TextField productMinTextField;

    /**
     * RUNTIME ERROR. Initially, a runtime error occurred due to input data for fields with an integer or double type and crashing as it was unable to parse the text.
     * Adding try and catch blocks to catch NumberFormatExceptions helped this run more smoothly.
     * This method saves the inputted data and adds a product to the inventory.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void save(ActionEvent event) throws IOException {
        Product product;

        try {
            if (Integer.parseInt(productMinTextField.getText()) > Integer.parseInt(productMaxTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot add product.");
                alert.setContentText("Please ensure that the minimum value is less than the maximum");
                alert.showAndWait();
                productMinTextField.clear();
                productMaxTextField.clear();
                return;
            } else if (!(Integer.parseInt(productMinTextField.getText()) <  Integer.parseInt(productInventoryTextField.getText())) ||
                    !(Integer.parseInt(productInventoryTextField.getText()) < Integer.parseInt(productMaxTextField.getText()))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot add product.");
                alert.setContentText("Please ensure that the inventory amount is sufficient.");
                alert.showAndWait();
                productInventoryTextField.clear();
                productMinTextField.clear();
                productMaxTextField.clear();
                return;
            } else {
                product = new Product(
                        productNameTextField.getText(),
                        Double.parseDouble(productPriceTextField.getText()),
                        Integer.parseInt(productInventoryTextField.getText()),
                        Integer.parseInt(productMinTextField.getText()),
                        Integer.parseInt(productMaxTextField.getText())
                );
                Inventory.addProduct(product);
            }
        } catch (NumberFormatException ignore) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot add product.");
            alert.setContentText("Please ensure that the information is correct.");
            alert.showAndWait();
            productNameTextField.clear();
            productPriceTextField.clear();
            productInventoryTextField.clear();
            productMinTextField.clear();
            productMaxTextField.clear();
            return;
        }

        for (Part part : addPartsAssociatedPartsTable.getItems()) {
            product.addAssociatedPart(part);
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Cancels adding a new product.
     * This method cancels the option to add a new product to the inventory.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Gets part search results.
     * This method is associated with the search text field in the parts table.
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

        addProductPartsTable.setItems(parts);
    }

    /**
     * Add associated part button.
     * This method is associated with the Add button and adds the parts selected to the product's information.
     */
    public void addAssociatedPartButton() {
        Part selectedPart = addProductPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No item selected.");
            alert.setContentText("Please select a part to add and associate to this product.");
            alert.showAndWait();
            return;
        }

        addPartsAssociatedPartsTable.getItems().add(selectedPart);
    }

    /**
     * Remove associated part button.
     * This method is associated with the Remove Associated Parts button and removes the parts selected from the product's information.
     */
    public void removeAssociatedPartButton() {
        Part selectedPart = addPartsAssociatedPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No item selected.");
            alert.setContentText("Please select a part to remove.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove?");
        alert.setHeaderText("Removing " + selectedPart.getPartName());
        alert.setContentText("Are you sure you want to remove this part from the product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
                addPartsAssociatedPartsTable.getItems().remove(selectedPart);
        }
    }

    /**
     * Initializes what is shown in the add product form.
     * This method overrides the initialize method in the Initializable interface and links the table view data with the inventory data for presentation.
     * @param url the URL
     * @param resourceBundle the Resource Bundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductPartsTable.setItems(Inventory.getAllParts());
        addProductPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        addProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        addProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        addProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        addPartsAssociatedPartsIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        addPartsAssociatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        addPartsAssociatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        addPartsAssociatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        addProductPartIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        addProductPartsTable.getSortOrder().add(addProductPartIdColumn);

        addPartsAssociatedPartsIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        addPartsAssociatedPartsTable.getSortOrder().add(addPartsAssociatedPartsIdColumn);

        addProductPartsTable.sort();
        addPartsAssociatedPartsTable.sort();
    }

}
