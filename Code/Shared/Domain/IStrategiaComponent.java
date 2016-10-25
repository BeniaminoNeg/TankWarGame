package Shared.Domain;

import java.util.Map;

public interface IStrategiaComponent {

    /**
     * @param c
     */
    void addChild(IStrategiaComponent c);

    void removeChild();

    IStrategiaComponent getChild();

    boolean hasChild();

    void setChild(IStrategiaComponent child);

    String getId();

    void setId(String id);

    Map getMap();

    boolean eseguiti(ITank tankTurno, ITank tankAvversario, CampoBattaglia campo);

}
