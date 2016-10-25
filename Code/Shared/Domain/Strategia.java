package Shared.Domain;

import Shared.Domain.Azioni.IAzione;
import Shared.Domain.Condizioni.ICondizione;
import Shared.Domain.Creator.AzioneCreator.IAzioneCreator;
import Shared.Domain.Creator.CodizioneCreator.DefaultCondizioneCreator;
import Shared.Domain.Creator.CodizioneCreator.ICondizioneCreator;
import Shared.Domain.Creator.ICreator;

import java.io.Serializable;
import java.util.*;

public class Strategia implements Serializable {
    private static final long serialVersionUID = 1;
    private List<IStrategiaComponent> conditionBlock;
    private IStrategiaComponent defaultCondition;
    private String nome;
    private boolean complete;

    private String id;


    public Strategia(ICreator iCondizioneCreator) {

        String idcond = UUID.randomUUID().toString();
        this.id = UUID.randomUUID().toString();
        this.conditionBlock = new ArrayList<IStrategiaComponent>();
        List<Integer> valori = new ArrayList<Integer>();
        //TODO Poca flessibilità
        this.defaultCondition = (IStrategiaComponent) ((DefaultCondizioneCreator) iCondizioneCreator).doMakeCondizione(idcond, true, valori);
        this.nome = "";
        this.complete = false;

    }

    public Strategia() {
    }

    public List<IStrategiaComponent> getConditionBlock() {
        return conditionBlock;
    }

    public void setConditionBlock(List<IStrategiaComponent> conditionBlock) {
        this.conditionBlock = conditionBlock;
    }

    public IStrategiaComponent getDefaultCondition() {
        return defaultCondition;
    }

    public void setDefaultCondition(IStrategiaComponent defaultCondition) {
        this.defaultCondition = defaultCondition;
    }

    public String getNome() {
        return nome;
    }

