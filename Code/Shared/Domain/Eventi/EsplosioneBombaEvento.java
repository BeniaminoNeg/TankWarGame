package Shared.Domain.Eventi;

import Shared.Domain.Caselle.ICasella;
import Shared.Domain.ITank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuele on 05/05/16.
 */
public class EsplosioneBombaEvento implements IEvento {

    private ITank tankOccupantteCasella;

    public EsplosioneBombaEvento(ITank tankOccupanteCasella) {
        this.tankOccupantteCasella = tankOccupanteCasella;
    }

    @Override
    public Map getMap() {
        Map evento = new HashMap();
        evento.put("TankEsploso",this.tankOccupantteCasella.getMap());
        evento.put("nomeEvento","EsplosioneBombaEvento");
        return evento;
    }
}
