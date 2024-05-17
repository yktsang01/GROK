/*
 * GrokStatusBar.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import java.awt.BorderLayout;
import java.io.Serial;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents the status bar of the application.
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class GrokStatusBar extends JPanel {
    
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The message label.
     */
    private final JLabel messageLabel;
    
    /**
     * Constructs a <code>GrokStatusBar</code>.
     */
    public GrokStatusBar() {
        setLayout(new BorderLayout());
        messageLabel = new JLabel("Welcome to GROK");
        messageLabel.setToolTipText("What you are doing or " +
                "what you have just done");
        add(messageLabel);
    }

    /**
     * Returns the message from the status bar.
     * 
     * @return the message form the status bar
     */
    public String getMessage() {
        return messageLabel.getText();
    }

    /**
     * Assigns the message to the status bar.
     * 
     * @param message the message
     */
    public final void setMessage(String message) {
        messageLabel.setText(message);
        add(messageLabel, BorderLayout.WEST);
    }

} // end class GrokStatusBar
