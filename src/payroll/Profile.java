package payroll;

/**
 Defines the profile of an employee with variables.
 @author Sailokesh Mondi, Tanay Somisetty
 */

public class Profile {
    private String name; //employee's name in the form "lastname,firstname"
    private String department; //department code: CS, ECE, IT
    private Date dateHired;


    /**
     Creates a profile object.
     @param 'name' of form "Lname,Fname', department initials, and date hired
     */
    public Profile(String name, String department, Date dateHired) {
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     Gives a string representation for a Profile object.
     @param 'none'
     @return a string in the format of "lastname,firtname::DEPT::MM/DD/YYYY"
     */
    @Override
    public String toString() {
        return this.name + "::" + department + "::" + this.dateHired.getDate();
    }


    /**
     Compares a Profile object with another Profile object so see if they're equal.
     Compares name, department, and dateHired.
     @param obj
     @return true if obj is a Profile object and is equal to this class's instance variables, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            if ((this.name.equals(profile.name) && this.department.equals(profile.department) &&
                    this.dateHired.equals(profile.dateHired))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     Accessor method that gets the name instance variable for this class.
     @param 'none'
     @return A String object for the name
     */
    public String getName() {
        return this.name;
    }

    /**
     Accessor method that gets the department instance variable for this class.
     @param 'none'
     @return A String object for the department
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     Accessor method that gets the dateHired instance variable for this class.
     @param 'none'
     @return A String object for the dateHired
     */
    public Date getDateHired() {
        return this.dateHired;
    }
}
