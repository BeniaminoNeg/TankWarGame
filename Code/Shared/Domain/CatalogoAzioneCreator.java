package Shared.Domain;

import Shared.Domain.Creator.AzioneCreator.*;
import Shared.Domain.Creator.ICreator;

import java.util.HashMap;
import java.util.Map;

public class CatalogoAzioneCreator implements ICatalogo {

    private Map<String, ICreator> azioniCreators;

    public CatalogoAzioneCreator() {
        this.azioniCreators = new HashMap<>();
        loadCreator();
    }

    @Override
    public ICreator getCreator(String idType) {
        return this.azioniCreators.get(idType);
    }

    @Override
    public Map<String, ICreator> getCreators() {
        return this.azioniCreators;
    }

    @Override
    public void setCreators(Map<String, ICreator> creators) {
        this.azioniCreators = creators;
    }

    private void loadCreator() {
        //TODO Questo è molto brutto, trovare via alternativa
        String key = "";
        ICreator creator;

        creator = AvanzaAvantiAzioneCreator.getSingletonInstance();
        key = creator.getIdType();
        this.azioniCreators.put(key, creator);

        creator = AvanzaDestraAzioneCreator.getSingletonInstance();
        key = creator.getIdType();
        this.azioniCreators.put(key, creator);

        creator = AvanzaSinistraAzioneCreator.getSingletonInstance();
        key = creator.getIdType();
        this.azioniCreators.put(key, creator);

        creator = IndietreggiaAzioneCreator.getSingletonInstance();
        key = creator.getIdType();
        this.azioniCreators.put(key, creator);

        creator = LasciaBombaAzioneCreator.getSingletonInstance();
        key = creator.getIdType();
        this.azioniCreators.put(key, creator);

        creator = SparaAzioneCreator.getSingletonInstance();
        key = creator.getIdType();
        this.azioniCreators.put(key, creator);

        creator = TemporeggiaAzioneCreator.getSingletonInstance();
        key = creator.getIdType();
        this.azioniCreators.put(key, creator);
    }

    @Override
    public Map getMap() {
        Map catalogo = new HashMap();
        for (String key : this.azioniCreators.keySet()) {
            catalogo.put(key, this.azioniCreators.get(key).getMap());
        }
        return catalogo;
    }
}