package Shared.Domain.Azioni;


import Shared.Domain.CampoBattaglia;
import Shared.Domain.Eventi.IEvento;
import Shared.Domain.ITank;

import java.util.List;

public interface IAzione {

    void fireEvent(List eventi);
    boolean eseguiti(ITank tankTurno, ITank tankAvversario, CampoBattaglia campo);

}