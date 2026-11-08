/*
 * Class: CMSC203 CRN 21751
 * Instructor: Gary Thai
 * Description: JUnit 5 tests for PropertyDriver. Each test replaces the
 *              keyboard (System.in) with a fixed text, runs main, captures what
 *              the program prints (System.out) and checks the output.
 * Due: 11/08/2026
 * Platform/compiler: Visual Studio Code, Java (JDK) - javac, JUnit 5
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Santiago Piguillem
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for {@link PropertyDriver}.
 *
 * @author Santiago Piguillem
 */
public class PropertyDriverJUnitTest {

    /** The real keyboard, saved to be restored after each test. */
    private InputStream originalIn;

    /** The real console, saved to be restored after each test. */
    private PrintStream originalOut;

    /** Holds everything that the driver prints during a test. */
    private ByteArrayOutputStream capturedOutput;

    /** Text that adds Property-1 and Property-2 (answers for menu option 1 twice). */
    private static final String TWO_PROPERTIES =
            "1\nProperty-1\nRockville\n3500\nJoe\n"
            + "1\nProperty-2\nGaithersburg\n2500\nMary\n";

    /**
     * Saves the real keyboard and console and starts capturing the output.
     */
    @BeforeEach
    public void setUp() {
        originalIn = System.in;
        originalOut = System.out;
        capturedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOutput));
    }

    /**
     * Restores the real keyboard and console after each test.
     */
    @AfterEach
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    /**
     * Runs PropertyDriver.main with the given text as keyboard input.
     *
     * @param keyboardText the text that the "user" types
     * @return everything that the driver printed, with \n as line separator
     */
    private String runDriver(String keyboardText) {
        System.setIn(new ByteArrayInputStream(keyboardText.getBytes()));
        PropertyDriver.main(new String[0]);
        System.out.flush();
        return capturedOutput.toString().replace("\r\n", "\n");
    }

    /**
     * Menu option 1 must add properties and print the index of each one.
     */
    @Test
    public void testAddPropertiesPrintsIndex() {
        String output = runDriver(TWO_PROPERTIES + "4\n");

        assertTrue(output.contains("Property added at index 0."));
        assertTrue(output.contains("Property added at index 1."));
    }

    /**
     * Menu option 2 must print the company, its properties and the total rent.
     */
    @Test
    public void testDisplayAllProperties() {
        String output = runDriver(TWO_PROPERTIES + "2\n4\n");

        assertTrue(output.contains("Management Company: Campus Realty (Tax ID: 123-45-6789)"));
        assertTrue(output.contains("0: Property-1, Rockville, Rent: $3500.00, Owner: Joe"));
        assertTrue(output.contains("1: Property-2, Gaithersburg, Rent: $2500.00, Owner: Mary"));
        assertTrue(output.contains("Total Rent: $6000.00"));
    }

    /**
     * Menu option 3 must print the total rent of the properties that were added.
     */
    @Test
    public void testShowTotalRent() {
        String output = runDriver(TWO_PROPERTIES + "3\n4\n");

        assertTrue(output.contains("Total rent: $6000.00"));
    }

    /**
     * With no properties the total rent must be 0.00.
     */
    @Test
    public void testTotalRentWithNoProperties() {
        String output = runDriver("3\n4\n");

        assertTrue(output.contains("Total rent: $0.00"));
    }

    /**
     * A sixth property must be rejected with a "full" message, because the
     * company holds a maximum of 5 properties.
     */
    @Test
    public void testAddPropertyWhenCompanyIsFull() {
        StringBuilder keyboard = new StringBuilder();
        for (int i = 1; i <= ManagementCompany.MAX_PROPERTIES + 1; i++) {
            keyboard.append("1\nProperty-").append(i).append("\nRockville\n1000\nJoe\n");
        }
        keyboard.append("4\n");

        String output = runDriver(keyboard.toString());

        assertTrue(output.contains("Property added at index 4."));
        assertTrue(output.contains("Cannot add property: the company is full"));
    }

    /**
     * Invalid menu input (a number out of range and text) must print an error
     * and the program must continue.
     */
    @Test
    public void testInvalidMenuInput() {
        String output = runDriver("9\nabc\n4\n");

        assertTrue(output.contains("Invalid choice"));
        assertTrue(output.contains("Invalid input"));
    }

    /**
     * An invalid or negative rent must be rejected and asked again.
     */
    @Test
    public void testInvalidRentIsAskedAgain() {
        String output = runDriver("1\nProperty-1\nRockville\nabc\n-5\n3500\nJoe\n3\n4\n");

        assertTrue(output.contains("Invalid input. Enter monthly rent:"));
        assertTrue(output.contains("Rent cannot be negative."));
        assertTrue(output.contains("Total rent: $3500.00"));
    }

    /**
     * Exit must show the copy constructor demonstration (deep copy) and the
     * programmer's name must be the last line of the output.
     */
    @Test
    public void testExitShowsCopyDemoAndProgrammerName() {
        String output = runDriver(TWO_PROPERTIES + "4\n");

        assertTrue(output.contains("--- Copy constructor demonstration ---"));
        assertTrue(output.contains("Original company total rent: $6000.00"));
        assertTrue(output.contains("Copied company total rent (first rent set to 0): $2500.00"));
        assertTrue(output.contains("Same Property object in both companies? false"));

        String[] lines = output.trim().split("\n");
        assertEquals("Programmer: Santiago Piguillem", lines[lines.length - 1]);
    }
}
