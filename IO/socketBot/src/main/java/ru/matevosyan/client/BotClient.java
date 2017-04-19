package ru.matevosyan.client;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.Socket;

/**
 * Created BotClient to interchange with server.
 * Created on 19.03.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BotClient {

    private Socket socket;
    private static final String SPACE = System.getProperty("line.separator");
    private boolean exit = true;

    /**
     * Constructor with passing socket (for better testing with mockito).
     * @param socket socket that represent input ans output stream in network connection.
     */

    public BotClient(final Socket socket) {
        this.socket = socket;
    }

    /**
     * Main method that initialise variable and start the program.
     * @param args without parameter don't work main method.
     */

    public static void main(String[] args) {
        Socket socket = new Socket();
        BotClient botClient = new BotClient(socket);
        botClient.startClient();

    }

    /**
     * Method startClient was created to send and receive data to the server using socket connection.
     */

    public void startClient() {

        try {

            try (

                    InputStream sin = socket.getInputStream();
                    OutputStream sout = socket.getOutputStream();

                    BufferedReader in = new BufferedReader(new InputStreamReader(sin));
                    PrintWriter out = new PrintWriter(sout, true);

                    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
                    String line;
                    System.out.printf("Type something to send to Oracle%s", SPACE);

                    while (this.exit) {

                        line = keyboard.readLine();
                        System.out.printf("Sending this line to the server...%s", SPACE);
                        out.println(line);

                        if (!(line.equals("Bye"))) {

                            line = in.readLine();
                            System.out.printf("Server ~ %s", line + SPACE);
                        } else {
                            this.exit = false;
                        }

                    }

            } catch (IOException x) {
                x.printStackTrace();
            }
            socket.close();

        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}