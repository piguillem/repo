/*
 * Class: CMSC203 CRN 21751
 * Instructor: Gary Thai
 * Description: JUnit 5 tests for the Property class: default constructor,
 *              parameterized constructor, copy constructor, setters and
 *              toString.
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for {@link Property}.
 *
 * @author Santiago Piguillem
 */
public class PropertyJUnitTest {

    /** Property used by the tests; created again before every test. */
    private Property property;

    /**
     * Creates a fresh property before each test so the tests are independent.
     */
    @BeforeEach
    public void setUp() {
        property = new Property("Sunset Villa", "Rockville", 1500.00, "Alice Smith");
    }

    /**
     * The default constructor must create empty text fields and a rent of 0.
     */
    @Test
    public void testDefaultConstructor() {
        Property empty = new Property();
        assertEquals("", empty.getPropertyName());
        assertEquals("", empty.getCity());
        assertEquals(0.00, empty.getRentAmount(), 0.001);
        assertEquals("", empty.getOwner());
    }

    /**
     * The parameterized constructor must store every value that it receives.
     */
    @Test
    public void testParameterizedConstructor() {
        assertEquals("Sunset Villa", property.getPropertyName());
        assertEquals("Rockville", property.getCity());
        assertEquals(1500.00, property.getRentAmount(), 0.001);
        assertEquals("Alice Smith", property.getOwner());
    }

    /**
     * The copy constructor must create a different object with the same values.
     */
    @Test
    public void testCopyConstructorCopiesValues() {
        Property copy = new Property(property);
        assertNotSame(property, copy);
        assertEquals(property.getPropertyName(), copy.getPropertyName());
        assertEquals(property.getCity(), copy.getCity());
        assertEquals(property.getRentAmount(), copy.getRentAmount(), 0.001);
        assertEquals(property.getOwner(), copy.getOwner());
    }

    /**
     * Changing the copy must not change the original property.
     */
    @Test
    public void testCopyConstructorIsIndependent() {
        Property copy = new Property(property);
        copy.setPropertyName("Changed");
        copy.setCity("Bethesda");
        copy.setRentAmount(99.99);
        copy.setOwner("Bob");

        assertEquals("Sunset Villa", property.getPropertyName());
        assertEquals("Rockville", property.getCity());
        assertEquals(1500.00, property.getRentAmount(), 0.001);
        assertEquals("Alice Smith", property.getOwner());
    }

    /**
     * Every setter must change the value returned by its getter.
     */
    @Test
    public void testSetters() {
        property.setPropertyName("Lake House");
        property.setCity("Gaithersburg");
        property.setRentAmount(2750.50);
        property.setOwner("Carol");

        assertEquals("Lake House", property.getPropertyName());
        assertEquals("Gaithersburg", property.getCity());
        assertEquals(2750.50, property.getRentAmount(), 0.001);
        assertEquals("Carol", property.getOwner());
    }

    /**
     * toString must return the one-line format used by the application.
     */
    @Test
    public void testToString() {
        String expected = "Sunset Villa, Rockville, Rent: $1500.00, Owner: Alice Smith";
        assertEquals(expected, property.toString());
    }
}
