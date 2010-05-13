package chat.server;

import java.rmi.RemoteException;
import java.rmi.Remote;

import chat.client.InterfaceClientChat;

public interface InterfaceServerChat extends Remote {
    public void registerClient(InterfaceClientChat newClient) throws RemoteException;
    public void unregisterClient(InterfaceClientChat newClient) throws RemoteException;
    public void publishMessage(InterfaceClientChat client, String message) throws RemoteException;
}