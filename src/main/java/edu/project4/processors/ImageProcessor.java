package edu.project4.processors;

import edu.project4.geometry.FractalImage;

/**
 * Represents a functional interface for processing {@link FractalImage} objects.
 * Implementing classes are expected to define the specific processing logic for a {@link FractalImage}.
 */
@FunctionalInterface
public interface ImageProcessor {
    /**
     * Processes the provided {@link FractalImage}.
     *
     * @param image The FractalImage to be processed.
     */
    void process(FractalImage image);
}
