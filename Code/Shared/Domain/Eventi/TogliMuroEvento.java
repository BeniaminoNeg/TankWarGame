package Shared.Domain.Eventi;

import Shared.Domain.Caselle.ICasella;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuele on 08/05/16.
 */
public class TogliMuroEvento implements IEvento {

    private ICasella muro = null;

    public TogliMuroEvento() {

    }

    public TogliMuroEvento(ICasella muroDaTogliere) {
        this.muro = muroDaTogliere;
    }

    @Override
    public Map getMap() {
        Map evento = new HashMap();
        if (muro!=null) {
            evento.put("muro",muro.getMap());
        } else {
            evento.put("muro",null);
        }
        evento.put("nomeEvento","TogliMuroEvento");
        return evento;
    }
}
