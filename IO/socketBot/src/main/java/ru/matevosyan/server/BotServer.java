package ru.matevosyan.server;
import java.net.*;
import java.io.*;

public class BotServer {

    private Socket socket;
    private static final int PORT = 19999;
    private static final String SPACE = System.getProperty("line.separator");

    public BotServer(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] ar) {
        try (Socket ss = new ServerSocket(PORT).accept()) {

            System.out.printf("Got a client%s", SPACE);

            BotServer botServer = new BotServer(ss);
            botServer.startServerWork();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServerWork() {

        try (InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();

//                 DataInputStream in = new DataInputStream(sin);
//                 DataOutputStream out = new DataOutputStream(sout)) {
             BufferedReader in = new BufferedReader(new InputStreamReader(sin));
             PrintWriter out = new PrintWriter(sout, true)) {

            String line;

            do {
//                    line = in.readUTF();
                line = in.readLine();
                System.out.printf("Client ~ %s%s", line, SPACE);

//                    out.writeUTF(serverAnswer(line) + SPACE);


                if (line.contains("Hello")) {
                    out.print("Hello my friend");
                    out.println();
//                    out.write("Hello my friend");
//                    out.println("");
                } else if(!line.equals("Hello") && !line.equals("Bye")) {
                    out.print("What do you want. I don't understand you!");
                    out.println();
//                    out.write("What do you want. I don't understand you!");
//                    out.println("");
                }

                    out.flush();
            } while (!line.equals("Bye"));

        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}