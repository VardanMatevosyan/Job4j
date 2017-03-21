package ru.matevosyan.client;

import java.net.*;
import java.io.*;

public class BotClient {
    public static void main(String[] ar) {
        int serverPort = 19999;
        String address = "127.0.0.1";

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("Yes! I just got hold of the program.");

            try (
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

//            DataInputStream in = new DataInputStream(sin);
//            DataOutputStream out = new DataOutputStream(sout);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
                String line = null;
                System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
                System.out.println();

                while (true) {
                    line = keyboard.readLine();
                    System.out.println("Sending this line to the server...");
//                    out.writeUTF(line);

                    out.write(line);
                    out.flush();

//                    line = in.readUTF();
                    line = in.readLine();
                    System.out.println("The server was very polite. It sent me this : " + line);
                    System.out.println();
                }
            } catch (IOException ioe) {
            ioe.getMessage();
        }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}