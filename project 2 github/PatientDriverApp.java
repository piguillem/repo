import java.util.Scanner;

/**
 * Class Description: PatientDriverApp reads a patient, creates three procedures and displays the patient, a table of procedures and a summary.
 * Pledge: I pledge that I have completed the programming assignment
 *         independently. I have not copied the code from a student or any
 *         source. I have not given my code to any student.
 * Course: CMSC 203
 * Due Date: 10/09/2026
 * Platform/Compiler: Visual Studio Code, Java (JDK)
 * @author Santiago Piguillem
 */
public class PatientDriverApp {

    /**
     * Runs the program: reads the patient, creates the three procedures,
     * displays everything and prints the programmer's name at the end.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        Scanner input=new Scanner(System.in);

        Patient patient=inputPatient(input);
        Procedure p1=createProcedure1();
        Procedure p2=createProcedure2();
        Procedure p3=createProcedure3();

        System.out.println();
        displayPatient(patient);
        System.out.println();
        displayProcedureTable(p1,p2,p3);
        System.out.println();
        displaySummary(p1,p2,p3);
        System.out.println();

        System.out.println("The program was developed by a Student: Santiago Piguillem 10/09/26");

        input.close();
    }

    /**
     * Asks the user for each attribute of the patient and creates the Patient.
     * @param input the Scanner that reads from the keyboard
     * @return the Patient created with the entered information
     */
    static Patient inputPatient(Scanner input)
    {
        System.out.println("Enter your first Name:");
        String first=input.nextLine();
        System.out.println("Enter your middle Name:");
        String middle=input.nextLine();
        System.out.println("Enter your last Name:");
        String last=input.nextLine();
        System.out.println("Enter your street address:");
        String street=input.nextLine();
        System.out.println("Enter your city:");
        String city=input.nextLine();
        System.out.println("Enter your state:");
        String state=input.nextLine();
        System.out.println("Enter your zip code:");
        String zipC=input.nextLine();
        System.out.println("Enter your phone number (###-###-####):");
        String pnumber=input.nextLine();
        System.out.println("Enter your emergency contact Name:");
        String eContactN=input.nextLine();
        System.out.println("Enter your emergency phone Number (###-###-####):");
        String ephoneN=input.nextLine();

        Patient patient=new Patient(first,middle,last,street,city,state,zipC,pnumber,eContactN,ephoneN);

        return patient;
    }

    /**
     * Creates the first procedure with the no-arg constructor and sets
     * every attribute with the setters.
     * @return the first Procedure
     */
    static Procedure createProcedure1()
    {
        Procedure procedure1=new Procedure();

        procedure1.setProcedureName("Physical Exam");
        procedure1.setDate("07/20/2026");
        procedure1.setPractitionerName("Dr. Gonzalez");
        procedure1.setChargesProcedure(356.23);

        return procedure1;
    }

    /**
     * Creates the second procedure with the name-and-date constructor and
     * sets the remaining attributes with the setters.
     * @return the second Procedure
     */
    static Procedure createProcedure2()
    {
        Procedure procedure2=new Procedure("X-ray","08/20/2026");
        procedure2.setPractitionerName("Dr. Smith");
        procedure2.setChargesProcedure(2346.81);

        return procedure2;
    }

    /**
     * Creates the third procedure with the constructor that sets all attributes.
     * @return the third Procedure
     */
    static Procedure createProcedure3()
    {
        Procedure procedure3=new Procedure("Blood Test","06/20/2025","Dr. Zxavier", 2050.45);
        return procedure3;
    }

    /**
     * Displays the patient information and whether the phone numbers are valid.
     * @param patient the Patient to display
     */
    static void displayPatient(Patient patient)
    {
        System.out.println("Patient Information");
        System.out.println("-------------------");
        System.out.println(patient);
        System.out.println("Phone Valid: "+patient.isValidPhoneNumber());
        System.out.println("Emergency Phone Valid: "+patient.isValidEmergencyPhoneNumber());
    }

