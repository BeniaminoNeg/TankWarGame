package Shared.Domain.Creator.CodizioneCreator;

import Shared.Domain.Condizioni.ICondizione;
import Shared.Domain.Condizioni.NemicoAvantiCondizione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beniamino on 15/02/16.
 */
public class NemicoAvantiCondizioneCreator implements ICreator, ICondizioneCreator {

    private static NemicoAvantiCondizioneCreator singletonInstance = null;

    private String name;
    private String description;
    private String idType;

    private NemicoAvantiCondizioneCreator() {
        this.name = "Nemico Avanti";
        this.description = "Questa condizione controlla se c'è un nemico nel semicampo avanti a sè";
        this.idType = "001";
    }

    public static NemicoAvantiCondizioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new NemicoAvantiCondizioneCreator();
        }
        return singletonInstance;
    }

    public ICondizione doMakeCondizione(String idCond, boolean vera, List<Integer> valori) {
        NemicoAvantiCondizione nemicoAvantiCondizione = new NemicoAvantiCondizione(this.name, this.description, idType, idCond, vera, valori);
        return nemicoAvantiCondizione;
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
