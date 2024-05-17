/*
 * WindowPositioner.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * Determines the X and Y positions of a window. Uses the default
 * toolkit to obtain the user screen size.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class WindowPositioner {

    /**
     * The default toolkit for getting user screen size.
     */
    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    /**
     * The dimension of user screen size.
     */
    private static final Dimension dim = tk.getScreenSize();

    /**
     * Returns the X position of a window.
     *
     * @param rect the rectangle representing the size of window
     * @return the X position of a window
     */
    public static int getXPosition(Rectangle rect) {
        return (dim.width/2) - (rect.width/2);
    }

    /**
     * Returns the Y position of a window.
     * 
     * @param rect the rectangle representing the size of window
     * @return the Y position of a window
     */
    public static int getYPosition(Rectangle rect) {
        return (dim.height/2) - (rect.height/2);
    }

} // end class WindowPositioner
