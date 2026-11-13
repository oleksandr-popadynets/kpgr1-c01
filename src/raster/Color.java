package raster;

/// utilities for working with 32-bit ARGB colors
public final class Color
{
    // non-instantiable class
    private Color() {}

    /// returns the alpha channel of the given color.
    /// the value is always within 0 255 range.
    public static int getAlpha(int argb) {
        return argb >> 24 & 0xff;
    }

    /// returns the red channel of the given color.
    /// the value is always within 0 255 range.
    public static int getRed(int argb) {
        return argb >> 16 & 0xff;
    }

    /// returns the green channel of the given color.
    /// the value is always within 0 255 range.
    public static int getGreen(int argb) {
        return argb >> 8 & 0xff;
    }

    /// returns the blue channel of the given color.
    /// the value is always within 0 255 range.
    public static int getBlue(int argb) {
        return argb & 0xff;
    }

    /// constructs a 32-bit ARGB directly from color channels.
    /// the given values are clamped within 0 to 255 range.
    public static int from(int a, int r, int g, int b) {
        return clamp255(a) << 24
             | clamp255(r) << 16
             | clamp255(g) << 8
             | clamp255(b);
    }

    /// constrains the given number from being less than 0 or larger than 255.
    private static int clamp255(int x) {
        return x < 0 ? 0 :
               x > 255 ? 255 :
               x;
    }

    /// constructs a 32-bit ARGB color from RGB channels,
    /// where the alpha channel is set to 255 by default.
    /// the given values are clamped within 0 to 255 range.
    public static int fromRGB(int r, int g, int b) {
        return from(255, r, g, b);
    }

    /// constructs a 32-bit ARGB from a black-and-white value,
    /// where the alpha channel is set to 255 by default.
    /// the given value is clamped within 0 to 255 range.
    public static int fromGrayscale(int rgb) {
        return from(255, rgb, rgb, rgb);
    }
}
