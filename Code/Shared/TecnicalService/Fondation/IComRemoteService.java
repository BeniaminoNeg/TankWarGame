package Shared.TecnicalService.Fondation;

import Shared.Util.DTO;

import java.io.IOException;

/**
 * Created by gioele on 04/03/16.
 */
public interface IComRemoteService {

    Object RichiediAlServer(DTO dto) throws IOException;


}
