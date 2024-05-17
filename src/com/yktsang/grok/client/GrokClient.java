/*
 * GrokClient.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */
package com.yktsang.grok.client;

import com.yktsang.grok.entity.Patient;
import com.yktsang.grok.server.GrokServer;
import com.yktsang.grok.util.WindowPositioner;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.Serial;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Represents the main window of the application.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class GrokClient extends JFrame {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The status bar.
     */
    private static GrokStatusBar statusBar;

    /**
     * Constructs a <code>GrokClient</code>.
     */
    public GrokClient() {
        super("Graphical Representation of Knowledge GROK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 0, 0));

        statusBar = new GrokStatusBar();
        add(statusBar, BorderLayout.SOUTH);

        add(new GrokMenuBar(), BorderLayout.NORTH);

        PatientDetailPane[] detailPaneAry = new PatientDetailPane[3];
        for (int i = 0; i < 3; i++) {
            detailPaneAry[i] = new PatientDetailPane(true);
        }

        List<Patient> patientList = GrokServer.getPatientList();
        int size = patientList.size();
        // ensure the list has at least 25 patients
        while (size < 25) {
            patientList.add(new Patient());
            size = patientList.size();
        }

        // fill in the panels
        PatientPane patientPane
                = new PatientPane(detailPaneAry, patientList);
        JScrollPane scrollPane = new JScrollPane(patientPane,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 350));
        for (PatientDetailPane dp : detailPaneAry) {
            mainPanel.add(dp);
        }
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);
        pack();
        Rectangle rect = getBounds();
        int x = WindowPositioner.getXPosition(rect);
        int y = WindowPositioner.getYPosition(rect);
        setBounds(x, y, rect.width, rect.height);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Returns the <code>GrokStatusBar</code>.
     *
     * @return the status bar
     */
    public static GrokStatusBar getStatusBar() {
        return statusBar;
    }

} // end class GrokClient
