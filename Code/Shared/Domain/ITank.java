package Shared.Domain;

import Shared.Domain.Caselle.ICasella;
import Shared.Util.OrientamentoEnum;

import java.util.Map;

/**
 * Created by beniamino on 22/03/16.
 */
public interface ITank {

    void setStrategia(Strategia strategia);
    Strategia getStrategia();

    ITank getTank();
    void setTank(Tank tank);

    Integer getLivello();

    String getId();
    void setId(String id);

    ICasella getCasellaPosizione();

    void setCasellaPosizione(ICasella casellaPosizione);

    void setBombeRimanenti(Integer bombeRimanenti);

    Integer getBombeRimanenti();

    void setOrientamento(OrientamentoEnum orientamento);

    OrientamentoEnum getOrientamento();

    void setOrientamento(Integer orientamento);

    void faiMossa(ITank altroTank, CampoBattaglia campo);

    boolean seiMorto();

    Integer getMaxVisioneSparo();

    void colpito (Integer danno);

    Integer getPotenzaArma();

    Map getMap();

}

