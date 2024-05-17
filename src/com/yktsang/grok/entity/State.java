/*
 * BodyTemperature.java
 *
 * GROK (Graphical Representation of Knowledge) is a stand-alone application 
 * analyzing patients' vital signs based on some data.
 *
 * This class or interface is part of the GROK project.
 * The class or interface must not be used outside of this context.
 */

package com.yktsang.grok.entity;

/**
 * Provides a listing of states for a {@link Patient}.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
public enum State {
    /**
     * The blank state. Used for an unknown patient.
     */
    BLANK,
    /**
     * The happy state.
     */
    HAPPY,
    /**
     * The sad state.
     */
    SAD,
    /**
     * The sick state.
     */
    SICK,
    /**
     * The dead state.
     */
    DEAD
} // end enum State
