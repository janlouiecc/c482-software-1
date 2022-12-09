package inv_mgmt_sys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class that encompasses both parts and products.
 */
public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds part to the inventory.
     * This method takes the input from the user and creates a part to add to the inventory.
     * @param newPart the new part
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds product to the inventory.
     * This method takes the input from the user and creates a product to add to the inventory.
     * @param newProduct the new product to add
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Lookup part by part ID. 
     * This method looks up the part by its exact part ID and returns the result.
     * @param partId the partial name being inputted by user
     * @return the part that matches the ID
     */
    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = getAllParts();
        for (Part part : allParts) {
            if (partId == part.getPartId()) {
                return part;
            }
        }
        return null;
    }

    /**
     * Lookup part by name/partial name. 
     * This method looks up the part by a partial string input from the user and returns the results that match.
     * @param partialName the partial name being inputted by user
     * @return the observable list of parts
     */
    public static ObservableList<Part> lookupPart(String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();

        for (Part part : allParts) {
            if(part.getPartName().contains(partialName)) {
                namedParts.add(part);
            }
        }

        return namedParts;
    }

    /**
     * Lookup product by product ID. 
     * This method looks up the product by its exact product ID and returns the result.
     * @param productId the partial name being inputted by user
     * @return the product that matches the ID
     */
    public static Product lookupProduct(int productId) {
        ObservableList<Product> allProducts = getAllProducts();
        for (Product product : allProducts) {
            if (productId == product.getProductId()) {
                return product;
            }
        }
        return null;
    }

    /**
     * Lookup product by name/partial name. 
     * This method looks up the product by a partial string input from the user and returns the results that match.
     * @param partialName the partial name being inputted by user
     * @return the observable list of products
     */
    public static ObservableList<Product> lookupProduct(String partialName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = getAllProducts();

        for (Product Product : allProducts) {
            if(Product.getProductName().contains(partialName)) {
                namedProducts.add(Product);
            }
        }

        return namedProducts;
    }

    /**
     * Confirms deletion of product.
     * A method that returns a boolean to confirm if the part has been deleted from the inventory.
     * @param selectedPart the selected part
     * @return A boolean value
     */
    public static boolean deletePart(Part selectedPart) {
        return !allParts.contains(selectedPart);
    }

    /**
     * Confirms deletion of product.
     *A method that returns a boolean to confirm if the product has been deleted from the inventory.
     * @param selectedProduct the selected product
     * @return A boolean value
     */
    public static boolean deleteProduct(Product selectedProduct) { return !allProducts.contains(selectedProduct);}

    /**
     * Gets all parts. 
     * The getter for all the parts in the inventory.
     * @return All parts in the inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets all products. 
     * The getter for all the products in the inventory.
     * @return All products in the inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
