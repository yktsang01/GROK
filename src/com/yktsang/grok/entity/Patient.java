/*
 * Patient.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.entity;

import com.yktsang.grok.util.GrokUtil;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a patient, storing basic information for identity purposes.
 * 
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class Patient implements Serializable {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The name.
     */
    private String name;
    /**
     * The gender.
     */
    private Gender gender;
    /**
     * The date of birth.
     */
    private Date dob;
    /**
     * The height. Measured in centimeters (cm).
     */
    private double height;
    /**
     * The weight. Measured in kilograms (kg).
     */
    private double weight;
    /**
     * The collection of {@link VitalSign}s.
     */
    private VitalSign vitalSign;
    /**
     * The state.
     */
    private State state;
    /**
     * The face corresponding to the <code>state</code>.
     */
    private BufferedImage face;
    /**
     * The age.
     */
    private int age;
    /**
     * The blood type.
     */
    private BloodType bloodType;
    /**
     * The certified dead date/time.
     */
    private Date certifiedDeadDate;
    
    /**
     * Constructs a <code>Patient</code>.
     */
    public Patient() {
        this.state = State.BLANK;
    }

    /**
     * Constructs a <code>Patient</code> with name, gender, birth year, 
     * birth month, birth day, and initial state.
     * 
     * @param name the name
     * @param gender the gender
     * @param year the birth year
     * @param month the birth month
     * @param day the birth day
     * @param state the initial state
     * @param bloodType the blood type
     */
    public Patient(String name, Gender gender, int year, int month, int day, 
            State state, BloodType bloodType) {
        this.name = name;
        this.gender = gender;
        this.state = state;
        this.face = GrokUtil.getFaceMap().get(state);
        this.bloodType = bloodType;
        this.vitalSign = new VitalSign();
        Calendar birth = Calendar.getInstance();
        birth.set(year, month - 1, day);
        this.dob = birth.getTime();
        calcAge(birth);
    }
    
    /**
     * Calculates the age.
     * 
     * @param birth the birth date calendar
     */
    private void calcAge(Calendar birth) {
        Calendar now = Calendar.getInstance();
        int temp = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            temp--;
        } else if ((now.get(Calendar.MONTH) == birth.get(Calendar.MONTH))
                && (now.get(Calendar.DAY_OF_MONTH) > birth.get(Calendar.DAY_OF_MONTH))) {
            temp--;
        }
        this.age = temp;
    }
    
    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the gender.
     * 
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }
    
    /**
     * Returns the date of birth.
     * 
     * @return the date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Returns the age.
     * 
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the height.
     * 
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Assigns the height.
     * 
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the weight.
     * 
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Assigns the weight.
     * 
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns the vital sign.
     * 
     * @return the vital sign
     */
    public VitalSign getVitalSign() {
        return vitalSign;
    }

    /**
     * Assigns the vital sign.
     * 
     * @param vitalSign the vital sign
     */
    public void setVitalSign(VitalSign vitalSign) {
        this.vitalSign = vitalSign;
    }

    /**
     * Returns the state.
     * 
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Assigns the state.
     * 
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Returns the face.
     * 
     * @return the face
     */
    public BufferedImage getFace() {
        return face;
    }

    /**
     * Assigns the face.
     * 
     * @param face the face
     */
    public void setFace(BufferedImage face) {
        this.face = face;
    }

    /**
     * Returns the blood type.
     * 
     * @return the blood type
     */
    public BloodType getBloodType() {
        return bloodType;
    }
    
    /**
     * Assigns the certified dead date.
     * 
     * @param certifiedDeadDate the certified dead date
     */
    public void setCertifiedDeadDate(Date certifiedDeadDate) {
        this.certifiedDeadDate = certifiedDeadDate;
    }

    /**
     * Returns the certified dead date.
     * 
     * @return the certified dead date
     */
    public Date getCertifiedDeadDate() {
        return certifiedDeadDate;
    }

    /**
     * Returns the hash code of a patient.
     * 
     * @return the hash code of a patient
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 73 * hash + (this.gender != null ? this.gender.hashCode() : 0);
        hash = 73 * hash + (this.dob != null ? this.dob.hashCode() : 0);
        return hash;
    }

    /**
     * Returns true if passed in parameter has the same attributes 
     * as current patient, false otherwise.
     * 
     * @param obj the passed in parameter
     * @return true if passed in parameter has the same attributes 
     * as current patient, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        return Objects.equals(this.dob, other.dob);
    }
    
} // end class Patient
