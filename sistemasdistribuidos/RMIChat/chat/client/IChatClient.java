package chat.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatClient extends Remote {

	public String getNickName() throws RemoteException;
	
	public void notifyMessage(IChatClient sender, String message) throws RemoteException;
	
	public void sendMessage(String message) throws RemoteException;
}
