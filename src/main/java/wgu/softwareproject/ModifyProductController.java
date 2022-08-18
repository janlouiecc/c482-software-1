package wgu.softwareproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    public TableColumn<Part, Integer> modifyPartsAssociatedPartsIdColumn;
    public TableColumn<Part, String> modifyPartsAssociatedPartsNameColumn;
    public TableColumn<Part, Integer> modifyPartsAssociatedPartsInventoryColumn;
    public TableColumn<Part, Double> modifyPartsAssociatedPartsPriceColumn;
    @FXML
    private TableView<Part> modifyPartsAssociatedTable;
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

    public void save(ActionEvent event) throws IOException {
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

        modifyProductPartsTable.setItems(parts);
    }

    public void addAssociatedPartButton() {
        Part selectedPart = modifyProductPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            return;
        }

        modifyProductPartsTable.getItems().remove(selectedPart);
        modifyPartsAssociatedTable.getItems().add(selectedPart);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyProductPartsTable.setItems(Inventory.getAllParts());
        modifyProductPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        modifyProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        modifyProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        modifyPartsAssociatedPartsIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        modifyPartsAssociatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyPartsAssociatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        modifyPartsAssociatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
    }
}
