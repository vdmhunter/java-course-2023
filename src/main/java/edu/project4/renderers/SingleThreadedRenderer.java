package edu.project4.renderers;

import edu.common.Generated;
import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SingleThreadedRenderer implements Renderer {
    private static final Color COLOR = new Color(0, 200, 200);
    public static final int START = -20;

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long seed,
        int symmetry
    ) {
        for (int num = 0; num < samples; ++num) {
            Point pw = world.getRandomPoint();

            for (short step = START; step < iterPerSample; ++step) {
                Transformation variation = getRandomVariation(variations);
                pw = variation.apply(pw);

                if (step >= 0) {
                    processSymmetry(canvas, world, pw, symmetry);
                }
            }
        }

        return canvas;
    }

    private void processSymmetry(FractalImage canvas, Rect world, Point pw, int symmetry) {
        double theta2 = 0.0;
        for (int s = 0; s < symmetry; ++s) {
            theta2 += Math.PI * 2.0 / symmetry;

            Point pwr = rotate(pw, theta2);
            if (world.contains(pwr)) {
                updatePixel(canvas, world, pwr);
            }
        }
    }

    private void updatePixel(FractalImage canvas, Rect world, Point pwr) {
        Pixel pixel = mapRange(world, pwr, canvas);
        if (pixel != null) {
            synchronized (pixel) {
                if (pixel.getHitCount() == 0) {
                    pixel.setRed(COLOR.getRed());
                    pixel.setGreen(COLOR.getGreen());
                    pixel.setBlue(COLOR.getBlue());
                } else {
                    pixel.setRed((pixel.getRed() + COLOR.getRed()) / 2);
                    pixel.setGreen((pixel.getGreen() + COLOR.getGreen()) / 2);
                    pixel.setBlue((pixel.getBlue() + COLOR.getBlue()) / 2);
                }

                pixel.incrementHitCount();
            }
        }
    }


    @Generated
    private Transformation getRandomVariation(@NotNull List<Transformation> variations) {
        return variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
    }


    @Generated
    @Contract("_, _ -> new")
    private @NotNull Point rotate(@NotNull Point point, double angle) {
        double x = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double y = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);

        return new Point(x, y);
    }

    @Generated
    private @Nullable Pixel mapRange(@NotNull Rect world, @NotNull Point pwr, @NotNull FractalImage canvas) {
        int x = (int) ((pwr.x() - world.x()) * canvas.width() / world.width());
        int y = (int) ((pwr.y() - world.y()) * canvas.height() / world.height());

        if (!canvas.contains(x, y)) {
            return null;
        }

        return canvas.getPixel(x, y);
    }
}
