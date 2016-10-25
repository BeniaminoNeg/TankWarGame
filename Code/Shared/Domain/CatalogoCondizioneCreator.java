package Shared.Domain;


import Shared.Domain.Creator.CodizioneCreator.*;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.Map;


public class CatalogoCondizioneCreator implements ICatalogo {

    private Map<String, ICreator> condizioniCreators;

    private String defaultCreatorKey;


    public CatalogoCondizioneCreator() {
        this.condizioniCreators = new HashMap<>();
        this.loadCreator();
        ICreator condizioneDefaultCreator = DefaultCondizioneCreator.getSingletonInstance();
        this.defaultCreatorKey = condizioneDefaultCreator.getIdType();
    }

    /**
     * @param idType id identificativo della generica condizione
     */
    public ICreator getCreator(String idType) {
        return this.condizioniCreators.get(idType);
    }

    @Override
    public Map<String, ICreator> getCreators() {
        return this.condizioniCreators;
    }

    public void setCreators(Map<String, ICreator> creators) {
        this.condizioniCreators = creators;
    }

    public ICreator getCondizioneDefaultCreator() {
        return this.condizioniCreators.get(this.defaultCreatorKey);
    }

    public String getDefaultCreatorKey() {
        return defaultCreatorKey;
    }

    public void setDefaultCreatorKey(String defaultCreatorKey) {
        this.defaultCreatorKey = defaultCreatorKey;
    }

    public Map<String, ICreator> getCondizioniCreators() {
        return condizioniCreators;
    }

    private void loadCreator() {
        ICreator iCreator = BombeRimanentiCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = NemicoAvantiCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = NemicoDestraCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = NemicoDietroCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = NemicoSinistraCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = NemicoSottoTiroCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = OstacoloAdiacenteAvantiCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = OstacoloAdiacenteDestraCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = OstacoloAdiacenteDietroCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = OstacoloAdiacenteSinistraCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = SullaBombaCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);
        iCreator = DefaultCondizioneCreator.getSingletonInstance();
        this.condizioniCreators.put(iCreator.getIdType(), iCreator);

    }

    @Override
    public Map getMap() {
        Map catalogo = new HashMap();
        for (String key : this.condizioniCreators.keySet()) {
            catalogo.put(key, this.condizioniCreators.get(key).getMap());
        }
        catalogo.put("defaultCreatorKey", this.defaultCreatorKey);
        return catalogo;
    }
}