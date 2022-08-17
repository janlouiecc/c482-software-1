package wgu.softwareproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (partId == part.getId()) {
                return part;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();

        for (Part part : allParts) {
            if(part.getName().contains(partialName)) {
                namedParts.add(part);
            }
        }

        return namedParts;
    }

    public static Product lookupProduct(int productId) {
        for (Product allProduct : allProducts) {
            if (productId == allProduct.getProductId()) {
                return allProduct;
            }
        }
        return null;
    }

    public static Product lookupProduct(String productName) {
        for (Product allProduct : allProducts) {
            if (Objects.equals(productName, allProduct.getProductName())) {
                return allProduct;
            }
        }
        return null;
    }

//    public static void updatePart(int index, Part selectedPart) {
//
//    }
//
//    public static void updateProduct(int index, Part selectedProduct) {
//
//    }
//
//    public static boolean deletePart(Part selectedPart) {
//        return true;
//    }
//
//    public static boolean deleteProduct(Part selectedProduct) {
//        return true;
//    }
//
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
