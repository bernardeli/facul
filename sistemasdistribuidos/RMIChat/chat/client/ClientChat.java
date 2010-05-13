package chat.client;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import chat.server.InterfaceServerChat;

public class ClientChat extends UnicastRemoteObject implements InterfaceClientChat, Serializable {
    private static final long serialVersionUID = 1234567891234567890L;

    private String nickName;
    private InterfaceServerChat server;

    public ClientChat(String nickName, String serverUrl) throws RemoteException {
        this.nickName = nickName;

        try {
            this.server = (InterfaceServerChat)Naming.lookup(serverUrl);	
            this.server.registerClient(this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void finalize(){
        try {
            this.server.unregisterClient(this);	
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getNickName() throws RemoteException {
        return this.nickName;
    }

    public void sendMessage(String message) throws RemoteException {

        try {
            this.server.publishMessage(this, message);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void notifyMessage(InterfaceClientChat sender, String message) throws RemoteException {
        System.out.println(String.format("%s: %s", sender.getNickName(), message));
    }

    public static void main(String[] args) {

        System.out.println("RMI Chat Service\n");
        System.out.println("Informe um nick name:\n");

        Scanner scan = new Scanner(System.in);
        String nickName = scan.next();

        InterfaceClientChat client = null;
        try {
            client = new ClientChat(nickName, "rmi://localhost:1099/chatServer");	
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Digite sua mensagem ou quit para sair\n");

        while (true) {
            String message = scan.next();
            if (message == "quit") {
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
            System.out.println(String.format("%s saiu do chat.", client.getNickName()));	
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
