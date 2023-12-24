package edu.project4;

import edu.project4.geometry.FractalImage;
import edu.project4.geometry.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for handling operations related to images.
 */
public final class ImageUtils {
    private ImageUtils() {
    }

    /**
     * Saves a fractal image to a file in the specified image format.
     *
     * @param image    The fractal image to be saved.
     * @param filename The path to the file where the image will be saved.
     * @param format   The image format in which to save the file (e.g., JPEG, BMP, PNG).
     * @throws IOException If an I/O error occurs during image saving.
     */
    public static void save(
        FractalImage image,
        @NotNull Path filename,
        @NotNull ImageFormat format
    ) throws IOException {
        ImageIO.write(createImage(image), format.name(), filename.toFile());
    }

    /**
     * Creates a BufferedImage from a {@link FractalImage}.
     *
     * @param image The fractal image from which to create a {@link BufferedImage}.
     * @return The created BufferedImage.
     */
    private static @NotNull BufferedImage createImage(@NotNull FractalImage image) {
        var bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_ARGB);
        int[] rgbArray = new int[image.width() * image.height()];

        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel pixel = image.getPixel(col, row);
                rgbArray[row * image.width() + col] = new Color(
                    pixel.getRed(),
                    pixel.getGreen(),
                    pixel.getBlue(),
                    pixel.getAlpha()
                ).getRGB();
            }
        }

        bufferedImage.setRGB(0, 0, image.width(), image.height(), rgbArray, 0, image.width());

        return bufferedImage;
    }
}
