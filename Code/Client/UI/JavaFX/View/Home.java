package Client.UI.JavaFX.View;

import Client.UI.UIUtils.ViewTransaction;
import Shared.Controllers.SimulareBattagliaHandler;
import Shared.Controllers.CreareStrategiaHandler;
import Shared.Controllers.LoginHandler;
import Shared.Domain.Player;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by beniamino on 03/03/16.
 */
public class Home implements Initializable {
    public Button startButton;
    public Button impostabattagliaButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startDemo(Event event) {

        CreareStrategiaHandler.getSingletonInstance().iniziaNuovaStrategia();

        ViewTransaction.getSingletonInstance().goToCreareStrategia(startButton);


    }

    public void impostaBattaglia(Event event){

        SimulareBattagliaHandler.getSingletonInstance().iniziaImpostareBattagliaCasuale();
        ViewTransaction.getSingletonInstance().goToImpostaBattaglia(impostabattagliaButton);

    }

/*
    public Map getStrategieMap(){
        Player playerLoggato = LoginHandler.getSingletonInstance().getPlayer();

        return null;

    }
    */
}
