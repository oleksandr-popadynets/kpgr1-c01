package raster;

/// represents an image of rasters where colors are in ARGB format, 32 bits each.
public interface Image
{
    /// returns the width of the image in rasters
    int width();

    /// returns the height of the image in rasters
    int height();

    /// returns the raster's color at the given coordinate.
    /// @throws IndexOutOfBoundsException if the given coordinate is outside the image.
    int getPixel(int x, int y);

    /// sets the raster's color at the given coordinate.
    /// @throws IndexOutOfBoundsException if the given coordinate is outside the image.
    void setPixel(int x, int y, int color);

    /// retreives the raster's color at the given coordinate if it is inside the image.
    /// otherwise, returns the given defaultColor.
    default int tryGetPixel(int x, int y, int defaultColor) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            return defaultColor;
        }

        return getPixel(x, y);
    }

    /// sets the raster's color at the given coordinate if it is inside the image.
    default void trySetPixel(int x, int y, int color) {
        if (x >= 0 && x < width() && y >= 0 && y < height()) {
            setPixel(x, y, color);
        }
    }
}
