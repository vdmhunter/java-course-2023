package edu.project4.geometry;

/**
 * Represents symmetry information, including reflections across the x-axis and y-axis,
 * and the number of rotational symmetries.
 */
public record Symmetry(boolean x, boolean y, int rot) {
}
