package edu.curtin.imageviewer;
import java.util.*;

import edu.curtin.imageviewer.ImageRecord;

/**
 * Represents an album of images.
 * 
 * @author Salah Mahamod
 */
public class Album 
{
    // FIXME: Insert your fields, constructors and methods here.
    ArrayList<ImageRecord> images = new ArrayList<ImageRecord>();

    public Album()
    {

    }
    // Empty constructor

    public void addImage(ImageRecord image)
    {
        images.add(image);
    }

    public ImageRecord getImage(int index)
    {
        return images.get(index);
    }

    public int size()
    {
        return images.size();
    }
}
