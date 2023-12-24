package edu.project4.renderers;

import edu.project4.geometry.Point;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a mathematical function used in fractal rendering, defined by a set of transformations
 * and associated parameters such as color, weight, and coefficients.
 */
@SuppressWarnings("MagicNumber")
public class Function {
    private List<Double> probabilityWeights;
    private Color color;
    private double weight;
    private final HashSet<Transformation> transformationSet = new HashSet<>();
    private final double[] coefficients = new double[COEFFICIENT_COUNT];
    private static final int COEFFICIENT_COUNT = 6;

    /**
     * Gets the color associated with this function.
     *
     * @return The color of the function.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the weight associated with this function.
     *
     * @return The weight of the function.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Creates a list of functions based on the given transformations and a random seed.
     *
     * @param transformations The list of transformations to be associated with the functions.
     * @param seed            The seed for random number generation.
     * @return A list of randomly generated functions.
     */
    public static @NotNull List<Function> createList(List<Transformation> transformations, long seed) {
        var random = new Random(seed);
        var result = new ArrayList<Function>();
        var numOfFunc = Math.max(random.nextInt(21), 2);

        for (var i = 0; i < numOfFunc; i++) {
            var fi = new Function();
            int maxNumOfTransPerFunc = random.nextInt(5) + 3;

            for (int j = 0; j < maxNumOfTransPerFunc; j++) {
                fi.transformationSet.add(transformations.get(random.nextInt(0, transformations.size())));
            }

            double totalSum = 0.0D;
            double weightInc = 0.05D;
            fi.probabilityWeights = new ArrayList<>(Collections.nCopies(transformations.size(), 0.0D));

            while (totalSum < 1.0D) {
                int idx = random.nextInt(0, transformations.size());
                Double w = fi.probabilityWeights.get(idx);
                w += weightInc;
                fi.probabilityWeights.set(idx, w);
                totalSum += weightInc;
            }

            fi.color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());

            double cofMult = 3.0D;
            double cofSub = 1.5D;

            for (var j = 0; j < COEFFICIENT_COUNT; j++) {
                var cof = random.nextDouble() * cofMult - cofSub;
                fi.coefficients[j] = cof;
            }

            result.add(fi);
        }

        setWeights(random, result);

        return result;
    }

    /**
     * Applies the function to a given point, performing a weighted sum of transformations.
     *
     * @param f The function to apply.
     * @param p The input point.
     * @return The result of applying the function to the point.
     */
    @Contract("_, _ -> new")
    public static @NotNull Point run(@NotNull Function f, Point p) {
        double[] result = {0.0D, 0.0D};

        f.transformationSet.forEach(t -> {
            var tmpPoint = t.apply(new Point(
                f.coefficients[0] * p.x() + f.coefficients[1] * p.y() + f.coefficients[2],
                f.coefficients[3] * p.x() + f.coefficients[4] * p.y() + f.coefficients[5]
            ));

            result[0] += tmpPoint.x() * f.weight;
            result[1] += tmpPoint.y() * f.weight;
        });

        return new Point(result[0], result[1]);
    }

    /**
     * Sets the weights for a list of functions using a random generator.
     *
     * @param random The random number generator.
     * @param result The list of functions to set weights for.
     */
    private static void setWeights(
        Random random,
        @NotNull ArrayList<Function> result
    ) {
        double totalSum = 0.0D;
        double weightInc = 0.5D;
        ArrayList<Double> weights = new ArrayList<>(Collections.nCopies(result.size(), 0.0D));

        while (totalSum < 0.999D) {
            int idx = random.nextInt(result.size());
            weights.set(idx, weights.get(idx) + weightInc);
            totalSum += weightInc;
            weightInc /= 2;
        }

        for (var i = 0; i < result.size(); i++) {
            result.get(i).weight = weights.get(i);
        }
    }
}
