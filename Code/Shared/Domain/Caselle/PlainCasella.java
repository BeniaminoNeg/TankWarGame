package Shared.Domain.Caselle;

import Shared.Domain.CampoBattaglia;
import Shared.Domain.Eventi.IEvento;
import Shared.Domain.ITank;
import Shared.Domain.Posizione;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beniamino on 31/03/16.
 */
public class PlainCasella implements ICasella,Serializable {

    private boolean disponibile = true;
    private Posizione posizione;
    private String id;
    private ITank tankOccupanteCasella = null;



    public PlainCasella(){}
    public PlainCasella(Posizione posizione) {
        this.posizione = posizione;
    }

    public ITank getTank() {
        return tankOccupanteCasella;
    }

    public void setTank(ITank tank) {
        this.tankOccupanteCasella = tank;
        this.disponibile=false;
    }

    public void togliTank(){
        tankOccupanteCasella=null;
        disponibile=true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id= id;
    }

    @Override
    public ICasella clone() {
        ICasella casella = new PlainCasella();
        casella.setPosizione(this.posizione);
        return casella;
    }

    @Override
    public boolean isDisponibile() {
        return disponibile;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public ITank getBombaTank() {
        return null;
    }

    @Override
    public Map getMap() {
        Map casella = new HashMap();

        casella.put("Id",this.id);
        casella.put("Posizione",this.posizione.getMap());
        casella.put("Disponibile",this.disponibile);
        casella.put("Tipo","PlainCasella");
        if(this.tankOccupanteCasella != null) {
            casella.put("tankOccupanteCasella", this.tankOccupanteCasella.getId());
        }
        else{
            casella.put("tankOccupanteCasella", null);
        }
        return casella;
    }

    @Override
    public IEvento eseguiti(CampoBattaglia campo) {
        return null;
    }

    @Override
    public void setBombaTank(ITank tank) {

    }

    @Override
    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
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