    /**
     * Displays one procedure as an aligned row of the table.
     * @param procedure the Procedure to display
     */
    static void displayProcedure(Procedure procedure)
    {
        String charge=procedure.getFormattedCharge();
        String name=procedure.getProcedureName();
        String line=name+"\t";
        if (name.length()<8) {
            line=line+"\t";
        }
        line=line+procedure.getDate()+"\t"+procedure.getPractitionerName()+"\t"+charge+"\t";
        if (charge.length()<8) {
            line=line+"\t";
        }
        System.out.println(line+procedure.getChargeCategory());
    }

    /**
     * Displays the table header and the three procedures.
     * @param procedure1 the first Procedure
     * @param procedure2 the second Procedure
     * @param procedure3 the third Procedure
     */
    static void displayProcedureTable(Procedure procedure1, Procedure procedure2, Procedure procedure3)
    {
        System.out.println("Procedure\tDate\t\tPractitioner\tCharge\t\tCategory");
        System.out.println("------------------------------------------------------------------------");
        displayProcedure(procedure1);
        displayProcedure(procedure2);
        displayProcedure(procedure3);
    }

    /**
     * Adds up the charges of the three procedures.
     * @param p1 the first Procedure
     * @param p2 the second Procedure
     * @param p3 the third Procedure
     * @return the total of the charges
     */
    static double calculateTotalCharges(Procedure p1, Procedure p2, Procedure p3)
    {
        double pt=p1.getChargesProcedure()+p2.getChargesProcedure()+p3.getChargesProcedure();
        return pt;
    }

    /**
     * Calculates the average charge of the three procedures.
     * @param p1 the first Procedure
     * @param p2 the second Procedure
     * @param p3 the third Procedure
     * @return the average of the charges
     */
    static double calculateAverageCharge(Procedure p1, Procedure p2, Procedure p3)
    {
        double pt=p1.getChargesProcedure()+p2.getChargesProcedure()+p3.getChargesProcedure();
        return pt/3.0;
    }

    /**
     * Finds the procedure with the highest charge.
     * @param p1 the first Procedure
     * @param p2 the second Procedure
     * @param p3 the third Procedure
     * @return the Procedure with the highest charge
     */
    static Procedure findHighestChargeProcedure(Procedure p1, Procedure p2, Procedure p3)
    {
        Procedure highest;
        highest=p1;
        if (p2.getChargesProcedure()>highest.getChargesProcedure()) {
            highest=p2;
        }

        if (p3.getChargesProcedure()>highest.getChargesProcedure()) {
            highest=p3;
        }

        return highest;
    }

    /**
     * Counts how many procedures are expensive (charges of 1000.00 or more).
     * @param p1 the first Procedure
     * @param p2 the second Procedure
     * @param p3 the third Procedure
     * @return the number of expensive procedures
     */
    static int countExpensiveProcedures(Procedure p1,Procedure p2, Procedure p3)
    {
        int expensivep=0;
        if (p1.isExpensiveProcedure()) {
            expensivep++;
        }
        if (p2.isExpensiveProcedure()) {
            expensivep++;
        }
        if (p3.isExpensiveProcedure()) {
            expensivep++;
        }

        return expensivep;
    }

    /**
     * Displays the total charges, the average charge, the highest-charge
     * procedure and the number of expensive procedures.
     * @param p1 the first Procedure
     * @param p2 the second Procedure
     * @param p3 the third Procedure
     */
    static void displaySummary(Procedure p1, Procedure p2, Procedure p3)
    {
        Procedure highest=findHighestChargeProcedure(p1,p2,p3);
        System.out.printf("Total Charges: $%,.2f\nAverage Charge: $%,.2f\nHighest Charge Procedure: %s\nNumber of Expensive Procedures: %d\n",calculateTotalCharges(p1, p2, p3),calculateAverageCharge(p1,p2,p3),highest.getProcedureName(),countExpensiveProcedures(p1, p2, p3));
    }
}
