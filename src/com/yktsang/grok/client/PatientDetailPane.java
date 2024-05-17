/*
 * PatientDetailPane.java
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
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * Represents a panel showing the details of a patient. 
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public class PatientDetailPane extends JPanel {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The replaceable flag.
     */
    private boolean replaceable;
    /**
     * The patient.
     */
    private Patient patient;
    /**
     * The patient timer.
     */
    private Timer patientTimer;
    /**
     * The bold font.
     */
    private static final Font boldFont =
            new Font(new JLabel().getFont().getName(),
            Font.BOLD, new JLabel().getFont().getSize());
    /**
     * The decimal format with leading zero and 1 decimal place.
     */
    private static final DecimalFormat decFmt = new DecimalFormat("0.0");
    /**
     * The number format for integer numbers.
     */
    private static final NumberFormat intFmt = NumberFormat.getIntegerInstance();

    /**
     * Constructs a <code>PatientDetailPane</code>, 
     * given the replaceable flag.
     * 
     * @param replace the replaceable flag
     */
    public PatientDetailPane(boolean replace) {
        this.replaceable = replace;
        setBorder(new EmptyBorder(5, 0, 0, 0));
    }
    
    /**
     * Constructs a <code>PatientDetailPane</code>, 
     * given the patient and the replaceable flag.
     * 
     * @param patient the patient
     * @param replace the replaceable flag
     */
    public PatientDetailPane(Patient patient, boolean replace) {
        this.replaceable = replace;
        this.patient = patient;
        showPatientDetail();
    }

    /**
     * Returns the replaceable flag.
     * 
     * @return the replaceable flag
     */
    public boolean isReplaceable() {
        return replaceable;
    }

    /**
     * Assigns the replaceable flag.
     * 
     * @param replace the replaceable flag
     */
    public void setReplaceable(boolean replace) {
        this.replaceable = replace;
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
        showPatientDetail();
    }
    
    /**
     * Shows the patient detail.
     */
    private void showPatientDetail() {
        removeAll();
        
        setLayout(new BorderLayout());
        
        VitalSign vs = patient.getVitalSign();

        JPanel namePanel = new JPanel();
        JLabel nameHeadingLabel = new JLabel("Full Name: ");
        nameHeadingLabel.setFont(boldFont);
        JLabel nameLabel = new JLabel(patient.getName());
        nameLabel.setFont(boldFont);
        namePanel.add(nameHeadingLabel);
        namePanel.add(nameLabel);
        add(namePanel, BorderLayout.NORTH);

        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        
        JPanel facePanel = new JPanel();
        JLabel faceLabel = new JLabel(new ImageIcon(patient.getFace()));
        faceLabel.setToolTipText(patient.getState().name());
        facePanel.add(faceLabel);
        imgPanel.add(facePanel, BorderLayout.NORTH);
        
        BufferedImage bodyImg = null;
        switch (patient.getGender()) {
            case MALE: {
                try {
                    bodyImg = ImageIO.read(Objects.requireNonNull(this.getClass()
                            .getClassLoader().getResourceAsStream(
                                    "com/yktsang/grok/entity/icons/"
                                            + "man_128x128.png")));
                } catch (IllegalArgumentException | IOException ex) {
                    System.err.println(ex.getMessage());
                }
                break;
            }
            case FEMALE: {
                try {
                    bodyImg = ImageIO.read(Objects.requireNonNull(this.getClass()
                            .getClassLoader().getResourceAsStream(
                                    "com/yktsang/grok/entity/icons/"
                                            + "woman_128x128.png")));
                } catch (IllegalArgumentException | IOException ex) {
                    System.err.println(ex.getMessage());
                }
                break;
            }
        }
        JLabel imgLabel = new JLabel(new ImageIcon(bodyImg));
        imgLabel.setToolTipText(patient.getGender().name());
        imgPanel.add(imgLabel, BorderLayout.CENTER);
        add(imgPanel, BorderLayout.WEST);
        
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        
        if (patient.getState().equals(State.DEAD)) {
            JPanel deadPanel = new JPanel();
            deadPanel.setLayout(new BorderLayout());
            JLabel deadHeadingLabel = new JLabel("Certified Dead: ");
            deadHeadingLabel.setFont(boldFont);
            SimpleDateFormat deadSdf = 
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JLabel deadLabel = 
                    new JLabel(deadSdf.format(patient.getCertifiedDeadDate()));
            deadPanel.add(deadHeadingLabel, BorderLayout.NORTH);
            deadPanel.add(deadLabel, BorderLayout.CENTER);
            dataPanel.add(deadPanel);
            dataPanel.add(generateDummyPanel());
        }

        JPanel dobPanel = new JPanel();
        dobPanel.setLayout(new BorderLayout());
        JLabel dobHeadingLabel = new JLabel("Date of Birth:");
        dobHeadingLabel.setFont(boldFont);
        SimpleDateFormat dobSdf = new SimpleDateFormat("yyyy-MM-dd");
        JLabel dobLabel = new JLabel(" "+dobSdf.format(patient.getDob()));
        dobPanel.add(dobHeadingLabel, BorderLayout.WEST);
        dobPanel.add(dobLabel, BorderLayout.CENTER);
        dataPanel.add(dobPanel);

        JPanel agePanel = new JPanel();
        agePanel.setLayout(new BorderLayout());
        JLabel ageHeadingLabel = new JLabel("Age:");
        ageHeadingLabel.setFont(boldFont);
        JLabel ageLabel = new JLabel(" "+patient.getAge());
        agePanel.add(ageHeadingLabel, BorderLayout.WEST);
        agePanel.add(ageLabel, BorderLayout.CENTER);
        dataPanel.add(agePanel);
        
        dataPanel.add(generateDummyPanel());
        
        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new BorderLayout());
        JLabel heightHeadingLabel = new JLabel("Height:");
        heightHeadingLabel.setFont(boldFont);
        JLabel heightLabel = 
                new JLabel(" "+decFmt.format(patient.getHeight())+" cm");
        heightPanel.add(heightHeadingLabel, BorderLayout.WEST);
        heightPanel.add(heightLabel, BorderLayout.CENTER);
        dataPanel.add(heightPanel);
        
        JPanel weightPanel = new JPanel();
        weightPanel.setLayout(new BorderLayout());
        JLabel weightHeadingLabel = new JLabel("Weight:");
        weightHeadingLabel.setFont(boldFont);
        JLabel weightLabel = 
                new JLabel(" "+decFmt.format(patient.getWeight())+" kg");
        weightPanel.add(weightHeadingLabel, BorderLayout.WEST);
        weightPanel.add(weightLabel, BorderLayout.CENTER);
        dataPanel.add(weightPanel);
        
        dataPanel.add(generateDummyPanel());

        JPanel bodyTempPanel = new JPanel();
        bodyTempPanel.setLayout(new BorderLayout());
        JLabel bodyTempHeadingLabel = new JLabel("Body Temperature:");
        bodyTempHeadingLabel.setFont(boldFont);
        JLabel bodyTempLabel = new JLabel(" "
                +decFmt.format(vs.getBodyTemperature())+" \u00B0C");
        bodyTempPanel.add(bodyTempHeadingLabel, BorderLayout.WEST);
        bodyTempPanel.add(bodyTempLabel, BorderLayout.CENTER);
        dataPanel.add(bodyTempPanel);
        
        JPanel pulsePanel = new JPanel();
        pulsePanel.setLayout(new BorderLayout());
        JLabel pulseHeadingLabel = new JLabel("Heart Rate:");
        pulseHeadingLabel.setFont(boldFont);
        JLabel pulseLabel = 
                new JLabel(" "+intFmt.format(vs.getHeartRate())+" beats/min");
        pulsePanel.add(pulseHeadingLabel, BorderLayout.WEST);
        pulsePanel.add(pulseLabel, BorderLayout.CENTER);
        dataPanel.add(pulsePanel);
        
        JPanel bloodPanel = new JPanel();
        bloodPanel.setLayout(new BorderLayout());
        JLabel bloodHeadingLabel = new JLabel("Blood Type:");
        bloodHeadingLabel.setFont(boldFont);
        JLabel bloodLabel = new JLabel(" "+patient.getBloodType());
        bloodPanel.add(bloodHeadingLabel, BorderLayout.WEST);
        bloodPanel.add(bloodLabel, BorderLayout.CENTER);
        dataPanel.add(bloodPanel);

        JPanel bloodPressurePanel = new JPanel();
        bloodPressurePanel.setLayout(new BorderLayout());
        JLabel bloodPressureHeadingLabel = new JLabel("Blood Pressure:");
        bloodPressureHeadingLabel.setFont(boldFont);
        JLabel bloodPressureLabel = new JLabel(" "
                +intFmt.format(vs.getSystolicBloodPressure())+" / "
                +intFmt.format(vs.getDiastolicBloodPressure())+" mmHg");
        bloodPressurePanel.add(bloodPressureHeadingLabel, BorderLayout.WEST);
        bloodPressurePanel.add(bloodPressureLabel, BorderLayout.CENTER);
        dataPanel.add(bloodPressurePanel);
        
        dataPanel.add(generateDummyPanel());

        dataPanel.add(generateDummyPanel());
        
        add(dataPanel, BorderLayout.CENTER);
        
        revalidate();
        
        if (this.patientTimer == null) {
            // use timer to change patient data
            patientTimer = new Timer(0, new UpdatePatientDetail(this));
            patientTimer.start();
        } else {
            if (patientTimer.isRunning()) {
                patientTimer.restart();
            }
        }
    }
    
    /**
     * Returns a dummy panel.
     * 
     * @return a dummy panel
     */
    private JPanel generateDummyPanel() {
        JPanel dummyPanel = new JPanel();
        dummyPanel.add(new JLabel("\n"));
        return dummyPanel;
    }
    
} // end class PatientDetailPane
