package edu.project4;

import edu.project4.models.Rect;
import edu.project4.transformations.Linear;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for Project 4
 */
public final class Project4Test {
    private final static int WIDTH = 1920;
    private final static int HEIGHT = 1080;
    private final static Rect WORLD = new Rect(-1.5, -1.5, 3.0, 3.0);
    private final static int SAMPLES = 100_000;
    private final static int ITER_PER_SAMPLE = 200;
    private final static int SYMMETRY = 9;
    private final static double GAMMA = 0.5;
    public static final Path IMAGES_FOLDER = Path.of("src/main/java/edu/project4/images/");
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void fractalImages_TestPerformance() throws IOException {
        //Arrange
        long seed = System.currentTimeMillis();

        List<Transformation> variations = new ArrayList<>();
        variations.add(new Linear());
        variations.add(new Sinusoidal());
        variations.add(new Spherical());

        String fileName = "image.png";
        Path path = IMAGES_FOLDER.resolve(fileName);

        // Act
        long timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITER_PER_SAMPLE,
            seed,
            SYMMETRY,
            GAMMA,
            1,
            path
        );
        long nanoEstimatedTime = System.nanoTime();
        long single = nanoEstimatedTime - timeStart;
        LOGGER.trace("Single thread, seconds:\t" + single / 1_000_000_000);

        timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITER_PER_SAMPLE,
            seed,
            SYMMETRY,
            GAMMA,
            Runtime.getRuntime().availableProcessors(),
            path
        );
        nanoEstimatedTime = System.nanoTime();
        long multi = nanoEstimatedTime - timeStart;
        LOGGER.trace("Multi threads, seconds:\t" + multi / 1_000_000_000);
        LOGGER.trace("Multi threaded renderer is faster by:\t" + (1.0 * single / multi));

        //Assert
        Assertions.assertTrue(single > multi);
    }
}
