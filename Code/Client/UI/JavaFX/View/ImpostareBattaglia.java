package Client.UI.JavaFX.View;

import Client.UI.JavaFX.CustomWidget.ConditionCreatorLabel;
import Client.UI.JavaFX.CustomWidget.ICreatorCustomLabel;
import Client.UI.JavaFX.CustomWidget.ICustomLabel;
import Client.UI.UIUtils.LabelsMaker;
import Client.UI.UIUtils.StrategiaPutter;
import Client.UI.UIUtils.ViewTransaction;
import Shared.Controllers.CreareStrategiaHandler;
import Shared.Controllers.SimulareBattagliaHandler;
import Shared.Controllers.StartUpHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by gioele on 22/03/16.
 */
public class ImpostareBattaglia implements Initializable {
    public VBox StrategieSpace;
    public Label StrategiaSelezionata;
    public VBox strategiaVBox;
    public StrategiaPutter strategiaPutter;

    public Button indietroButton;

    private String where = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inizio();

        strategiaPutter = new StrategiaPutter(strategiaVBox);

    }

    public Button startBattagliaButton;



    public void startBattaglia(Event event) throws IOException {

        SimulareBattagliaHandler simulareBattagliaHandler = SimulareBattagliaHandler.getSingletonInstance();
        simulareBattagliaHandler.iniziaImpostareBattagliaCasuale();
        try {
            simulareBattagliaHandler.impostaBattaglia();

        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }



        ViewTransaction.getSingletonInstance().goToBattaglia(startBattagliaButton);

    }


    public void inizio(){

        List<Map> strategias = SimulareBattagliaHandler.getSingletonInstance().getStrategieList();

        for(final Map strategia : strategias){
            final boolean premuto = false;
            final Button nomeStrategia = new Button((String) strategia.get("nome"));
            nomeStrategia.setPrefHeight(76.0);
            nomeStrategia.setPrefWidth(313.0);
            nomeStrategia.setStyle("-fx-background-color: #F63C1A;-fx-border-width:2px;-fx-border-color:white; -fx-text-fill:white;-fx-font-size: 22px; ");
            StrategieSpace.setMargin(nomeStrategia,new Insets(10,0,0,20));
            nomeStrategia.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(premuto == false) {
                        for(Node ButtonLista : StrategieSpace.getChildren()){
                            ButtonLista.setStyle("-fx-background-color: #F63C1A;-fx-border-width:2px;-fx-border-color:white;-fx-text-fill:white;-fx-font-size: 22px;");
                        }
                        StrategiaSelezionata.setText((String) strategia.get("nome"));
                        nomeStrategia.setStyle("-fx-background-color: #1AF63C;-fx-border-width:2px;-fx-border-color:white;-fx-text-fill:black;-fx-font-size: 22px;");
                        SimulareBattagliaHandler.getSingletonInstance().scegliStrategia((String) strategia.get("id"));
                        graficaStrategia((String) strategia.get("id"));


                    }


                }
            });

            StrategieSpace.getChildren().add(nomeStrategia);
        }

    }

    public void graficaStrategia(String idStrategia){

        strategiaVBox.getChildren().clear();

        Map ccc = StartUpHandler.getSingletonInstance().getCatalogoConditionCreatorMap();
        Map cac = StartUpHandler.getSingletonInstance().getCatalogoAzCreatorMap();

        List<ICreatorCustomLabel> condCLabels = LabelsMaker.getConditionCreatorLabels(ccc);
        List<ICreatorCustomLabel> azioneCLabels = LabelsMaker.getActionCreatorLabels(cac);

        ICreatorCustomLabel currentCLabel;
        ICreatorCustomLabel currentActionLabel;



       List<Map> Strategie= SimulareBattagliaHandler.getSingletonInstance().getStrategieList();
        Map StrategiaSelezionata = null;


        for(Map Strategia : Strategie){
                if(Strategia.get("id").equals(idStrategia)) {
                    Map condidizioneDefault = (Map) Strategia.get("defaultCondition");
                    List<Map> conditionBlock = (List) Strategia.get("conditionBlock");
                    for (Map CondizioneCorrente : conditionBlock) {
                        String id = (String) CondizioneCorrente.get("id");
                        String idType = (String) CondizioneCorrente.get("idType");
                        boolean vera = (boolean) CondizioneCorrente.get("vera");
                        Map child = (Map)CondizioneCorrente.get("child");

                        boolean nonAnnidata= false;
                        boolean azione = false;
                        while(child != null) {
                            for (int i = 0; ((child != null) && (i < condCLabels.size())) ; i++) {
                                currentCLabel = condCLabels.get(i);

                                if(azione == false) {
                                    if (currentCLabel.getIdType().equals(idType)) {
                                        if(vera == false){
                                            currentCLabel.setFalse();
                                        }
                                        ICustomLabel conditionLabel = currentCLabel.makeComponent(idType, where, vera);
                                        where = id;
                                        if(child.get("idTypeAz") != null){
                                            azione = true;
                                        }
                                        else{
                                            idType = (String)child.get("idType");
                                            vera = (boolean)child.get("vera");
                                            if(child.get("child") != null){
                                                child = (Map)child.get("child");

                                            }

                                        }
                                        if (nonAnnidata == false) {
                                            strategiaPutter.addLabel(conditionLabel, true);
                                            nonAnnidata = true;
                                        } else {
                                            strategiaPutter.addLabel(conditionLabel, false);
                                        }
                                    }
                                }
                                else{
                                    for (int j = 0; j < azioneCLabels.size(); j++) {
                                        currentActionLabel = azioneCLabels.get(j);
                                        if (child != null && currentActionLabel.getIdType().equals(child.get("idTypeAz"))) {
                                            ICustomLabel azioneLabel = currentActionLabel.makeComponent((String) child.get("id"), where, true);
                                            where = null;
                                            strategiaPutter.addLabel(azioneLabel, false);
                                            child = null;
                                            azione = false;
                                        }

                                    }

                                }
                            }
                        }

                    }

                    for (int i = 0; i < condCLabels.size(); i++) {
                        currentCLabel = condCLabels.get(i);
                        if (currentCLabel.getIdType() == "000") { //Non vogliamo che ci sia la cond di defaults
                            String idCondDef = (String) condidizioneDefault.get("id");
                            ICustomLabel deafultCondizioneLabel = currentCLabel.makeComponent(idCondDef, null, true);
                            strategiaPutter.addLabel(deafultCondizioneLabel, true);
                            Map child = (Map)condidizioneDefault.get("child");
                            for (int j = 0; j < azioneCLabels.size(); j++) {
                                currentActionLabel = azioneCLabels.get(j);
                                if (child != null && currentActionLabel.getIdType().equals(child.get("idTypeAz"))) {
                                    ICustomLabel azioneLabel = currentActionLabel.makeComponent((String) child.get("id"), where, true);
                                    where = null;
                                    strategiaPutter.addLabel(azioneLabel, false);
                                }

                            }


                        }
                }



            }




            }

    }


    public void indietro(Event event){
        ViewTransaction.getSingletonInstance().goToHome(indietroButton);

    }

}
