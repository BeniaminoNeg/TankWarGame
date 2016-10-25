package Shared.Controllers;


import Shared.Domain.*;
import Shared.Domain.Eventi.IEvento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gioele on 22/03/16.
 */
public class SimulareBattagliaHandler {
    private static SimulareBattagliaHandler singletonInstance = null;

    public static SimulareBattagliaHandler getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SimulareBattagliaHandler();
        }
        return singletonInstance;


    }



    private Player playerLoggato;
    private Battaglia battaglia = null;
    private List<Battaglia> battaglie = null;
    private List<IEvento> eventi = new ArrayList<>();
    private CampoBattaglia campoScelto;
    private SimulareBattagliaHandler() {
        this.playerLoggato = LoginHandler.getSingletonInstance().getPlayer();
    }



    public void iniziaImpostareBattagliaCasuale(){
        ITank tankPersonale = playerLoggato.getTank();
        this.battaglia = new Battaglia(tankPersonale);
        this.battaglia.setImpostatoreBattagliaCasuale();
    }

    public void iniziaImpostareBattagliaRipetuta() {
        this.battaglie = new ArrayList<>();
    }

    public void impostaBattaglia() throws IOException {

        try{this.battaglia.scegliAvversario();
        }catch (NullPointerException e){
            throw new NullPointerException("Tank avversario non trovato");
        }
        try {
            this.battaglia.creaCampoBattaglia();
            this.campoScelto = this.battaglia.getCampoBattaglia().clone();

        }catch (NullPointerException e){
            throw new NullPointerException("Campo Battaglia non trovato");
        }

        this.battaglia.posizionaTank();

        this.battaglia.impostaTurni();

    }

    public void impostaBattagliaRipetuta(Integer numeroPartita) throws IOException {
        this.battaglie.get(numeroPartita).scegliAvversario();
        this.battaglie.get(numeroPartita).creaCampoBattaglia();
        this.battaglie.get(numeroPartita).posizionaTank();
        this.battaglie.get(numeroPartita).impostaTurni();
    }

    public void faiMossa(){
        if (!battaglia.isTerminata()){
            battaglia.faiMossa();
        }
    }

    public void faiSimulazioniStatistiche(Integer numeroVolte) throws IOException {
        ITank tankPersonale = battaglia.getTankPersonale();
        ITank tankAvversario = this.battaglia.getTankAvversario();
        double vittorie = 0;
        double sconfitte = 0;

        System.out.println("ciao");
        double pareggio = 0;
        long iniziato = System.currentTimeMillis();
        for (int i=0;i<numeroVolte;i++) {
            Battaglia b = new Battaglia(tankPersonale);
            this.battaglie.add(b);

            System.out.println("prova");

            System.out.println("modifica");

            CampoBattaglia campoCopia = this.campoScelto.clone();


            b.setImpostatoreBattagliaRipetuta(tankAvversario,campoCopia);
            impostaBattagliaRipetuta(i);
            while (!b.isTerminata()) {
                b.faiMossa();
            }
            if (b.isTerminata()) {
                String risultato;
                risultato = b.getRisultato();
                switch (risultato) {
                    case "vittoria": vittorie++;
                        break;
                    case "pareggio": pareggio++;
                        break;
                    case "sconfitta": sconfitte++;
                }
            }
        }
        long finito = System.currentTimeMillis();
        long diff = finito - iniziato;
        System.out.println("FINITOOOOO in "+diff+" Millisecondi. Vittorie: "+vittorie+", Pareggi: "+ pareggio + ", Sconfitte: "+sconfitte + " Rapporto di vincita Vinte/Perse: "+ (float)(vittorie/sconfitte));
    }

    public boolean isFinita(){
        return battaglia.isTerminata();
    }

    public String statoBattaglia() {
        return this.battaglia.getRisultato();
    }

    public Map getCampoBattaglia(){
        return battaglia.getMap();
    }

    public Map getEventiMap() {
        Map map = new HashMap();
        List<Map> eventiCorrenti = new ArrayList<>();
        for (IEvento evento: this.eventi) {
            eventiCorrenti.add(evento.getMap());
        }
        map.put("eventi",eventiCorrenti);


        System.out.println("Ciao a tutti");
        return map;
    }



    public List<IEvento> getEventi() {
        return eventi;
    }

    public void setEventi(List eventi) {
        this.eventi = eventi;
    }


    public void getState(){
        battaglia.getState();
    }

    public Player getPlayerLoggato() {
        return playerLoggato;
    }

    public void setPlayerLoggato(Player playerLoggato) {
        playerLoggato = playerLoggato;
    }

    public void scegliStrategia(String idStrategia) {
        this.playerLoggato.scegliStrategia(idStrategia);
    }

    public List getStrategieList() {
        List<Strategia> strategieList =  this.playerLoggato.getStrategieList();
        List strategie = new ArrayList<>();
        for (Strategia s : strategieList) {
            strategie.add(s.getMap());
        }
        return strategie;
    }
}
//Sto facendo
