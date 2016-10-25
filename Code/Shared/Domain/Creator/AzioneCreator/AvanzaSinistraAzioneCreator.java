package Shared.Domain.Creator.AzioneCreator;

import Shared.Domain.Azioni.AvanzaSinistraAzione;
import Shared.Domain.Azioni.IAzione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emanuele on 15/02/16.
 */
public class AvanzaSinistraAzioneCreator implements ICreator, IAzioneCreator {

    private static AvanzaSinistraAzioneCreator singletonInstance = null;

    private String idType;
    private String name;
    private String description;

    private AvanzaSinistraAzioneCreator() {
        this.name = "Avanza a Sinistra";
        this.description = "Quest'azione fa avanzare il Tank a sinistra";
        this.idType = "003";
    }

    ;

    public static AvanzaSinistraAzioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AvanzaSinistraAzioneCreator();
        }
        return singletonInstance;
    }

    public IAzione doMakeAzione(String idAz, List<Integer> valori) {
        IAzione avanzaSinistraAzione = new AvanzaSinistraAzione(this.name, this.description, this.idType, idAz, valori);
        return avanzaSinistraAzione;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
