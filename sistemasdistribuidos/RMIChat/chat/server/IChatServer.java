package chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chat.client.IChatClient;

public interface IChatServer extends Remote {

	public void registerClient(IChatClient newClient) throws RemoteException;
	
	public void unregisterClient(IChatClient newClient) throws RemoteException;
	
	public void publishMessage(IChatClient client, String message) throws RemoteException;
}
