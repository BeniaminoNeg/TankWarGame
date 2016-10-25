package Shared.Domain.Eventi;

import javax.swing.text.TabExpander;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by emanuele on 04/05/16.
 */
public class TemporeggiaEvento implements IEvento {

    public TemporeggiaEvento() {

    }

    @Override
    public Map getMap() {
        Map evento = new HashMap();
        evento.put("nomeEvento","TemporeggiaEvento");
        return evento;
    }
}
