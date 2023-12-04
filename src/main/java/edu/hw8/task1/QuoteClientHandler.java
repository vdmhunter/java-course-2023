package edu.hw8.task1;

import edu.common.Generated;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code QuoteClientHandler} class represents a handler for individual client connections
 * in a multi-client quote server. It processes incoming requests, retrieves quotes based on
 * specified keywords, and sends appropriate responses to clients.
 */
public class QuoteClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Semaphore connectionSemaphore;
    private static final int BUFFER_SIZE = 1024;
    private static final HashMap<String, String> QUOTES = new HashMap<>();
    private static final String DEFAULT_QUOTE = "Извини, ты даже не достоин оскорблений. Твои слова не стоят и "
        + "байта в памяти.";
    private static final Logger LOGGER = LogManager.getLogger();

    static {
        QUOTES.put(
            "личности",
            "Не переходи на личности там, где их нет."
        );
        QUOTES.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами."
        );
        QUOTES.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        QUOTES.put(
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления."
        );
        QUOTES.put(
            "самоуверенность",
            "Такая самоуверенность, будто у тебя в руках не мышь, а пульт дистанционного управления вселенной."
        );
        QUOTES.put(
            "эрудиция",
            "Твоя эрудиция поражает — как если бы Google решил наделить котенка библиотечными знаниями."
        );
    }

    /**
     * Constructs a new {@code QuoteClientHandler} for a specific client socket and semaphore.
     *
     * @param clientSocket        The client socket for communication with the connected client.
     * @param connectionSemaphore The semaphore controlling access to the server's resources.
     */
    public QuoteClientHandler(Socket clientSocket, Semaphore connectionSemaphore) {
        this.clientSocket = clientSocket;
        this.connectionSemaphore = connectionSemaphore;
    }

    /**
     * Executes the handler logic for the client connection. Processes incoming requests,
     * retrieves quotes, and sends appropriate responses to the client.
     */
    @Override
    public void run() {
        try {
            processRequest();
        } finally {
            connectionSemaphore.release();
        }
    }

    /**
     * Processes an incoming request from the client, retrieves a quote based on the request,
     * and sends the response back to the client.
     */
    @Generated()
    private void processRequest() {

        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            var buffer = new byte[BUFFER_SIZE];

            while (true) {
                int bytesRead = inputStream.read(buffer);

                if (bytesRead != -1) {
                    String request = new String(buffer, 0, bytesRead);
                    String response = QUOTES.getOrDefault(request.toLowerCase(), DEFAULT_QUOTE);

                    outputStream.write(response.getBytes());
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
