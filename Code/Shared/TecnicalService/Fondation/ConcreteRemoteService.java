package Shared.TecnicalService.Fondation;

import Shared.Util.DTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by gioele on 04/03/16.
 */
public class ConcreteRemoteService implements IComRemoteService {


    private static ConcreteRemoteService singletonInstance = null;


    public static ConcreteRemoteService getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ConcreteRemoteService();
        }
        return singletonInstance;
    }


    @Override
    public DTO RichiediAlServer(DTO dto) throws IOException {
        Socket clientSocket = new Socket("localhost", 6789);
        DTO risp = null;
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(dto);
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());


            risp = (DTO) objectInputStream.readObject();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }


        return risp;
    }

}
