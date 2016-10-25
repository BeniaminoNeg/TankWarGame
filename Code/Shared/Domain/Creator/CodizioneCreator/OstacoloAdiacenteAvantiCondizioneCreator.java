package Shared.Domain.Creator.CodizioneCreator;

import Shared.Domain.Condizioni.ICondizione;
import Shared.Domain.Condizioni.OstacoloAdiacenteAvantiCondizione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beniamino on 15/02/16.
 */
public class OstacoloAdiacenteAvantiCondizioneCreator implements ICreator, ICondizioneCreator {

    private static OstacoloAdiacenteAvantiCondizioneCreator singletonInstance = null;

    private String name;
    private String description;
    private String idType;

    private OstacoloAdiacenteAvantiCondizioneCreator() {
        this.name = "Muro adiacente davanti";
        this.description = "Questa condizione controlla se c'è un muro avanti al tank";
        this.idType = "006";
    }

    public static OstacoloAdiacenteAvantiCondizioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new OstacoloAdiacenteAvantiCondizioneCreator();
        }
        return singletonInstance;
    }

    public ICondizione doMakeCondizione(String idCond, boolean vera, List<Integer> valori) {
        OstacoloAdiacenteAvantiCondizione ostacoloAdiacenteAvantiCondizione = new OstacoloAdiacenteAvantiCondizione(this.name, this.description, this.idType, idCond, vera, valori);
        return ostacoloAdiacenteAvantiCondizione;
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
