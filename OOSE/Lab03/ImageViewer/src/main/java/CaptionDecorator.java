package edu.curtin.imageviewer;

import edu.curtin.imageviewer.ImageRecord;

/**
 * Represents an image in an album.
 * @author Salah
 */
public abstract class CaptionDecorator implements ImageRecord
{
    public ImageRecord image;
    public String metadata;

    public abstract String getCaption();
    public String getFilename()
    {
        return image.getFilename();
    }
}
