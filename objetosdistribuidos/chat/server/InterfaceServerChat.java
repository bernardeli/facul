package chat.server;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;

import chat.client.InterfaceClientChat;

public interface InterfaceServerChat extends Remote {
    public void registerClient(InterfaceClientChat client) throws RemoteException;
    public void unregisterClient(InterfaceClientChat client) throws RemoteException;
    public void publishMessage(InterfaceClientChat client, String message) throws RemoteException;
}
