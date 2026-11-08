/*
 * Class: CMSC203 CRN 21751
 * Instructor: Gary Thai
 * Description: PropertyDriver is the console driver of the Property Management
 *              application. It creates a ManagementCompany, shows a menu to add
 *              properties, display them and show the total rent, and, when the
 *              program exits, demonstrates the copy constructors of Property
 *              and ManagementCompany.
 * Due: 11/08/2026
 * Platform/compiler: Visual Studio Code, Java (JDK) - javac
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Santiago Piguillem
 */

import java.util.Scanner;

/**
 * Console driver for the Property Management application.
 *
 * @author Santiago Piguillem
 */
public class PropertyDriver {

    /** Menu option that adds a property. */
    private static final int ADD = 1;

    /** Menu option that displays all properties. */
    private static final int DISPLAY = 2;

    /** Menu option that shows the total rent. */
    private static final int TOTAL_RENT = 3;

    /** Menu option that exits the program. */
    private static final int EXIT = 4;

    /**
     * Starts the program. Creates the company and repeats the menu until the
     * user chooses Exit. When the user exits, the copy constructors are
     * demonstrated and the programmer's name is printed as the last line.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ManagementCompany company = new ManagementCompany("Campus Realty", "123-45-6789");
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            choice = readMenuChoice(input);

            switch (choice) {
                case ADD:
                    addPropertyFromInput(input, company);
                    break;
                case DISPLAY:
                    System.out.println(company);
                    break;
                case TOTAL_RENT:
                    System.out.printf("Total rent: $%.2f%n", company.totalRent());
                    break;
                case EXIT:
                    demonstrateCopyConstructors(company);
                    System.out.println("Programmer: Santiago Piguillem");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                    break;
            }
        } while (choice != EXIT);

        input.close();
    }

    /**
     * Prints the menu of the application.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("   Assignment 4: Property Manager   ");
        System.out.println("====================================");
        System.out.println("1 - Add Property");
        System.out.println("2 - Display All Properties");
        System.out.println("3 - Show Total Rent");
        System.out.println("4 - Exit");
        System.out.print("Enter choice (1-4): ");
    }

    /**
     * Reads the menu choice. If the user does not type an integer, it shows an
     * error and asks again. If there is no more input, it returns EXIT so the
     * program ends instead of failing.
     *
     * @param input the scanner that reads the keyboard
     * @return the integer typed by the user
     */
    private static int readMenuChoice(Scanner input) {
        while (!input.hasNextInt()) {
            if (!input.hasNextLine()) {
                return EXIT;
            }
            input.nextLine();
            System.out.print("Invalid input. Enter an integer (1-4): ");
        }
        int choice = input.nextInt();
        input.nextLine(); // remove the end of the line left by nextInt()
        return choice;
    }

    /**
     * Reads the monthly rent. It keeps asking until the user types a number that
     * is not negative.
     *
     * @param input the scanner that reads the keyboard
     * @return a valid monthly rent
     */
    private static double readRent(Scanner input) {
        System.out.print("Enter monthly rent: ");
        while (true) {
            if (input.hasNextDouble()) {
                double rent = input.nextDouble();
                input.nextLine(); // remove the end of the line left by nextDouble()
                if (rent >= 0) {
                    return rent;
                }
                System.out.print("Rent cannot be negative. Enter monthly rent: ");
            } else {
                input.nextLine();
                System.out.print("Invalid input. Enter monthly rent: ");
            }
        }
    }

    /**
     * Asks the user for the data of a property, creates the Property object and
     * adds it to the company. Prints the index where it was stored, or a message
     * if the company is full.
     *
     * @param input   the scanner that reads the keyboard
     * @param company the company where the property is added
     */
    private static void addPropertyFromInput(Scanner input, ManagementCompany company) {
        Property property = new Property();

        System.out.print("Enter property name: ");
        property.setPropertyName(input.nextLine());
        System.out.print("Enter city: ");
        property.setCity(input.nextLine());
        property.setRentAmount(readRent(input));
        System.out.print("Enter owner name: ");
        property.setOwner(input.nextLine());

        int index = company.addProperty(property);
        if (index == -1) {
            System.out.println("Cannot add property: the company is full (maximum "
                    + ManagementCompany.MAX_PROPERTIES + " properties).");
        } else {
            System.out.println("Property added at index " + index + ".");
        }
    }

    /**
     * Demonstrates the copy constructors, arrays of objects and aggregation.
     * It copies the first property with the Property copy constructor and
     * copies the whole company with the ManagementCompany copy constructor
     * (deep copy). Then it changes the COPIES and shows that the original
     * objects did not change.
     *
     * @param company the company to copy (it is not modified)
     */
    private static void demonstrateCopyConstructors(ManagementCompany company) {
        System.out.println();
        System.out.println("--- Copy constructor demonstration ---");
        if (company.getPropertyCount() == 0) {
            System.out.println("Add at least one property (option 1) to see the copy "
                    + "constructors in action.");
            System.out.println();
            return;
        }

        // Property copy constructor
        Property original = company.getProperty(0);
        Property propertyCopy = new Property(original);
        propertyCopy.setOwner("Changed Owner");
        System.out.println("Original property: " + original);
        System.out.println("Copied property (owner changed): " + propertyCopy);

        // ManagementCompany copy constructor (deep copy of the array of Property)
        ManagementCompany companyCopy = new ManagementCompany(company);
        companyCopy.getProperty(0).setRentAmount(0.00);
        System.out.println("Aggregation: the company holds a Property[] with "
                + company.getPropertyCount() + " of " + ManagementCompany.MAX_PROPERTIES
                + " positions used.");
        System.out.printf("Original company total rent: $%.2f%n", company.totalRent());
        System.out.printf("Copied company total rent (first rent set to 0): $%.2f%n",
                companyCopy.totalRent());
        System.out.println("Same Property object in both companies? "
                + (company.getProperty(0) == companyCopy.getProperty(0))
                + " (false means it is a deep copy)");
        System.out.println();
    }
}
