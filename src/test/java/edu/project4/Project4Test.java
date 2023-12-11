package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Rect;
import edu.project4.transformations.Disc;
import edu.project4.transformations.Linear;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Swirl;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests for Project 4
 */
class Project4Test {
    @TempDir Path tempDir;

    private final static int WIDTH = 1920;
    private final static int HEIGHT = 1080;
    private final static Rect WORLD = new Rect(-1.5, -1.5, 3.0, 3.0);
    private final static int SAMPLES = 100_000;
    private final static int ITERATION_PER_SAMPLE = 100;
    private final static int SYMMETRY = 9;
    private final static double GAMMA = 0.5;
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Test performance of Fractal Flame Generator with single and multi-threaded rendering")
    void fractalImages_TestPerformance() throws IOException {
        //Arrange
        Path imageFile = tempDir.resolve("image.png");
        long seed = System.currentTimeMillis();

        List<Transformation> variations = new ArrayList<>();
        variations.add(new Disc());
        variations.add(new Linear());
        variations.add(new Sinusoidal());
        variations.add(new Spherical());
        variations.add(new Swirl());

        ImageFormat format = ImageFormat.PNG;
        Path path = imageFile.toAbsolutePath();

        // Act
        long timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITERATION_PER_SAMPLE,
            seed,
            SYMMETRY,
            GAMMA,
            1,
            path,
            format
        );
        long single = System.nanoTime() - timeStart;
        LOGGER.trace("Single thread, seconds:\t" + single / 1_000_000_000);

        timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITERATION_PER_SAMPLE,
            seed,
            SYMMETRY,
            GAMMA,
            Runtime.getRuntime().availableProcessors(),
            path,
            format
        );
        long multi = System.nanoTime() - timeStart;
        LOGGER.trace("Multi threads, seconds:\t" + multi / 1_000_000_000);
        LOGGER.trace("Multi threaded renderer is faster by:\t" + ((double) single / multi));

        //Assert
        Assertions.assertTrue(Files.size(imageFile) > 0);
    }


    @Test
    @DisplayName("Test equality and hashCode for FractalImage instances with identical properties")
    void fractalImage_TestHashCode() {
        // Arrange
        var fractalImage1 = new FractalImage(new Pixel[]{}, 10, 10);
        var fractalImage2 = new FractalImage(new Pixel[]{}, 10, 10);


        // Act & Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(fractalImage1, fractalImage2),
            () -> Assertions.assertEquals(fractalImage1.hashCode(), fractalImage2.hashCode()),
            () -> Assertions.assertEquals(fractalImage1.toString(), fractalImage2.toString())
        );
    }
}
