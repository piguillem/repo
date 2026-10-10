/**
 * Class Description: Patient models a patient with a name, address, phone number and emergency contact.
 * Pledge: I pledge that I have completed the programming assignment
 *         independently. I have not copied the code from a student or any
 *         source. I have not given my code to any student.
 * Course: CMSC 203
 * Due Date: 10/09/2026
 * Platform/Compiler: Visual Studio Code, Java (JDK)
 * @author Santiago Piguillem
 */
public class Patient
{
    //fields
    private String firstName;
    private String middleName;
    private String lastName;

    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    private String phoneNumber;
    private String emergencyContactName;
    private String contactPhoneNumber;

    //constructors

    /**
     * No-arg constructor. Sets every field to an empty string.
     */
    public Patient()
    {
        firstName="";
        middleName="";
        lastName="";
        streetAddress="";
        city="";
        state="";
        zipCode="";
        phoneNumber="";
        emergencyContactName="";
        contactPhoneNumber="";
    }

    /**
     * Constructor that initializes the three name fields. The other
     * fields are set to an empty string so no method works with null.
     * @param firstName the first name
     * @param middleName the middle name
     * @param lastName the last name
     */
    public Patient(String firstName,String middleName, String lastName)
    {
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        streetAddress="";
        city="";
        state="";
        zipCode="";
        phoneNumber="";
        emergencyContactName="";
        contactPhoneNumber="";
    }

    /**
     * Constructor that initializes all of the attributes.
     * @param firstName the first name
     * @param middleName the middle name
     * @param lastName the last name
     * @param streetAddress the street address
     * @param city the city
     * @param state the state
     * @param zipCode the ZIP code
     * @param phoneNumber the phone number (###-###-####)
     * @param emergencyContactName the emergency contact's name
     * @param contactPhoneNumber the emergency contact's phone (###-###-####)
     */
    public Patient(String firstName,String middleName, String lastName,String streetAddress, String city, String state, String zipCode,String phoneNumber, String emergencyContactName, String contactPhoneNumber)
    {
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.streetAddress=streetAddress;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
        this.phoneNumber=phoneNumber;
        this.emergencyContactName=emergencyContactName;
        this.contactPhoneNumber=contactPhoneNumber;
    }

    //setters

    /** Sets the first name. @param firstName the new first name */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /** Sets the middle name. @param middleName the new middle name */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    /** Sets the last name. @param lastName the new last name */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /** Sets the street address. @param streetAddress the new street address */
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    /** Sets the city. @param city the new city */
    public void setCity(String city)
    {
        this.city = city;
    }

    /** Sets the state. @param state the new state */
    public void setState(String state)
    {
        this.state = state;
    }

    /** Sets the ZIP code. @param zipCode the new ZIP code */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /** Sets the phone number. @param phoneNumber the new phone number */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /** Sets the emergency contact name. @param emergencyContactName the new name */
    public void setEmergencyContactName(String emergencyContactName)
    {
        this.emergencyContactName = emergencyContactName;
    }

