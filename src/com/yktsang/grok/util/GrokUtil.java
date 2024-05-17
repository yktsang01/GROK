/*
 * GrokUtil.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.util;

import com.yktsang.grok.entity.Patient;
import com.yktsang.grok.entity.State;
import com.yktsang.grok.entity.VitalSign;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * Provides the common utilities for the application. 
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class GrokUtil {

    /**
     * The map of faces. The face is represented as 
     * a <code>BufferedImage</code>.
     */
    private static final Map<State, BufferedImage> faceMap
            = new EnumMap<>(State.class);

    /**
     * Populates the <code>faceMap</code>. Each face represents a state, 
     * such as happy or sad.
     */
    public static void loadFaceMap() {
        try {
            faceMap.put(State.HAPPY, ImageIO.read(Objects.requireNonNull(GrokUtil.class
                    .getClassLoader().getResourceAsStream(
                            "com/yktsang/grok/entity/icons/happy_48x48.png"))));
            faceMap.put(State.SAD, ImageIO.read(Objects.requireNonNull(GrokUtil.class
                    .getClassLoader().getResourceAsStream(
                            "com/yktsang/grok/entity/icons/sad_48x48.png"))));
            faceMap.put(State.SICK, ImageIO.read(Objects.requireNonNull(GrokUtil.class
                    .getClassLoader().getResourceAsStream(
                            "com/yktsang/grok/entity/icons/sick_48x48.png"))));
            faceMap.put(State.DEAD, ImageIO.read(Objects.requireNonNull(GrokUtil.class
                    .getClassLoader().getResourceAsStream(
                            "com/yktsang/grok/entity/icons/dead_48x48.png"))));
        } catch (IllegalArgumentException | IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Returns the <code>faceMap</code>.
     * 
     * @return the faceMap
     */
    public static Map<State, BufferedImage> getFaceMap() {
        return faceMap;
    }

    /**
     * Declares a patient dead.
     * 
     * @param patient the patient to become dead
     */
    public static void declarePatientIsDead(Patient patient) {
        patient.setState(State.DEAD);
        patient.setFace(faceMap.get(State.DEAD));
        patient.setCertifiedDeadDate(Calendar.getInstance().getTime());
        VitalSign vs = patient.getVitalSign();
        vs.setBodyTemperature(0.0);
        vs.setDiastolicBloodPressure(0);
        vs.setSystolicBloodPressure(0);
        vs.setHeartRate(0);
    }
    
} // end class GrokUtil
