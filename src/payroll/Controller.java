package payroll;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.text.SimpleDateFormat;

/**
 The "control" class that is part of the MVC-design pattern.
 Implements functionalities for the GUI, such as importing,exporting,computing and
 printing payments for all employees.
 @author Sailokesh Mondi, Tanay Somisetty

 */

public class Controller {

    private static final int MANAGER_CODE = 1;
    private static final int DEPT_HEAD_CODE = 2;
    private static final int DIRECTOR_CODE = 3;
    @FXML
    private ToggleGroup deptToggle;
    @FXML
    private ToggleGroup employeeToggle;
    @FXML
    private ToggleGroup managerToggle;
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField salaryField;
    @FXML
    private Label salaryLabel;
    @FXML
    private TextField hoursField;
    @FXML
    private Label hoursLabel;
    @FXML
    private TextField rateField;
    @FXML
    private Label rateLabel;
    @FXML
    private RadioButton managerRadioBtn;
    @FXML
    private RadioButton departmentHeadRadioBtn;
    @FXML
    private RadioButton directorRadioBtn;
    @FXML
    private TextArea textArea;

    private final Company myCompany;


    public Controller() {
        myCompany = new Company();
    }

    /**
     Method to add an employee to the GUI database.
     Checks for all validations and handle all exceptions.
     @param 'none'
     */
    @FXML
    public void addEmployee() {

        if (doBasicValidation()) {

            // Employee type validation for all
            if (employeeToggle.getSelectedToggle() == null) {
                textArea.appendText("Employee type must be selected"  + "\n");
                return;
            }

            String employeeType = ((RadioButton) employeeToggle.getSelectedToggle()).getText();

            if (employeeType.equals("Full Time") || employeeType.equals("Management")) {
                if (salaryField.getText() == null || salaryField.getText() == "") {
                    textArea.appendText("Salary cannot be empty"  + "\n");
                    return;
                }

                try {
                    Double.parseDouble(salaryField.getText());
                } catch (Exception e) {
                    textArea.appendText("Salary must be a number" + "\n");
                    return;
                }

            } else if (employeeType.equals("Part Time")) {
                if (rateField.getText() == null || rateField.getText() == "") {
                    textArea.appendText("Rate cannot be empty"  + "\n");
                    return;
                }

                try {
                    Double.parseDouble(rateField.getText());
                } catch (Exception e) {
                    textArea.appendText("Rate must be a number"  + "\n");
                    return;
                }

            }

            if (employeeType.equals("Management")) {
                if (managerToggle.getSelectedToggle() == null) {
                    textArea.appendText("Management type must be selected"  + "\n");
                    return;
                }
            }

            boolean added = myCompany.add(getEmployee());
            if (added) {
                textArea.appendText("Employee added"  + "\n");
            } else {
                textArea.appendText("Employee already exists"  + "\n");
            }
        }

    }

    /**
     Method to remove an employee from GUI database and calls remove in Company class
     Checks for all validations and handles all exceptions.
     @param 'none'
     */
    @FXML
    public void removeEmployee() {
        if (doBasicValidation()) {
            boolean removed = myCompany.remove(getBasicEmployee());
            if (removed) {
                textArea.appendText("Employee removed"  + "\n");
            } else {
                textArea.appendText("Employee doesn't exist"  + "\n");
            }
        }
    }

    /**
     Method to set hours for a part-time employee for GUI database and calls setHours in Company class.
     Checks for all validations and handles all exceptions.
     @param 'none'
     */
    @FXML
    public void setHours() {
        if (doBasicValidation() && doHoursValidation()) {
            Employee employee = getBasicEmployee();
            Parttime parttime = new Parttime(employee.getProfile(), 0);
            parttime.setHoursWorked(Integer.parseInt(hoursField.getText()));
            boolean success = myCompany.setHours(parttime);
            if(success) {
                textArea.appendText("Working Hours set"  + "\n");
            }else {
                textArea.appendText("Employee doesn't exist"  + "\n");
            }
        }

    }

    /**
     Method to clear all fields entered into the GUI including radio buttons for employee type and department type.
     @param 'none'
     */
    @FXML
    public void clear() {
        nameField.appendText("");
        deptToggle.selectToggle(null);
        dateField.setValue(null);
        employeeToggle.selectToggle(null);
        salaryField.appendText("");
        hoursField.appendText("");
        rateField.appendText("");
        managerToggle.selectToggle(null);

    }

