package Client.UI.JavaFX.View.VEventi;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

/**
 * Created by gioele on 09/05/16.
 */
public class VTogliMuroEvento implements VEvento {

    GridPane CampoBattaglia;
    Map tankPersonale;
    Map tankAvversario;

    public static double dimensioneCampoCelle ;
    public static double dimensioneCampoPixel ;



    @Override
    public void eseguiti(Map Evento, List<Label> Player) throws InterruptedException {

        Map Muro = (Map) Evento.get("muro");

        if (Muro != null) {
            final Map PosizioneMuro = (Map)Muro.get("Posizione");


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    CampoBattaglia.getChildren().remove(getNodeByRowColumnIndex((int)PosizioneMuro.get("X"),(int)PosizioneMuro.get("Y"),CampoBattaglia));
                }
            });
        }








    }


    public Node getNodeByRowColumnIndex(final int column,final int row,GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            if (node instanceof Label) {
                if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                    result = node;
                    break;
                }
            }
        }
        return result;
    }


    public void setGrid(GridPane grid) {
        this.CampoBattaglia = grid;
    }

    @Override
    public void setParametriGui(List<Node> Parametri) {
        setGrid((GridPane)Parametri.get(0));
    }

    @Override
    public void setTankOnBattleGui(Map tankOnBattleGui) {
        this.tankPersonale = (Map)tankOnBattleGui.get("TankPersonale");
        this.tankAvversario = (Map)tankOnBattleGui.get("TankAvversario");

    }

    @Override
    public void setDimesioneCampo(Map dimesioneCampo) {
        dimensioneCampoCelle = (Double) dimesioneCampo.get("dimensioneCampoCelle");
        dimensioneCampoPixel = (Double) dimesioneCampo.get("dimensioneCampoPixel");
    }
}
