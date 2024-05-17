/*
 * DisplayPatientDetail.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import com.yktsang.grok.entity.Patient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.io.Serializable;

/**
 * Displays the details of the patient.
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class DisplayPatientDetail implements ActionListener, Serializable {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The array of patient detailed panels.
     */
    private final PatientDetailPane[] panelAry;
    /**
     * The currently shown patients.
     */
    private static final Patient[] onDisplayPatient = new Patient[3];

    /**
     * Constructs a <code>DisplayPatientDetail</code>, 
     * given an array of patient detailed panels.
     * 
     * @param panelAry the array of patient detailed panels
     */
    public DisplayPatientDetail(PatientDetailPane[] panelAry) {
        this.panelAry = panelAry;
    }

    /**
     * Displays the details of the patient, given the action event.
     * 
     * @param ae the action event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        PatientButton button = (PatientButton) ae.getSource();
        Patient p = button.getPatient();
        
        // check if the selected patient is displayed
        for (Patient tempP : onDisplayPatient) {
            if (p.equals(tempP)) {
                GrokClient.getStatusBar().setMessage(
                        "Patient " + p.getName() + " is already shown");
                return;
            }
        }
        
        GrokClient.getStatusBar().setMessage(
                "Patient " + p.getName() + " is clicked");
        
        int panelToReplace = -1;
        for (int i = 0; i < panelAry.length; i++) {
            if (panelAry[i].isReplaceable()) {
                panelAry[i].setReplaceable(false);
                panelToReplace = i;
                // reset all panels to replaceable once used up
                if (i == (panelAry.length - 1)) {
                    for (PatientDetailPane patientDetailPane : panelAry) {
                        patientDetailPane.setReplaceable(true);
                    }
                }
                break;
            }
        }
        
        PatientDetailPane dp = panelAry[panelToReplace];
        dp.setPatient(p);
        dp.revalidate();

        // assign the patient array
        for (int i=0; i<panelAry.length; i++) {
            onDisplayPatient[i] = panelAry[i].getPatient();
        }
    }

} // end class DisplayPatientDetail