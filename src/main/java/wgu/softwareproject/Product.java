package wgu.softwareproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private int productMin;
    private int productMax;
    private int associatedPartsCount;
    private static int productIdGenerator = 2;

    public Product(String productName, double productPrice, int productStock, int productMin, int productMax) {
        setProductId(productIdGenerator);
        setProductName(productName);
        setProductPrice(productPrice);
        setProductStock(productStock);
        setProductMin(productMin);
        setProductMax(productMax);
        setAssociatedPartsCount(0);
        productIdGenerator += 2;
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
        associatedPartsCount++;
    }

    public void deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public int getAssociatedPartsCount() {
        return associatedPartsCount;
    }

    public void setAssociatedPartsCount(int associatedPartsCount) {
        this.associatedPartsCount = associatedPartsCount;
    }
}
