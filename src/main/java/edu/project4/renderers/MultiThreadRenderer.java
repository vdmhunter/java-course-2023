package edu.project4.renderers;

import edu.common.Generated;
import edu.project4.geometry.FractalImage;
import edu.project4.geometry.Symmetry;
import edu.project4.transformations.Transformation;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * A multi-threaded renderer for fractal images, utilizing multiple threads to enhance performance.
 */
public class MultiThreadRenderer implements Renderer {

    private final int threadsCount;

    /**
     * Constructs a {@code MultiThreadRenderer} with the specified number of threads.
     *
     * @param threadsCount The number of threads to use for rendering.
     */
    public MultiThreadRenderer(int threadsCount) {
        this.threadsCount = Math.min(threadsCount, Runtime.getRuntime().availableProcessors());
    }

    /**
     * Renders a fractal image using multiple threads.
     *
     * @param canvas        The initial {@link FractalImage} canvas to be rendered.
     * @param variations    The list of transformations to be applied during rendering.
     * @param samples       The number of samples to be taken during rendering.
     * @param iterPerSample The number of iterations per sample.
     * @param seed          The seed for random number generation.
     * @param symmetry      The symmetry information to be applied during rendering.
     * @return The rendered {@link FractalImage}.
     */
    @Override
    public FractalImage render(
        FractalImage canvas,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed,
        Symmetry symmetry
    ) {
        var threads = new Thread[threadsCount];
        int samplesPerThread = samples / threadsCount;

        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                var renderer = new SingleThreadRenderer();
                renderer.render(
                    canvas,
                    variations,
                    samplesPerThread,
                    iterPerSample,
                    seed,
                    symmetry
                );
            });

            threads[i].start();
        }

        waitForThreadsCompletion(threads);

        return canvas;
    }

    /**
     * Waits for the completion of multiple threads.
     *
     * @param threads The array of threads to wait for.
     */
    @Generated
    private static void waitForThreadsCompletion(Thread @NotNull [] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
