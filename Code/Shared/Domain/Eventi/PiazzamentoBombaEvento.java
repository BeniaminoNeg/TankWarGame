package Shared.Domain.Eventi;

import Shared.Domain.Caselle.ICasella;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuele on 04/05/16.
 */
public class PiazzamentoBombaEvento implements IEvento {

    private ICasella casella;

    public PiazzamentoBombaEvento(ICasella casella) {
        this.casella = casella;
    }

    @Override
    public Map getMap() {
        Map evento = new HashMap();
        evento.put("casella",this.casella.getMap());
        evento.put("nomeEvento","PiazzamentoBombaEvento");
        return evento;
    }
}
