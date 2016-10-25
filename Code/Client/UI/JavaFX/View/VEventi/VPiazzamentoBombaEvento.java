package Client.UI.JavaFX.View.VEventi;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

/**
 * Created by gioele on 05/05/16.
 */
public class VPiazzamentoBombaEvento implements VEvento {

    GridPane CampoBattaglia;
    Map tankPersonale;
    Map tankAvversario;

    public static double dimensioneCampoCelle ;
    public static double dimensioneCampoPixel ;

    @Override
    public void eseguiti(Map Evento, List<Label> Player) {

        final Map casella = (Map)Evento.get("casella");
        final String TankOccup = (String) casella.get("tankOccupanteCasella");
        final Map PosizioneCasella = (Map) casella.get("Posizione");

        final Label Bomba = CreaBomba(TankOccup);


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                CampoBattaglia.add(Bomba,(int)PosizioneCasella.get("X"),(int)PosizioneCasella.get("Y"));
            }
        });


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


    private Label CreaBomba(String TankOccupante){

        Label Bomba = new Label();
        Image immagine = null;
        if(tankPersonale.get("Id").equals(TankOccupante)) {
            immagine = new Image("Client/UI/JavaFX/View/Image/Bomba.png");
        }
        else{
            immagine = new Image("Client/UI/JavaFX/View/Image/BombaAvversario.png");
        }
        ImageView imageView = new ImageView(immagine);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(25);


        Bomba.setGraphic(imageView);
        Bomba.setPrefHeight(10);
        Bomba.setPrefWidth(10);
        Bomba.setPadding(new Insets(0,0,0,((dimensioneCampoPixel/dimensioneCampoCelle)/4)));


        return Bomba;
    }

}
