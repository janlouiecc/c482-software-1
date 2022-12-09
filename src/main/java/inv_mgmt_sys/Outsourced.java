package inv_mgmt_sys;

/**
 * The Outsourced subclass to the superclass of Part.
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     * Instantiates a new outsourced part.
     * The constructor for the outsourced part which calls the superclass part's constructor and adds a machine ID.
     * @param name        The part's name
     * @param price       The part's price
     * @param stock       The part's stock amount
     * @param min         The part's minimum amount needed for inventory
     * @param max         The part's maximum amount needed for inventory
     * @param companyName The machine ID for the in-house part
     */
    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name, price, stock, min, max);
        setCompanyName(companyName);
    }

    /**
     * Gets company name for outsourced part.
     * The getter for the company name field.
     * @return The string company name value for the part
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets company name for outsourced part.
     * The setter for the company name field.
     * @param companyName The company name for the outsourced part.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
