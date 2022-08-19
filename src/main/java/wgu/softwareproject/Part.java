package wgu.softwareproject;

/**
 * This is the Part abstract superclass that is extended when In-house or Outsourced parts are initialized.
 */
public abstract class Part {
    private int partId;
    private String partName;
    private double partPrice;
    private int partStock;
    private int partMin;
    private int partMax;
    private static int partIdGenerator = 1;

    /**
     * Instantiates a new in-house part.
     * The constructor for the in-house part which calls the superclass part's constructor and adds a machine ID.
     * @param partName      The part's name
     * @param partPrice     The part's price
     * @param partStock     The part's stock amount
     * @param partMin       The part's minimum amount needed for inventory
     * @param partMax       The part's maximum amount needed for inventory
     */
    public Part(String partName, double partPrice, int partStock, int partMin, int partMax) {
        setPartId(partIdGenerator);
        setPartName(partName);
        setPartPrice(partPrice);
        setPartStock(partStock);
        setPartMin(partMin);
        setPartMax(partMax);
        partIdGenerator += 2;
    }

    /**
     * Gets the part ID.
     * The getter for the part ID field.
     * @return The integer part ID value for the part
     */
    public int getPartId() {
        return partId;
    }

    /**
     * Sets part id for the part.
     * The setter for the part ID field
     * @param partId The part ID
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }

    /**
     * Gets the part name.
     * The getter for the part name field.
     * @return The string part name value for the part
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets part name for the part.
     * The setter for the part name field.
     * @param partName The part name
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * Gets the part price.
     * The getter for the part price field.
     * @return The double part price value for the part
     */
    public double getPartPrice() {
        return partPrice;
    }

    /**
     * Sets part price.
     * The setter for the part price field
     * @param partPrice The part price
     */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    /**
     * Gets the part stock amount.
     * The getter for the part stock amount field.
     * @return The integer part stock amount value
     */
    public int getPartStock() {
        return partStock;
    }

    /**
     * Sets the part stock amount.
     * The setter for the part stock amount field
     * @param partStock The part ID
     */
    public void setPartStock(int partStock) {
        this.partStock = partStock;
    }

    /**
     * Gets the part minimum amount for inventory. 
     * The getter for the part minimum field.
     * @return The part minimum amount
     */
    public int getPartMin() {
        return partMin;
    }

    /**
     * Sets the part minimum amount for inventory. 
     * The setter for the part minimum field.
     * @param partMin The part's minimum amount needed for inventory
     */
    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }

    /**
     * Gets the part maximum amount for inventory. 
     * The getter for the part maximum field.
     * @return The part maximum amount
     */
    public int getPartMax() {
        return partMax;
    }

    /**
     * Sets the part maximum amount for inventory. 
     * The setter for the part maximum field.
     * @param partMax The part's maximum amount needed for inventory
     */
    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }
    
}