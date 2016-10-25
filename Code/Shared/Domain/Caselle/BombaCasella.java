package Shared.Domain.Caselle;

import Shared.Domain.CampoBattaglia;
import Shared.Domain.Eventi.EsplosioneBombaEvento;
import Shared.Domain.Eventi.IEvento;
import Shared.Domain.ITank;
import Shared.Domain.Posizione;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuele on 05/05/16.
 */
public class BombaCasella implements ICasella,Serializable {

    private boolean disponibile = true;
    private Posizione posizione;
    private String id;
    public ITank tankOccupanteCasella = null;
    public ITank bombaTank = null;

    public BombaCasella() {}

    public BombaCasella(Posizione posizione) {
        this.posizione = posizione;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public ICasella clone() {
        ICasella casella = new BombaCasella();
        casella.setPosizione(this.posizione);
        return casella;
    }

    @Override
    public boolean isDisponibile() {
        return disponibile;
    }

    @Override
    public ITank getTank() {
        return this.tankOccupanteCasella;
    }

    @Override
    public void setTank(ITank tank) {
        tankOccupanteCasella = tank;
        this.disponibile = false;
    }

    @Override
    public void togliTank() {
        this.tankOccupanteCasella = null;
        this.disponibile = true;
    }

    @Override
    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public ITank getBombaTank() {
        return bombaTank;
    }

    @Override
    public Map getMap() {
        Map casella = new HashMap();
        casella.put("Id",this.id);
        casella.put("Posizione",this.posizione.getMap());
        casella.put("Disponibile",this.disponibile);
        casella.put("Tipo","BombaCasella");
        if(this.tankOccupanteCasella != null) {
            casella.put("tankOccupanteCasella", this.tankOccupanteCasella.getId());
        }
        else{
            casella.put("tankOccupanteCasella", null);
        }

        if(this.bombaTank != null) {
            casella.put("bombaTank", this.bombaTank.getId());
        }
        else{
            casella.put("bombaTank", null);
        }
        return casella;
    }

    @Override
    public IEvento eseguiti(CampoBattaglia campo) {
        IEvento risultato = null;
        if (this.bombaTank != this.tankOccupanteCasella) {
            this.tankOccupanteCasella.colpito(1);
            ICasella plainCasella = new PlainCasella(this.posizione);
            plainCasella.setTank(this.tankOccupanteCasella);
            campo.setCasella(plainCasella,this.posizione);
            tankOccupanteCasella.setCasellaPosizione(plainCasella);
            risultato = new EsplosioneBombaEvento(this.tankOccupanteCasella);
        }
        return risultato;
    }

    @Override
    public void setBombaTank(ITank tank) {
        this.bombaTank = tank;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public ITank getTankOccupanteCasella() {
        return tankOccupanteCasella;
    }

    public void setTankOccupanteCasella(ITank tankOccupanteCasella) {
        this.tankOccupanteCasella = tankOccupanteCasella;
    }
}
