package ru.matevosyan.client;

import java.net.*;
import java.io.*;

public class BotClient {

    private static final int PORT = 19999;
    private static final String ADDRESS = "127.0.0.1";
    private Socket socket;
    private static final String SPACE = System.getProperty("line.separator");

    public BotClient(final Socket socket) {
        this.socket = socket;
    }

    public BotClient() {

    }

    public static void main(String[] args) {
            BotClient botClient = new BotClient();
            botClient.startClient();

    }

    public void startClient() {

        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            socket = new Socket(ipAddress, PORT);
//            System.out.printf("Yes! I just got hold of the program. %s", SPACE);

            try (

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

//            DataInputStream in = new DataInputStream(sin);
//            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader in = new BufferedReader(new InputStreamReader(sin));
            PrintWriter out = new PrintWriter(sout, true);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
                String line;
                System.out.printf("Type something to send to Oracle%s", SPACE);

                do {
                    line = keyboard.readLine();
                    System.out.printf("Sending this line to the server...%s", SPACE);
                    out.println(line);

                    line = in.readLine();
                    System.out.printf("Server ~ %s", line + SPACE);

                } while (!line.equals("Bye"));
            } catch (IOException ioe) {
            ioe.getMessage();
            }
            socket.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}