package Server.Controller;

/**
 * Created by gioele on 16/02/16.
 */
public class ControllerPrelevaStrategiaObserver implements Observer {

    public void ControllerPrelevaStrategiaObserver() {

    }


    @Override
    public void update(ControllerFacade controller) {
        if (controller.getFunzione() == "Preleva") {
            System.out.println("Prelevo la strategia");
        }


    }
}
