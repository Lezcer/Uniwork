package edu.curtin.imageviewer;

import edu.curtin.imageviewer.ImageRecord;
/**
 * Represents an image in an album.
 * @author Salah
 */

public class ConcreteImage implements ImageRecord 
{
    private String filename;
    private String caption;
    
    public ConcreteImage(String newFilename, String newCaption)
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
