package Shared.Domain.Condizioni;

import Shared.Domain.Azioni.IAzione;
import Shared.Domain.CampoBattaglia;
import Shared.Domain.Caselle.ICasella;
import Shared.Domain.Caselle.MuroCasella;
import Shared.Domain.IStrategiaComponent;
import Shared.Domain.ITank;
import Shared.Util.OrientamentoEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beniamino on 15/02/16.
 */
public class NemicoSottoTiroCondizione implements ICondizione, IStrategiaComponent, Serializable {


    private String name;
    private String description;
    private String idType;

    private String id;
    private boolean vera;
    private List<Integer> valori;
    private IStrategiaComponent child;

    public NemicoSottoTiroCondizione(String name, String description, String idType, String idCond, boolean vera, List<Integer> valori) {
        this.name = name;
        this.description = description;
        this.idType = idType;
        this.id = idCond;
        this.vera = vera;
        this.valori = valori;
        this.child = null;
    }

    public NemicoSottoTiroCondizione() {
    }

    @Override
    public boolean eseguiti(ITank tankTurno, ITank tankAvversario, CampoBattaglia campo) {
        boolean verifica = false;
        boolean visioneFinita = false;
        Integer maxVisioneSparoTankTurno = tankTurno.getMaxVisioneSparo();
        ICasella casellaTankTurno = tankTurno.getCasellaPosizione();
        ICasella casellaTankAvversario = tankAvversario.getCasellaPosizione();
        OrientamentoEnum orientamentoTankTurno = tankTurno.getOrientamento();
        List campoDirezione = campo.getCaselleByOrientamento(casellaTankTurno,orientamentoTankTurno);
        if (!campoDirezione.isEmpty()) {
            int i = 0;
            while (i<maxVisioneSparoTankTurno && !verifica && !visioneFinita && i<campoDirezione.size()) {
                if (!((ICasella)campoDirezione.get(i)).isDisponibile()) {
                    if (casellaTankAvversario == campoDirezione.get(i)) {
                        verifica = true;
                    } else {
                        visioneFinita = true;
                    }
                }
                i++;
            }
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
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void addChild(IStrategiaComponent c) {
        if (this.child == null) {
            this.child = c;
        }
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
    public void removeChild() {
        this.child = null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public List<Integer> getValori() {
        return valori;
    }

    public void setValori(List<Integer> valori) {
        this.valori = valori;
    }

    @Override
    public IStrategiaComponent getChild() {
        return this.child;

    }

    public void setChild(IStrategiaComponent child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

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
