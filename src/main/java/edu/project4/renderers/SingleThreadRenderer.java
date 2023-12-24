package edu.project4.renderers;

import edu.common.Generated;
import edu.project4.geometry.FractalImage;
import edu.project4.geometry.Pixel;
import edu.project4.geometry.Point;
import edu.project4.geometry.Symmetry;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A single-threaded renderer for fractal images.
 */
@SuppressWarnings("MagicNumber")
public class SingleThreadRenderer implements Renderer {
    private static final Object LOCK_OBJ = new Object();
    private static final Random RANDOM = new Random();

    /**
     * Renders a fractal image using a single thread.
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
        @NotNull FractalImage canvas,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed,
        Symmetry symmetry
    ) {
        RANDOM.setSeed(seed);

        List<Function> functions = Function.createList(variations, seed);
        double[] weights = getWeights(functions);

        boolean firstPass = true;

        for (int i = 0; i < samples; i++) {
            Point p = getRandomPoint();
            int[] nCrd = {0, 0};

            int currentRot = 0;
            int[] c = {RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)};

            for (short j = 0; j < iterPerSample; j++) {
                var w = weightedRand(weights);
                p = Function.run(functions.get(w), p);
                p = applySymmetry(p, symmetry, j);

                nCrd[0] = (int) Math.round((p.x() + 2 * ((double) canvas.width() / canvas.height()))
                    * ((double) canvas.height() / 4));
                nCrd[1] = (int) Math.round((p.y() + 2) * ((double) canvas.height() / 4));

                if (symmetry.rot() > 1) {
                    nCrd = rotatePoint(
                        nCrd,
                        currentRot,
                        canvas.width() / 2,
                        canvas.height() / 2
                    );

                    currentRot = (currentRot + (int) (2 * Math.PI / symmetry.rot())) % (int) (2 * Math.PI);
                }

                c[0] = ((c[0] + functions.get(w).getColor().getRed()) / 2);
                c[1] = ((c[1] + functions.get(w).getColor().getGreen()) / 2);
                c[2] = ((c[2] + functions.get(w).getColor().getBlue()) / 2);

                if (!firstPass) {
                    if (!canvas.contains(nCrd[0], nCrd[1])) {
                        continue;
                    }

                    Pixel pixel = canvas.getPixel(nCrd[0], nCrd[1]);
                    updatePixelWithColor(pixel, c);
                }

                firstPass = false;
            }
        }

        return canvas;
    }

    /**
     * Generates a random point within the range [-1, 1] along both axes.
     *
     * @return A random point.
     */
    public @NotNull Point getRandomPoint() {
        double randomX = 2 * RANDOM.nextDouble() - 1;
        double randomY = 2 * RANDOM.nextDouble() - 1;

        return new Point(randomX, randomY);
    }

    /**
     * Updates a pixel in the canvas with a new color based on the provided values.
     *
     * @param pixel The pixel to be updated.
     * @param c     The color values to update the pixel.
     */
    @Generated
    private static void updatePixelWithColor(Pixel pixel, int[] c) {
        if (pixel != null) {
            synchronized (LOCK_OBJ) {
                pixel.setRed((c[0] + (pixel.getAlpha() / 12)) % 256);
                pixel.setGreen((c[1] + (pixel.getAlpha() / 12)) % 256);
                pixel.setBlue((c[2] + (pixel.getAlpha() / 12)) % 256);
                pixel.setAlpha((pixel.getAlpha() + 4) % 256);
                pixel.incHitCount();
            }
        }
    }

    /**
     * Applies symmetry transformations to a point based on the specified symmetry information.
     *
     * @param p        The original point.
     * @param symmetry The symmetry information to be applied.
     * @param i        The iteration index for determining symmetry conditions.
     * @return The point after applying symmetry transformations.
     */
    @Contract("_, _, _ -> new")
    @Generated
    private static @NotNull Point applySymmetry(@NotNull Point p, @NotNull Symmetry symmetry, int i) {
        if (!symmetry.x() && !symmetry.y()) {
            return p;
        }

        double x = p.x();
        double y = p.y();

        if (symmetry.x() && i % 2 == 0) {
            y *= -1;
        }

        if (symmetry.y() && i % 2 == 0) {
            x *= -1;
        }

        if (symmetry.x() && i % 3 == 0) {
            x *= -1;
            y *= -1;
        }

        return new Point(x, y);
    }

    /**
     * Selects a random index based on a weighted probability distribution.
     *
     * @param weights The array of weights.
     * @return The selected index.
     */
    private static int weightedRand(double @NotNull [] weights) {
        double rand = RANDOM.nextDouble();
        double w = 0.0D;

        for (int i = 0; i < weights.length; i++) {
            w += weights[i];

            if (rand <= w) {
                return i;
            }
        }

        return 0;
    }

    /**
     * Obtains the weights associated with a list of functions.
     *
     * @param functionList The list of functions.
     * @return An array of weights.
     */
    private static double @NotNull [] getWeights(@NotNull List<Function> functionList) {
        double[] weightArray = new double[functionList.size()];

        for (int i = 0; i < functionList.size(); i++) {
            weightArray[i] = functionList.get(i).getWeight();
        }

        return weightArray;
    }

    /**
     * Rotates a point around a specified center by a given angle.
     *
     * @param p     The original point.
     * @param angle The rotation angle.
     * @param cx    The x-coordinate of the rotation center.
     * @param cy    The y-coordinate of the rotation center.
     * @return The rotated point.
     */
    @Contract("_, _, _, _ -> new")
    private static int @NotNull [] rotatePoint(int @NotNull [] p, long angle, int cx, int cy) {
        p[0] -= cx;
        p[1] -= cy;

        double xNew = p[0] * Math.cos(angle) - p[1] * Math.sin(angle);
        double yNew = p[0] * Math.sin(angle) + p[1] * Math.cos(angle);

        int newX = (int) Math.round(xNew + cx);
        int newY = (int) Math.round(yNew + cy);

        return new int[] {newX, newY};
    }
}
