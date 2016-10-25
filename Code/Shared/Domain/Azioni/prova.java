package Shared.Domain.Azioni;

import Shared.Domain.CampoBattaglia;
import Shared.Domain.Caselle.ICasella;
import Shared.Domain.Eventi.IEvento;
import Shared.Domain.ITank;
import Shared.Domain.Posizione;

import java.util.Map;

/**
 * Created by beniamino on 23/05/16.
 */
public class prova implements ICasella{

    public int a;
    public int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public ICasella clone() {
        return null;
    }

    @Override
    public boolean isDisponibile() {
        return false;
    }

    @Override
    public ITank getTank() {
        return null;
    }

    @Override
    public void setTank(ITank tank) {

    }

    @Override
    public void togliTank() {

    }

    @Override
    public void setPosizione(Posizione posizione) {

    }

    @Override
    public Posizione getPosizione() {
        return null;
    }

    @Override
    public ITank getBombaTank() {
        return null;
    }

    @Override
    public Map getMap() {
        return null;
    }

    @Override
    public IEvento eseguiti(CampoBattaglia campo) {
        return null;
    }

    @Override
    public void setBombaTank(ITank tank) {

    }
}
