package Shared.Domain.StartupBattle;


import Shared.Domain.Caselle.ICasella;
import Shared.Domain.ITank;
import Shared.Domain.Posizione;
import Shared.Domain.Tank;
import Shared.Domain.CampoBattaglia;
import Shared.Util.RandomMinMax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gioele on 22/03/16.
 */
public class ImpostatoreBattagliaCasuale implements IImpostatoreBattaglia {

    private IGeneratoreAvversario generatoreAvversario = new RandomAvversarioGenerator();
    private IGeneratoreCampoBattaglia generatoreCampoBattaglia = new RandomCampoBattagliaGenerator();

    public ITank getAvversario(Integer livello) throws IOException {
        try{  return ((RandomAvversarioGenerator) this.generatoreAvversario).getAvversario(livello);
        }catch (NullPointerException e){
            throw new NullPointerException("Tank avversario non trovato");
        }


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
                posY=RandomMinMax.randInt(0,dimY-1);
                posizione = new Posizione(posX,posY);
                casellaAttuale = campo.getCasella(posizione);
                if (PosizionamentoDestraSinistra == 0) {
                    casellaPrecedente = casellaAttuale;
                }
                if (casellaPrecedente.isDisponibile()) {
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
    public CampoBattaglia getCampoBattaglia(Integer livello) throws IOException {
        CampoBattaglia campoBattaglia = null;
        try {
            campoBattaglia = ((RandomCampoBattagliaGenerator) this.generatoreCampoBattaglia).getCampoBattaglia(livello);
        }catch (NullPointerException e){
            throw new NullPointerException("Campo Battaglia non trovato");
        }
        return campoBattaglia;
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
