package edu.project4;

import edu.project4.geometry.FractalImage;
import edu.project4.geometry.Symmetry;
import edu.project4.processors.GammaProcessor;
import edu.project4.renderers.MultiThreadRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for generating fractal images based on specified parameters.
 */
public final class ImageGenerator {
    private ImageGenerator() {
    }

    /**
     * Generates a fractal image based on the provided parameters and saves it to a file.
     *
     * @param params The parameters for generating the fractal image.
     * @return The generated {@link FractalImage}.
     * @throws IOException If an I/O error occurs during image saving.
     */
    public static FractalImage generate(@NotNull Params params) throws IOException {
        var canvas = FractalImage.create(params.width(), params.height());

        Renderer renderer = params.threads() == 1
            ? new SingleThreadRenderer()
            : new MultiThreadRenderer(params.threads());

        FractalImage image = renderer.render(
            canvas,
            params.transformations(),
            params.samples(),
            params.iterPerSample(),
            params.seed(),
            params.symmetry()
        );

        if (params.gamma() != 0.0D) {
            GammaProcessor processor = new GammaProcessor(params.gamma());
            processor.process(image);
        }

        ImageUtils.save(image, params.path(), params.format());

        return image;
    }

    /**
     * Parameters for generating a fractal image.
     */
    public record Params(
        int width,
        int height,
        List<Transformation> transformations,
        int samples,
        short iterPerSample,
        long seed,
        Symmetry symmetry,
        double gamma,
        int threads,
        Path path,
        ImageFormat format
    ) {
    }
}
