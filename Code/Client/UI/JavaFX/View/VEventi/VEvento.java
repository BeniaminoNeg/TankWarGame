package Client.UI.JavaFX.View.VEventi;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

/**
 * Created by gioele on 05/05/16.
 */
public interface VEvento {

   void eseguiti(Map Evento, List<Label> Player) throws InterruptedException;

    void setParametriGui(List<Node> Parametri);

    void setTankOnBattleGui(Map tankOnBattleGui);

    void setDimesioneCampo(Map dimesioneCampo);




}