    /**
     Method to import a file to the GUI database.
     Checks if file is not selected.
     @param 'none'
     */
    @FXML
    public void importEmployees() {

      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().add(new ExtensionFilter("TXT Files", "*.txt"));
      File file = fileChooser.showOpenDialog(null);

      try {
          Scanner sc = new Scanner(file);
          textArea.appendText("File imported" + "\n");
          while (sc.hasNextLine()) {
              String line = sc.nextLine();
              String[] tokens = line.split(",");

              final int EMP_TYPE_INDEX = 0;
              final int NAME_INDEX = 1;
              final int DEPT_INDEX = 2;
              final int DATE_INDEX = 3;
              final int COMP_INDEX = 4;
              final int MANG_INDEX = 5;

              final int FIRST_NAME_INDEX = 0;
              final int LAST_NAME_INDEX = 1;

              String[] nameTokens = tokens[NAME_INDEX].split("\\s+");
              String name = nameTokens[LAST_NAME_INDEX] + "," + nameTokens[FIRST_NAME_INDEX];
              Date date = new Date(tokens[DATE_INDEX]);
              Profile profile = new Profile(name, tokens[DEPT_INDEX], date);

              if (tokens[EMP_TYPE_INDEX].equals("P")) {
                Parttime parttime = new Parttime(profile, Double.parseDouble(tokens[COMP_INDEX]));
                myCompany.add(parttime);

              }
              else if (tokens[EMP_TYPE_INDEX].equals("F")) {
                  Fulltime fulltime = new Fulltime(profile, Double.parseDouble(tokens[COMP_INDEX]));
                  myCompany.add(fulltime);
              }

              else if (tokens[EMP_TYPE_INDEX].equals("M")) {
                  Management management = new Management(profile, Double.parseDouble(tokens[COMP_INDEX]),
                          Integer.parseInt(tokens[MANG_INDEX]));
                  myCompany.add(management);
              }
          }
          printAll();
          sc.close();
      }
      catch (FileNotFoundException e) {
          textArea.appendText("No File Selected" + "\n");
      }

    }

    /**
     Method to export the file from the database.
     Checks to see if database is empty.
     @param 'none'
     */
    @FXML
    public void exportEmployees() {
        if (myCompany.exportDatabase() == null) {
            textArea.appendText("Cannot export file, employee database may be empty" + "\n");
        }
        else {
            File file = myCompany.exportDatabase();
            textArea.appendText("Employee database exported, saved at " + file.getAbsolutePath() + "\n");
            printAll();
        }
    }

    /**
     Method to print all employees in the current order.
     Calls print method in Company class.
     @param 'none'
     */
    @FXML
    public void printAll() {
        textArea.appendText(myCompany.print());
    }

    /**
     Method to print all employees by department.
     Calls printByDepartment in Company class.
     @param 'none
     */
    @FXML
    public void printDept() {
        textArea.appendText(myCompany.printByDepartment());
    }

    /**
     Method to print all employees by date hired.
     Calls printByDate in Company class.
     @param 'none'
     */
    @FXML
    public void printHired() {
        textArea.appendText(myCompany.printByDate());
    }

    /**
     Method to compute calculations for all employees.
     Calls processPayments in Company class.
     @param 'none'
     */
    @FXML
    public void compute() {
        int numEmployee = myCompany.getNumEmployee();
        if (numEmployee == 0) {
            textArea.appendText("Employee database is empty." + "\n");
        }
        else {
            myCompany.processPayments();
            textArea.appendText("Calculation of employee payments is done." + "\n");
        }
    }

    /**
     Method to display only properties for a Full-time employee and hide other fields
     @param 'none'
     @return void return type
     */
    @FXML
    public void hideForFullTime() {
        hoursLabel.setVisible(false);
        hoursField.setVisible(false);
        rateLabel.setVisible(false);
        rateField.setVisible(false);
        managerRadioBtn.setVisible(false);
        departmentHeadRadioBtn.setVisible(false);
        directorRadioBtn.setVisible(false);
        salaryLabel.setVisible(true);
        salaryField.setVisible(true);
    }

    /**
     Method to display only properties for a Part-time employee and hide other fields
     @param 'none'
     @return void return type
     */
    @FXML
    public void hideForPartTime() {
        managerRadioBtn.setVisible(false);
        departmentHeadRadioBtn.setVisible(false);
        directorRadioBtn.setVisible(false);
        salaryLabel.setVisible(false);
        salaryField.setVisible(false);

        hoursLabel.setVisible(true);
        hoursField.setVisible(true);
        rateLabel.setVisible(true);
        rateField.setVisible(true);
    }

