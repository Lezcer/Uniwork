package edu.curtin.imageviewer;
/**
 * Extends CaptionDecorator.
 * @author Salah
 */
public class DecRating extends CaptionDecorator
{
    public DecRating(ImageRecord image, String rating)
    {
        this.image = image;
        this.metadata = rating;
    }

    public String getCaption()
    {
        return image.getCaption() + ", Rating: " + metadata;
    }
}
