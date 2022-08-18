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

public class ModifyPartController implements Initializable {

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

    private static Part partToModify = null;

    public void save(ActionEvent event) throws IOException {
        try {
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
        } catch (NumberFormatException ignored) {
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