    /**
     Method to display only properties for a Management employee and hide other fields
     @param 'none'
     @return void return type
     */
    @FXML
    public void hideForManagement() {
        hoursLabel.setVisible(false);
        hoursField.setVisible(false);
        rateLabel.setVisible(false);
        rateField.setVisible(false);

        managerRadioBtn.setVisible(true);
        departmentHeadRadioBtn.setVisible(true);
        directorRadioBtn.setVisible(true);
        salaryLabel.setVisible(true);
        salaryField.setVisible(true);
    }

    /**
     Helper method to get the employee type with all fields pertaining to the emplyoee
     @param 'none'
     @return Employee object
     */
    private Employee getEmployee() {

        Employee employee = null;
        String newName = nameField.getText();

        String newDept = ((RadioButton) deptToggle.getSelectedToggle()).getText();


        Date newDateHired = new Date(dateField.getEditor().getText());


        String newSalary = salaryField.getText();


        String newHours = hoursField.getText();


        String newRate = rateField.getText();


        String employeeType = ((RadioButton) employeeToggle.getSelectedToggle()).getText();
        String annualSalary = salaryField.getText();
        String hoursWorked = hoursField.getText();
        String rate = rateField.getText();

        String managerType = null;


        if (managerToggle.getSelectedToggle() != null) {
            managerType = ((RadioButton) managerToggle.getSelectedToggle()).getText();
        }

        Profile newProfile = new Profile(newName, newDept, newDateHired);

        if (employeeType.equals("Full Time")) {

            employee = new Fulltime(newProfile, Double.parseDouble(annualSalary));

        } else if (employeeType.equals("Part Time")) {
            Parttime pt = new Parttime(newProfile, Double.parseDouble(rate));
            employee = pt;
        } else if (employeeType.equals("Management")) {
            int managerCode = -1;

            if (managerType.equals("Manager")) {
                managerCode = MANAGER_CODE;
            }
            if (managerType.equals("Department Head")) {
                managerCode = DEPT_HEAD_CODE;
            }
            if (managerType.equals("Director")) {
                managerCode = DIRECTOR_CODE;
            }
            employee = new Management(newProfile, Double.parseDouble(annualSalary), managerCode);


        }

        return employee;
    }

    /**
     Helper method to do all hours validation for invalid hours
     @param 'none'
     * @return false if hours are negative or over 100, true otherwise
     */

    private boolean doHoursValidation() {
        final int MAX_HOURS = 100;

        if (hoursField.getText() == null) {
            textArea.appendText("Invalid working hours"  + "\n");
            return false;
        }


            int hours = -1;
            try {
                hours = Integer.parseInt(hoursField.getText());
            } catch (Exception e) {
                textArea.appendText("Invalid working hours"  + "\n");
                return false;
            }

            if (hours <= 0 || hours > MAX_HOURS) {
                textArea.appendText("Invalid working hours"  + "\n");
                return false;
            }

        return true;
    }

    /**
     Helper method to validate errors such as name fields,departments, and dates
     @param 'none'
     *@return false if either name is empty, department is not selected, or date is invalid, true otherwise
     */

    private boolean doBasicValidation() {

        // Name Validation for all employees
        if (nameField.getText() == null || nameField.getText() == "") {
            textArea.appendText("Name cannot be blank"  + "\n");
            return false;
        }

        // Department Validation for all employees
        if (deptToggle.getSelectedToggle() == null) {
            textArea.appendText("Department must be selected"  + "\n");
            return false;
        }

        if (!isValidDate()) {
            textArea.appendText("Invalid date"  + "\n");
            return false;
        }
        return true;

    }

    /**
     Helper method to get the name, department, date hired, and profile fields of an employee
     @param 'none'
     * @return Employee object
     */
    private Employee getBasicEmployee() {


        String newName = nameField.getText();

        String newDept = ((RadioButton) deptToggle.getSelectedToggle()).getText();


        Date newDateHired = new Date(dateField.getEditor().getText());


        Profile newProfile = new Profile(newName, newDept, newDateHired);

        return new Employee(newProfile, 0);
    }

    /**
     Helper method which checks for date validation by calling isValid in Date class
     @param 'none'
     @return true if the date is valid, false otherwise
     */
    private boolean isValidDate() {

        String strDt = dateField.getEditor().getText();


        // Date validation for all employees
        if (strDt == null) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
            sdf.parse(strDt);
            Date newDateHired = new Date(strDt);
            if (!newDateHired.isValid()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}
