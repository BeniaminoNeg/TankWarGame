package Shared.Domain;

import Shared.Domain.Caselle.ICasella;
import Shared.Util.OrientamentoEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by beniamino on 22/03/16.
 */
public class Tank implements ITank,Serializable {

    private Strategia strategia = null;
    private String id= UUID.randomUUID().toString();
    private Integer livello;

    public void Tank(){}



    public Integer getLivello() {
        return livello;
    }

    public void setLivello(Integer livello) {
        this.livello = livello;
    }

    @Override
    public Strategia getStrategia() {
        return strategia;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public ICasella getCasellaPosizione() {
        return null;
    }

    @Override
    public void setCasellaPosizione(ICasella casellaPosizione) {

    }

    @Override
    public void setBombeRimanenti(Integer bombeRimanenti) {

    }

    @Override
    public Integer getBombeRimanenti() {
        return null;
    }

    @Override
    public void setOrientamento(OrientamentoEnum orientamento) {

    }

    @Override
    public OrientamentoEnum getOrientamento() {
        return null;
    }

    @Override
    public void setOrientamento(Integer orientamento) {

    }

    @Override
    public void faiMossa(ITank altroTank, CampoBattaglia campo) {

    }

    @Override
    public boolean seiMorto() {
        return false;
    }

    @Override
    public Integer getMaxVisioneSparo() {
        return null;
    }

    @Override
    public void colpito(Integer danno) {

    }

    @Override
    public Integer getPotenzaArma() {
        return null;
    }

    @Override
    public Map getMap() {
        Map tank = new HashMap();
        tank.put("Id",this.id);
        tank.put("Livello",this.livello);
        tank.put("Strategia",this.strategia.getMap());

        return tank;
    }

    @Override
    public void setStrategia(Strategia strategia) {
        this.strategia = strategia;
    }

    @Override
    public ITank getTank() {
        return null;
    }

    @Override
    public void setTank(Tank tank) {

    }
}
