package ru.matevosyan.server;
import java.net.*;
import java.io.*;
public class BotServer {
    public static void main(String[] ar)    {
        int port = 19999;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept();
            System.out.println("Got a client :)!");
            System.out.println();

            try (InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout)) {

                String line = null;

                while(true) {
                    line = in.readUTF();
                    System.out.println("The dumb client just sent me this line : " + String.valueOf(line));
                    System.out.println("I'm sending it back...");
                    out.writeUTF(line);
                    out.flush();
                    System.out.println("Waiting for the next line...");
                    System.out.println();
                }

            } catch (IOException ioe) {
                ioe.getMessage();
            }

        } catch(Exception x) { x.printStackTrace(); }
    }
}