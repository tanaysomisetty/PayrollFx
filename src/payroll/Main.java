package payroll;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

    /**
    Initializes the stage and the GUI.
    @author sailokesh Mondi, Tanay Somisetty
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

        /**
         Starts the scene and opens the window.
         * @param primaryStage
         * @throws Exception
         */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Payroll Processing");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

}
