/*
 * Class: CMSC203 CRN 21751
 * Instructor: Gary Thai
 * Description: Property represents a single rental property. It stores the
 *              property name, the city, the monthly rent and the owner. It
 *              provides a default constructor, a parameterized constructor,
 *              a copy constructor, getters, setters and a toString method.
 * Due: 11/08/2026
 * Platform/compiler: Visual Studio Code, Java (JDK) - javac
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Santiago Piguillem
 */

/**
 * A single rental property managed by a {@link ManagementCompany}.
 *
 * @author Santiago Piguillem
 */
public class Property {

    /** Name of the property (for example "Property-1"). */
    private String propertyName;

    /** City where the property is located. */
    private String city;

    /** Monthly rent of the property in dollars. */
    private double rentAmount;

    /** Name of the owner of the property. */
    private String owner;

    /**
     * Default constructor. Creates a property with empty text fields and a
     * rent of 0.00.
     */
    public Property() {
        propertyName = "";
        city = "";
        rentAmount = 0.00;
        owner = "";
    }

    /**
     * Parameterized constructor. Creates a property with the given values.
     *
     * @param propertyName the name of the property
     * @param city         the city where the property is located
     * @param rentAmount   the monthly rent
     * @param owner        the name of the owner
     */
    public Property(String propertyName, String city, double rentAmount, String owner) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
    }

    /**
     * Copy constructor. Creates a new, independent property that has the same
     * field values as the given property. Changing the new object does not
     * change the original one.
     *
     * @param other the property to copy
     */
    public Property(Property other) {
        this.propertyName = other.propertyName;
        this.city = other.city;
        this.rentAmount = other.rentAmount;
        this.owner = other.owner;
    }

    /**
     * Sets the name of the property.
     *
     * @param propertyName the new property name
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * Sets the city of the property.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the monthly rent of the property.
     *
     * @param rentAmount the new monthly rent
     */
    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    /**
     * Sets the owner of the property.
     *
     * @param owner the new owner name
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Returns the name of the property.
     *
     * @return the property name
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns the city of the property.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the monthly rent of the property.
     *
     * @return the monthly rent
     */
    public double getRentAmount() {
        return rentAmount;
    }

    /**
     * Returns the owner of the property.
     *
     * @return the owner name
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the property as one line of text, for example:
     * "Property-1, Rockville, Rent: $3500.00, Owner: Joe".
     *
     * @return a text description of the property
     */
    @Override
    public String toString() {
        return String.format("%s, %s, Rent: $%.2f, Owner: %s",
                propertyName, city, rentAmount, owner);
    }
}
