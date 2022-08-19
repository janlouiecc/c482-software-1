package wgu.softwareproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the product class to create product objects. 
 */
public class Product {

    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private int productMin;
    private int productMax;
    private static int productIdGenerator = 2;

    /**
     * Instantiates a new Product. 
     * The constructor for the product.
     * @param productName  the product name
     * @param productPrice the product price
     * @param productStock the product stock
     * @param productMin   the product min
     * @param productMax   the product max
     */
    public Product(String productName, double productPrice, int productStock, int productMin, int productMax) {
        setProductId(productIdGenerator);
        setProductName(productName);
        setProductPrice(productPrice);
        setProductStock(productStock);
        setProductMin(productMin);
        setProductMax(productMax);
        productIdGenerator += 2;
    }

    /**
     * Sets product id for the product.
     * The setter for the product ID field
     * @param productId The product ID
     */
    public void setProductId(int productId) {
        this.productId =  productId;
    }

    /**
     * Sets product name for the product.
     * The setter for the product name field.
     * @param productName The product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Sets product price.
     * The setter for the product price field
     * @param productPrice The product price
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Sets the product stock amount.
     * The setter for the product stock amount field
     * @param productStock The product ID
     */
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     * Sets the product minimum amount for inventory. 
     * The setter for the product minimum field.
     * @param productMin The product's minimum amount needed for inventory
     */
    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    /**
     * Sets the product maximum amount for inventory. 
     * The setter for the product maximum field.
     * @param productMax The product's maximum amount needed for inventory
     */
    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    /**
     * Gets the product ID.
     * The getter for the product ID field.
     * @return The integer product ID value for the product
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Gets the product name.
     * The getter for the product name field.
     * @return The string product name value for the product
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Gets the product price.
     * The getter for the product price field.
     * @return The double product price value for the product
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * Gets the product stock amount.
     * The getter for the product stock amount field.
     * @return The integer product stock amount value
     */
    public int getProductStock() {
        return productStock;
    }

    /**
     * Gets the product minimum amount for inventory. 
     * The getter for the product minimum field.
     * @return The product minimum amount
     */
    public int getProductMin() {
        return productMin;
    }

    /**
     * Gets the part maximum amount for inventory.
     * The getter for the part maximum field.
     * @return The part maximum amount
     */
    public int getProductMax() {
        return productMax;
    }

    /**
     * Adds associated part.
     * Adds a part object and associates it with the product object.
     * @param part The part to associate with the project
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Gets all associated parts.
     * The getter for the associated parts of a product object.
     * @return  All associated parts of the product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
