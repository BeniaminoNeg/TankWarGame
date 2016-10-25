package Shared.Domain.Creator.AzioneCreator;

import Shared.Domain.Azioni.AvanzaAvantiAzione;
import Shared.Domain.Azioni.IAzione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvanzaAvantiAzioneCreator implements ICreator, IAzioneCreator {

    private static AvanzaAvantiAzioneCreator singletonInstance = null;

    private String idType;
    private String name;
    private String description;

    private AvanzaAvantiAzioneCreator() {
        this.name = "Avanza Avanti";
        this.description = "Quest'azione fa avanzare il Tank avanti";
        this.idType = "000";
    }

    ;

    public static AvanzaAvantiAzioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AvanzaAvantiAzioneCreator();
        }
        return singletonInstance;
    }

    public IAzione doMakeAzione(String idAz, List<Integer> valori) {
        IAzione avanzaAvantiAzione = new AvanzaAvantiAzione(this.name, this.description, this.idType, idAz, valori);
        return avanzaAvantiAzione;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Map getMap() {
        Map azioneCreator = new HashMap();
        azioneCreator.put("idType", this.idType);
        azioneCreator.put("name", this.name);
        azioneCreator.put("description", this.description);
        return azioneCreator;
    }
}