package payroll;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class Controller {

    @FXML
    private ToggleGroup deptToggle;

    @FXML
    private ToggleGroup employeeToggle;

    @FXML
    private ToggleGroup managerToggle;

    @FXML
    private Button add;

    @FXML
    private Button remove;

    @FXML
    private Button set;

    @FXML
    private Button clr;

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
    private MenuItem fileImportMenu;

    @FXML
    private MenuItem fileExportMenu;

    @FXML
    private MenuItem printAllMenu;

    @FXML
    private MenuItem printDeptMenu;

    @FXML
    private MenuItem printHiredMenu;

    @FXML
    private MenuItem paymentComputeMenu;

    @FXML
    private RadioButton csRadioBtn;

    @FXML
    private RadioButton itRadioBtn;

    @FXML
    private RadioButton eceRadioBtn;

    @FXML
    private RadioButton fullRadioBtn;

    @FXML
    private RadioButton partRadioBtn;

    @FXML
    private RadioButton mangRadioBtn;

    @FXML
    private RadioButton managerRadioBtn;

    @FXML
    private RadioButton departmentHeadRadioBtn;

    @FXML
    private RadioButton directorRadioBtn;

    @FXML
    private TextArea addEmployeeTextArea;

    private Company myCompany;

    private static final int MANAGER_CODE = 1;
    private static final int DEPT_HEAD_CODE = 2;
    private static final int DIRECTOR_CODE = 3;


    public Controller() {
        myCompany = new Company();
    }

    @FXML
    public void addEmployee() {

        // Name Validation for all employees
        if (nameField.getText() == null || nameField.getText() == "") {
            addEmployeeTextArea.setText("Name cannot be blank");
            return;
        }

        // Department Validation for all employees
        if (deptToggle.getSelectedToggle() == null) {
            addEmployeeTextArea.setText("Department must be selected");
            return;
        }

        // Date validation for all employees
        if (dateField.getValue() == null) {
            addEmployeeTextArea.setText("Date must be selected");
            return;
        }

        LocalDate dt = dateField.getValue();
        // DO the date beyond


        // Employee type validation for all
        if (employeeToggle.getSelectedToggle() == null) {
            addEmployeeTextArea.setText("Employee type must be selected");
            return;
        }

        String employeeType = ((RadioButton)employeeToggle.getSelectedToggle()).getText();

        if(employeeType.equals("Full Time") || employeeType.equals("Management") ) {
            if (salaryField.getText() == null || salaryField.getText() == "")   {
                addEmployeeTextArea.setText("Salary cannot be empty");
                return;
            }

            try {
                Double.parseDouble(salaryField.getText());
            }catch (Exception e) {
                addEmployeeTextArea.setText("Salary must be a number");
                return;
            }

        }else if(employeeType.equals("Part Time")) {
            if (rateField.getText() == null || rateField.getText() == "")   {
                addEmployeeTextArea.setText("Rate cannot be empty");
                return;
            }

            try {
                Integer.parseInt(rateField.getText());
            }catch (Exception e) {
                addEmployeeTextArea.setText("Rate must be a number");
                return;
            }

            if (hoursField.getText() == null || hoursField.getText() == "")   {
                addEmployeeTextArea.setText("Hour cannot be empty");
                return;
            }

            try {
                Integer.parseInt(hoursField.getText());
            }catch (Exception e) {
                addEmployeeTextArea.setText("Hour must be a number");
                return;
            }

        }

        if(employeeType.equals("Management") ) {
            if(managerToggle.getSelectedToggle() == null) {
                addEmployeeTextArea.setText("Management type must be selected");
                return;
            }
        }

        boolean added = myCompany.add(getEmployee());
        if (added) {
            addEmployeeTextArea.setText("Employee added");
        } else {
            addEmployeeTextArea.setText("Employee already exists");
        }

    }

    @FXML
    public void removeEmployee() {
        // Name Validation for all employees
        if (nameField.getText() == null || nameField.getText() == "") {
            addEmployeeTextArea.setText("Name cannot be blank");
            return;
        }

        boolean removed = myCompany.remove(getEmployee());
        if (removed) {
            addEmployeeTextArea.setText("Employee removed");
        } else {
            addEmployeeTextArea.setText("Employee doesn't exists");
        }

    }

    @FXML
    public void setHours() {

    }

    @FXML
    public void clear() {

    }

    @FXML
    public void importEmployees() {

    }

    @FXML
    public void exportEmployees() {

    }

    @FXML
    public void printAll() {

    }

    @FXML
    public void printDept() {

    }

    @FXML
    public void printHired() {

    }

    @FXML
    public void compute() {

    }

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

    private Employee getEmployee() {

        Employee employee = null;
        String newName = nameField.getText();

        String newDept = ((RadioButton) deptToggle.getSelectedToggle()).getText();



        LocalDate dt = dateField.getValue();


        String strDt = dt.getMonth().getValue() + "/" + dt.getDayOfMonth() + "/" + dt.getYear();

        Date newDateHired = new Date(strDt);


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
            pt.setHoursWorked(Integer.parseInt(hoursWorked));
            employee = pt;

        } else if (employeeType.equals("Management")) {
            int managerCode = -1;

            if (managerType.equals("Manager")) {
                managerCode = MANAGER_CODE;
            }
            if (managerType.equals("Department Head")) {
                managerCode = MANAGER_CODE;
            }
            if (managerType.equals("Director")) {
                managerCode = DIRECTOR_CODE;
            }
            employee = new Management(newProfile, Double.parseDouble(annualSalary), managerCode);


        }

        return employee;
    }


}
