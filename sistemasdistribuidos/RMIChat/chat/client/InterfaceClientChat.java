package chat.client;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface InterfaceClientChat extends Remote {
    public String getNickName() throws RemoteException;
    public void notifyMessage(InterfaceClientChat sender, String message) throws RemoteException;
    public void sendMessage(String message) throws RemoteException;
}
