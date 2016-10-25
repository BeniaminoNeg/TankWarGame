package Shared.Domain.Creator.CodizioneCreator;

import Shared.Domain.Condizioni.BombeRimanentiCondizione;
import Shared.Domain.Condizioni.ICondizione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emanuele on 16/02/16.
 */
public class BombeRimanentiCondizioneCreator implements ICreator, ICondizioneCreator {

    private static BombeRimanentiCondizioneCreator singletonInstance = null;

    private String name;
    private String description;
    private String idType;

    private BombeRimanentiCondizioneCreator() {
        this.name = "Ho bombe rimanenti";
        this.description = "Questa condizione controlla se il Tank ha ancora delle bombe";
        this.idType = "010";
    }

    public static BombeRimanentiCondizioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new BombeRimanentiCondizioneCreator();
        }
        return singletonInstance;
    }

    public ICondizione doMakeCondizione(String idCond, boolean vera, List<Integer> valori) {
        BombeRimanentiCondizione bombeRimanentiCondizione = new BombeRimanentiCondizione(this.name, this.description, this.idType, idCond, vera, valori);
        return bombeRimanentiCondizione;
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
