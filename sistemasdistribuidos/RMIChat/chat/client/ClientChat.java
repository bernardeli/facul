package chat.client;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import chat.server.InterfaceServerChat;

public class ClientChat extends UnicastRemoteObject implements InterfaceClientChat, Serializable {
    private static final long serialVersionUID = 1234567891234567890L;

    private String nick;
    private InterfaceServerChat server;

    public ClientChat(String nick, String serverUrl) throws RemoteException {
        this.nick = nick;

        try {
            this.server = (InterfaceServerChat)Naming.lookup(serverUrl);	
            this.server.registerClient(this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getNick() throws RemoteException {
        return this.nick;
    }

    public void finalize() throws RemoteException{
        try {
            this.server.unregisterClient(this);	
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sendMessage(String message) throws RemoteException {
        try {
            this.server.publishMessage(this, message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void notifyMessage(InterfaceClientChat sender, String message) throws RemoteException {
        System.out.println(String.format(">> %s: %s", sender.getNick(), message));
    }

    public static void main(String[] args) {
        System.out.println(">> Informe um nick para entrar no chat:");

        Scanner scan = new Scanner(System.in);
        String nick = scan.next();
        InterfaceClientChat client = null;

        try {
            client = new ClientChat(nick, "rmi://localhost:1099/chatServer");	
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Digite sua mensagem ou quit para sair.");

        while (true) {
            String message = scan.next();
            if (message.equals("quit")) {
                break;
            }
            else {
                try {
                    client.sendMessage(message);	
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        try {
            client.sendMessage("saiu do chat");
            client.finalize();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
