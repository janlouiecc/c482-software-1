package wgu.softwareproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This is the Modify Part Controller class.
 * This class controls our Modify Part form and contains the methods for the functionality of modifying parts in the inventory.
 */
public class ModifyPartController implements Initializable {

    /**
     * The Part type (in-house or outsourced).
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

    private Part partToModify;

    /**
     * RUNTIME ERROR. Initially, a runtime error occurred due to input data for fields with an integer or double type and crashing as it was unable to parse the text.
     * Adding try and catch blocks to catch NumberFormatExceptions helped this run more smoothly.
     * This method modifies the inputted data of a part in the inventory and exits back to the main form.
     * @param event The action event when the button this method is associated with is clicked.
     * @throws IOException Added to the method signature to handle java.io.IOException
     */
    public void save(ActionEvent event) throws IOException {
        try {
            if (Integer.parseInt(partMinTextField.getText()) > Integer.parseInt(partMaxTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot modify part.");
                alert.setContentText("Please ensure that the minimum value is less than the maximum");
                alert.showAndWait();
                partMinTextField.setText(String.valueOf(partToModify.getPartMin()));
                partMaxTextField.setText(String.valueOf(partToModify.getPartMax()));
                return;
            } else if (!(Integer.parseInt(partMinTextField.getText()) <  Integer.parseInt(partInventoryTextField.getText())) ||
                    !(Integer.parseInt(partInventoryTextField.getText()) < Integer.parseInt(partMaxTextField.getText()))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Cannot modify part.");
                alert.setContentText("Please ensure that the inventory amount is sufficient.");
                alert.showAndWait();
                partInventoryTextField.setText(String.valueOf(partToModify.getPartStock()));
                partMinTextField.setText(String.valueOf(partToModify.getPartMin()));
                partMaxTextField.setText(String.valueOf(partToModify.getPartMax()));
                return;
            } else {
                partToModify.setPartName(partNameTextField.getText());
                partToModify.setPartPrice(Double.parseDouble(partPriceTextField.getText()));
                partToModify.setPartStock(Integer.parseInt(partInventoryTextField.getText()));
                partToModify.setPartMin(Integer.parseInt(partMinTextField.getText()));
                partToModify.setPartMax(Integer.parseInt(partMaxTextField.getText()));

                if (partToModify instanceof Outsourced) {
                    ((Outsourced) partToModify).setCompanyName(partTypeTextField.getText());
                } else {
                    ((InHouse) partToModify).setMachineId(Integer.parseInt(partTypeTextField.getText()));
                }
            }
        } catch (NumberFormatException ignore) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot modify part.");
            alert.setContentText("Please ensure that the information is correct.");
            alert.showAndWait();
            partNameTextField.setText(partToModify.getPartName());
            partInventoryTextField.setText(String.valueOf(partToModify.getPartStock()));
            partPriceTextField.setText(String.valueOf(partToModify.getPartPrice()));
            partMinTextField.setText(String.valueOf(partToModify.getPartMin()));
            partMaxTextField.setText(String.valueOf(partToModify.getPartMax()));
            return;
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Cancels modifying a part.
     * This method cancels the option to modify a part in the inventory and exits back to the main form.
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

    /**
     * Initializes what is shown in the 'modify part' form.
     * This method overrides the initialize method in the Initializable interface and links the table view data with the inventory data for presentation.
     * @param url the URL
     * @param resourceBundle the Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partToModify = MainController.getPartToModify();

        if (partToModify instanceof Outsourced) {
            partType.selectToggle(outsourcedPart);
            getTypeOfPart();
            partTypeTextField.setText(((Outsourced) partToModify).getCompanyName());
        } else {
            partType.selectToggle(inHousePart);
            getTypeOfPart();
            partTypeTextField.setText(String.valueOf(((InHouse) partToModify).getMachineId()));
        }

        partNameTextField.setText(partToModify.getPartName());
        partInventoryTextField.setText(String.valueOf(partToModify.getPartStock()));
        partPriceTextField.setText(String.valueOf(partToModify.getPartPrice()));
        partMinTextField.setText(String.valueOf(partToModify.getPartMin()));
        partMaxTextField.setText(String.valueOf(partToModify.getPartMax()));

    }
}
