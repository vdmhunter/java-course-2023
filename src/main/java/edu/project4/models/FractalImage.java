package edu.project4.models;

import edu.common.Generated;
import java.util.Arrays;
import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record FractalImage(Pixel[] data, int width, int height) {
    @Contract("_, _ -> new")
    public static @NotNull FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];

        for (int i = 0; i < data.length; ++i) {
            data[i] = new Pixel(0, 0, 0, 0);
        }

        return new FractalImage(data, width, height);
    }

    @Generated
    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel getPixel(int x, int y) {
        return data[y * width + x];
    }

    @Override
    @Generated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FractalImage other)) {
            return false;
        }

        return width == other.width && height == other.height && Arrays.equals(data, other.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "FractalImage{width=" + width + ", height=" + height + ", data=" + Arrays.toString(data) + "}";
    }
}

