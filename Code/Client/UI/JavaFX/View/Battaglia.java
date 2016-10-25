
package Client.UI.JavaFX.View;

import Client.UI.JavaFX.View.VEventi.VEvento;
import Client.UI.UIUtils.ViewTransaction;
import Shared.Controllers.SimulareBattagliaHandler;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;


import java.net.URL;
import java.util.*;

/**
 * Created by gioele on 22/03/16.
 */
public class Battaglia implements Initializable {

    public Button indietroButton;
    public GridPane CampoBattagliaGrid;
    public static double dimensioneCampoCelle = 10;


    public static double dimensioneCampoPixel ;
    public Label numeroTurni;
    public Label risultatoBattaglia;
    public HBox RisultatoHbox;

    Thread th;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      dimensioneCampoPixel = CampoBattagliaGrid.getPrefWidth();


        final SimulareBattagliaHandler simulareBattagliaHandler = SimulareBattagliaHandler.getSingletonInstance();


        //QUI INIZIA IL SETTAGGIO DELLA GRAFICA

       Map Battaglia = simulareBattagliaHandler.getCampoBattaglia();

        Map CampoBattaglia = (Map)Battaglia.get("CampoBattaglia");

        List<HashMap> CampoBat = new ArrayList<HashMap>((List)CampoBattaglia.get("CampoBattaglia"));

        Map[][] campo = new HashMap[10][10];

        for(int i=0;i<(10*10);i++){
            Integer x = i % 10;
            Integer y = (i-x)/10;
            campo[x][y] = CampoBat.get(i);
        }
        for(int i=0; i<dimensioneCampoCelle ; i++){
            ColumnConstraints colonna = new ColumnConstraints();
            colonna.setPercentWidth(100/dimensioneCampoCelle);

            CampoBattagliaGrid.getColumnConstraints().add(colonna);

            RowConstraints riga = new RowConstraints();
            riga.setPercentHeight(100/dimensioneCampoCelle);
            CampoBattagliaGrid.getRowConstraints().add(riga);
        }
        for(int i =0 ; i< 10 ; i++){
            for(int j=0 ; j<10; j++){
                Map elemento = campo[i][j];
                if(elemento.get("Tipo").equals("MuroCasella")){
                    Label Muro = new Label();
                    Muro.setPrefHeight(100);
                    Muro.setPrefWidth(100);
                    Muro.setStyle("-fx-background-color: #000000");


                    CampoBattagliaGrid.add(Muro,i,j);
                }
            }
        }

        // Creazione 1 Player
        final Label PlayerCorrente = new Label();
        Image immagine = new Image("Client/UI/JavaFX/View/Image/tank.png");
        ImageView imageView = new ImageView(immagine);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(CampoBattagliaGrid.getPrefHeight()/dimensioneCampoCelle-10);

        PlayerCorrente.setGraphic(imageView);
        PlayerCorrente.setPrefHeight(10);
        PlayerCorrente.setPrefWidth(10);

      //  PlayerCorrente.setPadding(new Insets(0,0,0,(dimensioneCampoPixel/dimensioneCampoCelle)/4));

        // Creazione Secondo Player

        final Label PlayerAvversario = new Label();
        Image immagineAvversario = new Image("Client/UI/JavaFX/View/Image/tankAvversario.png");
        ImageView imageViewAvversario = new ImageView(immagineAvversario);
        imageViewAvversario.setPreserveRatio(true);
        imageViewAvversario.setFitHeight(CampoBattagliaGrid.getPrefHeight()/dimensioneCampoCelle-10);

        PlayerAvversario.setGraphic(imageViewAvversario);
        PlayerAvversario.setPrefHeight(10);
        PlayerAvversario.setPrefWidth(10);


     //   PlayerAvversario.setPadding(new Insets(0,0,0,(dimensioneCampoPixel/dimensioneCampoCelle)/4));

        // Posizionamento Tank

        Map TankPersonale = (Map)Battaglia.get("TankPersonale");

        PlayerCorrente.setId((String) TankPersonale.get("Id"));

