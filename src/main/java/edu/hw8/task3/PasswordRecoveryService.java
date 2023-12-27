package edu.hw8.task3;

import edu.common.Generated;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code PasswordRecoveryService} class is responsible for recovering passwords by generating
 * and checking password hashes against a provided database of hashed logins. It supports both
 * single-threaded and multi thread processing password recovery strategies.
 */
public final class PasswordRecoveryService {
    private final Map<String, String> hashedLoginDatabase;
    private final String characterSet;
    private final int maxPasswordSize;
    private final ConcurrentHashMap<String, String> passwordDatabase;
    private static final int FF = 0xFF;
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructs a {@code PasswordRecoveryService} with the specified parameters.
     *
     * @param hashedLoginDatabase The database containing hashed logins and their corresponding hashes.
     * @param characterSet        The character set used for password generation.
     * @param maxPasswordSize     The maximum size of generated passwords.
     */
    public PasswordRecoveryService(Map<String, String> hashedLoginDatabase, String characterSet, int maxPasswordSize) {
        this.hashedLoginDatabase = hashedLoginDatabase;
        this.characterSet = characterSet;
        this.maxPasswordSize = maxPasswordSize;
        this.passwordDatabase = new ConcurrentHashMap<>();
    }

    /**
     * Retrieves the database containing recovered logins and passwords.
     *
     * @return The concurrent map containing recovered logins and passwords.
     */
    public ConcurrentMap<String, String> getPasswordDatabase() {
        return passwordDatabase;
    }

    /**
     * Runs the single-threaded password recovery process using an alphabetic password generator.
     */
    public void runSingleThreadedPasswordRecovery() {
        var generator = new AlphabeticPasswordGenerator(characterSet, maxPasswordSize);

        for (int i = 0; i < maxPasswordSize; ++i) {
            while (generator.hasNextPassword()) {
                String password = generator.generate();
                String hash = generateMD5Hash(password);

                if (hashedLoginDatabase.containsKey(hash)) {
                    String login = hashedLoginDatabase.get(hash);
                    passwordDatabase.put(login, password);
                }
            }

            generator = new AlphabeticPasswordGenerator(
                characterSet,
                maxPasswordSize - i - 1
            );
        }
    }

    /**
     * Runs the multi thread processing password recovery process using character subset password generators
     * and the specified executor service.
     *
     * @param executorService The executor service for managing password generation threads.
     * @param threadNumber    The number of threads to use in the recovery process.
     */
    public void runMultiThreadedPasswordRecovery(ExecutorService executorService, int threadNumber) {
        CountDownLatch latch = new CountDownLatch(threadNumber);
        int part = characterSet.length() / threadNumber;

        for (int i = 0; i < threadNumber; i++) {
            int startIndex = part * i;
            int upperLimit = (i == threadNumber - 1)
                ? characterSet.length() - 1
                : part * (i + 1) - 1;

            submitPasswordGenerationTask(executorService, startIndex, upperLimit, latch);
        }

        awaitLatch(latch);
    }

    /**
     * Runs a password generation thread within the specified character subset range.
     *
     * @param startIndex The starting index of the character subset.
     * @param upperLimit The upper limit index of the character subset.
     */
    @Generated
    private void runPasswordGenerationThread(int startIndex, int upperLimit) {
        var generator = new CharacterSubsetPasswordGenerator(characterSet, startIndex, upperLimit, maxPasswordSize);

        for (int i = 0; i < maxPasswordSize; ++i) {
            while (generator.hasMorePasswords()) {
                String password = generator.generatePassword();
                String hash = generateMD5Hash(password);

                if (hashedLoginDatabase.containsKey(hash)) {
                    String login = hashedLoginDatabase.get(hash);
                    passwordDatabase.put(login, password);
                }
            }

            generator = new CharacterSubsetPasswordGenerator(
                characterSet,
                startIndex,
                upperLimit,
                maxPasswordSize - i - 1
            );
        }
    }

    /**
     * Generates the MD5 hash for the given input string.
     *
     * @param input The input string for which to generate the MD5 hash.
     * @return The MD5 hash as a hexadecimal string.
     * @throws Md5HashingException If an error occurs during MD5 hashing.
     */
    @Generated
    private static @NotNull String generateMD5Hash(@NotNull String input) {
        try {
            var md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            var hexString = new StringBuilder();

            for (byte b : digest) {
                String hex = Integer.toHexString(FF & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            String errorMessage = "MD5 algorithm not available";
            LOGGER.error(errorMessage, e);

            throw new Md5HashingException(errorMessage, e);
        }
    }

    /**
     * Waits for the specified countdown latch to reach zero, handling interruptions.
     *
     * @param latch The countdown latch to await.
     */
    @Generated
    private static void awaitLatch(@NotNull CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Submits a password generation task to the specified executor service.
     *
     * @param executorService The executor service for managing password generation threads.
     * @param startIndex      The starting index of the character subset.
     * @param upperLimit      The upper limit index of the character subset.
     * @param latch           The countdown latch to decrement upon completion.
     */
    @Generated
    private void submitPasswordGenerationTask(
        @NotNull ExecutorService executorService,
        int startIndex,
        int upperLimit,
        CountDownLatch latch
    ) {
        Runnable passwordGenerationRunnable = new Runnable() {
            @Override
            @Generated
            public void run() {
                try {
                    runPasswordGenerationThread(startIndex, upperLimit);
                } finally {
                    latch.countDown();
                }
            }
        };

        executorService.submit(passwordGenerationRunnable);
    }
}
