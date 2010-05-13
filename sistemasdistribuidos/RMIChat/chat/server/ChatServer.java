package chat.server;

import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import chat.client.IChatClient;

public class ChatServer extends UnicastRemoteObject implements IChatServer, Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private ArrayList<IChatClient> connectedClients;
    private ArrayList<String> history;

    public ChatServer() throws RemoteException {
        this.connectedClients = new ArrayList<IChatClient>();
        this.history = new ArrayList<String>();
    }

    public void registerClient(IChatClient newClient) throws RemoteException {
        connectedClients.add(newClient);
    }

    public void unregisterClient(IChatClient newClient) throws RemoteException {
        connectedClients.remove(newClient);
    }

    public void publishMessage(IChatClient client, String message) throws RemoteException {
        this.history.add(message);

        for (IChatClient chatClient : this.connectedClients) {
            if (chatClient.getNickName() != client.getNickName())
                chatClient.notifyMessage(client, message);
        }
    }

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());

        try {
            Registry registry = LocateRegistry.getRegistry();
            IChatServer server = new ChatServer();
            registry.rebind("chatServer", server);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
