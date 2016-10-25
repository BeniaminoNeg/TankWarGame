package Shared.Domain.Caselle;

import Shared.Domain.CampoBattaglia;
import Shared.Domain.Eventi.IEvento;
import Shared.Domain.ITank;
import Shared.Domain.Posizione;

import java.util.Map;

/**
 * Created by gioele on 22/03/16.
 */
public interface ICasella {

    String getId();

    void setId(String id);

    public ICasella clone();
    public boolean isDisponibile();
    public ITank getTank();
    public void setTank(ITank tank);
    public void togliTank();
    public void setPosizione(Posizione posizione);
    public Posizione getPosizione();
    public ITank getBombaTank();
    public Map getMap();
    public IEvento eseguiti(CampoBattaglia campo);

    void setBombaTank(ITank tank);
}
