/*
 * VitalSign.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a collection of vital signs, such as heart rate, 
 * body temperature, etc.
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class VitalSign implements Serializable {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The body temperature. Measured in degrees Celsius (\u00B0C).
     */
    private double bodyTemperature;
    /**
     * The heart rate. Measured in beats per minute (beats/min).
     */
    private int heartRate;
    /**
     * The systolic (high) blood pressure. 
     * Measured in millimeter of mercury (mmHg).
     */
    private int systolicBloodPressure;
    /**
     * The diastolic (resting) blood pressure. 
     * Measured in millimeter of mercury (mmHg).
     */
    private int diastolicBloodPressure;

    /**
     * Constructs a <code>VitalSign</code>.
     */
    public VitalSign() {}

    /**
     * Returns the body temperature.
     * 
     * @return the body temperature
     */
    public double getBodyTemperature() {
        return bodyTemperature;
    }

    /**
     * Assigns the body temperature.
     * 
     * @param bodyTemperature the body temperature
     */
    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    /**
     * Returns the heart rate.
     * 
     * @return the heart rate
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * Assigns the heart rate.
     * 
     * @param heartRate the heart rate
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * Returns the systolic (high) blood pressure.
     * 
     * @return the systolic (high) blood pressure
     */
    public int getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    /**
     * Assigns the systolic (high) blood pressure.
     * 
     * @param systolicBloodPressure the systolic (high) blood pressure
     */
    public void setSystolicBloodPressure(int systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }
    
    /**
     * Returns the diastolic (resting) blood pressure.
     * 
     * @return the diastolic (resting) blood pressure
     */
    public int getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    /**
     * Assigns the diastolic (resting) blood pressure.
     * 
     * @param diastolicBloodPressure the diastolic (resting) blood pressure
     */
    public void setDiastolicBloodPressure(int diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

} // end class VitalSign
