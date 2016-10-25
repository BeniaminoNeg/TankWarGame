package Shared.Domain.StartupBattle;

import Shared.Domain.ITank;
import Shared.TecnicalService.Fondation.ConcreteRemoteService;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;

import java.io.IOException;


/**
 * Created by gioele on 22/03/16.
 */
public class RandomAvversarioGenerator implements  IGeneratoreAvversario {

    public ITank getAvversario(Integer livello) throws IOException {
        ITank tank;

        ConcreteRemoteService service = ConcreteRemoteService.getSingletonInstance();
        DTOMaker dtoMaker = DTOMaker.getSingletonInstance();

        DTO risp = (DTO) service.RichiediAlServer(dtoMaker.getPlayerAvversarioTankRandomDTO(livello));

        if (risp.getFunzione().equals("TankTrovato")) {
            tank = (ITank) risp.getOggettiTrasferimento().get(0);
        }
        else {
            throw new NullPointerException("Tank avversario casuale non trovato");
        }

        return tank;
    }
}
