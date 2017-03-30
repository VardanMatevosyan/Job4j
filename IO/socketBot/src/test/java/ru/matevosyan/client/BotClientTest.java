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
 * Created by Admin on 26.03.2017.
 */
public class BotClientTest {

    private static final String LN = System.getProperty("line.separator");

    public void whenClientSendThenServerReturn(String input, String expected, String typeIn) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream outType = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outType));

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream typeValue = new ByteArrayInputStream(typeIn.getBytes());

        System.setIn(typeValue);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);


        BotClient botClient = new BotClient(socket);

        botClient.startClient();

        assertThat(outType.toString("UTF-8"), is(expected));
    }


    @Test
    public void whenSendHelloThenReturnHello() throws IOException {
        this.whenClientSendThenServerReturn("Hello my friend",
                String.format("Type something to send to Oracle%sSending this line to the server...%s" +
                                "Server ~ Hello my friend%sSending this line to the server...%sServer ~ null%s",
                        LN, LN, LN, LN, LN),
                String.format("Hello%sBye", LN));
    }
//    @Test
//    public void whenSendByThenReturnEmpty() throws IOException {
//        this.whenClientSendThenServerReturn("", "", "Bye");
//    }
}