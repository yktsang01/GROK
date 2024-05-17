/*
 * GrokMenuBar.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.Serial;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Represents the menu bar of the application.
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class GrokMenuBar extends JMenuBar {
    
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a <code>GrokMenuBar</code>.
     */
    public GrokMenuBar() {
        displayFileMenu();
        displayHelpMenu();
    }

    /**
     * Displays the file menu.
     */
    private void displayFileMenu() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);

        JMenuItem exitItem = new JMenuItem(new ExitApp());
        exitItem.setToolTipText("Exit application");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(exitItem);

        add(menu);
    }

    /**
     * Displays the help menu.
     */
    private void displayHelpMenu() {
        JMenu menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);

        JMenuItem aboutItem =
                new JMenuItem(new AboutApp());
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(aboutItem);

        add(menu);
    }

} // end class GrokMenuBar
