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

    public Controller(){
        myCompany = new Company();
    }

    @FXML
    public void addEmployee() {

        String newName = nameField.getText();
        String newDept = ((RadioButton)deptToggle.getSelectedToggle()).getText();

        System.out.println(newDept);

        LocalDate  dt = dateField.getValue();
        String strDt = dt.getMonth().getValue()+"/"+dt.getDayOfMonth()+"/"+dt.getYear();

        Date newDateHired = new Date(strDt);

        String employeeType = ((RadioButton)employeeToggle.getSelectedToggle()).getText();
        String annualSalary = salaryField.getText();
        String hoursWorked = hoursField.getText();
        String rate = rateField.getText();

        String managerType = null;

        if(managerToggle.getSelectedToggle() != null) {
            managerType = ((RadioButton) managerToggle.getSelectedToggle()).getText();
        }

        Profile newProfile = new Profile(newName, newDept, newDateHired);

        if(employeeType.equals("Full Time")) {
            Fulltime newFT = new Fulltime(newProfile, Double.parseDouble(annualSalary));
            boolean added = myCompany.add(newFT);
            if (added) {
                addEmployeeTextArea.setText(newFT.toString()+" added");
            }else  {
                addEmployeeTextArea.setText(newFT.toString()+" already exists");
            }
        }else if(employeeType.equals("Part Time")) {
            Parttime newPT = new Parttime(newProfile, Double.parseDouble(rate));
            newPT.setHoursWorked(Integer.parseInt(hoursWorked));
            boolean added = myCompany.add(newPT);
            if (added) {
                addEmployeeTextArea.setText(newPT.toString()+" added");
            }else  {
                addEmployeeTextArea.setText(newPT.toString()+" already exists");
            }
        }else if(employeeType.equals("Management")) {
            Management newMG = new Management(newProfile, Double.parseDouble(annualSalary), 1);
            boolean added = myCompany.add(newMG);
            if (added) {
                addEmployeeTextArea.setText(newMG.toString()+" added");
            }else  {
                addEmployeeTextArea.setText(newMG.toString()+" already exists");
            }

        }



       // System .out.println("User logged in1"+name+department+dtHired);
    }

    @FXML
    public void removeEmployee() {

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



}
