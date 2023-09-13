package org.bootcamp.tests;

import org.example.ParkingLot;
import org.example.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ParkingLotTests {
    @BeforeEach
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field parkingSpots = ParkingLot.class.getDeclaredField("parkingSpots");
        parkingSpots.setAccessible(true);
        parkingSpots.set(null, null);
        Field parkingLotInstance = ParkingLot.class.getDeclaredField("parkingLotInstance");
        parkingLotInstance.setAccessible(true);
        parkingLotInstance.set(null, null);
    }

    @Test
    public void shouldBeAbleToParkTheCar() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle = new Vehicle();
        boolean isParked = parkingLot.park(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void shouldAbleToParkMultipleCar() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle1 = new Vehicle();
        boolean isParked = parkingLot.park(vehicle1);
        Assertions.assertTrue(isParked);
        Vehicle vehicle2 = new Vehicle();
        boolean isParked2 = parkingLot.park(vehicle2);
        Assertions.assertTrue(isParked2);
    }

    @Test
    public void shouldNotBeAbleToParkCarIfParkingIsFull() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle1 = new Vehicle();
        boolean isParked = parkingLot.park(vehicle1);
        Assertions.assertTrue(isParked);

        Vehicle vehicle2 = new Vehicle();
        boolean isParked2 = parkingLot.park(vehicle2);
        Assertions.assertTrue(isParked2);

        Vehicle vehicle3 = new Vehicle();
        boolean isParked3 = parkingLot.park(vehicle3);
        Assertions.assertFalse(isParked3);
    }
}