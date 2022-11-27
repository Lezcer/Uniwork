package edu.curtin.reminder.ui;
import edu.curtin.reminder.controller.Controller;
import edu.curtin.reminder.model.ChangeObs;
import edu.curtin.reminder.model.Reminder;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * The main reminder application window. This contains a list of the reminders,
 * along with an "add" button that opens an "add reminder" window, and a 
 * "remove" button that removes the currently-selected reminder.
 */
public class MainWindow implements ChangeObs
{
    private JList<String> remindersWidget;
    
    private AddReminderWindow addReminderWindow;
    private Controller controller;
    
    /**
     * Set everything up. We need the controller for two purposes: (1) so we 
     * can tell it to remove a reminder, and (2) so we can pass it on to the 
     * AddReminderWindow constructor.
     */
    public MainWindow(Controller controller, AddReminderWindow addReminderWindow)
    {
        this.controller = controller;
        this.addReminderWindow = addReminderWindow;
    }
    
    public void show()
    {    
        JFrame window = new JFrame("Reminder App");       // Window title.
        window.setPreferredSize(new Dimension(600, 300)); // Window size
        
        // Make the whole program close when this window is closed.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
        addReminderWindow = new AddReminderWindow(controller);
        
        // Our important widgets:
        remindersWidget = new JList<>();
        remindersWidget.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        // Boring window layout stuff.
        JPanel contentPane = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(remindersWidget);
        JToolBar toolbar = new JToolBar();
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        toolbar.add(addButton);
        toolbar.add(removeButton);
        window.getRootPane().setContentPane(contentPane);
        
        // When the "add" button is clicked, make the "add reminder" window visible.
        addButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    addReminderWindow.show();
                }
            }
        );
        
        // When the "remove" button is clicked, tell the controller to remove
        // the currently selected reminder.
        removeButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Figure out which reminder is currently selected.
                    int index = remindersWidget.getSelectedIndex();
                    if(index != -1)
                    {
                        // If there *is* something currently selected, tell the
                        // controller to remove it.
                        controller.removeReminder(index);
                        
                        // FIXME: this change needs to be reflected on-screen and in the reminders file.
                    }
                }
            }
        );
        
        showReminders(controller.getReminders());
        
        // Trigger the layout algorithm.
        window.pack();
        window.setVisible(true);
    }
    
    /**
     * Takes a list of Reminder objects and displays them in the window.
     */
    public void showReminders(List<Reminder> reminders)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);    
        Vector<String> tasks = new Vector<>();
        for(Reminder rem : reminders)
        {
            tasks.add(rem.getTime().format(formatter) + " -- " + rem.getTask());
        }
        remindersWidget.setListData(tasks);
    }

    public void update(List<Reminder> reminders)
    {

    }
}
