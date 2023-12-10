package edu.project4.renderers;

import edu.project4.models.FractalImg;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImg render(
        FractalImg canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long seed,
        int symmetry
    );
}
