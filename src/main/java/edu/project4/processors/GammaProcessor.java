package edu.project4.processors;

import edu.project4.geometry.FractalImage;
import edu.project4.geometry.Pixel;
import org.jetbrains.annotations.NotNull;

public class GammaProcessor implements ImageProcessor {
    private final double gamma;
    private double max = 0.0D;

    public GammaProcessor(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(@NotNull FractalImage image) {
        calculatePixelIntensity(image);
        adjustPixelColors(image);
    }

    private void calculatePixelIntensity(@NotNull FractalImage image) {
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel pixel = image.getPixel(col, row);

                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    max = Math.max(max, pixel.getNormal());
                }
            }
        }
    }

    private void adjustPixelColors(@NotNull FractalImage image) {
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel pixel = image.getPixel(col, row);
                pixel.setNormal(pixel.getNormal() / max);

                applyGammaToPixel(pixel);
            }
        }
    }

    private void applyGammaToPixel(@NotNull Pixel pixel) {
        pixel.setRed(applyGammaToChannel(pixel.getRed(), pixel.getNormal()));
        pixel.setGreen(applyGammaToChannel(pixel.getGreen(), pixel.getNormal()));
        pixel.setBlue(applyGammaToChannel(pixel.getBlue(), pixel.getNormal()));
    }

    private int applyGammaToChannel(int colorChannel, double normal) {
        return (int) (colorChannel * Math.pow(normal, 1.0D / gamma));
    }
}
