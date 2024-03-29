package payroll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


import java.util.Calendar;

/**
 An array-based container class that implements employee database.
 Stores a list of employees, which may include instances of full-time,part-time,
 and management.
 @author Sailokesh Mondi, Tanay Somisetty
 */

public class Company {
    private Employee[] emplist;
    private int numEmployee;
    final static int INITIAL_CAPACITY = 4;
    final static int SORT_BY_DATE = 1;
    final static String DEPARTMENT_CS = "CS";
    final static String DEPARTMENT_ECE = "ECE";
    final static String DEPARTMENT_IT = "IT";


    public Company() {
        this.emplist = new Employee[INITIAL_CAPACITY];
        this.numEmployee = 0;
    }

    /**
     Method to search for and find an employee in the list
     @param employee
     @return emplist index if the employee is found, -1 otherwise.
     */
    private int find(Employee employee) {
        for (int i = 0; i < emplist.length && emplist[i] != null; i++ ) {
            if (employee.equals(emplist[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     Helper method to grow the capacity of employee array by 4
     @param 'none'
     */
    private void grow() {

        final int capacityToIncreaseBy = 4;
        int currCapacity = this.emplist.length;
        Employee[] newEmployees = new Employee[currCapacity + capacityToIncreaseBy];

        for (int i = 0; i < currCapacity; i++) {
            newEmployees[i] = emplist[i];
        }

        emplist = newEmployees;

    }


    /**
      Method to check the profile before adding
      @param employee
      @return true if the employee was able to be added, false otherwise
     */
    public boolean add(Employee employee) {

        if (find(employee) < 0) {
            // If the array is fully occupied, call grow
            if (numEmployee == emplist.length) {
                grow();
            }
            emplist[numEmployee] = employee;
            this.numEmployee++;
            return true;
        }
        return false;
    }

    /**
      Method to remove an employee from the list
      @param employee
      @return true if the employee was able to be removed, false otherwise
     */
    public boolean remove(Employee employee) {

        int removeIndex = find(employee);
        if (removeIndex >= 0) {
            emplist[removeIndex] = null;
            this.numEmployee--;
            shift(removeIndex + 1);
            return true;
        }
        else {
            return false;
        }


    }

    /**
      Helper method to shift all items of an array begin at index start to the end of the array back by 1
     @param 'starting' index
     */
    private void shift(int start) {

        for (int i = start; i < emplist.length; i++) {
            emplist[i - 1] = emplist[i];
        }
        emplist[emplist.length - 1] = null;

    }


    /**
      Method to set working hours for a part-time employee
      @param employee object
      @return true if successful, false otherwise
     */
    public boolean setHours(Employee employee) {

        final int MAX_HOURS = 100;

        if (employee instanceof Parttime) {
            int setHoursIndex = find(employee);
            if (setHoursIndex < 0) {
                return false;
            }
            int hours = ((Parttime) employee).getHoursWorked();
            if(hours > 0 && hours <= MAX_HOURS) {
                ((Parttime) emplist[setHoursIndex]).setHoursWorked(hours);
                return true;
            }
        }

        return false;
    }

    /**
     Method to process payments for all employees.
     @param 'none'
     */
    public void processPayments() {

        for (Employee employee : emplist) {
            if (employee instanceof Parttime) {
                Parttime partimeEmployee = (Parttime) employee;
                partimeEmployee.calculatePayment();
            } else if (employee instanceof Fulltime) {
                Fulltime fulltime = (Fulltime) employee;
                fulltime.calculatePayment();
            } else if (employee instanceof Management) {
                Management management = (Management) employee;
                management.calculatePayment();
            }
        }

    }

    /**
     Method to print earning statements for all employees.
     @param 'none'
     @return String output of print
     */
    public String print() {
        if (numEmployee == 0) {
            return "Employee database is empty";
        }
        String output = "--Printing earning statements for all employees--" + "\n";
        for (int i = 0; i < numEmployee; i++) {
            output = output + emplist[i].toString() + "\n";
        }

        return output;
    }

    /**
     Helper method to implement selection sort for two different purposes: sort by date hired and sort by department
     @param 'sorting' type/method
     */
    private void selectionSort(final int sortBy) {
        if (sortBy == SORT_BY_DATE) {
            for (int i = 0; i < numEmployee - 1; i++) {
                for (int j = i + 1; j < numEmployee; j++) {
                    Date dt1 = emplist[i].getProfile().getDateHired();
                    Calendar cal1 = Calendar.getInstance();
                    cal1.set(Calendar.DATE, dt1.getDay());
                    cal1.set(Calendar.MONTH, dt1.getMonth());
                    cal1.set(Calendar.YEAR, dt1.getYear());

                    Date dt2 = emplist[j].getProfile().getDateHired();
                    Calendar cal2 = Calendar.getInstance();
                    cal2.set(Calendar.DATE, dt2.getDay());
                    cal2.set(Calendar.MONTH, dt2.getMonth());
                    cal2.set(Calendar.YEAR, dt2.getYear());

                    if (cal1.getTime().compareTo(cal2.getTime()) > 0) {
                        Employee temp_employee = emplist[i];
                        emplist[i] = emplist[j];
                        emplist[j] = temp_employee;
                    }
                }

            }
        }
    }

    /**
     Method to print earning statements by department.
     @param 'none'
     @return String output of print by department
     */
    public String printByDepartment() {
        if (numEmployee == 0) {
            return "Employee database is empty.";
        }

        Employee[] cSDepArray = new Employee[emplist.length];
        Employee[] eCEDepArray = new Employee[emplist.length];
        Employee[] itDepArray = new Employee[emplist.length];

        int csDepCount = 0;
        int eceDepCount = 0;
        int itDepCount = 0;

        String output = "--Printing earning statements by department--" + "\n";

        for (int i = 0; i < numEmployee; i++) {
            String department = emplist[i].getProfile().getDepartment();
            if (DEPARTMENT_CS.equals(department)) {
                cSDepArray[csDepCount] = emplist[i];
                csDepCount++;
            } else if (DEPARTMENT_ECE.equals(department)) {
                eCEDepArray[eceDepCount] = emplist[i];
                eceDepCount++;
            } else if (DEPARTMENT_IT.equals(department)) {
                itDepArray[itDepCount] = emplist[i];
                itDepCount++;
            }
        }

        for (int i = 0; i < csDepCount; i++) {
            output = output + cSDepArray[i] + "\n";
        }

        for (int i = 0; i < eceDepCount; i++) {
            output = output + eCEDepArray[i] + "\n";
        }

        for (int i = 0; i < itDepCount; i++) {
            output = output + itDepArray[i] + "\n";
        }

        return output;
    }

    /**
     Method to print earning statements by date hired.
     @param 'none'
     @return String output of print by date
     */
    public String printByDate() {

        if (numEmployee == 0) {
            System.out.println("Employee database is empty.");
            return "Employee database is empty.";
        }
        else {
            selectionSort(SORT_BY_DATE);
            String output = "--Printing earning statements by date hired--" + "\n";
            for (int i = 0; i < numEmployee; i++) {
                output = output + emplist[i].toString() + "\n";
            }
            return output;
        }
    }

    /**
     * Accessor method to get the number of employees.
     * @param 'none'
     * @return int value of the number of employees in emplist
     */
    public int getNumEmployee() {
        return this.numEmployee;
    }

    /**
     * Export employees from emplist into a text file
     * @param 'none'
     * @return a File object containing the employees in format 'X, First Last,DEPT,MM/DD/YYYY,#####'
     */
    public File exportDatabase() {
        if (numEmployee == 0){
            return null;
        }

        File file = new File("Database.txt");
        try {
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < numEmployee; i++) {
                Employee currEmp = emplist[i];
                Profile currProf = currEmp.getProfile();
                String output = "";

                final int LAST_NAME_INDEX = 0;
                final int FIRST_NAME_INDEX = 1;
                String nameTokens[] = currProf.getName().split(",");
                output = nameTokens[FIRST_NAME_INDEX] + " " + nameTokens[LAST_NAME_INDEX] + "," +
                        currProf.getDepartment() + "," + currProf.getDateHired().getDate() + "," +
                        currEmp.getComp();


                if (currEmp instanceof Parttime) {
                    output = "P," + output;
                }
                else if (currEmp instanceof Fulltime) {
                    output = "F," + output;
                }
                else if (currEmp instanceof Management) {
                    Management currMang = (Management) currEmp;
                    output = "M," + output + "," + currMang.getManagerCode();

                }

                pw.println(output);
            }
            pw.close();
        }
        catch (FileNotFoundException e) {
            return null;
        }
        return file;
    }

}





