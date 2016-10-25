package Server.Controller;

import Server.ServerUtil.HibernateUtil;
import Server.ServerUtil.RispostaMaker;

import Shared.Domain.Player;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Gioele on 05/03/2016.
 */
public class ControllerPrelevaPlayerObserver implements Observer {
    @Override
    public void update(ControllerFacade controller) {
        if (controller.getFunzione().equals("PrelevaPlayer")) {
            System.out.println("Prelevo Il Player");
            Session session = HibernateUtil.getSessionFactory().openSession();

            String Username = (String) controller.getOggettiPersistenti().get(0);
            String Password = (String) controller.getOggettiPersistenti().get(1);
            DTO rispostaDTO = null;

            Criteria cr = session.createCriteria(Player.class);
            cr.add(Restrictions.eq("nome", Username));
            List results = cr.list();
            if (results.size() != 0) {
                Player player = (Player) results.get(0);
                rispostaDTO = DTOMaker.getSingletonInstance().getDatiLoginServerResponce(true, player);
            } else {
                rispostaDTO = DTOMaker.getSingletonInstance().getDatiLoginServerResponce(false, null);
            }

            RispostaMaker.getSingletonInstance().ImmettiRisposta(rispostaDTO);



        }
    }
}
