/*
 * ExitApp.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import java.awt.event.ActionEvent;
import java.io.Serial;
import javax.swing.AbstractAction;

/**
 * Exits the application.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class ExitApp extends AbstractAction {
    
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a <code>ExitApp</code>.
     */
    public ExitApp() {
        putValue(AbstractAction.NAME, "Exit");
        putValue(AbstractAction.SHORT_DESCRIPTION,
                "Exits application");
    }

    /**
     * Exits application.
     *
     * @param ae action event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }

} // end class ExitApp
