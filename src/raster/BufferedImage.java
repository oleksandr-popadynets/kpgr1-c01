package raster;

/// an implementation of raster.Image via extension of java.awt.BufferedImage
public class BufferedImage extends java.awt.image.BufferedImage implements Image
{
    public BufferedImage(int width, int height) {
        super(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public int width() {
        return getWidth();
    }

    @Override
    public int height() {
        return getHeight();
    }

    @Override
    public int getPixel(int x, int y) {
        checkBounds(x, y);
        return getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        checkBounds(x, y);
        setRGB(x, y, color);
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new IndexOutOfBoundsException(
                "Pixel coordinate ("+x+", "+y+")" +
                "is out of image bounds (0.."+width()+", 0.."+height()+")"
            );
        }
    }
}
