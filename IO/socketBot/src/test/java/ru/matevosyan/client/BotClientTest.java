package ru.matevosyan.client;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created BotClientTest to test BotClient class.
 * Created on 25.03.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BotClientTest {

    private static final String LN = System.getProperty("line.separator");

    /**
     * Method whenSendHelloAndByeThenCheckOutput was created to test when client send the word that server is knowing.
     * @throws IOException to handle invoking  input and output exception.
     */

    @Test
    public void whenSendHelloAndByeThenCheckOutput() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream outType = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outType));


        ByteArrayInputStream in = new ByteArrayInputStream("Hello my friend".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String expected = String.format("Type something to send to Oracle%s"
                + "Sending this line to the server...%s"
                + "Server ~ %s%sSending this line to the server...%s", LN, LN, "Hello my friend", LN, LN);

        ByteArrayInputStream typeValue = new ByteArrayInputStream(String.format("Hello%sBye", LN).getBytes());
        System.setIn(typeValue);

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        BotClient botClient = new BotClient(socket);
        botClient.startClient();

        assertThat(outType.toString(), is(expected));
    }

    /**
     * Method whenSendBlaBlaThenCheckOutputThatServerDoNotKnowWhatThatMean was created to test.
     * When client send the word that server dose not knowing.
     * @throws IOException to handle invoking  input and output exception.
     */

    @Test
    public void whenSendBlaBlaThenCheckOutputThatServerDoNotKnowWhatThatMean() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream outType = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outType));


        ByteArrayInputStream in = new ByteArrayInputStream("What do you want. I don't understand you!".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String expected = String.format("Type something to send to Oracle%s"
                + "Sending this line to the server...%s"
                + "Server ~ %s%s"
                + "Sending this line to the server...%s", LN, LN, "What do you want. I don't understand you!", LN, LN);

        ByteArrayInputStream typeValue = new ByteArrayInputStream(String.format("BlaBlaBla%sBye", LN).getBytes());
        System.setIn(typeValue);

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        BotClient botClient = new BotClient(socket);
        botClient.startClient();

        assertThat(outType.toString(), is(expected));
    }

    /**
     * Method whenSendByeThenCheckOutputThatClientGetNothingJustCommonInfoSentences was created to test.
     * When client send Bye to the server.
     * @throws IOException to handle invoking  input and output exception.
     */

    @Test
    public void whenSendByeThenCheckOutputThatClientGetNothingJustCommonInfoSentences() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream outType = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outType));


        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String expected = String.format("Type something to send to Oracle%s"
                + "Sending this line to the server...%s", LN, LN);

        ByteArrayInputStream typeValue = new ByteArrayInputStream("Bye".getBytes());
        System.setIn(typeValue);

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        BotClient botClient = new BotClient(socket);
        botClient.startClient();

        assertThat(outType.toString(), is(expected));
    }

}