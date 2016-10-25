package Shared.Domain.Creator.AzioneCreator;

import Shared.Domain.Azioni.IAzione;
import Shared.Domain.Azioni.IndietreggiaAzione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emanuele on 15/02/16.
 */
public class IndietreggiaAzioneCreator implements ICreator, IAzioneCreator {

    private static IndietreggiaAzioneCreator singletonInstance = null;

    private String idType;
    private String name;
    private String description;

    private IndietreggiaAzioneCreator() {
        this.name = "Indietreggia";
        this.description = "Quest'azione fa indietreggiare il Tank";
        this.idType = "002";
    }

    ;

    public static IndietreggiaAzioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new IndietreggiaAzioneCreator();
        }
        return singletonInstance;
    }

    public IAzione doMakeAzione(String idAz, List<Integer> valori) {
        IAzione indietreggiaAzione = new IndietreggiaAzione(this.name, this.description, this.idType, idAz, valori);
        return indietreggiaAzione;
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
