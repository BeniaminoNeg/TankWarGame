package Client.UI.UIUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by beniamino on 08/03/16.
 */
public class ViewTransaction {
    private static ViewTransaction ourInstance = new ViewTransaction();

    private ViewTransaction() {
    }

    public static ViewTransaction getSingletonInstance() {
        return ourInstance;
    }

    public void goToHome(Node transactionNode) {
        Stage stage;
        Parent root = null;

        stage = (Stage) transactionNode.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("../JavaFX/Fxml/home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setFullScreenExitHint("");

       stage.setFullScreen(true);

        stage.show();
    }

    public void goToCreareStrategia(Node transactionNode) {
        Stage stage;
        Parent root = null;

        stage = (Stage) transactionNode.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("../JavaFX/Fxml/crearestrategia.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setFullScreenExitHint("");
       stage.setFullScreen(true);
        stage.show();
    }

    public void goToLogin(Node transactionNode) {
        Stage stage;
        Parent root = null;

        stage = (Stage) transactionNode.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("../JavaFX/Fxml/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);


        stage.setFullScreen(true);
        stage.show();
    }

    public void goToImpostaBattaglia(Node transactionNode){
        Stage stage;
        Parent root = null;

        stage = (Stage) transactionNode.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("../JavaFX/Fxml/impostareBattaglia.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setFullScreenExitHint("");

       stage.setFullScreen(true);

        stage.show();


    }

    public void goToBattaglia(Node transactionNode){
        Stage stage;
        Parent root = null;

        stage = (Stage) transactionNode.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("../JavaFX/Fxml/Battaglia.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setFullScreenExitHint("");
       stage.setFullScreen(true);
        stage.show();


    }

}
