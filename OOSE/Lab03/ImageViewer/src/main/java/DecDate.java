package edu.curtin.imageviewer;
/**
 * Extends CaptionDecorator.
 * @author Salah
 */
public class DecDate extends CaptionDecorator
{
    public DecDate(ImageRecord image, String date)
    {
        this.image = image;
        this.metadata = date;
    }

    public String getCaption()
    {
        return image.getCaption() + ", Date of this image is: " + metadata;
    }
}
