package Shared.Domain;

import Shared.Controllers.SimulareBattagliaHandler;
import Shared.Domain.Eventi.*;
import Shared.Domain.StartupBattle.IImpostatoreBattaglia;
import Shared.Domain.StartupBattle.ImpostatoreBattagliaCasuale;
import Shared.Domain.StartupBattle.ImpostatoreBattagliaRipetuta;
import Shared.Domain.TankDecorator.TankOnBattle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gioele on 22/03/16.
 */
public class Battaglia {

    private boolean terminata;
    private ITank tankPersonale;
    private ITank tankAvversario;
    private CampoBattaglia campoBattaglia;
    private Turno turno;
    private IImpostatoreBattaglia impBattaglia;
    private String risultato = "inCorso";



    public Battaglia(ITank tankPersonale){
        terminata = false;
        this.tankPersonale = new TankOnBattle(tankPersonale);
    }

    public void setImpostatoreBattagliaCasuale() {
        this.impBattaglia=new ImpostatoreBattagliaCasuale();
    }

    public void setImpostatoreBattagliaRipetuta(ITank tankAvversario,CampoBattaglia campoScelto) {
        this.impBattaglia = new ImpostatoreBattagliaRipetuta(tankAvversario,campoScelto);
    }

    public  Battaglia(){}

    public String getRisultato() {
        return risultato;
    }

    public CampoBattaglia getCampoBattaglia() {
        return campoBattaglia;
    }

    public void setCampoBattaglia(CampoBattaglia campoBattaglia) {
        this.campoBattaglia = campoBattaglia;
    }

    public boolean isTerminata() {
        return terminata;
    }

    public void setTerminata(boolean terminata) {
        this.terminata = terminata;
    }

    public ITank getTankPersonale() {
        return tankPersonale;
    }

    public void setTankPersonale(Tank tankPersonale) {
        this.tankPersonale = new TankOnBattle(tankPersonale);
    }

    public ITank getTankAvversario() {
        return tankAvversario;
    }

    public void setTankAvversario(ITank tankAvversario) {
        this.tankAvversario = new TankOnBattle(tankAvversario);
    }

    public void scegliAvversario() throws IOException {
        try{  tankAvversario=impBattaglia.getAvversario(tankPersonale.getLivello());
            this.setTankAvversario(tankAvversario);
        }catch (NullPointerException e){
            throw new NullPointerException("Tank avversario non trovato");
        }

    }

    public void creaCampoBattaglia() throws IOException {
        try {
            this.campoBattaglia=impBattaglia.getCampoBattaglia(tankPersonale.getLivello());

        }catch (NullPointerException e){
            throw new NullPointerException("Campo Battaglia non trovato");
        }


    }

    public void posizionaTank() {
        this.impBattaglia.posizionaTank(this.tankPersonale,this.tankAvversario,this.campoBattaglia);
    }

    public void impostaTurni() {
        String idTankPersonale = this.tankPersonale.getId();
        String idTankAvversario = this.tankAvversario.getId();

        this.turno = new Turno(idTankPersonale,idTankAvversario,100);//todo questo 500 diverrà flessibile?
        String idTankInizio = this.impBattaglia.decidiTurno(idTankPersonale,idTankAvversario);
        this.turno.setaChiTocca(idTankInizio);
    }

    public void faiMossa(){
        String idAChiTocca = turno.aChiTocca();
        List<ITank> partecipanti = new ArrayList<>();
        partecipanti.add(tankPersonale);
        partecipanti.add(tankAvversario);
        //TODO Nota: occorrerrebbe una estensione al caso con più di 2 giocatori
        ITank tankAttuale;
        ITank tankAltro;
        if (idAChiTocca.equals(tankPersonale.getId())){
            tankAttuale=tankPersonale;
            tankAltro=tankAvversario;
        }
        else {
            tankAttuale=tankAvversario;
            tankAltro=tankPersonale;
        }
        tankAttuale.faiMossa(tankAltro,campoBattaglia);
        IEvento evento;
        if (tankAvversario.seiMorto()){
            risultato="vittoria";
            terminata=true;
            evento = new VittoriaEvento();
        } else {
            if (tankPersonale.seiMorto()) {
                risultato = "sconfitta";
                terminata = true;
                evento = new SconfittaEvento();
            } else {
                if (turno.isFinitaPartita()) {
                    risultato = "pareggio";
                    terminata = true;
                    evento = new PareggioEvento();
                } else {
                    risultato = "inCorso";
                    terminata = false;
                    evento = new InCorsoEvento(turno);
                }
            }
        }
        List eventi = SimulareBattagliaHandler.getSingletonInstance().getEventi();
        eventi.add(evento);
        turno.increment();
        if (this.turno.getNumeroTurno()%20 == 0) {
            IEvento togliMuro = this.campoBattaglia.rimuoviMuroCasuale();
            if (togliMuro!=null) {
                eventi.add(togliMuro);
            }
        }
        SimulareBattagliaHandler.getSingletonInstance().setEventi(eventi);

    }

    public Map getMap(){
        Map Battaglia = new HashMap();

        Battaglia.put("TankPersonale",this.tankPersonale.getMap());
        Battaglia.put("TankAvversario",this.tankAvversario.getMap());
        Battaglia.put("Terminata",this.terminata);
        Battaglia.put("CampoBattaglia",this.campoBattaglia.getMap());
        Battaglia.put("Risultato",this.risultato);
        Battaglia.put("Turno",this.turno.getMap());

        return  Battaglia;


    }


    public void getState(){

    }
}
