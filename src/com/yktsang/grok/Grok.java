/*
 * Grok.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok;

import com.yktsang.grok.client.GrokClient;
import com.yktsang.grok.server.GrokServer;
import com.yktsang.grok.util.GrokUtil;
import javax.swing.SwingUtilities;

/**
 * Provides the entry point for the application. 
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class Grok {

    /**
     * Provides the entry point for the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // all available faces
        GrokUtil.loadFaceMap();
        // load up patient data
        new GrokServer();
        // main window
        SwingUtilities.invokeLater(GrokClient::new);
    }
    
} // end class Grok
