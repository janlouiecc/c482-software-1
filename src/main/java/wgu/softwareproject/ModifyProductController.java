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
 * This is the Modify Product Controller class.
 * This class controls our Modify Product form and contains the methods for the functionality of modifying products in the inventory.
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TableColumn<Part, Integer> modifyPartsAssociatedPartsIdColumn;
    @FXML
    private TableColumn<Part, String> modifyPartsAssociatedPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> modifyPartsAssociatedPartsInventoryColumn;
    @FXML
    private TableColumn<Part, Double> modifyPartsAssociatedPartsPriceColumn;
    @FXML
    private TableView<Part> modifyPartsAssociatedPartsTable;
    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<Part> modifyProductPartsTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductPartIdColumn;
    @FXML
    private TableColumn<Part, String> modifyProductPartNameColumn;
    @FXML
    private TableColumn<Part, Double> modifyProductPartPriceColumn;
    @FXML
    private TableColumn<Part, Integer> modifyProductPartInventoryColumn;
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

    private Product productToModify;

    /**
     * RUNTIME ERROR. Initially, a runtime error occurred due to input data for fields with an integer or double type and crashing as it was unable to parse the text.
     * Adding try and catch blocks to catch NumberFormatExceptions helped this run more smoothly.
     * This method modifies the inputted data of a product in the inventory and exits back to the main form.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void save(ActionEvent event) throws IOException {

        try {
            if (Integer.parseInt(productMinTextField.getText()) > Integer.parseInt(productMaxTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot modify product.");
                alert.setContentText("Please ensure that the minimum value is less than the maximum");
                alert.showAndWait();
                productMinTextField.clear();
                productMaxTextField.clear();
                return;
            } else if (!(Integer.parseInt(productMinTextField.getText()) <  Integer.parseInt(productInventoryTextField.getText())) ||
                    !(Integer.parseInt(productInventoryTextField.getText()) < Integer.parseInt(productMaxTextField.getText()))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot modify product.");
                alert.setContentText("Please ensure that the inventory amount is sufficient.");
                alert.showAndWait();
                productInventoryTextField.clear();
                productMinTextField.clear();
                productMaxTextField.clear();
                return;
            } else {
                productToModify.setProductName(productNameTextField.getText());
                productToModify.setProductPrice(Double.parseDouble(productPriceTextField.getText()));
                productToModify.setProductStock(Integer.parseInt(productInventoryTextField.getText()));
                productToModify.setProductMin(Integer.parseInt(productMinTextField.getText()));
                productToModify.setProductMax(Integer.parseInt(productMaxTextField.getText()));
            }
        } catch (NumberFormatException ignore) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot modify product.");
            alert.setContentText("Please ensure that the information is correct.");
            alert.showAndWait();
            productNameTextField.setText(productToModify.getProductName());
            productInventoryTextField.setText(String.valueOf(productToModify.getProductStock()));
            productPriceTextField.setText(String.valueOf(productToModify.getProductPrice()));
            productMinTextField.setText(String.valueOf(productToModify.getProductMin()));
            productMaxTextField.setText(String.valueOf(productToModify.getProductMax()));
            return;
        }

        productToModify.getAllAssociatedParts().clear();
        for (Part part : modifyPartsAssociatedPartsTable.getItems()) {
            productToModify.addAssociatedPart(part);
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Cancels modifying a product.
     * This method cancels the option to modify a product in the inventory and exits back to the main form.
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

        modifyProductPartsTable.setItems(parts);
    }

    /**
     * Add associated part button.
     * This method adds an associated part to the product.
     */
    public void addAssociatedPartButton() {
        Part selectedPart = modifyProductPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No item selected.");
            alert.setContentText("Please select a part to add and associate to this product.");
            alert.showAndWait();
            return;
        }

        modifyPartsAssociatedPartsTable.getItems().add(selectedPart);
    }

    /**
     * Remove associated part button.
     * This method removes an associated part to the product.
     */
    public void removeAssociatedPartButton() {
        Part selectedPart = modifyPartsAssociatedPartsTable.getSelectionModel().getSelectedItem();

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
            modifyPartsAssociatedPartsTable.getItems().remove(selectedPart);
        }
    }

    /**
     * Initializes what is shown in the 'modify product' form.
     * This method overrides the initialize method in the Initializable interface and links the table view data with the inventory data for presentation.
     * @param url the URL
     * @param resourceBundle the Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productToModify = MainController.getProductToModify();
        ObservableList<Part> associatedParts = productToModify.getAllAssociatedParts();

        modifyProductPartsTable.setItems(Inventory.getAllParts());
        modifyProductPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        modifyProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        modifyProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        modifyProductPartIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        modifyProductPartsTable.getSortOrder().add(modifyProductPartIdColumn);
        modifyProductPartsTable.sort();

        modifyPartsAssociatedPartsIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        modifyPartsAssociatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyPartsAssociatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        modifyPartsAssociatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        modifyPartsAssociatedPartsIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        modifyPartsAssociatedPartsTable.getSortOrder().add(modifyPartsAssociatedPartsIdColumn);
        modifyPartsAssociatedPartsTable.sort();

        productNameTextField.setText(productToModify.getProductName());
        productInventoryTextField.setText(String.valueOf(productToModify.getProductStock()));
        productPriceTextField.setText(String.valueOf(productToModify.getProductPrice()));
        productMinTextField.setText(String.valueOf(productToModify.getProductMin()));
        productMaxTextField.setText(String.valueOf(productToModify.getProductMax()));

        for (Part part : associatedParts) {
            modifyPartsAssociatedPartsTable.getItems().add(part);
        }

        modifyProductPartIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        modifyProductPartsTable.getSortOrder().add(modifyProductPartIdColumn);

        modifyPartsAssociatedPartsIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        modifyPartsAssociatedPartsTable.getSortOrder().add(modifyPartsAssociatedPartsIdColumn);

        modifyProductPartsTable.sort();
        modifyPartsAssociatedPartsTable.sort();
    }
}
