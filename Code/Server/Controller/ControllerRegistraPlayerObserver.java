package Server.Controller;

import Server.ServerUtil.HibernateUtil;
import Shared.Domain.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created by gioele on 16/02/16.
 */
public class ControllerRegistraPlayerObserver implements Observer {

    public ControllerRegistraPlayerObserver() {


    }

    @Override
    public void update(ControllerFacade controller) {

        if (controller.getFunzione().equals("RegistraPlayer")) {

            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                session.beginTransaction();
                session.save((Player) controller.getOggettiPersistenti().get(0));

                session.getTransaction().commit();
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();

            }


            System.out.println("Registro il Player nel DB");


        }

    }
}
