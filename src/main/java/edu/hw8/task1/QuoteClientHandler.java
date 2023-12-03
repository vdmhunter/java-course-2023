package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

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
            "Чем ниже интеллект, тем громче оскорбления"
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

    public QuoteClientHandler(Socket clientSocket, Semaphore connectionSemaphore) {
        this.clientSocket = clientSocket;
        this.connectionSemaphore = connectionSemaphore;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            processRequest(inputStream, outputStream);

        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            connectionSemaphore.release();
        }
    }

    private void processRequest(@NotNull InputStream inputStream, OutputStream outputStream) throws IOException {
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
    }
}
