package edu.project3;

import edu.project3.analyzers.Processor;
import edu.project3.report.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {

        String[] a = {"--path", "*nginx*.txt", "--format", "adoc"};

        Processor nginxLogProcessor = new Processor(a);
        Report result = nginxLogProcessor.processAllAnalyzers();

        LOGGER.info(result.content());
    }
}
