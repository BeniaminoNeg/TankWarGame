package Shared.Domain.Eventi;

import Shared.Domain.Turno;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuele on 05/05/16.
 */
public class InCorsoEvento implements IEvento {

    private Turno turno;

    public InCorsoEvento(Turno turno) {
        this.turno = turno;
    }

    @Override
    public Map getMap() {
        Map evento = new HashMap();
        evento.put("turno",turno.getMap());
        evento.put("nomeEvento","InCorsoEvento");
        return evento;
    }
}
