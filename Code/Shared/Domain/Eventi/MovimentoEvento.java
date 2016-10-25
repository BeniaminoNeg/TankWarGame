package Shared.Domain.Eventi;

import Shared.Domain.Caselle.ICasella;
import Shared.Domain.ITank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuele on 04/05/16.
 */
public class MovimentoEvento implements IEvento {

    private ITank tankTurno;

    public MovimentoEvento(ITank tankTurno) {
        this.tankTurno = tankTurno;
    }

    @Override
    public Map getMap() {
        Map evento = new HashMap();
        evento.put("tankTurno",this.tankTurno.getMap());
        evento.put("nomeEvento","MovimentoEvento");
        return evento;
    }
}
