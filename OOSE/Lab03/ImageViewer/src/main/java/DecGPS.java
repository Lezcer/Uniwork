package edu.curtin.imageviewer;
/**
 * Extends CaptionDecorator.
 * @author Salah
 */
public class DecGPS extends CaptionDecorator
{
    public DecGPS(ImageRecord image, String gps)
    {
        this.image = image;
        this.metadata = gps;
    }

    public String getCaption()
    {
        return image.getCaption() + ", GPS coordinates: " + metadata;
    }
}
