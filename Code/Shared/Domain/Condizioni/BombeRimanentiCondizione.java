package Shared.Domain.Condizioni;

import Shared.Domain.Azioni.IAzione;
import Shared.Domain.CampoBattaglia;
import Shared.Domain.IStrategiaComponent;
import Shared.Domain.ITank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emanuele on 16/02/16.
 */
public class BombeRimanentiCondizione implements ICondizione, IStrategiaComponent, Serializable {


    private String name;
    private String description;
    private String idType;

    private String id;
    private boolean vera;
    private List<Integer> valori;
    private IStrategiaComponent child;

    public BombeRimanentiCondizione(String name, String description, String idType, String idCond, boolean vera, List<Integer> valori) {
        this.name = name;
        this.description = description;
        this.idType = idType;
        this.id = idCond;
        this.vera = vera;
        this.valori = valori;
        this.child = null;
    }

    public BombeRimanentiCondizione() {
    }

    @Override
    public boolean eseguiti(ITank tankTurno, ITank tankAvversario, CampoBattaglia campo) {
        boolean verifica = false;
        if (tankTurno.getBombeRimanenti() != 0) {
           verifica = true;
        }
        if (!this.vera) {
            verifica = !verifica;
        }
        return verifica;
    }

    @Override
    public IAzione getAzione() {
        return null;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String idCond) {
        this.id = idCond;
    }

    @Override
    public void addChild(IStrategiaComponent c) {
        if (this.child == null) {
            this.child = c;
        }
    }

    @Override
    public void removeChild() {
        this.child = null;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getIdType() {
        return idType;
    }

    @Override
    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Override
    public List<Integer> getValori() {
        return valori;
    }

    @Override
    public void setValori(List<Integer> valori) {
        this.valori = valori;
    }

    @Override
    public IStrategiaComponent getChild() {
        return this.child;

    }

    @Override
    public boolean hasChild() {
        boolean risultato=false;
        if (this.child != null) {
            risultato = true;
        }
        return risultato;
    }

    @Override
    public void setChild(IStrategiaComponent child) {
        this.child = child;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean getVera() {
        return vera;
    }

    @Override
    public void setVera(boolean vera) {
        this.vera = vera;
    }

    @Override
    public Map getMap() {
        Map condizione = new HashMap();
        List valoriCorrenti = new ArrayList<>();
        if(valori != null) {
            for (Integer v : this.valori) {
                valoriCorrenti.add(v);
            }
        }
        condizione.put("id", this.id);
        condizione.put("idType", this.idType);
        condizione.put("name", this.name);
        condizione.put("description", this.description);
        condizione.put("vera", this.vera);
        condizione.put("valori", this.valori);
        if (this.child != null) {
            condizione.put("child", this.child.getMap());
        } else {
            condizione.put("child", null);
        }
        return condizione;
    }
}
