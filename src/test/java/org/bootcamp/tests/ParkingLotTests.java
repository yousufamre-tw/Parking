package org.bootcamp.tests;

import org.example.ParkingLot;
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
        boolean isParked = parkingLot.parkCar();
        Assertions.assertTrue(isParked);
    }

    @Test
    public void shouldAbleToParkMultipleCar() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        boolean isParked = parkingLot.parkCar();
        Assertions.assertTrue(isParked);
        boolean isParked2 = parkingLot.parkCar();
        Assertions.assertTrue(isParked2);
    }

    @Test
    public void shouldNotBeAbleToParkCarIfParkingIsFull() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        boolean isParked = parkingLot.parkCar();
        Assertions.assertTrue(isParked);
        boolean isParked2 = parkingLot.parkCar();
        Assertions.assertTrue(isParked2);
        boolean isParked3 = parkingLot.parkCar();
        Assertions.assertFalse(isParked3);
    }
}