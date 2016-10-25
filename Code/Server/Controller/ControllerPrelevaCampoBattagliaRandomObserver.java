package Server.Controller;

import Server.ServerUtil.HibernateUtil;
import Server.ServerUtil.RispostaMaker;
import Shared.Domain.CampoBattaglia;
import Shared.Domain.Tank;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Random;

/**
 * Created by Gioele on 06/04/2016.
 */
public class ControllerPrelevaCampoBattagliaRandomObserver implements Observer {
    @Override
    public void update(ControllerFacade controller) {
        if (controller.getFunzione().equals("PrelevaCampoBattagliaRandom")) {
            Integer livello = (Integer)controller.getOggettiPersistenti().get(0);
            DTO rispostaDTO = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria cr = session.createCriteria(CampoBattaglia.class);
            List results = cr.list();
            // Scelta casuale di un risultato

            Integer c = ((results.size()));
            Random random = new Random();


            Integer Casuale = random.nextInt(c);
            rispostaDTO = DTOMaker.getSingletonInstance().getCampoBattagliaRandomResponce((CampoBattaglia) results.get(Casuale));


            RispostaMaker.getSingletonInstance().ImmettiRisposta(rispostaDTO);






        }
    }
}
