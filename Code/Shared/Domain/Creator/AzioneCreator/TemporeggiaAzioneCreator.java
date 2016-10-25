package Shared.Domain.Creator.AzioneCreator;

import Shared.Domain.Azioni.IAzione;
import Shared.Domain.Azioni.TemporeggiaAzione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emanuele on 15/02/16.
 */
public class TemporeggiaAzioneCreator implements ICreator, IAzioneCreator {

    private static TemporeggiaAzioneCreator singletonInstance = null;

    private String idType;
    private String name;
    private String description;

    private TemporeggiaAzioneCreator() {
        this.name = "Temporeggia";
        this.description = "Quest'azione fa passare un turno senza eseguire nulla al Tank";
        this.idType = "006";
    }

    ;

    public static TemporeggiaAzioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new TemporeggiaAzioneCreator();
        }
        return singletonInstance;
    }

    public IAzione doMakeAzione(String idAz, List<Integer> valori) {
        IAzione temporeggiaAzione = new TemporeggiaAzione(this.name, this.description, this.idType, idAz, valori);
        return temporeggiaAzione;
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