    /**
     * @param nomeStrategia
     */
    public void setNome(String nomeStrategia) {
        this.nome = nomeStrategia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String aggiungiAzioneDefault(ICreator iAzioneCreator, List<Integer> valori) {
        String idAzione = UUID.randomUUID().toString();
        IStrategiaComponent azione = (IStrategiaComponent) ((IAzioneCreator) iAzioneCreator).doMakeAzione(idAzione, valori);
        this.defaultCondition.addChild(azione);
        return idAzione;
    }

    public boolean getComplete() {
        return this.complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * @param condizionecreator
     * @param valori
     */
    public String aggiungiCondizione(ICreator condizionecreator, boolean vera, List<Integer> valori) {
        String idcond = UUID.randomUUID().toString();
        IStrategiaComponent condizione = (IStrategiaComponent) ((ICondizioneCreator) condizionecreator).doMakeCondizione(idcond, vera, valori);
        this.conditionBlock.add(condizione);
        return idcond;
    }

    public String aggiungiCondizioneAnnidata(ICreator condizioneCreator, String idCondPadre, boolean vera, List<Integer> valori) {
        String idcond = UUID.randomUUID().toString();
        IStrategiaComponent condizione = (IStrategiaComponent) ((ICondizioneCreator) condizioneCreator).doMakeCondizione(idcond, vera, valori);
        boolean trovato = false;
        List<IStrategiaComponent> padriFoglie = this.getPadriFoglie();
        IStrategiaComponent currentfoglia = null;
        IStrategiaComponent padre = null;
        for (int i = 0; i < padriFoglie.size() && !trovato; i++) {
            padre = padriFoglie.get(i);
            currentfoglia = padre.getChild();
            if (currentfoglia != null) {
                if (currentfoglia.getId().equals(idCondPadre)) {
                    trovato = true;
                    currentfoglia.addChild(condizione);
                }
            } else {
                if (padre.getId().equals(idCondPadre)) {
                    trovato = true;
                    padre.addChild(condizione);
                }
            }

        }
        return idcond;
    }

    public List<IStrategiaComponent> getPadriFoglie() {
        List<IStrategiaComponent> result = new ArrayList<>();
        IStrategiaComponent padreCurrentLine = null;
        IStrategiaComponent currentLine = null;
        for (int i = 0; i < conditionBlock.size(); i++) {
            padreCurrentLine = conditionBlock.get(i);
            currentLine = padreCurrentLine;
            while (currentLine.getChild() != null) {
                currentLine = currentLine.getChild();
                if (currentLine.getChild() != null) {
                    padreCurrentLine = currentLine;
                }
            }
            result.add(padreCurrentLine);
        }
        return result;
    }

    /**
     * @param az
     * @param valori
     * @param idCond
     */
    public String aggiungiAzione(ICreator az, List<Integer> valori, String idCond) {
        List<IStrategiaComponent> padriFoglie = this.getPadriFoglie();
        String idAz = UUID.randomUUID().toString();
        IStrategiaComponent azione = (IStrategiaComponent) ((IAzioneCreator) az).doMakeAzione(idAz, valori);
        boolean trovato = false;
        IStrategiaComponent currentfoglia = null;
        for (int i = 0; i < padriFoglie.size() && !trovato; i++) {
            if (padriFoglie.get(i).getChild() != null) {
                currentfoglia = padriFoglie.get(i).getChild();
            } else {
                currentfoglia = padriFoglie.get(i);
            }
            if (currentfoglia.getId().equals(idCond)) {
                trovato = true;
                currentfoglia.addChild(azione);
            }
        }
        return idAz;

    }

    public void rimuoviComponente(String id) {
        boolean trovato = false;
        if (this.defaultCondition.getChild() != null && this.defaultCondition.getChild().getId().equals(id)) {
            this.defaultCondition.removeChild();
            trovato = true;
        }
        IStrategiaComponent currentPadreFoglia = null;
        IStrategiaComponent currentFoglia = null;
        if (!trovato) {
            List<IStrategiaComponent> padriFoglie = this.getPadriFoglie();
            for (int i = 0; i < padriFoglie.size() && !trovato; i++) {
                currentPadreFoglia = padriFoglie.get(i);
                currentFoglia = currentPadreFoglia;
                if (currentPadreFoglia.getChild() != null) {
                    currentFoglia = currentPadreFoglia.getChild();
                    if (currentFoglia.getId().equals(id)) {
                        trovato = true;
                        currentPadreFoglia.removeChild();
                    }
                } else {
                    if (currentFoglia.getId().equals(id)) {
                        trovato = true;
                        this.conditionBlock.remove(i);
                    }
                }
            }
        }

    }
    
    public boolean verificaCompletezza() {
        List<IStrategiaComponent> padriFoglie = this.getPadriFoglie();
        boolean sbagliato=false;
        for (int i=0;i<padriFoglie.size() && !sbagliato;i++) {
            IStrategiaComponent figlio = padriFoglie.get(i).getChild();
            if (figlio == null) {
                sbagliato = true;
            } else {
                Class[] interfaces = figlio.getClass().getInterfaces();
                boolean azione = false;
                for (Class interfaccia: interfaces) {
                    System.out.println(interfaccia);
                    if (interfaccia.toString().equals("interface Shared.Domain.Azioni.IAzione")) {
                        azione = true;
                    }
                }
                if (!azione) {
                    sbagliato = true;
                }
            }
        }
        if (this.defaultCondition.getChild() == null || this.nome.equals("")) {
            sbagliato = true;
        }
        return !sbagliato;
    }


    public Map getMap() {
        Map strategie = new HashMap();
        List strategiaComponent = new ArrayList<>();
        for (IStrategiaComponent is : this.conditionBlock) {
            strategiaComponent.add(is.getMap());
        }
        strategie.put("id", this.id);
        strategie.put("nome", this.nome);
        strategie.put("complete", this.complete);
        strategie.put("defaultCondition", this.defaultCondition.getMap());
        strategie.put("conditionBlock", strategiaComponent);
        return strategie;
    }

    public void faiMossa(ITank mioTank, ITank altroTank, CampoBattaglia campo){
        int blocksize = conditionBlock.size();
        boolean trovataUnaVera = false;
        IStrategiaComponent currentComponent;
        for (int i=0; i<blocksize && !trovataUnaVera; i++){
            currentComponent=conditionBlock.get(i);
            boolean figlio = true;
            while (currentComponent.eseguiti(mioTank,altroTank,campo) && figlio){
                currentComponent=currentComponent.getChild();
                figlio=currentComponent.hasChild();
            }
            if (!figlio){
                trovataUnaVera=true;
            }

        }

        if(!trovataUnaVera){
            defaultCondition.getChild().eseguiti(mioTank,altroTank,campo);
        }

    }
}