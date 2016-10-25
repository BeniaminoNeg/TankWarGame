package Shared.Domain;


import Shared.Domain.Creator.ICreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Serializable {

      private String id;
    private String nome;
    private List<Strategia> strategieList;
    private ITank tank;

    public Player() {
        this.strategieList = new ArrayList<>();
        //this.id =  UUID.randomUUID().toString(); alla fine


    }

    public Strategia iniziaNuovaStrategia(ICreator condizioneCreator) {
        Strategia strategia = new Strategia(condizioneCreator);
        this.strategieList.add(strategia);
        return strategia;
    }

    public List<Strategia> getStrategieList() {
        return strategieList;
    }

    public void setStrategieList(List<Strategia> strategieList) {
        this.strategieList = strategieList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void aggiungiStrategia(Strategia strategia) {
        this.strategieList.add(strategia);
    }

    public void eliminaUltimaStrategia(){

        strategieList.remove(strategieList.size()-1);

    }

    public Map getMap() {
        Map player = new HashMap();
        List strategie = new ArrayList<>();
        for (Strategia s : this.strategieList) {
            strategie.add(s.getMap());
        }
        player.put("id", this.id);
        player.put("nome", this.nome);
        player.put("strategieList", strategie);
        return player;
    }

    public ITank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void scegliStrategia(String idStrategia) {
        boolean trovato = false;
        Strategia strategiaCorrente = new Strategia();
        for (int i=0;i<this.strategieList.size() && !trovato;i++) {
            strategiaCorrente = this.strategieList.get(i);
            if (strategiaCorrente.getId().equals(idStrategia)) {
                trovato = true;
            }
        }
        this.tank.setStrategia(strategiaCorrente);
    }
}