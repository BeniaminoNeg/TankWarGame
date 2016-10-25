package Client.UI.JavaFX.View.VEventi;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

/**
 * Created by gioele on 05/05/16.
 */
public class VInCorsoEvento implements VEvento {

    private GridPane CampoBattaglia;
    private Label NumeroTurni;

    @Override
    public void eseguiti(Map Evento, List<Label> Player) {

        Map Turno = (Map)Evento.get("turno");

        final int NumeroTurno = (int)Turno.get("NumeroTurno");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                NumeroTurni.setText(" Turno Numero : "+NumeroTurno);
            }
        });




    }

    public void setGrid(GridPane grid) {
            this.CampoBattaglia = grid;
    }


    public void setNumeroTurni(Label turni){
        this.NumeroTurni = turni;
    }

    @Override
    public void setParametriGui(List<Node> Parametri) {
        setGrid((GridPane)Parametri.get(0));
        setNumeroTurni((Label)Parametri.get(1));
    }

    @Override
    public void setTankOnBattleGui(Map tankOnBattleGui) {

    }

    @Override
    public void setDimesioneCampo(Map dimesioneCampo) {

    }

}
