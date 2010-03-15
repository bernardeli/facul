// Coded by Ricardo Bernardelli - 405.2941-1

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) {

        String filename = "/Users/rbernardelli/projects/facul/objetosdistribuidos/trash/model" + Math.random() + ".py";

        try {
            Socket socket = new Socket("127.0.0.1", 7777);
            DataInputStream in = new DataInputStream (socket.getInputStream());
            FileOutputStream file_in = new FileOutputStream (filename);
            DataOutputStream out = new DataOutputStream (file_in);

            byte buffer[] = new byte[512];

            while (in.read(buffer) != -1)
                System.out.print("\nReceiving buffer from server");
                out.write(buffer, 0, buffer.length);
        } catch (IOException exception) {
            System.out.println("\nException: " + exception);
        }
    }
} 
