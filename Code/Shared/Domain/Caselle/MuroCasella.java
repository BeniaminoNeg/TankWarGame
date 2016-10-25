package Shared.Domain.Caselle;


import Shared.Domain.CampoBattaglia;
import Shared.Domain.Caselle.ICasella;
import Shared.Domain.Eventi.IEvento;
import Shared.Domain.ITank;
import Shared.Domain.Posizione;
import Shared.Util.OrientamentoEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by gioele on 22/03/16.
 */
public class MuroCasella implements ICasella,Serializable {

    private String id;
    private Posizione posizione;

    public MuroCasella(){}
    public MuroCasella(Posizione posizione) {
        this.posizione = posizione;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    @Override
    public ICasella clone() {
        ICasella casella = new MuroCasella();
        casella.setPosizione(this.posizione);
        return casella;
    }

    @Override
    public boolean isDisponibile() {
        return false;
    }

    @Override
    public ITank getTank() {
        return null;
    }

    @Override
    public void setTank(ITank tank) {

    }

    public void togliTank() {

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

        casella.put("Tipo","MuroCasella");
        casella.put("Id",this.id);
        casella.put("Posizione",this.posizione.getMap());

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
}
