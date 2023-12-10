package edu.project4;

import edu.project4.models.FractalImg;
import edu.project4.models.Rect;
import edu.project4.processors.GammaCorrector;
import edu.project4.renderers.MultiThreadedRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleThreadedRenderer;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings({"ParameterNumber"})
public class FractalFlameGenerator {
    private FractalFlameGenerator() {
    }

    public static FractalImg generate(
        int width,
        int height,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long seed,
        int symmetry,
        double gamma,
        int nThreads,
        Path path
    ) throws IOException {
        var canvas = FractalImg.create(width, height);

        Renderer renderer = nThreads == 1
            ? new SingleThreadedRenderer()
            : new MultiThreadedRenderer(nThreads);

        FractalImg renderedImage = renderer.render(
            canvas,
            world,
            variations,
            samples,
            iterPerSample,
            seed,
            symmetry
        );

        if (gamma != 0.0) {
            GammaCorrector processor = new GammaCorrector(gamma);
            processor.process(renderedImage);
        }

        ImageUtils.save(renderedImage, path);

        return renderedImage;
    }
}
