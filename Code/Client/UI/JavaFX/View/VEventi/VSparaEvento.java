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
 * Created by Gioele on 06/05/2016.
 */
public class VSparaEvento implements VEvento {


    private GridPane CampoBattaglia;
    private Map tankPersonale;
    private Map tankAvversario;

    public static double dimensioneCampoCelle ;
    public static double dimensioneCampoPixel ;

    @Override
    public void eseguiti(Map Evento, List<Label> Player) throws InterruptedException {

        List<Map> caselleSparoPropagazione = (List)Evento.get("casellePropagazione");

        Map tankColpito= (Map)Evento.get("TankColpito");
        boolean vivo;
        if (tankColpito!=null) {
            vivo = (boolean)tankColpito.get("Vivo");
        } else {
            vivo = true;
        }


        final Label colpo = creaColpo();

            for(Map casellaPropagazione : caselleSparoPropagazione){

                final Map posizione = (Map)casellaPropagazione.get("Posizione");


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        CampoBattaglia.getChildren().remove(colpo);
                        CampoBattaglia.add(colpo,(int)posizione.get("X"),(int)posizione.get("Y"));


                    }

                });
                Thread.sleep(50);
            }

        Thread.sleep(20);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                    CampoBattaglia.getChildren().remove(colpo);

            }
        });


        Map CasellaTankColpito = (Map)Evento.get("casellaTank");
        if(CasellaTankColpito!= null){

            for(final Label PlayerCorrente : Player){
                if(PlayerCorrente.getId().equals((String)CasellaTankColpito.get("tankOccupanteCasella"))){
                    if(!vivo) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                PlayerCorrente.setGraphic(getTankEsploso());
                            }
                        });
                    }
                }
            }


        }

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


    private Label creaColpo(){

        Label Colpo = new Label();
        Image immagine = null;
        immagine = new Image("Client/UI/JavaFX/View/Image/Colpo.png");

        ImageView imageView = new ImageView(immagine);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(20);


        Colpo.setGraphic(imageView);
        Colpo.setPrefHeight(10);
        Colpo.setPrefWidth(10);
        Colpo.setPadding(new Insets(0,0,0,((dimensioneCampoPixel/dimensioneCampoCelle)/4)));


        return Colpo;

    }


}
