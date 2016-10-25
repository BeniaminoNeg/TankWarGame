package Shared.Controllers;

import Shared.Domain.Player;
import Shared.TecnicalService.Fondation.ConcreteRemoteService;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;

import java.io.IOException;

/**
 * Created by gioele on 22/03/16.
 */
public class LoginHandler {
    private static LoginHandler singletonInstance = null;


    public static LoginHandler getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new LoginHandler();
        }
        return singletonInstance;
    }


    private Player player;

    private LoginHandler() {
    }


    public Player getPlayerFromServer(String username, String password) throws IOException {

        ConcreteRemoteService service = ConcreteRemoteService.getSingletonInstance();
        DTOMaker dtoMaker = DTOMaker.getSingletonInstance();

        DTO risp = (DTO) service.RichiediAlServer(dtoMaker.getEffettuaLoginDTO(username, password));


        if (risp.getFunzione().equals("UtenteTrovato")) {
            this.player = (Player) risp.getOggettiTrasferimento().get(0);
        }
        else {
            throw new NullPointerException("Utente non trovato");
        }

        return player;

    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
