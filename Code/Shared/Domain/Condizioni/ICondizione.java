package Shared.Domain.Condizioni;


import Shared.Domain.Azioni.IAzione;
import Shared.Domain.CampoBattaglia;
import Shared.Domain.IStrategiaComponent;
import Shared.Domain.ITank;

import java.util.List;

public interface ICondizione {

    boolean eseguiti(ITank tankTurno, ITank tankAvversario, CampoBattaglia campo);

    IAzione getAzione();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    String getIdType();

    void setIdType(String idType);

    String getId();

    void setId(String id);

    List<Integer> getValori();

    void setValori(List<Integer> valori);

    IStrategiaComponent getChild();

    void setChild(IStrategiaComponent child);

    boolean getVera();

    void setVera(boolean vera);
}