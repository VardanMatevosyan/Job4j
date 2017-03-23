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
            System.out.println("Got a client");
            System.out.println();

            try (InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout)) {

//                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String line = "";

                while(!line.equals("Пока")) {
                    line = in.readUTF();
//                    line = in.readLine();
                    System.out.println("The dumb client just sent me this line : " + String.valueOf(line));
                    System.out.println("I'm sending it back...");
                    //out.writeUTF(line);
//                    out.write(line);

                    if (line.contains("Hello")) {
                        out.writeUTF("Hello my friend");
                    } else if (line.contains("How do you do")) {
                        out.writeUTF("Thank's, I'm good, and you?");
                    } else if (line.equals("Which poem did you like?")) {
                        out.writeUTF("I love \"My Foolish Dog\"");
                    } else if (line.equals("Can you send me?")) {

                        out.writeUTF("\n\nYes, you are welcome \n" +
                                "My dog is quite hip\n" +
                                "Except when he takes a dip\n" +
                                "He looks like a fool\n" +
                                "when he jumps in the pool\n" +
                                "and reminds me of a sinking ship\n" +
                                "\n");
                    } else if (line.contains("Пока")) {
                        line = "Пока";
                    } else {
                        out.writeUTF("What do you want. I don't understand you!");
                    }
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