package edu.project4.renderers;

import edu.project4.geometry.FractalImage;
import edu.project4.geometry.Symmetry;
import edu.project4.transformations.Transformation;
import java.util.List;

/**
 * Represents a functional interface for rendering fractal images based on a set of transformations.
 * Implementing classes are expected to define the rendering logic for generating a {@link FractalImage}.
 */
@FunctionalInterface
public interface Renderer {
    /**
     * Renders a fractal image based on the specified parameters.
     *
     * @param canvas        The initial FractalImage canvas to be rendered.
     * @param variations    The list of transformations to be applied during rendering.
     * @param samples       The number of samples to be taken during rendering.
     * @param iterPerSample The number of iterations per sample.
     * @param seed          The seed for random number generation.
     * @param symmetry      The symmetry information to be applied during rendering.
     * @return The rendered {@link FractalImage}.
     */
    FractalImage render(
        FractalImage canvas,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed,
        Symmetry symmetry
    );
}
