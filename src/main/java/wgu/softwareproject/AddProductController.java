package wgu.softwareproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<Part> addProductPartsTable;
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

    public void save(ActionEvent event) throws IOException {

        try {
            Product product = new Product(
                    productNameTextField.getText(),
                    Double.parseDouble(productPriceTextField.getText()),
                    Integer.parseInt(productInventoryTextField.getText()),
                    Integer.parseInt(productMinTextField.getText()),
                    Integer.parseInt(productMaxTextField.getText())
            );
            Inventory.addProduct(product);
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

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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

        addProductPartsTable.setItems(parts);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductPartsTable.setItems(Inventory.getAllParts());
        addProductPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        addProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        addProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        addProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
    }
}
