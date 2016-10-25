package Shared.Controllers;

import Shared.Domain.CatalogoCondizioneCreator;
import Shared.Domain.Creator.ICreator;
import Shared.Domain.ICatalogo;
import Shared.Domain.Player;
import Shared.Domain.Strategia;
import Shared.TecnicalService.Fondation.ConcreteRemoteService;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CreareStrategiaHandler {

    private static CreareStrategiaHandler singletonInstance = null;

    private Player currentPlayer;
    private Strategia strategiaCorrente;

    private CreareStrategiaHandler() {
        this.currentPlayer = LoginHandler.getSingletonInstance().getPlayer();

    }

    public static CreareStrategiaHandler getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new CreareStrategiaHandler();
        }
        return singletonInstance;
    }

    /**
     * @param idTypeCond
     * @param valori
     */
    public String scegliCondizione(String idTypeCond, boolean vera, List<Integer> valori) {
        ICatalogo ccc = StartUpHandler.getSingletonInstance().getCatalogoCondCreator();
        ICreator iCondizioneCreator = ccc.getCreator(idTypeCond);
        String idCondPadre = this.strategiaCorrente.aggiungiCondizione(iCondizioneCreator, vera, valori);
        return idCondPadre;
    }

    public String scegliCondizioneAnnidata(String idTypeCond, String idCondPadre, boolean vera, List<Integer> valori) {
        ICatalogo ccc = StartUpHandler.getSingletonInstance().getCatalogoCondCreator();
        ICreator iCondizioneCreator = ccc.getCreator(idTypeCond);
        String NuovoIdCondPadre = this.strategiaCorrente.aggiungiCondizioneAnnidata(iCondizioneCreator, idCondPadre, vera, valori);
        return NuovoIdCondPadre;
    }

    /**
     * @param idTypeAz
     * @param idCond
     * @param valori
     */
    public String associaAzione(String idTypeAz, String idCond, List<Integer> valori) {
        ICatalogo catalogoAzioneCreator = StartUpHandler.getSingletonInstance().getCatalogoAzCreator();
        ICreator ac = catalogoAzioneCreator.getCreator(idTypeAz);
        String idAzPadre = this.strategiaCorrente.aggiungiAzione(ac, valori, idCond);
        return idAzPadre;
    }

    public void iniziaNuovaStrategia() {
        ICatalogo catalogoCondizioneCreator = StartUpHandler.getSingletonInstance().getCatalogoCondCreator();
        ICreator condizioneDefaultCreator = ((CatalogoCondizioneCreator) catalogoCondizioneCreator).getCondizioneDefaultCreator();
        this.strategiaCorrente = currentPlayer.iniziaNuovaStrategia(condizioneDefaultCreator);
    }

    /**
     * @param idTypeAz
     * @param valori
     */
    public String scegliAzioneDefault(String idTypeAz, List<Integer> valori) {
        ICatalogo cac = StartUpHandler.getSingletonInstance().getCatalogoAzCreator();
        ICreator ac = cac.getCreator(idTypeAz);
        String idAzDefault = this.strategiaCorrente.aggiungiAzioneDefault(ac, valori);
        return idAzDefault;
    }

    /**
     * @param nomeStrategia
     */
    public void inserisciNomeStrategia(String nomeStrategia) {
        this.strategiaCorrente.setNome(nomeStrategia);
    }

    public void rimuoviComponente(String id) {
        this.strategiaCorrente.rimuoviComponente(id);
    }

    public boolean terminaStrategia() throws IOException {
        boolean correttezzaStrategia = this.strategiaCorrente.verificaCompletezza();
        if (correttezzaStrategia) {
            this.strategiaCorrente.setComplete(true);
            ConcreteRemoteService service = ConcreteRemoteService.getSingletonInstance();
            DTOMaker dtoMaker = DTOMaker.getSingletonInstance();
            DTO risp = (DTO) service.RichiediAlServer(dtoMaker.getStrategiaDTO());
        }
        return this.strategiaCorrente.getComplete();
    }

    public void distruggiStrategiaCorrente() {

        this.strategiaCorrente = null;
        currentPlayer.eliminaUltimaStrategia();

    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Strategia getStrategiaCorrente() {
        return strategiaCorrente;
    }

    public void setStrategiaCorrente(Strategia strategiaCorrente) {
        this.strategiaCorrente = strategiaCorrente;
    }

    public Map getStrategiaCorrenteMap() {
        return this.strategiaCorrente.getMap();
    }
}