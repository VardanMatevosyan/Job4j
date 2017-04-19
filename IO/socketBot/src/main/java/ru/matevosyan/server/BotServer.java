package ru.matevosyan.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created BotServer to interchange with client.
 * Created on 19.03.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BotServer {

    private Socket socket;
    private static final int PORT = 19999;
    private static final String SPACE = System.getProperty("line.separator");

    /**
     * Constructor with passing socket but in server side send ServerSocket.
     * @param socket socket that represent input ans output stream in network connection.
     */

    public BotServer(Socket socket) {
        this.socket = socket;
    }

    /**
     * Main method that initialise variable and start the program with Server socket and when client will be connect.
     * @param args without parameter don't work main method.
     */


    public static void main(String[] args) {
        try (Socket ss = new ServerSocket(PORT).accept()) {

            System.out.printf("Got a client%s", SPACE);

            BotServer botServer = new BotServer(ss);
            botServer.startServerWork();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method startServerWork was created to send and receive data to the client using ServerSocket connection.
     */

    public void startServerWork() {

        try (InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();

             BufferedReader in = new BufferedReader(new InputStreamReader(sin));
             PrintWriter out = new PrintWriter(sout, true)) {

            String line;

            do {

                line = in.readLine();
                System.out.printf("Client ~ %s%s", line, SPACE);

                if (line.contains("Hello")) {
                    out.print("Hello my friend");
                    out.println();
                } else if (!line.equals("Hello") && !line.equals("Bye")) {
                    out.print("What do you want. I don't understand you!");
                    out.println();
                }

                out.flush();
            } while (!line.equals("Bye"));

        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}