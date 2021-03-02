package payroll;

import java.text.DecimalFormat;

/**
 Extends the Employee class and includes specific data and operations to a full-time employee
 @author Sailokesh Mondi, Tanay Somisetty
 */

public class Fulltime extends Employee {

    /**
     Creates a Fulltime employee using the given profile and compensation
     @param 'profile' object and compensation of type double
     */
    public Fulltime(Profile profile, double comp) {
        super(profile, comp);
    }

    /**
     Method to calculate payments for a full-time employee
     @param 'none'
     */
    @Override
    public void calculatePayment() {
        final int PAYMENT_PERIODS = 26;
        double annualSalary = super.getComp();

        super.setPayment(annualSalary / PAYMENT_PERIODS);
    }

    /**
     Gives a string representation of a Fulltime object
     @param 'none'
     @return a string in the format Lname,Fname::DEPT::MM/DD/YYYY::Payment $0.00::FULL TIME::Annual Salary $0.00
     */
    @Override
    public String toString() {

        DecimalFormat dFormat = new DecimalFormat("$###,###.##");

        String profileInfo = super.toString();
        String paymentInfo = String.valueOf(dFormat.format(super.getPayment()));
        String salaryInfo = String.valueOf(dFormat.format(super.getComp()));

        return (profileInfo + "::" + "Payment " + paymentInfo + "::FULL TIME::Annual Salary " + salaryInfo);

    }

    /**
     Checks to see whether two Fulltime objects are equal by comparing their profiles
     @param 'An' Object obj
     @return true if obj is a Fulltime object and is equal to this class's profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fulltime) {
            Fulltime fulltime = (Fulltime) obj;
            Profile inProfile = fulltime.getProfile();

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
    }