        Map CasellaPosizioneTankPersonale = (Map)TankPersonale.get("CasellaPosizione");
        int OrientamentoTankPersonale = (int)TankPersonale.get("Orientamento");
        double OrientamentoGradiTankPersonale = 0;
        switch (OrientamentoTankPersonale) {
            case 0: OrientamentoGradiTankPersonale = 0 ;
                break;
            case 1: OrientamentoGradiTankPersonale = 90;
                break;
            case 2: OrientamentoGradiTankPersonale = 180;
                break;
            case 3: OrientamentoGradiTankPersonale = -90;

        }
        PlayerCorrente.setRotate(OrientamentoGradiTankPersonale);

        Map PosizioneTankPersonale = (Map)CasellaPosizioneTankPersonale.get("Posizione");



        Map TankAvversario = (Map)Battaglia.get("TankAvversario");

        PlayerAvversario.setId((String) TankAvversario.get("Id"));

        Map CasellaPosizioneTankAvversario = (Map)TankAvversario.get("CasellaPosizione");
        int OrientamentoTankAvversario = (int)TankAvversario.get("Orientamento");
        double OrientamentoGradiTankAvversario = 0;
        switch (OrientamentoTankAvversario) {
            case 0: OrientamentoGradiTankAvversario = 0 ;
                break;
            case 1: OrientamentoGradiTankAvversario = 90;
                break;
            case 2: OrientamentoGradiTankAvversario= 180;
                break;
            case 3: OrientamentoGradiTankAvversario= -90;

        }
        PlayerAvversario.setRotate(OrientamentoGradiTankAvversario);

        Map PosizioneTankAvversario = (Map)CasellaPosizioneTankAvversario.get("Posizione");

        CampoBattagliaGrid.add(PlayerCorrente, (int)PosizioneTankPersonale.get("X"), (int)PosizioneTankPersonale.get("Y"));
        CampoBattagliaGrid.add(PlayerAvversario, (int)PosizioneTankAvversario.get("X"), (int)PosizioneTankAvversario.get("Y"));


        final List PlayerLabel = new ArrayList<>();
        PlayerLabel.add(PlayerCorrente);
        PlayerLabel.add(PlayerAvversario);




        //QUI FINISCE IL SETTAGGIO GRAFICA

        //Si battaglia ragazzi
        Integer i=0;

            // Inizializzo tutti I parametri NODE da passare ai vari eventi
        final List<Node> ParametriEventi = new ArrayList<>();
        ParametriEventi.add(CampoBattagliaGrid);
        ParametriEventi.add(numeroTurni);
        ParametriEventi.add(risultatoBattaglia);
        ParametriEventi.add(RisultatoHbox);


        final Map TankInBattle = new HashMap<>();
        TankInBattle.put("TankPersonale",TankPersonale);
        TankInBattle.put("TankAvversario",TankAvversario);


        final Map dimensioneCampo = new HashMap<>();
        dimensioneCampo.put("dimensioneCampoCelle",dimensioneCampoCelle);
        dimensioneCampo.put("dimensioneCampoPixel",dimensioneCampoPixel);


        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (!SimulareBattagliaHandler.getSingletonInstance().isFinita()) {

                        final int finalI = i;


                        SimulareBattagliaHandler.getSingletonInstance().faiMossa();

                        // su questa lista ci sono gli eventi avvenuti nel dominio
                        List<Map> Eventi = (List)simulareBattagliaHandler.getEventiMap().get("eventi");


                        for (Map e : Eventi){
                                Class classe = Class.forName("Client.UI.JavaFX.View.VEventi.V" + e.get("nomeEvento"));
                                VEvento eventoIstanziato = (VEvento) classe.newInstance();
                                eventoIstanziato.setParametriGui(ParametriEventi);
                                eventoIstanziato.setTankOnBattleGui(TankInBattle);
                                eventoIstanziato.setDimesioneCampo(dimensioneCampo);
                                eventoIstanziato.eseguiti(e, PlayerLabel);

                        }

                        i++;
                        Thread.sleep(80);



                }

                return null;
            }
        };




        th = new Thread(task);
        th.start();



    }



    public void indietro(Event event) throws InterruptedException {

        ViewTransaction.getSingletonInstance().goToHome(indietroButton);
        th.interrupt();
        System.out.println("Stato thread Campo Battaglia : "+th.getState().toString());

    }
}
