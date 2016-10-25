package Shared.Domain.StartupBattle;

import Shared.Domain.CampoBattaglia;
import Shared.TecnicalService.Fondation.ConcreteRemoteService;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;

import java.io.IOException;

/**
 * Created by gioele on 22/03/16.
 */
public class RandomCampoBattagliaGenerator implements  IGeneratoreCampoBattaglia {

    public CampoBattaglia getCampoBattaglia(Integer livello) throws IOException {
        CampoBattaglia campoBattaglia;

        ConcreteRemoteService service = ConcreteRemoteService.getSingletonInstance();
        DTOMaker dtoMaker = DTOMaker.getSingletonInstance();

        DTO risp = (DTO) service.RichiediAlServer(dtoMaker.getCampoBattagliaRandomDTO(livello));

        if (risp.getFunzione().equals("CampoBattagliaRandomTrovato")) {
            campoBattaglia = (CampoBattaglia) risp.getOggettiTrasferimento().get(0);

            //Conversione da ArrayList ad ArrayBidimensionale
            campoBattaglia.ConvertiDaArrayListAdArray();



        }
        else {
            throw new NullPointerException("Campo Battaglia casuale non trovato");
        }

        return campoBattaglia;
    }

}
