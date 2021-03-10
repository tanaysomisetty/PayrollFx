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
    private TextField hoursField;

    @FXML
    private TextField rateField;

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

}
