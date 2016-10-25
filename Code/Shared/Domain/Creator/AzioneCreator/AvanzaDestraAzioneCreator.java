package Shared.Domain.Creator.AzioneCreator;

import Shared.Domain.Azioni.AvanzaDestraAzione;
import Shared.Domain.Azioni.IAzione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emanuele on 15/02/16.
 */
public class AvanzaDestraAzioneCreator implements ICreator, IAzioneCreator {

    private static AvanzaDestraAzioneCreator singletonInstance = null;

    private String idType;
    private String name;
    private String description;

    private AvanzaDestraAzioneCreator() {
        this.name = "Avanza a Destra";
        this.description = "Quest'azione fa avanzare il Tank a destra";
        this.idType = "001";
    }

    ;

    public static AvanzaDestraAzioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AvanzaDestraAzioneCreator();
        }
        return singletonInstance;
    }

    public IAzione doMakeAzione(String idAz, List<Integer> valori) {
        IAzione avanzaDestraAzione = new AvanzaDestraAzione(this.name, this.description, this.idType, idAz, valori);
        return avanzaDestraAzione;
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
