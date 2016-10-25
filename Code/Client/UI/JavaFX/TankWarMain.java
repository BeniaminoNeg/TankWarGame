package Client.UI.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TankWarMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Fxml/login.fxml"));

        primaryStage.setTitle("Tank War");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));

        //primaryStage.getIcons().add(new Image("file:../../../../Image/tankicon.png"));

        /*
        primaryStage.getIcons().add(
                new Image(
                        TankWarMain.class.getResourceAsStream( "../../../../Image/tankicon.png" )));
        */


        primaryStage.show();

    }
}
