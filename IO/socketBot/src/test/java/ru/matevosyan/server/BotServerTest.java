package ru.matevosyan.server;

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
 * Created BotClientTest to test BotClient class.
 * Created on 25.03.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BotServerTest {

    private static final String LN = System.getProperty("line.separator");
    private static final String EMPTYSTRING = System.getProperty("line.separator");

    /**
     * Method whenClientAskThenServerAnswer was created for check correct output and expected value.
     * @param input passing variable that server must read.
     * @param expected value that will be check with input variable.
     * @throws IOException to handle invoking  input and output exception.
     */

    private void whenClientAskThenServerAnswer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotServer botServer = new BotServer(socket);

        botServer.startServerWork();

        assertThat(out.toString(), is(expected));

    }

    /**
     * whenClientAskForExitThenServerAnswerEmpty was created to test server responding value to client.
     * invoking {@link BotServerTest#whenClientAskThenServerAnswer(String, String)} and passing client input value.
     * and expected value.
     * @throws IOException to handle invoking  input and output exception.
     */

    @Test
    public void whenClientAskForExitThenServerAnswerEmpty() throws IOException {

        this.whenClientAskThenServerAnswer("Bye", "");

    }

    /**
     * whenClientAskForExitThenServerAnswerEmpty was created to test server responding value to client.
     * invoking {@link BotServerTest#whenClientAskThenServerAnswer(String, String)} and passing client input value.
     * and expected value.
     * @throws IOException to handle invoking  input and output exception.
     */

    @Test
    public void whenClientAskForBlaBlaThenServerAnswerDoNotUnderstand() throws IOException {

        this.whenClientAskThenServerAnswer(String.format("sdfsdsdfsdfsdf%sBye", LN),
                "What do you want. I don't understand you!" + EMPTYSTRING);

    }

    /**
     * whenClientAskForExitThenServerAnswerEmpty was created to test server responding value to client.
     * invoking {@link BotServerTest#whenClientAskThenServerAnswer(String, String)} and passing client input value.
     * and expected value.
     * @throws IOException to handle invoking  input and output exception.
     */

    @Test
    public void whenClientAskForBlaBlaTwoTimesThenServerAnswerDoNotUnderstandTwoTimes() throws IOException {

        this.whenClientAskThenServerAnswer(String.format("sdfsdsdfsdfsdf%sBsdssdsdsdsdy%sBye", LN, LN),
                "What do you want. I don't understand you!" + EMPTYSTRING
                        + "What do you want. I don't understand you!" + EMPTYSTRING);

    }

}