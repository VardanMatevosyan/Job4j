package ru.matevosyan.client;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

//        ByteArrayOutputStream outType = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outType));

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ByteArrayInputStream typeValue = new ByteArrayInputStream(typeIn.getBytes());
        System.setIn(typeValue);

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        BotClient botClient = new BotClient(socket);
        botClient.startClient();

//        byte[] buff = new byte[1024];
//        int len;
//
//        OutputStream outputStream = new ByteArrayOutputStream();
//        while ((len = socket.getInputStream().read(buff)) > 0) {
//            outputStream.write(buff, 0, len);
//        }

        assertThat(out.toString(), is(expected));
    }


    @Test
    public void whenSendByeThenReturnEmpty() throws IOException {
        this.whenClientSendThenServerReturn("Bye", "", "Bye");
    }

    @Test
    public void whenSendHelloThenReturnHello() throws IOException {
        this.whenClientSendThenServerReturn("Hello my friend" + LN, "Hello my friend", String.format("Hello%sBye", LN));

    }
}