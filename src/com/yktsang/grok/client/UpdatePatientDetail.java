/*
 * UpdatePatientDetail.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.io.Serializable;
import javax.swing.Timer;

/**
 * Changes the data of the patient.
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class UpdatePatientDetail implements ActionListener, Serializable {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The detailed patient panel.
     */
    private final PatientDetailPane detailPane;

    /**
     * Constructs a <code>UpdatePatientDetail</code>.
     * 
     * @param detailPane the detailed patient panel
     */
    public UpdatePatientDetail(PatientDetailPane detailPane) {
        this.detailPane = detailPane;
    }

    /**
     * Changes the data of the patient.
     * 
     * @param ae the action event
     */
	@Override
    public void actionPerformed(ActionEvent ae) {
        Timer patientTimer = (Timer) ae.getSource();
        detailPane.setPatient(detailPane.getPatient());
        detailPane.revalidate();
        patientTimer.restart();
    }
    
} // end class UpdatePatientDetail
