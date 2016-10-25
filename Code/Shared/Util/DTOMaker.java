package Shared.Util;

import Shared.Controllers.CreareStrategiaHandler;
import Shared.Domain.CampoBattaglia;
import Shared.Domain.Player;
import Shared.Domain.Tank;

/**
 * Created by gioele on 04/03/16.
 */
public class DTOMaker {

    private static DTOMaker singletonInstance = null;


    private DTOMaker() {


    }

    public static DTOMaker getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DTOMaker();
        }
        return singletonInstance;
    }

    public DTO getDatiLoginServerResponce(boolean trovato, Player player) {
        DTO dto = new DTO();
        if (trovato == true) {
            dto.setFunzione("UtenteTrovato");
            dto.aggiungiOggettoTrasferimento(player);
        } else {
            dto.setFunzione("UtenteNonTrovato");
        }

        return dto;
    }


    public DTO getEffettuaLoginDTO(String username, String password) {
        DTO dto = new DTO();
        dto.setFunzione("PrelevaPlayer");
        dto.aggiungiOggettoTrasferimento(username);
        dto.aggiungiOggettoTrasferimento(password);
        return dto;
    }


    public DTO getPlayerDTO() {

        DTO dto = new DTO();
        dto.setFunzione("RegistraPlayer");
        dto.aggiungiOggettoTrasferimento(CreareStrategiaHandler.getSingletonInstance().getCurrentPlayer());

        return dto;

    }

    public DTO getStrategiaDTO() {
        DTO dto = new DTO();
        dto.setFunzione("RegistraStrategia");
        Player player = CreareStrategiaHandler.getSingletonInstance().getCurrentPlayer();
        dto.aggiungiOggettoTrasferimento(player.getId());
        dto.aggiungiOggettoTrasferimento(player.getStrategieList().get(player.getStrategieList().size() - 1));
        return dto;

    }

    public DTO getStrategiaServerResponceDTO() {
        DTO dto = new DTO();
        dto.setFunzione("StrategiaRegistrata");

        return dto;

    }



    public DTO getPlayerAvversarioTankRandomResponce(Tank tank){
        DTO dto= new DTO();
        dto.setFunzione("TankTrovato");
        dto.aggiungiOggettoTrasferimento(tank);
        return dto;
    }


    public DTO getPlayerAvversarioTankRandomDTO(Integer livello){
        DTO dto= new DTO();
        dto.setFunzione("PrelevaPlayerAvversarioTankRandom");
        dto.aggiungiOggettoTrasferimento(livello);
        return dto;
    }


    public DTO getCampoBattagliaRandomDTO(Integer livello) {
        DTO dto= new DTO();
        dto.setFunzione("PrelevaCampoBattagliaRandom");
        dto.aggiungiOggettoTrasferimento(livello);
        return dto;
    }

    public DTO getCampoBattagliaRandomResponce(CampoBattaglia campoBattaglia) {
        DTO dto= new DTO();
        dto.setFunzione("CampoBattagliaRandomTrovato");
        dto.aggiungiOggettoTrasferimento(campoBattaglia);
        return dto;
    }
}