    /** Sets the emergency contact phone. @param contactPhoneNumber the new phone */
    public void setContactPhoneNumber(String contactPhoneNumber)
    {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    /**
     * Sets the three name fields at once.
     * @param fName the first name
     * @param mName the middle name
     * @param lName the last name
     */
    public void buildFullName(String fName, String mName, String lName)
    {
        firstName=fName;
        middleName=mName;
        lastName=lName;
    }

    /**
     * Sets the four address fields at once.
     * @param sAddress the street address
     * @param ccity the city
     * @param sstate the state
     * @param zCode the ZIP code
     */
    public void buildAddress(String sAddress, String ccity, String sstate, String zCode)
    {
        streetAddress=sAddress;
        city=ccity;
        state=sstate;
        zipCode=zCode;
    }

    /**
     * Sets the emergency contact name and phone at once.
     * @param Ename the emergency contact's name
     * @param Ephone the emergency contact's phone
     */
    public void buildEmergencyContact(String Ename,String Ephone)
    {
        emergencyContactName=Ename;
        contactPhoneNumber=Ephone;
    }

    //Getters

    /** Returns the first name. @return the first name */
    public String getFirstName()
    {
        return firstName;
    }

    /** Returns the middle name. @return the middle name */
    public String getMiddleName()
    {
        return middleName;
    }

    /** Returns the last name. @return the last name */
    public String getLastName()
    {
        return lastName;
    }

    /** Returns the street address. @return the street address */
    public String getStreetAddress()
    {
        return streetAddress;
    }

    /** Returns the city. @return the city */
    public String getCity()
    {
        return city;
    }

    /** Returns the state. @return the state */
    public String getState()
    {
        return state;
    }

    /** Returns the ZIP code. @return the ZIP code */
    public String getZipCode()
    {
        return zipCode;
    }

    /** Returns the phone number. @return the phone number */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /** Returns the emergency contact name. @return the emergency contact name */
    public String getEmergencyContactName()
    {
        return emergencyContactName;
    }

    /** Returns the emergency contact phone. @return the emergency contact phone */
    public String getContactPhoneNumber()
    {
        return contactPhoneNumber;
    }

    /**
     * Builds the full name of the patient.
     * @return the name in the form "First Middle Last"
     */
    public String buildFullName()
    {
        return firstName+" "+middleName+" "+lastName;
    }

    /**
     * Builds the full address of the patient.
     * @return the address in the form "Street City State ZIP"
     */
    public String buildAddress()
    {
        return streetAddress+" "+city+" "+state+" "+zipCode;
    }

    /**
     * Builds the emergency contact information.
     * @return the contact in the form "EmergencyName EmergencyPhone"
     */
    public String buildEmergencyContact()
    {
        return emergencyContactName+" "+contactPhoneNumber;
    }

    //toString

    /**
     * Displays all of the patient information using the three build methods.
     * @return a multi-line String with the name, address, phone and emergency contact
     */
    public String toString()
    {
        return "Name: "+buildFullName()+"\n"+"Address: "+buildAddress()+"\n"+"Phone Number: "+phoneNumber+"\n"+"Emergency Contact: "+buildEmergencyContact();
    }

    // methods

    /**
     * Checks that the phone number has the format ###-###-####
     * (12 characters, dashes in positions 3 and 7, digits everywhere else).
     * @return true if the phone number is valid, false otherwise
     */
    public boolean isValidPhoneNumber()
    {
        if (phoneNumber.length()!=12) {
            return false;
        }

        for(int i=0;i<12;i++)
        {
            if (i==3 || i==7) {
                if (phoneNumber.charAt(i)!='-') {
                    return false;
                }
            }
            else if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that the emergency contact phone has the format ###-###-####.
     * @return true if the emergency phone number is valid, false otherwise
     */
    public boolean isValidEmergencyPhoneNumber()
    {
        if (contactPhoneNumber.length()!=12) {
            return false;
        }

        for(int i=0;i<12;i++)
        {
            if (i==3 || i==7) {
                if (contactPhoneNumber.charAt(i)!='-') {
                    return false;
                }
            }
            else if (!Character.isDigit(contactPhoneNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Builds the name with the last name first.
     * @return the name in the form "Last, First Middle"
     */
    public String getLastFirstMiddle()
    {
        return lastName+", "+firstName+" "+middleName;
    }

    /**
     * Compares the patient's city and state with the given ones, ignoring case.
     * @param city the city to compare
     * @param state the state to compare
     * @return true if both the city and the state match, false otherwise
     */
    public boolean hasSameCityState(String city, String state)
    {
        if (!(this.city.equalsIgnoreCase(city) && this.state.equalsIgnoreCase(state))) {
            return false;
        }
        return true;
    }

    /**
     * Replaces the whole address of the patient.
     * @param street the new street address
     * @param city the new city
     * @param state the new state
     * @param zip the new ZIP code
     */
    public void updateAddress(String street, String city, String state, String zip)
    {
        streetAddress=street;
        this.city=city;
        this.state=state;
        zipCode=zip;
    }

    /**
     * Builds a formatted summary of the patient and the emergency contact.
     * @return a multi-line String with the patient, phone and emergency contact
     */
    public String getContactSummary()
    {
        return "Patient: "+getLastFirstMiddle()+"\n"+"Phone number: "+phoneNumber+"\n"+"Emergency contact: "+emergencyContactName+" "+contactPhoneNumber;
    }
}
