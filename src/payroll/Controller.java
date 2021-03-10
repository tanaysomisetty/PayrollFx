package payroll;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {
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
    public void addEmployee() {
        System.out.println("User logged in");
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
