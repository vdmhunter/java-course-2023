package edu.project4.processors;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import org.jetbrains.annotations.NotNull;

public final class GammaCorrector {
    private final double gamma;
    private double max = 0.0D;

    public GammaCorrector(double gamma) {
        this.gamma = gamma;
    }

    public void process(@NotNull FractalImage image) {
        calculateNormal(image);
        adjustColors(image);
    }

    private void calculateNormal(@NotNull FractalImage image) {
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel current = image.getPixel(col, row);

                if (current.getHitCount() != 0) {
                    current.setNormal(Math.log10(current.getHitCount()));
                    max = Math.max(max, current.getNormal());
                }
            }
        }
    }

    private void adjustColors(@NotNull FractalImage image) {
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel current = image.getPixel(col, row);
                current.setNormal(current.getNormal() / max);
                applyGammaCorrection(current);
            }
        }
    }

    private void applyGammaCorrection(@NotNull Pixel pixel) {
        pixel.setRed(applyGammaToChannel(pixel.getRed(), pixel.getNormal()));
        pixel.setGreen(applyGammaToChannel(pixel.getGreen(), pixel.getNormal()));
        pixel.setBlue(applyGammaToChannel(pixel.getBlue(), pixel.getNormal()));
    }

    private int applyGammaToChannel(int colorChannel, double normal) {
        return (int) (colorChannel * Math.pow(normal, 1.0 / gamma));
    }
}
