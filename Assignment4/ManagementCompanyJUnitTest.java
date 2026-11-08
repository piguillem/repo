/*
 * Class: CMSC203 CRN 21751
 * Instructor: Gary Thai
 * Description: JUnit 5 tests for the ManagementCompany class: adding
 *              properties, total rent, full array, copy of the property that
 *              is added, deep copy constructor and toString.
 * Due: 11/08/2026
 * Platform/compiler: Visual Studio Code, Java (JDK) - javac, JUnit 5
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Santiago Piguillem
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for {@link ManagementCompany}.
 *
 * @author Santiago Piguillem
 */
public class ManagementCompanyJUnitTest {

    /** Company used by the tests; created again before every test. */
    private ManagementCompany company;

    /**
     * Creates a fresh, empty company before each test.
     */
    @BeforeEach
    public void setUp() {
        company = new ManagementCompany("Campus Realty", "123-45-6789");
    }

    /**
     * A new company must have its name and tax ID, no properties and a total
     * rent of 0.
     */
    @Test
    public void testNewCompanyIsEmpty() {
        assertEquals("Campus Realty", company.getName());
        assertEquals("123-45-6789", company.getTaxId());
        assertEquals(0, company.getPropertyCount());
        assertEquals(0.0, company.totalRent(), 0.001);
        assertNull(company.getProperty(0));
    }

    /**
     * addProperty must return the index where the property was stored and
     * increase the property count.
     */
    @Test
    public void testAddPropertyReturnsIndexAndCount() {
        int index0 = company.addProperty(new Property("Property-1", "Rockville", 3500.0, "Joe"));
        int index1 = company.addProperty(new Property("Property-2", "Gaithersburg", 2500.0, "Mary"));

        assertEquals(0, index0);
        assertEquals(1, index1);
        assertEquals(2, company.getPropertyCount());
        assertEquals("Property-2", company.getProperty(1).getPropertyName());
    }

    /**
     * totalRent must be the sum of the rent of all the properties.
     */
    @Test
    public void testTotalRent() {
        company.addProperty(new Property("Property-1", "Rockville", 3500.0, "Joe"));
        company.addProperty(new Property("Property-2", "Gaithersburg", 2500.0, "Mary"));

        assertEquals(6000.0, company.totalRent(), 0.001);
    }

    /**
     * When the array is full, addProperty must return -1 and must not change
     * the property count.
     */
    @Test
    public void testAddPropertyWhenFull() {
        for (int i = 0; i < ManagementCompany.MAX_PROPERTIES; i++) {
            company.addProperty(new Property("P" + i, "City", 1000.0 + i, "Owner"));
        }

        int result = company.addProperty(new Property("Extra", "City", 2000.0, "OwnerX"));

        assertEquals(-1, result);
        assertEquals(ManagementCompany.MAX_PROPERTIES, company.getPropertyCount());
    }

    /**
     * The company must store its own copy of the property, so changing the
     * object that was passed to addProperty does not change the company.
     */
    @Test
    public void testAddPropertyStoresACopy() {
        Property p = new Property("Property-1", "Rockville", 3500.0, "Joe");
        company.addProperty(p);

        p.setRentAmount(1.0);

        assertNotSame(p, company.getProperty(0));
        assertEquals(3500.0, company.totalRent(), 0.001);
    }

    /**
     * The copy constructor must copy the name, tax ID, count and total rent,
     * and must create new Property objects (deep copy).
     */
    @Test
    public void testCopyConstructorCopiesData() {
        company.addProperty(new Property("Apt 1", "Rockville", 1200.0, "Bob"));
        company.addProperty(new Property("Apt 2", "Rockville", 1500.0, "Carol"));

        ManagementCompany copy = new ManagementCompany(company);

        assertNotSame(company, copy);
        assertEquals(company.getName(), copy.getName());
        assertEquals(company.getTaxId(), copy.getTaxId());
        assertEquals(company.getPropertyCount(), copy.getPropertyCount());
        assertEquals(company.totalRent(), copy.totalRent(), 0.001);
        assertNotSame(company.getProperty(0), copy.getProperty(0));
        assertNotSame(company.getProperty(1), copy.getProperty(1));
    }

    /**
     * Changing a property of the copy, or adding a property to the original,
     * must not affect the other company (proof of the deep copy).
     */
    @Test
    public void testCopyConstructorIsDeepCopy() {
        company.addProperty(new Property("Apt 1", "Rockville", 1200.0, "Bob"));
        company.addProperty(new Property("Apt 2", "Rockville", 1500.0, "Carol"));

        ManagementCompany copy = new ManagementCompany(company);
        copy.getProperty(0).setRentAmount(0.0);
        company.addProperty(new Property("Apt 3", "Bethesda", 1000.0, "Dan"));

        assertEquals(1200.0, company.getProperty(0).getRentAmount(), 0.001);
        assertEquals(3700.0, company.totalRent(), 0.001);
        assertEquals(1500.0, copy.totalRent(), 0.001);
        assertEquals(2, copy.getPropertyCount());
    }

    /**
     * toString must show the company, every property with its index, and the
     * total rent.
     */
    @Test
    public void testToString() {
        company.addProperty(new Property("Property-1", "Rockville", 3500.0, "Joe"));
        company.addProperty(new Property("Property-2", "Gaithersburg", 2500.0, "Mary"));

        String text = company.toString();

        assertTrue(text.contains("Management Company: Campus Realty (Tax ID: 123-45-6789)"));
        assertTrue(text.contains("0: Property-1, Rockville, Rent: $3500.00, Owner: Joe"));
        assertTrue(text.contains("1: Property-2, Gaithersburg, Rent: $2500.00, Owner: Mary"));
        assertTrue(text.contains("Total Rent: $6000.00"));
    }
}
