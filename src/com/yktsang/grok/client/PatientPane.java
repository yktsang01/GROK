/*
 * PatientPane.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import com.yktsang.grok.entity.Patient;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Represents a panel showing patients. 
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class PatientPane extends JPanel {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a <code>PatientPane</code>, given an array of detailed 
     * panels and a list of patients.
     * 
     * @param detailPaneAry the array of detailed patient panels
     * @param patientList the list of patients
     */
    public PatientPane(PatientDetailPane[] detailPaneAry, 
            List<Patient> patientList) {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new GridLayout(0, 5, 0, 0));
        for (Patient p : patientList) {
            PatientButton button = new PatientButton(p);
            if (p.getName() != null) {
                button.addActionListener(
                        new DisplayPatientDetail(detailPaneAry));
            }
            add(button);
        }
    }
    
} // end class PatientPane
