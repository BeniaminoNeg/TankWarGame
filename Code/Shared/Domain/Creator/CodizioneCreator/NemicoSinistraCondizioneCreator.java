package Shared.Domain.Creator.CodizioneCreator;

import Shared.Domain.Condizioni.ICondizione;
import Shared.Domain.Condizioni.NemicoSinistraCondizione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beniamino on 15/02/16.
 */
public class NemicoSinistraCondizioneCreator implements ICreator, ICondizioneCreator {

    private static NemicoSinistraCondizioneCreator singletonInstance = null;

    private String name;
    private String description;
    private String idType;

    private NemicoSinistraCondizioneCreator() {
        this.name = "Nemico a sinistra";
        this.description = "Questa condizione controlla se c'è un nemico nel semicampo alla sinistra del tank";
        this.idType = "003";
    }

    public static NemicoSinistraCondizioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new NemicoSinistraCondizioneCreator();
        }
        return singletonInstance;
    }

    public ICondizione doMakeCondizione(String idCond, boolean vera, List<Integer> valori) {
        NemicoSinistraCondizione nemicoSinistraCondizione = new NemicoSinistraCondizione(this.name, this.description, this.idType, idCond, vera, valori);
        return nemicoSinistraCondizione;
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
