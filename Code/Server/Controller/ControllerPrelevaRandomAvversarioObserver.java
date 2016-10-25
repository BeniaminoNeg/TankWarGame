package Server.Controller;

import Server.ServerUtil.HibernateUtil;
import Server.ServerUtil.RispostaMaker;
import Shared.Domain.Tank;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import java.util.List;
import java.util.Random;

/**
 * Created by Gioele on 31/03/2016.
 */
public class ControllerPrelevaRandomAvversarioObserver implements Observer {
    @Override
    public void update(ControllerFacade controller) {

        if (controller.getFunzione().equals("PrelevaPlayerAvversarioTankRandom")) {
            System.out.println("Prelevo il tank di un avversario casuale");

            Integer livello = (Integer)controller.getOggettiPersistenti().get(0);

            Integer limiteSuperiore = livello + 5;
            Integer limiteInferiore = livello -5;
            if(limiteInferiore < 0){
                limiteInferiore = 0;
            }

            DTO rispostaDTO = null;

            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria cr = session.createCriteria(Tank.class);
            cr.add(Restrictions.between("livello",limiteInferiore,limiteSuperiore));
            List results = cr.list();

            // Scelta casuale di un risultato

            Integer c = ((results.size()));
            Random random = new Random();


            Integer Casuale = random.nextInt(c);

            if (Casuale == 0){
                Casuale = 1;
            }


            rispostaDTO = DTOMaker.getSingletonInstance().getPlayerAvversarioTankRandomResponce((Tank)results.get(Casuale));


            RispostaMaker.getSingletonInstance().ImmettiRisposta(rispostaDTO);



        }

    }
}
