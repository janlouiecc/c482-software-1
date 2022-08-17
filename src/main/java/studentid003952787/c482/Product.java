package studentid003952787.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private static final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private int productMin;
    private int productMax;

    public Product(int productId, String productName, double productPrice, int productStock, int productMin, int productMax) {
        setProductId(productId);
        setProductName(productName);
        setProductPrice(productPrice);
        setProductStock(productStock);
        setProductMin(productMin);
        setProductMax(productMax);
    }

    public void setProductId(int productId) {
        this.productId =  productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public int getProductMin() {
        return productMin;
    }

    public int getProductMax() {
        return productMax;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
    }

    public static ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
