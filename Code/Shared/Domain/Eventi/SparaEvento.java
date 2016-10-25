package Shared.Domain.Eventi;

import Shared.Domain.Caselle.ICasella;
import Shared.Domain.ITank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emanuele on 04/05/16.
 */
public class SparaEvento implements IEvento {

    private List<ICasella> casellePropagazione = new ArrayList<>();
    private ICasella casellaTank;
    private ITank tankTurno;
    private ITank tankColpito;

    public SparaEvento(List casellePropagazione, ICasella casellaTank, ITank tankTurno,ITank tankColpito) {
        this.casellePropagazione = casellePropagazione;
        this.casellaTank = casellaTank;
        this.tankTurno = tankTurno;
        this.tankColpito = tankColpito;
    }

    @Override
    public Map getMap() {
        Map evento = new HashMap();
        List caselleCorrenti = new ArrayList<>();
        for (ICasella c : this.casellePropagazione) {
            caselleCorrenti.add(c.getMap());
        }
        evento.put("casellePropagazione",caselleCorrenti);
        if (casellaTank != null) {
            evento.put("casellaTank",casellaTank.getMap());
            evento.put("TankColpito",tankColpito.getMap());

        } else {
            evento.put("casellaTank",null);
            evento.put("TankColpito",null);
        }
        evento.put("TankCheSpara",tankTurno.getMap());
        evento.put("nomeEvento","SparaEvento");

        return evento;
    }
}
