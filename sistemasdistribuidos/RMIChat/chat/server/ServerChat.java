package chat.server;

import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import chat.client.InterfaceClientChat;

public class ServerChat extends UnicastRemoteObject implements InterfaceServerChat, Serializable {

    private static final long serialVersionUID = 1234567891234567890L;

    private ArrayList<InterfaceClientChat> connectedClients;
    private ArrayList<String> history;

    public ServerChat() throws RemoteException {
        this.connectedClients = new ArrayList<InterfaceClientChat>();
        this.history = new ArrayList<String>();
    }

    public void registerClient(InterfaceClientChat client) throws RemoteException {
        connectedClients.add(client);
    }

    public void unregisterClient(InterfaceClientChat client) throws RemoteException {
        connectedClients.remove(client);
    }

    public void publishMessage(InterfaceClientChat client, String message) throws RemoteException {
        this.history.add(message);

        for (InterfaceClientChat chatClient : this.connectedClients) {
            if (chatClient.getNick() != client.getNick())
                chatClient.notifyMessage(client, message);
        }
    }

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());

        try {
            Registry registry = LocateRegistry.getRegistry();
            InterfaceServerChat server = new ServerChat();
            registry.rebind("chatServer", server);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
