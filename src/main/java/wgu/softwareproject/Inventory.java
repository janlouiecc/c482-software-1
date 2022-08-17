package wgu.softwareproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        for (Part allPart : allParts) {
            if (partId == allPart.getId()) {
                return allPart;
            }
        }
        return null;
    }

    public Part lookupPart(String partName) {
        for (Part allPart : allParts) {
            if (Objects.equals(partName, allPart.getName())) {
                return allPart;
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        for (Product allProduct : allProducts) {
            if (productId == allProduct.getProductId()) {
                return allProduct;
            }
        }
        return null;
    }

    public Product lookupProduct(String productName) {
        for (Product allProduct : allProducts) {
            if (Objects.equals(productName, allProduct.getProductName())) {
                return allProduct;
            }
        }
        return null;
    }

    public void updatePart(int index, Part selectedPart) {

    }

    public void updateProduct(int index, Part selectedProduct) {

    }

    public boolean deletePart(Part selectedPart) {
        return true;
    }

    public boolean deleteProduct(Part selectedProduct) {
        return true;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
