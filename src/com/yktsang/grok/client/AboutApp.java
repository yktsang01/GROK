/*
 * AboutApp.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import com.yktsang.grok.util.WindowPositioner;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.Serial;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Displays the about window.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class AboutApp extends AbstractAction {
    
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The dialog window.
     */
    private JDialog dialog;
    /**
     * The <code>GrokStatusBar</code>.
     */
    private GrokStatusBar statusBar;
    /**
     * The status bar existing message.
     */
    private String prevMessage;
    
    /**
     * Constructs a <code>AboutApp</code>.
     */
    public AboutApp() {
        putValue(AbstractAction.NAME, "About");
        putValue(AbstractAction.SHORT_DESCRIPTION,
                "What this application is about");
    }

    /**
     * Displays the about window.
     *
     * @param ae the action event
     */
	@Override
    public void actionPerformed(ActionEvent ae) {
        statusBar = GrokClient.getStatusBar();
        prevMessage = statusBar.getMessage();
        statusBar.setMessage("Viewing about");

        dialog = new JDialog();
        dialog.setTitle("About");
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        // info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel versionLabel = new JLabel("Product Version: GROK 1.0");
        infoPanel.add(versionLabel);
        infoPanel.add(new JLabel("\n"));
        JLabel unitLabel = new JLabel("Units of Measurement");
        unitLabel.setFont(new Font(new JLabel().getFont().getName(),
            Font.BOLD, new JLabel().getFont().getSize()));
        infoPanel.add(unitLabel);
        JLabel heightLabel = new JLabel("Height = centimeters (cm)");
        infoPanel.add(heightLabel);
        JLabel weightLabel = new JLabel("Weight = kilograms (kg)");
        infoPanel.add(weightLabel);
        JLabel bodyTempLabel = 
                new JLabel("Body Temperature = degrees Celsius (\u00B0C)");
        infoPanel.add(bodyTempLabel);
        JLabel pulseLabel = 
                new JLabel("Heart Rate = beats per minute (beats/min)");
        infoPanel.add(pulseLabel);
        JLabel bloodPressureLabel = 
                new JLabel("Blood Pressure = millimeters of mercury (mmHg)");
        infoPanel.add(bloodPressureLabel);
        infoPanel.add(new JLabel("\n"));
        JLabel imgLabel = new JLabel("Images for the application are "
                + "by courtesy of http://www.freeimages.com");
        infoPanel.add(imgLabel);
        dialog.add(infoPanel, BorderLayout.CENTER);
        
        // button panel
        JPanel panel = new JPanel();
        JButton button = new JButton(new DisposeAbout());
        panel.add(button);
        dialog.add(panel, BorderLayout.SOUTH);

        dialog.pack();
        Rectangle rect = dialog.getBounds();
        int x = WindowPositioner.getXPosition(rect);
        int y = WindowPositioner.getYPosition(rect);
        dialog.setBounds(x, y, rect.width, rect.height);
        dialog.setVisible(true);
        
    }
    
    /**
     * Disposes the about window.
     */
    private class DisposeAbout extends AbstractAction {

        /**
         * The serial version UID.
         */
        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * Constructs a <code>DisposeAbout</code>.
         */
        private DisposeAbout() {
            putValue(AbstractAction.NAME, "Close");
            putValue(AbstractAction.SHORT_DESCRIPTION, "Close");
        }

        /**
         * Disposes about window.
         *
         * @param ae the action event
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            statusBar.setMessage(prevMessage);
            dialog.dispose();
        }

    } // end inner class DisposeAbout

} // end class AboutApp
