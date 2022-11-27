package edu.curtin.imageviewer;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene; 
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.File;

/**
 * Represents the main user interface for the Image Viewer application.
 * @author Dave and Salah Mahamod
 */
public class MainWindow
{
    private Album album;
    private Stage stage;
    
    private BorderPane mainBox = new BorderPane();
    private ImageView imageWidget = new ImageView();
    private Label captionWidget = new Label();
 
    private int currentIndex = 0;

    public MainWindow(Album album, Stage stage)
    {
        this.album = album;
        this.stage = stage;
    }
    
    public File chooseAlbumFile()
    {
        FileChooser dialog = new FileChooser();
        dialog.setTitle("Select Album File");
        dialog.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        File currentDir = new File(System.getProperty("user.dir"));
        File resourcesDir = new File(currentDir, "resources");        
        if(resourcesDir.isDirectory())
        {
            dialog.setInitialDirectory(resourcesDir);
        }
        else
        {
            dialog.setInitialDirectory(currentDir);
        }
        return dialog.showOpenDialog(stage);
    }
    
    /**
     * Builds the main UI, based on an Album and a JavaFX 'Stage' (which is basically a 
     * pre-existing window).
     */
    public void show()
    {
        Platform.setImplicitExit(true);
        stage.setTitle("JavaFX Image Viewer");
        
        // *** #2 Fix this code so that it loads the initial (first) image. ***
        ImageRecord currentImage = album.getImage(0);
        String filename = currentImage.getFilename();
        /**
         * Get the image from index 0
         * Get the image's name using the getter
         */
        String url = new File(filename).toURI().toString();
        imageWidget.setImage(new Image(url));
        
        // Add 'mainBox' to the window. This is a container for holding the other bits: the toolbar, 
        // scroller (containing the image), and caption.
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        
        Button prevBtn = new Button("Previous");
        Button nextBtn = new Button("Next");
        ToolBar toolBar = new ToolBar(prevBtn, nextBtn);
        mainBox.setTop(toolBar);
        
        // Set up nextBtnHandler() to be called when nextBtn is clicked, and similarly for prevBtn.
        // ('abc::xyz' is a method reference, essentially a tiny object that calls the specified 
        // method.)
        prevBtn.setOnAction(this::prevBtnHandler);
        nextBtn.setOnAction(this::nextBtnHandler);
        
        ScrollPane scroller = new ScrollPane();
        scroller.setContent(imageWidget);
        mainBox.setCenter(scroller);
        
        // #4 FIXME: fix this code so that it displays the caption for the first image.
        String caption = currentImage.getCaption();
        /**
         * Got the image's caption using the getter
         */
        captionWidget.setText(caption);
        mainBox.setBottom(captionWidget);
        
        stage.sizeToScene();
        stage.show();
    }
    
    /**
    * Retrieves the album.
    */
    public Album getAlbum()
    {
        return album;
    }

    /**
     * Called to handle "previous" button clicks.
     */
    private void prevBtnHandler(ActionEvent event)
    {
        // #5 FIXME: fix this code so that it actually displays the previous image & caption.
        currentIndex = (currentIndex - 1) % album.size();
        // Adjusts the current index

        ImageRecord currentImage = album.getImage(currentIndex);
        String filename = currentImage.getFilename();
        String caption = currentImage.getCaption();
        /**
         * Gets the image from the album using a global variable index
         * The filename and caption are retrieved using the setters
         * then passed on
         */

        String url = new File(filename).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(caption);
    }

    /**
     * Called to handle "next" button clicks.
     */
    private void nextBtnHandler(ActionEvent event)
    {
        // #6 FIXME: fix this code so that it actually displays the next image & caption.
        currentIndex = (currentIndex + 1) % album.size();
        // Adjusts the current index

        ImageRecord currentImage = album.getImage(currentIndex);
        String filename = currentImage.getFilename();
        String caption = currentImage.getCaption();
        /**
         * Gets the image from the album using a global variable index
         * The filename and caption are retrieved using the setters
         * then passed on
         */

        String url = new File(filename).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(caption);
    }
}
