package edu.curtin.reminder.ui;
import edu.curtin.reminder.controller.Controller;

// We're using a 3rd-party widget to display the date-time picker. (This isn't built into Swing.)
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


/**
 * The window for adding reminders. Contains a text field, plus "Add" and "Close" buttons.
 */
public class AddReminderWindow
{
    private static final int PADDING = 10;    
    private Controller controller;
    
    /** 
     * We need to import a Controller reference, because 
     * this is where we tell the controller to add a reminder. 
     */
    public AddReminderWindow(Controller controller)
    {
        // We need to import a Controller reference, because this part of the UI tells the controller 
        // to add a reminder.
        this.controller = controller;
    }
    
    private JComponent align(JComponent c)
    {
        c.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        return c;
    }
    
    public void show()
    {
        JFrame window = new JFrame("Add Reminder");

        // Initialise the 3rd-party date/time picker widget.
        DatePickerSettings dateSettings = new DatePickerSettings();
        TimePickerSettings timeSettings = new TimePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        timeSettings.setAllowEmptyTimes(false);
        DateTimePicker dateTimePicker = new DateTimePicker(dateSettings, timeSettings);
        
        // These are the other important widgets.
        JTextField taskWidget = new JTextField(50);    // Space to enter some reminder text. 
        JButton addButton = new JButton("Add");     // A button to add the reminder.
        JButton closeButton = new JButton("Close"); // A button to close the window.
                
        // Now we do the boring window layout stuff.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(PADDING, 0)));
        buttonPanel.add(closeButton);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        
        contentPane.add(align(new JLabel("Task")));
        contentPane.add(align(taskWidget));
        contentPane.add(Box.createRigidArea(new Dimension(01, PADDING)));
        contentPane.add(align(new JLabel("Due Date/Time")));
        contentPane.add(align(dateTimePicker));
        contentPane.add(Box.createRigidArea(new Dimension(0, PADDING)));
        contentPane.add(align(buttonPanel));
        window.getRootPane().setContentPane(contentPane);
        
        
        // The add button tells the controller to add a new reminder.
        addButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Retrieve the text entered by the user.
                    String task = taskWidget.getText();
                    LocalDateTime time = dateTimePicker.getDateTimeStrict();
                    
                    if(task.length() > 0 && time != null)
                    {
                        // If the fields are not empty, add a new reminder.
                        controller.addReminder(task, time);
                                                
                        // FIXME: this change needs to be reflected on-screen and in the reminders file.
                        // ...
                    }
                }
            }
        );
        
        // The close button simply hides this window; i.e. making it invisible to the user (but 
        // still present in memory).
        closeButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    window.setVisible(false);
                }
            }
        );
        
        // Trigger the window layout algorithm.
        window.pack();
        window.setVisible(true);
    }
}
