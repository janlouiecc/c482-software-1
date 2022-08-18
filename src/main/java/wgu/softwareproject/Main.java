package wgu.softwareproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This is the Main class.
 * The Main class extends the Application abstract class for the Inventory Management System.
 * @author janlouiecc
 */
public class Main extends Application {

    /**
     * Starts our JavaFX application.
     * This method is the entry point for the JavaFX application.
     * @param stage This parameter is the top level JavaFX container that contains our scenes.
     */
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
        Outsourced mouse = new Outsourced("Mouse", 10.00, 50, 1, 150, "Target");
        Inventory.addPart(mouse);

        InHouse monitor = new InHouse("Monitor", 100.00, 40, 1, 200, 1000);
        Inventory.addPart(monitor);

        Outsourced keyboard = new Outsourced("Keyboard", 15.00, 50, 1, 150, "Walmart");
        Inventory.addPart(keyboard);

        InHouse processor = new InHouse("Processor", 1000.00, 100, 1, 200, 1001);
        Inventory.addPart(processor);

        InHouse tower = new InHouse("Tower Case", 500.00, 100, 1, 200, 1002);
        Inventory.addPart(tower);

        Product gamingLaptop = new Product("Gaming Laptop", 2000.00, 80, 1, 200);
        Inventory.addProduct(gamingLaptop);
        gamingLaptop.addAssociatedPart(keyboard);

        Product businessLaptop = new Product("Business Laptop", 1000.00, 80, 1, 200);
        Inventory.addProduct(businessLaptop);
        businessLaptop.addAssociatedPart(processor);

        Product gamingDesktop = new Product("Gaming Desktop", 2000.00, 80, 1, 200);
        Inventory.addProduct(gamingDesktop);
        gamingDesktop.addAssociatedPart(monitor);
        gamingDesktop.addAssociatedPart(mouse);
        gamingDesktop.addAssociatedPart(tower);

        Product businessDesktop = new Product("Business Desktop", 1000.00, 80, 1, 200);
        Inventory.addProduct(businessDesktop);
    }

    /**
     * This is the main method.
     * This method is the entry point for our program that launches the JavaFX application.
     * @param args This parameter is the arguments for our entry point into the program.
     */
    public static void main(String[] args) {
        addTestData();
        launch(args);
    }

}