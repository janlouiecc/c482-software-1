package wgu.softwareproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    private static void addTestData() {
        Outsourced o = new Outsourced(1, "Mouse", 10.00, 50, 1, 150, "Target");
        Inventory.addPart(o);

        InHouse i = new InHouse(2, "Monitor", 100.00, 40, 1, 200, 1000);
        Inventory.addPart(i);

        Product product = new Product(1, "Laptop", 1000.00, 80, 1, 200);
        Inventory.addProduct(product);
    }

    public static void main(String[] args) {
        addTestData();
        launch(args);
    }
}