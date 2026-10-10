/**
 * Class Description: Procedure models a medical procedure with a name, date, practitioner and charges.
 * Pledge: I pledge that I have completed the programming assignment
 *         independently. I have not copied the code from a student or any
 *         source. I have not given my code to any student.
 * Course: CMSC 203
 * Due Date: 10/09/2026
 * Platform/Compiler: Visual Studio Code, Java (JDK)
 * @author Santiago Piguillem
 */
public class Procedure
{
    //fields
    private String procedureName;
    private String date;
    private String practitionerName;
    private double chargesProcedure;

    //constructors

    /**
     * No-arg constructor. Sets the text fields to an empty string and the charges to 0.
     */
    public Procedure()
    {
        procedureName="";
        date="";
        practitionerName="";
        chargesProcedure=0;
    }

    /**
     * Constructor that initializes the procedure name and the date. The
     * practitioner is set to an empty string and the charges to 0.
     * @param procedureName the name of the procedure
     * @param date the date of the procedure
     */
    public Procedure(String procedureName,String date)
    {
        this.procedureName=procedureName;
        this.date=date;
        practitionerName="";
        chargesProcedure=0;
    }

    /**
     * Constructor that initializes all of the attributes.
     * @param procedureName the name of the procedure
     * @param date the date of the procedure
     * @param practitionerName the name of the practitioner
     * @param chargesforProcedure the charges for the procedure
     */
    public Procedure(String procedureName, String date,String practitionerName, double chargesforProcedure)
    {
        this.procedureName=procedureName;
        this.date=date;
        this.practitionerName=practitionerName;
        this.chargesProcedure=chargesforProcedure;
    }

    //toString

    /**
     * Displays all of the procedure information.
     * @return a multi-line String with the name, date, practitioner and charges
     */
    public String toString()
    {
        return "Procedure name: "+procedureName+"\nDate: "+date+"\nPractitioner's name: "+practitionerName+"\nCharges for procedure: "+getFormattedCharge();
    }

    //setters

    /** Sets the procedure name. @param proName the new name */
    public void setProcedureName(String proName)
    {
        procedureName=proName;
    }

    /** Sets the date. @param date the new date */
    public void setDate(String date)
    {
        this.date=date;
    }

    /** Sets the practitioner name. @param pName the new practitioner */
    public void setPractitionerName(String pName)
    {
        practitionerName=pName;
    }

    /** Sets the charges. @param cProcedure the new charges */
    public void setChargesProcedure(double cProcedure)
    {
        chargesProcedure=cProcedure;
    }

    //Getters

    /** Returns the procedure name. @return the procedure name */
    public String getProcedureName()
    {
        return procedureName;
    }

    /** Returns the date. @return the date */
    public String getDate()
    {
        return date;
    }

    /** Returns the practitioner name. @return the practitioner name */
    public String getPractitionerName()
    {
        return practitionerName;
    }

    /** Returns the charges. @return the charges */
    public double getChargesProcedure()
    {
        return chargesProcedure;
    }

    //methods

    /**
     * Checks whether the procedure is expensive.
     * @return true if the charges are 1000.00 or more, false otherwise
     */
    public boolean isExpensiveProcedure()
    {
        if (chargesProcedure>=1000.00) {
            return true;
        }
        return false;
    }

    /**
     * Reduces the charges by the given percent. Only values from 0 to 100
     * are valid; otherwise a message is printed and the charges do not change.
     * @param percent the discount percent (0 to 100)
     */
    public void applyDiscount(double percent)
    {
        if (percent<0 || percent>100) {
            System.out.println("Invalid percentage. It must be between 0 and 100.");
        }
        else {
            chargesProcedure=chargesProcedure-(chargesProcedure*percent/100);
        }
    }

    /**
     * Classifies the charges of the procedure.
     * @return "Low" (under 500), "Medium" (500 to 999.99) or "High" (1000 or more)
     */
    public String getChargeCategory()
    {
        if (chargesProcedure<500) {
            return "Low";
        }
        if (chargesProcedure<1000.00) {
            return "Medium";
        }
        else {
            return "High";
        }
    }

    /**
     * Checks whether the procedure was performed by the given practitioner.
     * @param practitionerName the name to compare
     * @return true if the names match, false otherwise
     */
    public boolean isPerformedBy(String practitionerName)
    {
        if (this.practitionerName.equals(practitionerName)) {
            return true;
        }
        return false;
    }

    /**
     * Formats the charges with a dollar sign, commas and two decimals.
     * @return the charges as a String such as "$1,400.75"
     */
    public String getFormattedCharge()
    {
        return String.format("$%,.2f", chargesProcedure);
    }
}
