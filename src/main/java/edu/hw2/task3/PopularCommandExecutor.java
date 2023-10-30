package edu.hw2.task3;

import edu.hw2.task3.connectionmanagers.ConnectionManager;
import edu.hw2.task3.exceptions.ConnectionException;
import java.util.Objects;

/**
 * The {@code PopularCommandExecutor} class is responsible for executing a popular command.
 * It uses a {@link ConnectionManager} to get a connection and then attempts to execute the command.
 * If the execution fails due to a {@link ConnectionException}, it will retry up to a maximum number of attempts.
 */
public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    /**
     * Constructs a new {@code PopularCommandExecutor} with the specified ConnectionManager and
     * maximum number of attempts.
     *
     * @param manager     the {@link ConnectionManager} used to get connections for executing commands.
     * @param maxAttempts the maximum number of attempts to execute a command before giving up.
     * @throws NullPointerException     if manager is null.
     * @throws IllegalArgumentException if maxAttempts is not greater than 0.
     */
    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        Objects.requireNonNull(manager);

        if (!(maxAttempts > 0)) {
            throw new IllegalArgumentException("The input integer 'maxAttempts' must be greater than 0.");
        }

        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    /**
     * Attempts to execute a given command. If the execution fails due to a {@link ConnectionException},
     * it will retry up to maxAttempts times. If it still fails after max attempts,
     * it throws a {@link ConnectionException} with a detailed error message.
     *
     * @param command the command to be executed.
     * @throws ConnectionException if unable to execute the command after max attempts due to connection issues.
     */
    @SuppressWarnings("SameParameterValue")
    void tryExecute(String command) {
        for (int a = 1; a <= maxAttempts; a++) {
            try (var connection = manager.getConnection()) {
                connection.execute(command);
            } catch (ConnectionException e) {
                if (a == maxAttempts) {
                    throw new ConnectionException("Unable to execute the command '"
                        + command
                        + "' after "
                        + maxAttempts
                        + " attempts due to connection issues.", e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
