/*
 * Class: CMSC203 CRN 21751
 * Instructor: Gary Thai
 * Description: ManagementCompany represents a company that manages rental
 *              properties. It holds a fixed-size array of Property objects
 *              (aggregation), adds properties, calculates the total rent and
 *              has a copy constructor that makes a deep copy of the array.
 * Due: 11/08/2026
 * Platform/compiler: Visual Studio Code, Java (JDK) - javac
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Santiago Piguillem
 */

/**
 * A company that manages up to {@value #MAX_PROPERTIES} properties.
 * ManagementCompany "has-a" Property[] (aggregation).
 *
 * @author Santiago Piguillem
 */
public class ManagementCompany {

    /** Maximum number of properties that the company can manage. */
    public static final int MAX_PROPERTIES = 5;

    /** Name of the company. */
    private String name;

    /** Tax ID of the company. */
    private String taxId;

    /** Fixed-size array that holds the properties of the company. */
    private Property[] properties;

    /** Number of properties currently stored in the array. */
    private int propertyCount;

    /**
     * Default constructor. Creates a company with an empty name, an empty tax
     * ID and no properties.
     */
    public ManagementCompany() {
        name = "";
        taxId = "";
        properties = new Property[MAX_PROPERTIES];
        propertyCount = 0;
    }

    /**
     * Parameterized constructor. Creates a company with the given name and tax
     * ID and no properties.
     *
     * @param name  the name of the company
     * @param taxId the tax ID of the company
     */
    public ManagementCompany(String name, String taxId) {
        this.name = name;
        this.taxId = taxId;
        properties = new Property[MAX_PROPERTIES];
        propertyCount = 0;
    }

    /**
     * Copy constructor. Creates a new company with the same name, tax ID and
     * property count as the given company. It makes a DEEP copy: a new array is
     * created and every property is copied with the Property copy constructor,
     * so the two companies do not share any Property object.
     *
     * @param other the company to copy
     */
    public ManagementCompany(ManagementCompany other) {
        this.name = other.name;
        this.taxId = other.taxId;
        this.properties = new Property[other.properties.length];
        for (int i = 0; i < other.propertyCount; i++) {
            this.properties[i] = new Property(other.properties[i]);
        }
        this.propertyCount = other.propertyCount;
    }

    /**
     * Adds a copy of the given property to the next free position of the array.
     * The company stores its own copy (made with the Property copy
     * constructor), so later changes to the object passed in do not affect the
     * company.
     *
     * @param p the property to add
     * @return the index where the property was stored, or -1 if the array is
     *         full or the property is null
     */
    public int addProperty(Property p) {
        if (p == null || propertyCount >= properties.length) {
            return -1;
        }
        properties[propertyCount] = new Property(p);
        propertyCount++;
        return propertyCount - 1;
    }

    /**
     * Calculates the total monthly rent of all the properties in the company.
     *
     * @return the sum of the rent of every stored property (0.0 if none)
     */
    public double totalRent() {
        double sum = 0;
        for (int i = 0; i < propertyCount; i++) {
            sum += properties[i].getRentAmount();
        }
        return sum;
    }

    /**
     * Returns the number of properties currently stored in the company.
     *
     * @return the property count
     */
    public int getPropertyCount() {
        return propertyCount;
    }

    /**
     * Returns the name of the company.
     *
     * @return the company name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the tax ID of the company.
     *
     * @return the tax ID
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Returns the property stored at the given index (the object stored in the
     * array, not a copy).
     *
     * @param index the position in the array (0 to propertyCount - 1)
     * @return the property at that index, or null if the index is not valid
     */
    public Property getProperty(int index) {
        if (index < 0 || index >= propertyCount) {
            return null;
        }
        return properties[index];
    }

    /**
     * Returns a summary of the company: its name, tax ID, every property with
     * its index, and the total rent.
     *
     * @return a multi-line text description of the company
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Management Company: %s (Tax ID: %s)%n", name, taxId));
        text.append("Properties:").append(System.lineSeparator());
        if (propertyCount == 0) {
            text.append("(no properties yet)").append(System.lineSeparator());
        }
        for (int i = 0; i < propertyCount; i++) {
            text.append(String.format("%d: %s%n", i, properties[i]));
        }
        text.append(String.format("Total Rent: $%.2f", totalRent()));
        return text.toString();
    }
}
