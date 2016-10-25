package Server;

import Server.ServerUtil.HibernateUtil;
import Shared.Domain.CampoBattaglia;
import Shared.Domain.Caselle.ICasella;
import Shared.Domain.Caselle.MuroCasella;
import Shared.Domain.Caselle.PlainCasella;
import Shared.Domain.Posizione;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by gioele on 01/04/16.
 */
public class GeneraCampoBattagliaDemo {

    private ICasella[][] campo;
    private ICasella[] campomonodimensionale;
    private List<ICasella> caselleMonodimensionali = new ArrayList<ICasella>();
    private static  Integer dimensioneCampo = 10;

    public GeneraCampoBattagliaDemo() {

        CampoBattaglia campoBattaglia = new CampoBattaglia();

        campo = new ICasella[dimensioneCampo][dimensioneCampo];
        campomonodimensionale = new ICasella[dimensioneCampo*dimensioneCampo];

        campoBattaglia.setDimensioneCampo(dimensioneCampo);
        for(int i=0;i<dimensioneCampo;i=i+1){
            for(int j=0;j<dimensioneCampo;j=j+1){
                ICasella casellaConcreta = new PlainCasella();
                casellaConcreta.setId(UUID.randomUUID().toString());
                casellaConcreta.setPosizione(new Posizione(i,j));
                campo[i][j] = casellaConcreta;
                campomonodimensionale[i+j*dimensioneCampo]= casellaConcreta;
            }
        }

        Random random = new Random();
        for(int j=0;j<10;j=j+1){
                Integer x = random.nextInt(10);
                Integer y = random.nextInt(10);
            ICasella casellaMuroConcreta = new MuroCasella();
            casellaMuroConcreta.setId(UUID.randomUUID().toString());
            casellaMuroConcreta.setPosizione(new Posizione(x,y));
            campo[x][y] = casellaMuroConcreta;
            campomonodimensionale[x+y*dimensioneCampo]= casellaMuroConcreta;

        }


        for(int j=0;j<campomonodimensionale.length;j=j+1){
            caselleMonodimensionali.add(campomonodimensionale[j]);
        }



        campoBattaglia.setCaselleMonodimensionali(caselleMonodimensionali);
        campoBattaglia.setId(UUID.randomUUID().toString());



        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(campoBattaglia);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        }


        System.out.println("Registro il campo di Battaglia nel DB");





    }







}
