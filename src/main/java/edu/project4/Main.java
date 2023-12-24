package edu.project4;

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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int SAMPLES = 100_000;
    private static final short ITERATION_PER_SAMPLE = 100;
    private static final double GAMMA = 2.5;
    private static final long SEED = System.currentTimeMillis();
    private static final Path IMAGES_DIR = Path.of("src", "main", "java", "edu", "project4", "images");
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws IOException {
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
        String fileName = String.format("image_%d.%s", SEED, format.name().toLowerCase());
        Path path = IMAGES_DIR.resolve(fileName);
        Symmetry symmetry = new Symmetry(RANDOM.nextBoolean(), RANDOM.nextBoolean(), RANDOM.nextInt(5) + 1);

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
                Runtime.getRuntime().availableProcessors(),
                path,
                format
            )
        );

        long single = System.nanoTime() - timeStart;
        LOGGER.info("Multi threads, seconds: {}", (double) single / 1_000_000_000);
    }
}
