package edu.project4;

import edu.project4.geometry.FractalImage;
import edu.project4.geometry.Pixel;
import edu.project4.geometry.Symmetry;
import edu.project4.transformations.Bubble;
import edu.project4.transformations.Cross;
import edu.project4.transformations.Cylinder;
import edu.project4.transformations.Diamond;
import edu.project4.transformations.Disc;
import edu.project4.transformations.Ex;
import edu.project4.transformations.Fisheye;
import edu.project4.transformations.Hankerchief;
import edu.project4.transformations.Heart;
import edu.project4.transformations.Horseshoe;
import edu.project4.transformations.Hyperbolic;
import edu.project4.transformations.Linear;
import edu.project4.transformations.Polar;
import edu.project4.transformations.Power;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Spiral;
import edu.project4.transformations.Swirl;
import edu.project4.transformations.Tangent;
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

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int SAMPLES = 100_000;
    private static final short ITERATION_PER_SAMPLE = 100;
    private static final double GAMMA = 1.5D;
    private static final long SEED = 6L;
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Test performance of Fractal Flame Generator with single and multi-threaded rendering")
    void fractalImages_TestPerformance() throws IOException {
        //Arrange
        Path imageFile = tempDir.resolve("image.png");

        List<Transformation> transformations = new ArrayList<>();
        transformations.add(new Bubble());
        transformations.add(new Cross());
        transformations.add(new Cylinder());
        transformations.add(new Diamond());
        transformations.add(new Disc());
        transformations.add(new Ex());
        transformations.add(new Fisheye());
        transformations.add(new Hankerchief());
        transformations.add(new Heart());
        transformations.add(new Horseshoe());
        transformations.add(new Hyperbolic());
        transformations.add(new Linear());
        transformations.add(new Polar());
        transformations.add(new Power());
        transformations.add(new Sinusoidal());
        transformations.add(new Spherical());
        transformations.add(new Spiral());
        transformations.add(new Swirl());
        transformations.add(new Tangent());

        ImageFormat format = ImageFormat.PNG;
        Path path = imageFile.toAbsolutePath();
        Symmetry symmetry = new Symmetry(false, true, 2);

        // Act
        long timeStart = System.nanoTime();
        ImageGenerator.generate(new ImageGenerator.Params(
                WIDTH,
                HEIGHT,
                transformations,
                SAMPLES,
                ITERATION_PER_SAMPLE,
                SEED,
                symmetry,
                GAMMA,
                1,
                path,
                format
            )
        );

        long single = System.nanoTime() - timeStart;
        LOGGER.trace("Single thread, seconds: {}", (double) single / 1_000_000_000);

        timeStart = System.nanoTime();
        ImageGenerator.generate(new ImageGenerator.Params(
                WIDTH,
                HEIGHT,
                transformations,
                SAMPLES,
                ITERATION_PER_SAMPLE,
                SEED,
                symmetry,
                GAMMA,
                Runtime.getRuntime().availableProcessors(),
                path,
                format
            )
        );

        long multi = System.nanoTime() - timeStart;
        LOGGER.trace("Multi threads, seconds: {}", (double) multi / 1_000_000_000);
        LOGGER.trace("Multi threaded renderer is faster by: {}", ((double) single / multi));

        //Assert
        Assertions.assertTrue(Files.size(imageFile) > 0);
    }

    @Test
    @DisplayName("Test equality and hashCode for FractalImage instances with identical properties")
    void fractalImage_TestHashCode() {
        // Arrange
        var fractalImage1 = new FractalImage(new Pixel[] {}, 10, 10);
        var fractalImage2 = new FractalImage(new Pixel[] {}, 10, 10);

        // Act & Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(fractalImage1, fractalImage2),
            () -> Assertions.assertEquals(fractalImage1.hashCode(), fractalImage2.hashCode()),
            () -> Assertions.assertEquals(fractalImage1.toString(), fractalImage2.toString())
        );
    }
}
