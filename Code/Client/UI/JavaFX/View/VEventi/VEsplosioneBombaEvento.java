package Client.UI.JavaFX.View.VEventi;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

/**
 * Created by Gioele on 07/05/2016.
 */
public class VEsplosioneBombaEvento implements VEvento {


    GridPane CampoBattaglia;
    Map tankPersonale;
    Map tankAvversario;

    public static double dimensioneCampoCelle ;
    public static double dimensioneCampoPixel ;

    @Override
    public void eseguiti(Map Evento, List<Label> Player) throws InterruptedException {

        Map tankEsploso = (Map)Evento.get("TankEsploso");
        String idTankOccupanteCasellaEsplosa = (String)tankEsploso.get("Id");
        Map casellaPosizioneTankEsploso = (Map)tankEsploso.get("CasellaPosizione");
        final Map posizioneTankEsploso = (Map)casellaPosizioneTankEsploso.get("Posizione");
        boolean vivo = (boolean)tankEsploso.get("Vivo");



        for(final Label PlayerCorrente : Player){
            if(PlayerCorrente.getId().equals(idTankOccupanteCasellaEsplosa)){
                if(!vivo) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            PlayerCorrente.setGraphic(getTankEsploso());
                        }
                    });
                }
                else{
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            CampoBattaglia.getChildren().remove(getNodeByRowColumnIndex((int)posizioneTankEsploso.get("X"),(int)posizioneTankEsploso.get("Y"),CampoBattaglia));
                        }
                    });
                }
            }

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



    private ImageView getTankEsploso(){
        Image immagineTankEsploso = new Image("Client/UI/JavaFX/View/Image/TankEsploso.png");
        ImageView imageView = new ImageView(immagineTankEsploso);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(dimensioneCampoPixel/dimensioneCampoCelle-10);

        return imageView;
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
