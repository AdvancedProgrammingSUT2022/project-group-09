package com.civilization.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit test for simple App.
 */
@ExtendWith(MockitoExtension.class)
public class CoordinationTest {
    /**
     * Rigorous Test.
     */
    //Coordination test
    
    @Test
    void firstInvalidCoordination() {
        Coordination coordination = new Coordination(Map.getRow() + 1, 5);
        assertFalse(coordination.isValidCoordination());
    }

    @Test 
    void secondInvalidCoordination() {
        Coordination coordination = new Coordination(-1, 5);
        assertFalse(coordination.isValidCoordination());
    }

    @Test
    void thirdInvalidCoordination() {
        Coordination coordination = new Coordination(5, Map.getColumn() + 1);
        assertFalse(coordination.isValidCoordination());
    }

    @Test
    void fourthInvalidCoordination() {
        Coordination coordination = new Coordination(5, -100);
        assertFalse(coordination.isValidCoordination());
    }

    @Test
    void fifthInvaliidCoordination() {
        Coordination coordination = new Coordination(-1, -6);
        assertFalse(coordination.isValidCoordination());
    }

    @Test
    void sixthInvalidCoordination() {
        Coordination coordination = new Coordination(Map.getRow(), Map.getColumn());
        assertFalse(coordination.isValidCoordination());
    }

    @Test
    void seventhInvalidCoordination() {
        Coordination coordination = new Coordination(Map.getRow() + 10, -10);
        assertFalse(coordination.isValidCoordination());
    }

    @Test
    void eightthInvalidCoordination() {
        Coordination coordination = new Coordination(-10, Map.getColumn() + 100);
        assertFalse(coordination.isValidCoordination());
    }

    @Test
    void firstValidCoordination() {
        Coordination coordination = new Coordination(10, 20);
        assertTrue(coordination.isValidCoordination());
    }

    @Test
    void secondValidCoordination() {
        Coordination coordination = new Coordination(2, 8);
        assertTrue(coordination.isValidCoordination());
    }
}
