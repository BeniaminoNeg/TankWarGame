package Server.Controller;

import Server.ServerUtil.HibernateUtil;
import Server.ServerUtil.RispostaMaker;
import Shared.Domain.Player;
import Shared.Domain.Strategia;
import Shared.Util.DTO;
import Shared.Util.DTOMaker;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by gioele on 04/03/16.
 */
public class ControllerRegistraStrategiaObserver implements Observer {


    @Override
    public void update(ControllerFacade controller) {
        if (controller.getFunzione().equals("RegistraStrategia")) {


            String playerId = (String) controller.getOggettiPersistenti().get(0);
            Strategia strategia = (Strategia) controller.getOggettiPersistenti().get(1);
            DTO rispostaDTO = null;

            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria cr = session.createCriteria(Player.class);
            cr.add(Restrictions.eq("id", playerId));
            List results = cr.list();
            Player player = (Player) results.get(0);
            player.aggiungiStrategia(strategia);
            rispostaDTO = DTOMaker.getSingletonInstance().getStrategiaServerResponceDTO();
            RispostaMaker.getSingletonInstance().ImmettiRisposta(rispostaDTO);
            System.out.println("Player Immesso nella risposta");

            try {
                session.beginTransaction();

                session.update(player);

                session.getTransaction().commit();
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }

            System.out.println("Registro una strategia al player");


        }


    }
}
