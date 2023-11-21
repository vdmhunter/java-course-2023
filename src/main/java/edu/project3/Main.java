package edu.project3;

import edu.project3.analyzers.Processor;
import edu.project3.report.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main entry point for the Nginx Log Analyzer application.
 * Initializes and executes the log processing pipeline.
 */
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    /**
     * The main method that serves as the entry point for the application.
     * It initializes the log processor, processes all analyzers, and logs the result.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Processor nginxLogProcessor = new Processor(args);
        Report result = nginxLogProcessor.processAllAnalyzers();

        LOGGER.info(result.content());
    }
}
