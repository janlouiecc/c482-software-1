package inv_mgmt_sys;

/**
 * The InHouse subclass to the superclass of Part.
 */
public class InHouse extends Part{

    private int machineId;

    /**
     * Instantiates a new in-house part.
     * The constructor for the in-house part which calls the superclass part's constructor and adds a machine ID.
     * @param name      The part's name
     * @param price     The part's price
     * @param stock     The part's stock amount
     * @param min       The part's minimum amount needed for inventory
     * @param max       The part's maximum amount needed for inventory
     * @param machineId The machine ID for the in-house part
     */
    public InHouse(String name, double price, int stock, int min, int max, int machineId) {
        super(name, price, stock, min, max);
        setMachineId(machineId);
    }

    /**
     * Gets machine id for in-house part.
     * The getter for the machine ID field.
     * @return The integer machine ID value for the part
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets machine id for in-house part.
     * The setter for the machine ID field
     * @param machineId The machine ID for the in-house part
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
