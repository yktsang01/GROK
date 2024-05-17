/*
 * GrokServer.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */
package com.yktsang.grok.server;

import com.yktsang.grok.entity.BloodType;
import com.yktsang.grok.entity.Gender;
import com.yktsang.grok.entity.Patient;
import com.yktsang.grok.entity.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the server of the application.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class GrokServer {

    /**
     * The list of patients.
     */
    private static List<Patient> patientList;

    /**
     * Constructs a <code>GrokServer</code>.
     */
    public GrokServer() {
        populatePatientList();
    }

    /**
     * Populates the <code>patientList</code>.
     */
    private void populatePatientList() {
        patientList = new ArrayList<>();
        
        File f = new File("patients.txt");
        String data;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            br.readLine(); // header

            while ((data = br.readLine()) != null) {
                String[] ary = data.split(",");
                String name = ary[0];
                String tempGender = ary[1].toUpperCase();
                Gender gender = null;
                if (tempGender.equals("M")) {
                    gender = Gender.MALE;
                } else if (tempGender.equals("F")) {
                    gender = Gender.FEMALE;
                }
                String[] dob = ary[2].split("-");
                int year = Integer.parseInt(dob[0]);
                int month = Integer.parseInt(dob[1]);
                int day = Integer.parseInt(dob[2]);
                State state = State.valueOf(ary[3].toUpperCase());
                BloodType bloodType = BloodType.valueOf(ary[4].toUpperCase());
                double height = Double.parseDouble(ary[5]);
                double weight = Double.parseDouble(ary[6]);
                double bodyTemp = Double.parseDouble(ary[7]);
                int heartRate = Integer.parseInt(ary[8]);
                int sysBp = Integer.parseInt(ary[9]);
                int diaBp = Integer.parseInt(ary[10]);

                Patient p = new Patient(name, gender,
                        year, month, day, state, bloodType);
                p.setHeight(height);
                p.setWeight(weight);
                p.getVitalSign().setBodyTemperature(bodyTemp);
                p.getVitalSign().setHeartRate(heartRate);
                p.getVitalSign().setSystolicBloodPressure(sysBp);
                p.getVitalSign().setDiastolicBloodPressure(diaBp);
                patientList.add(p);
            }
        } catch (IOException ioe) {
            System.err.println("Cannot open or problem reading file patients.txt");
        }
        // ignore
    }

    /**
     * Returns the <code>patientList</code>.
     *
     * @return the list of patients
     */
    public static List<Patient> getPatientList() {
        return patientList;
    }

    /**
     * Returns a random body temperature.
     *
     * @return a random body temperature
     */
    public static double genRandomBodyTemperature() {
        Random randomGenerator = new Random();
        // range from 0.0 to 50.0 inclusive
        return randomGenerator.nextDouble() * 50;
    }

    /**
     * Returns a random systolic blood pressure.
     *
     * @return a random systolic blood pressure
     */
    public static int genRandomSystolicBloodPressure() {
        Random randomGenerator = new Random();
        // range from 0 to 199 inclusive
        return randomGenerator.nextInt(200);
    }

    /**
     * Returns a random diastolic blood pressure.
     *
     * @return a random diastolic blood pressure
     */
    public static int genRandomDiastolicBloodPressure() {
        Random randomGenerator = new Random();
        // range from 0 to 119 inclusive
        return randomGenerator.nextInt(120);
    }

    /**
     * Returns a random heart rate.
     *
     * @return a random heart rate
     */
    public static int genRandomHeartRate() {
        Random randomGenerator = new Random();
        // range from 0 to 149 inclusive
        return randomGenerator.nextInt(150);
    }

    /**
     * Returns a random height.
     *
     * @return a random height
     */
    public static double genRandomHeight() {
        Random randomGenerator = new Random();
        // range from 0.0 to 200.0 inclusive
        return randomGenerator.nextDouble() * 200;
    }

    /**
     * Returns a random weight.
     *
     * @return a random weight
     */
    public static double genRandomWeight() {
        Random randomGenerator = new Random();
        // range from 0.0 to 300.0 inclusive
        return randomGenerator.nextDouble() * 300;
    }

} // end class GrokServer

