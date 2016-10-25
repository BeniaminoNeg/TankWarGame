package Shared.Domain.Creator.CodizioneCreator;


import Shared.Domain.Condizioni.DefaultCondizione;
import Shared.Domain.Condizioni.ICondizione;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beniamino on 15/02/16.
 * Questa classe è un Singleton
 */
public class DefaultCondizioneCreator implements ICreator, ICondizioneCreator {

    private static DefaultCondizioneCreator singletonInstance = null;

    private String name;
    private String description;
    private String idType;

    private DefaultCondizioneCreator() {
        this.name = "Condizione di Default";
        this.description = "Questa è una condizione sempre vera";
        this.idType = "000";
    }

    public static DefaultCondizioneCreator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DefaultCondizioneCreator();
        }
        return singletonInstance;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdType() {
        return idType;
    }

    @Override
    public void setIdType(String idType) {
        this.idType = idType;
    }

    public ICondizione doMakeCondizione(String idCond, boolean vera, List<Integer> valori) {
        DefaultCondizione defaultCondizione = new DefaultCondizione(this.name, this.description, this.idType, idCond, vera, valori);
        return defaultCondizione;
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
