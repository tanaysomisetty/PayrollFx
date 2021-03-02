package payroll;

import java.text.DecimalFormat;

/**
 This class extends the Employee class and includes specific data and operations for a part-time employee.
 @author Sailokesh Mondi, Tanay Somisetty
 */

public class Parttime extends Employee {
    private int hoursWorked;
    final static int MAX_HOURS = 80;
    final static double OVERTIME_RATE = 1.5;

    /**
     Constructor that takes profile and compensation. Calls the constructor from the super class.
     @param profile object for an employee, compensation as type double
     */
    public Parttime(Profile profile, double comp) {
        super(profile, comp);
    }

    /**
     Calculates the payment for a part-time employee
     @param 'none'
     */
    @Override
    public void calculatePayment() {

        int regHours = this.hoursWorked;
        int overTimeHours = 0;

        if (regHours > MAX_HOURS) {
            overTimeHours = regHours - MAX_HOURS;
            regHours = MAX_HOURS;
        }

        double regPay = regHours * super.getComp();
        double overTimePay = overTimeHours * (super.getComp() * OVERTIME_RATE);

        super.setPayment(regPay + overTimePay);
    }

    /**
     Gives a string representation of a Parttime object
     @param 'none'
     @return a string in the format Lname,Fname::DEPT::MM/DD/YYYY::Payment $0.00::PART TIME::Hourly Rate $0.00: Hours worked this period: 0
     */
    @Override
    public String toString() {

        DecimalFormat dFormat = new DecimalFormat("$###,###.##");

        String profileInfo = super.toString();
        String paymentInfo = String.valueOf(dFormat.format(super.getPayment()));
        String wageInfo = String.valueOf(dFormat.format(super.getComp()));
        String hoursInfo = String.valueOf(this.hoursWorked);

        return (profileInfo + "::" + "Payment " + paymentInfo + "::PART TIME::Hourly Rate " + wageInfo + "::Hours " +
                "worked this period: " + hoursInfo);

    }

    /**
     Checks to see whether two Parttime objects are equal by comparing their profiles
     @param 'An' Object obj
     @return true if obj is a Parttime object and is equal to this class's profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Parttime) {
            Parttime parttime = (Parttime) obj;
            Profile inProfile = parttime.getProfile();

            Boolean nameCheck = inProfile.getName().equals(getProfile().getName());
            Boolean deptCheck = inProfile.getDepartment().equals(getProfile().getDepartment());
            int dateCheck = inProfile.getDateHired().compareTo(getProfile().getDateHired());

            if (nameCheck && deptCheck && (dateCheck == 0)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     Accessor method to get the hours worked for a parttime employee
     @param 'none'
     @return an integer value that gets the hours worked for the employee
     */
    public int getHoursWorked() {
        return this.hoursWorked;

    }


    /**
     Modifer method to set the hours worked for a parttime employee
     @param 'hours' worked of type int
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

}
