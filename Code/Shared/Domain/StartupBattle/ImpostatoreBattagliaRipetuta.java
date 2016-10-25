package Shared.Domain.StartupBattle;

import Shared.Domain.CampoBattaglia;
import Shared.Domain.Caselle.ICasella;
import Shared.Domain.ITank;
import Shared.Domain.Posizione;
import Shared.Util.RandomMinMax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emanuele on 07/05/16.
 */
public class ImpostatoreBattagliaRipetuta implements IImpostatoreBattaglia {

    private ITank tankAvversario;
    private CampoBattaglia campoBattaglia;

    public ImpostatoreBattagliaRipetuta(ITank tankAvversario, CampoBattaglia campo) {
        this.tankAvversario = tankAvversario;
        this.campoBattaglia = campo;
    }

    @Override
    public ITank getAvversario(Integer livello) {
        return this.tankAvversario;
    }

    @Override
    public void posizionaTank(ITank tankCasa, ITank tankTrasferta, CampoBattaglia campo) {
        List<ITank> tank = new ArrayList<>();
        tank.add(tankCasa);
        tank.add(tankTrasferta);

        int dimX=campo.getDimensioneX();
        int dimY=campo.getDimensioneY();


        ICasella casellaAttuale;
        ICasella casellaPrecedente = null;
        Integer posX;
        Integer posY;
        Posizione posizione;
        int PosizionamentoDestraSinistra = 0;

        for (ITank t: tank) {
            do {

                if((PosizionamentoDestraSinistra%2) == 0) {
                    posX = 0;
                }
                else{
                    posX = dimX-1;
                }
                posY= RandomMinMax.randInt(0,dimY-1);
                posizione = new Posizione(posX,posY);
                casellaAttuale = campo.getCasella(posizione);
                if (casellaPrecedente==null || casellaPrecedente.isDisponibile()) {
                    PosizionamentoDestraSinistra++;
                }
                casellaPrecedente = casellaAttuale;
            } while(!casellaAttuale.isDisponibile());
            campo.posizionaTank(t,posizione);
            casellaAttuale.setPosizione(posizione);
            t.setCasellaPosizione(casellaAttuale);
            Integer or = RandomMinMax.randInt(0,3);
            t.setOrientamento(or);
        }
    }

    @Override
    public CampoBattaglia getCampoBattaglia(Integer livello) {
        return this.campoBattaglia;
    }

    @Override
    public String decidiTurno(String idTankPersonale, String idTankAvversario) {
        Integer random = RandomMinMax.randInt(0,1);
        String idInizio;
        if (random == 1) {
            idInizio = idTankAvversario;
        } else {
            idInizio = idTankPersonale;
        }
        return idInizio;
    }
}
