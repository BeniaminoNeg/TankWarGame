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
public class VMovimentoEvento implements VEvento{


    private GridPane CampoBattaglia;

    public VMovimentoEvento(){

    }



    @Override
    public void eseguiti(Map Evento,List<Label> Player) {

        Map tankTurno = (Map)Evento.get("tankTurno");

        for(final Label playerLabel : Player){
            if(playerLabel.getId().equals(tankTurno.get("Id"))){
                Map CasellaPosizioneTank = (Map) tankTurno.get("CasellaPosizione");
                int OrientamentoTank = (int) tankTurno.get("Orientamento");
                double OrientamentoGradiTank = 0;
                switch (OrientamentoTank) {
                    case 0:
                        OrientamentoGradiTank= 0;
                        break;
                    case 1:
                        OrientamentoGradiTank= 90;
                        break;
                    case 2:
                        OrientamentoGradiTank= 180;
                        break;
                    case 3:
                        OrientamentoGradiTank = -90;

                }


                playerLabel.setRotate(OrientamentoGradiTank);

                final Map PosizioneTank = (Map) CasellaPosizioneTank.get("Posizione");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        CampoBattaglia.getChildren().remove(playerLabel);
                        CampoBattaglia.add(playerLabel,(int)PosizioneTank.get("X"),(int)PosizioneTank.get("Y"));

                    }
                });

            }
        }

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

    }

    @Override
    public void setDimesioneCampo(Map dimesioneCampo) {

    }


}
