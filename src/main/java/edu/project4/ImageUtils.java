package edu.project4;

import edu.project4.models.FractalImg;
import edu.project4.models.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.NotNull;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImg image, @NotNull Path filename) throws IOException {
        ImageIO.write(createImage(image), "png", filename.toFile());
    }

    private static @NotNull BufferedImage createImage(@NotNull FractalImg image) {
        var bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        int[] rgbData = new int[image.width() * image.height()];

        for (int row = 0; row < image.height(); ++row) {
            for (int col = 0; col < image.width(); ++col) {
                Pixel pixel = image.getPixel(col, row);
                rgbData[row * image.width() + col] = new Color(pixel.getR(), pixel.getG(), pixel.getB()).getRGB();
            }
        }

        bufferedImage.setRGB(0, 0, image.width(), image.height(), rgbData, 0, image.width());
        return bufferedImage;
    }
}
