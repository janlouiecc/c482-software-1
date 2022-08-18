package wgu.softwareproject;

/**
 * This is the Part abstract class that is extended when In-house or Outsourced parts are initialized.
 * @author janlouiecc
 */

public abstract class Part {
    private int partId;
    private String partName;
    private double partPrice;
    private int partStock;
    private int partMin;
    private int partMax;
    private static int partIdGenerator = 1;

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
     * @return the id
     */
    public int getPartId() {
        return partId;
    }

    /**
     * @param partId the id to set
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }

    /**
     * @return the name
     */
    public String getPartName() {
        return partName;
    }

    /**
     * @param partName the name to set
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * @return the price
     */
    public double getPartPrice() {
        return partPrice;
    }

    /**
     * @param partPrice the price to set
     */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }
    
    /**
     * @return the stock
     */
    public int getPartStock() {
        return partStock;
    }

    /**
     * @param partStock the stock to set
     */
    public void setPartStock(int partStock) {
        this.partStock = partStock;
    }

    /**
     * @return the min
     */
    public int getPartMin() {
        return partMin;
    }

    /**
     * @param partMin the min to set
     */
    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }

    /**
     * @return the max
     */
    public int getPartMax() {
        return partMax;
    }

    /**
     * @param partMax the max to set
     */
    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }
    
}