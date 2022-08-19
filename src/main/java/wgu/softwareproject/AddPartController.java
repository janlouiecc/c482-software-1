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

/**
 * This is the Add Part Controller class.
 * This class controls our Add Part form and contains the methods for the functionality of adding parts to the inventory.
 */
public class AddPartController {

    /**
     * The Part type.
     */
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

    /**
     * RUNTIME ERROR. Initially, a runtime error occurred due to input data for fields with an integer or double type and crashing as it was unable to parse the text.
     * Adding try and catch blocks to catch NumberFormatExceptions helped this run more smoothly.
     * This method saves the inputted data, adds a part to the inventory and exits back to the main form.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void save(ActionEvent event) throws IOException {
        if (partTypeLabel.getText().equals("Machine ID")) {
            try {
                if (Integer.parseInt(partMinTextField.getText()) > Integer.parseInt(partMaxTextField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Cannot add part.");
                    alert.setContentText("Please ensure that the minimum value is less than the maximum");
                    alert.showAndWait();
                    partMinTextField.clear();
                    partMaxTextField.clear();
                    return;
                } else if (!(Integer.parseInt(partMinTextField.getText()) <  Integer.parseInt(partInventoryTextField.getText())) ||
                        !(Integer.parseInt(partInventoryTextField.getText()) < Integer.parseInt(partMaxTextField.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Cannot add part.");
                    alert.setContentText("Please ensure that the inventory amount is sufficient.");
                    alert.showAndWait();
                    partInventoryTextField.clear();
                    partMinTextField.clear();
                    partMaxTextField.clear();
                    return;
                } else {
                    InHouse part = new InHouse(
                            partNameTextField.getText(),
                            Double.parseDouble(partPriceTextField.getText()),
                            Integer.parseInt(partInventoryTextField.getText()),
                            Integer.parseInt(partMinTextField.getText()),
                            Integer.parseInt(partMaxTextField.getText()),
                            Integer.parseInt(partTypeTextField.getText()));
                    Inventory.addPart(part);
                }
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
                if (Integer.parseInt(partMinTextField.getText()) > Integer.parseInt(partMaxTextField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Cannot add part.");
                    alert.setContentText("Please ensure that the minimum value is less than the maximum");
                    alert.showAndWait();
                    partMinTextField.clear();
                    partMaxTextField.clear();
                    return;
                } else if (!(Integer.parseInt(partMinTextField.getText()) <  Integer.parseInt(partInventoryTextField.getText())) ||
                        !(Integer.parseInt(partInventoryTextField.getText()) < Integer.parseInt(partMaxTextField.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Cannot add part.");
                    alert.setContentText("Please ensure that the inventory amount is sufficient.");
                    alert.showAndWait();
                    partInventoryTextField.clear();
                    partMinTextField.clear();
                    partMaxTextField.clear();
                    return;
                } else {
                    Outsourced part = new Outsourced(
                            partNameTextField.getText(),
                            Double.parseDouble(partPriceTextField.getText()),
                            Integer.parseInt(partInventoryTextField.getText()),
                            Integer.parseInt(partMinTextField.getText()),
                            Integer.parseInt(partMaxTextField.getText()),
                            partTypeTextField.getText());
                    Inventory.addPart(part);
                }
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

    /**
     * Cancels adding a new part.
     * This method cancels the option to add a new part to the inventory and exits back to the main form.
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
     * Gets the type of part.
     * This method obtains the information of which radio buttons are selected (In-House or Outsourced) to know what type of information needs to be associated with the new part.
     */
    public void getTypeOfPart() {
        if (inHousePart.isSelected()) {
            partTypeLabel.setText("Machine ID");
        } else if (outsourcedPart.isSelected()) {
            partTypeLabel.setText("Company Name");
        }
    }
}
