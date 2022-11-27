package edu.curtin.imageviewer;

/**
 * Represents an image in an album.
 * @author Dave
 */
public class ImageRecord
{
    private String filename;
    private String caption;
    
    public ImageRecord(String newFilename, String newCaption)
    {
        filename = newFilename;
        caption = newCaption;
    }
    
    public String getFilename()
    {
        return filename;
    }
    
    public String getCaption()
    {
        return caption;
    }
}

