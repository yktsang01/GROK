/*
 * PatientButton.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.client;

import com.yktsang.grok.entity.Patient;
import com.yktsang.grok.entity.State;
import com.yktsang.grok.util.GrokUtil;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 * Represents a button for a patient. Each patient is displayed as 
 * a face (icon). 
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class PatientButton extends JButton {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The patient.
     */
    private Patient patient;

    /**
     * Constructs a <code>PatientButton</code>, given the patient.
     * 
     * @param patient the patient
     */
    public PatientButton(Patient patient) {
        this.patient = patient;
        if (patient.getState().equals(State.BLANK)) {
            setEnabled(false);
        } else {
            setIcon(new ImageIcon(patient.getFace()));
            setToolTipText(patient.getName());
            new Timer(5000, new ChangePatientData(this)).start();
        }
    }

    /**
     * Returns the patient.
     * 
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Assigns the patient.
     * 
     * @param patient the patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    /**
     * Changes the current state of the patient.
     * 
     * @param state the new state
     */
    public void changePatientState(State state) {
        Map<State, BufferedImage> faceMap = GrokUtil.getFaceMap();
        BufferedImage face = faceMap.get(state);
        setIcon(new ImageIcon(face));
        revalidate();
    }

} // end class PatientButton
