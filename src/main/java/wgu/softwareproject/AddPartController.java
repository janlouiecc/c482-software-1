package wgu.softwareproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddPartController {

    @FXML
    public ToggleGroup partType;
    @FXML
    private TextField partNameTextField;
    @FXML
    private TextField partInventoryTextField;
    @FXML
    private TextField partPriceTextField;
    @FXML
    private TextField partMaxTextField;
    @FXML
    private TextField partMinTextField;
    @FXML
    private TextField partTypeTextField;
    @FXML
    private Label partTypeLabel;
    @FXML
    private RadioButton inHousePart, outsourcedPart;

    public void save(ActionEvent event) throws IOException {
        if (partTypeLabel.getText().equals("Machine ID")) {
            try {
                InHouse part = new InHouse(
                        partNameTextField.getText(),
                        Double.parseDouble(partPriceTextField.getText()),
                        Integer.parseInt(partInventoryTextField.getText()),
                        Integer.parseInt(partMinTextField.getText()),
                        Integer.parseInt(partMaxTextField.getText()),
                        Integer.parseInt(partTypeTextField.getText()));
                Inventory.addPart(part);
            } catch (NumberFormatException ignored) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot add part.");
                alert.setContentText("Please ensure that the information is correct.");
                alert.showAndWait();
                partNameTextField.clear();
                partPriceTextField.clear();
                partInventoryTextField.clear();
                partMinTextField.clear();
                partMaxTextField.clear();
                partTypeTextField.clear();
                return;
            }
        } else if (partTypeLabel.getText().equals("Company Name")) {
            try {
                Outsourced part = new Outsourced(
                        partNameTextField.getText(),
                        Double.parseDouble(partPriceTextField.getText()),
                        Integer.parseInt(partInventoryTextField.getText()),
                        Integer.parseInt(partMinTextField.getText()),
                        Integer.parseInt(partMaxTextField.getText()),
                        partTypeTextField.getText());
                Inventory.addPart(part);
            } catch (NumberFormatException ignored) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot add part.");
                alert.setContentText("Please ensure that the information is correct.");
                alert.showAndWait();
                partNameTextField.clear();
                partPriceTextField.clear();
                partInventoryTextField.clear();
                partMinTextField.clear();
                partMaxTextField.clear();
                partTypeTextField.clear();
                return;
            }
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

    public void getTypeOfPart() {
        if (inHousePart.isSelected()) {
            partTypeLabel.setText("Machine ID");
        } else if (outsourcedPart.isSelected()) {
            partTypeLabel.setText("Company Name");
        }
    }
}
