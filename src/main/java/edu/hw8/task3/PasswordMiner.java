package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import org.jetbrains.annotations.NotNull;

public final class PasswordMiner {
    private final Map<String, String> md5HashLoginDatabase;
    private final String alphabet;
    private final int maxPasswordLength;
    private final ConcurrentHashMap<String, String> loginPasswordDatabase;

    public PasswordMiner(Map<String, String> md5hashLoginDatabase, String alphabet, int maxPasswordLength) {
        this.md5HashLoginDatabase = md5hashLoginDatabase;
        this.alphabet = alphabet;
        this.maxPasswordLength = maxPasswordLength;
        this.loginPasswordDatabase = new ConcurrentHashMap<>();
    }

    public ConcurrentMap<String, String> getLoginPasswordDatabase() {
        return loginPasswordDatabase;
    }

    public void singleThreadedPasswordMiner() {
        var generator = new PasswordGenerator(
            alphabet,
            maxPasswordLength
        );

        for (int i = 0; i < maxPasswordLength; ++i) {
            while (generator.hasNextPassword()) {
                String password = generator.generate();
                String hash = createMd5Hash(password);

                if (md5HashLoginDatabase.containsKey(hash)) {
                    String login = md5HashLoginDatabase.get(hash);
                    loginPasswordDatabase.put(login, password);
                }
            }

            generator = new PasswordGenerator(
                alphabet,
                maxPasswordLength - i - 1
            );
        }
    }

    public void multiThreadedPasswordMiner(ExecutorService executorService, int threadNumber) {
        var latch = new CountDownLatch(threadNumber);

        int part = (int) Math.floor((double) alphabet.length() / threadNumber);
        for (int i = 0; i < threadNumber; ++i) {
            int from = part * i;
            int to = (i == threadNumber - 1)
                ? alphabet.length() - 1
                : part * (i + 1) - 1;

            executorService.submit(() -> {
                try {
                    passwordGeneratorThread(from, to);
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private void passwordGeneratorThread(int from, int to) {
        var generator = new FromToPasswordGenerator(
            alphabet,
            from, to,
            maxPasswordLength
        );

        // Iterate through all passwords of length N, then N-1... down to size 1
        for (int i = 0; i < maxPasswordLength; ++i) {
            while (generator.hasNextPassword()) {
                String password = generator.generate();
                String hash = createMd5Hash(password);

                if (md5HashLoginDatabase.containsKey(hash)) {
                    String login = md5HashLoginDatabase.get(hash);
                    loginPasswordDatabase.put(login, password);
                }
            }

            generator = new FromToPasswordGenerator(
                alphabet,
                from, to,
                maxPasswordLength - i - 1
            );
        }
    }

    @SuppressWarnings("MagicNumber")
    private static @NotNull String createMd5Hash(String input) {
        try {
            var md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
