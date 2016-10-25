package Server;

import Server.Controller.ControllerFacade;
import Server.ServerUtil.RispostaMaker;
import Shared.Util.DTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gioele on 16/02/16.
 */
public class ConnectionListner {

    private ControllerFacade controllerFacade;
    private ServerSocket welcomeSocket = null;

    public ConnectionListner() {

    }

    public void StartServer() throws IOException {

        try {
            welcomeSocket = new ServerSocket(6789);
            System.out.println("Apertura Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {

            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectInputStream objectInput = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            DTO dto = null;
            try {

                dto = (DTO) objectInput.readObject();
                controllerFacade.ArrivaRichiesta(dto);
                System.out.println("Arrivata Una Richesta");
                DTO Risposta = RispostaMaker.getSingletonInstance().PrelevaRisposta();
                objectOutputStream.writeObject(Risposta);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                connectionSocket.close();
            }

        }


    }

    public void setControllerFacade(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }
}