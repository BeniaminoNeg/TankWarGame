package Shared.Domain.Creator.AzioneCreator;

import Shared.Domain.Azioni.IAzione;

import java.util.List;

/**
 * Created by beniamino on 26/02/16.
 */
public interface IAzioneCreator {
    IAzione doMakeAzione(String idAz, List<Integer> valori);
}
