// Coded by Ricardo Bernardelli - 405.2941-1

import java.io.*;
import java.net.*;

public class Server {

    private static int port = 7777;
    private static int client_id = 0;
    public static void main(String[] args) {

        try {
            ServerSocket listener = new ServerSocket(port);
            Socket server;
            Connect connection;

            System.out.println("\nWaiting for connection...");

            while((server = listener.accept()) != null) {
                System.out.println("\nIntercepting connection from client: "+ client_id++);
                Connect connect_obj = new Connect(server, client_id);
                Thread thread = new Thread(connect_obj); // Cria thread com o objeto da classe Connect
                thread.start();
            }

        } catch (IOException exception) {
            System.out.println("\nException: " + exception);
            exception.printStackTrace();
        }
    }
}

class Connect implements Runnable {

    private Socket client;
    private final int client_id;
    private final String file = "/Users/rbernardelli/Documents/models.py";

    Connect(Socket client, int client_id) {
        this.client = client;
        this.client_id = client_id;
    }

    public void run () {

        try {
            DataOutputStream out = new DataOutputStream (client.getOutputStream());
            FileInputStream file_in = new FileInputStream (file);
            DataInputStream in = new DataInputStream (file_in);

            byte buffer[] = new byte[512];

            while (in.read(buffer) != -1){
                System.out.print("\nSending buffer to: " + client_id);
                out.write(buffer, 0, buffer.length);
            }
        } catch (Exception exception) {
            System.out.println("\nException" + exception);
            System.exit(0);
        } finally {
            try {
                client.close();
            } catch (Exception exception) {
                System.out.println("\nException" + exception);
                System.exit(0);
            }
        }
    }
}
