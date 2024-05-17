/*
 * ChangePatientData.java
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
import com.yktsang.grok.entity.VitalSign;
import com.yktsang.grok.server.GrokServer;
import com.yktsang.grok.util.GrokUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import javax.swing.Timer;

/**
 * Changes the data of the patient.
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class ChangePatientData implements ActionListener, Serializable {
    
    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The patient button.
     */
    private final PatientButton patientButton;
    /**
     * The previous state.
     */
    private State previousState;
    /**
     * The patient.
     */
    private Patient patient;

    /**
     * Constructs a <code>ChangePatientData</code>.
     * 
     * @param patientButton the patient button
     */
    public ChangePatientData(PatientButton patientButton) {
        this.patientButton = patientButton;
    }

    /**
     * Changes the data of the patient.
     * 
     * @param ae the action event
     */
	@Override
    public void actionPerformed(ActionEvent ae) {
        Timer patientTimer = (Timer) ae.getSource();
        this.patient = patientButton.getPatient();
        changeHeight();
        changeWeight();
        changeVitalSigns();
        changeState();
        if (patient.getState().equals(State.DEAD)) {
            patientTimer.stop();
        } else {
            patientTimer.restart();
        }
    }
    
    /**
     * Changes the state of the patient.
     */
    private void changeState() {
        State currentState = patient.getState();
        if (currentState.equals(State.DEAD)) {
            patientButton.changePatientState(State.DEAD);
            return;
        }
        Map<State, BufferedImage> faceMap = GrokUtil.getFaceMap();
        
        for (State s : State.values()) {
            if (s.equals(State.BLANK) 
                    || s.equals(currentState) 
                    || s.equals(previousState)) {
                continue;
            }
            this.previousState = currentState;
            patient.setState(s);
            patient.setFace(faceMap.get(s));
            patientButton.changePatientState(s);
            break;
        }
    }
    
    /**
     * Changes the height of the patient.
     */
    private void changeHeight() {
        patient.setHeight(GrokServer.genRandomHeight());
    }
    
    /**
     * Changes the weight of the patient.
     */
    private void changeWeight() {
        patient.setWeight(GrokServer.genRandomWeight());
    }
    
    /**
     * Changes the vital signs of the patient.
     */
    private void changeVitalSigns() {
        VitalSign vs = patient.getVitalSign();
        int pulse = GrokServer.genRandomHeartRate();
        if (pulse == 0) {
            GrokUtil.declarePatientIsDead(patient);
            return;
        } else {
            vs.setHeartRate(pulse);
            vs.setBodyTemperature(GrokServer.genRandomBodyTemperature());
            vs.setDiastolicBloodPressure(
                    GrokServer.genRandomDiastolicBloodPressure());
            vs.setSystolicBloodPressure(
                    GrokServer.genRandomSystolicBloodPressure());
        }
        patient.setVitalSign(vs);
    }
    
} // end class ChangePatientData
