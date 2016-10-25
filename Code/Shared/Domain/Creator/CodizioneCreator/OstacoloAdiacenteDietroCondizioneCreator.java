package Shared.Domain.Creator.CodizioneCreator;

import Shared.Domain.Condizioni.ICondizione;
import Shared.Domain.Condizioni.OstacoloAdiacenteDietroCondizione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beniamino on 16/02/16.
 */
public class OstacoloAdiacenteDietroCondizioneCreator implements ICreator, ICondizioneCreator {

    private static OstacoloAdiacenteDietroCondizioneCreator singletonInstance = null;

    private String name;
    private String description;
    private String idType;

    private OstacoloAdiacenteDietroCondizioneCreator() {
        this.name = "Muro adiacente dietro";
        this.description = "Questa condizione controlla se c'è un muro dietro al tank";
        this.idType = "009";
    }

    public static OstacoloAdiacenteDietroCondizioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new OstacoloAdiacenteDietroCondizioneCreator();
        }
        return singletonInstance;
    }

    public ICondizione doMakeCondizione(String idCond, boolean vera, List<Integer> valori) {
        OstacoloAdiacenteDietroCondizione ostacoloAdiacenteDietroCondizione = new OstacoloAdiacenteDietroCondizione(this.name, this.description, this.idType, idCond, vera, valori);
        return ostacoloAdiacenteDietroCondizione;
    }

    @Override
    public String getIdType() {
        return this.idType;
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
        Map condizioneCreator = new HashMap();
        condizioneCreator.put("idType", this.idType);
        condizioneCreator.put("name", this.name);
        condizioneCreator.put("description", this.description);
        return condizioneCreator;
    }
}
