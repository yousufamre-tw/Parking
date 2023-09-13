package org.bootcamp.tests;

import org.example.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTests {
    @Test
    public void shouldBeAbleToParkTheCar() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        boolean isParked = parkingLot.parkCar();
        Assertions.assertTrue(isParked);
    }

    @Test
    public void shouldNotAbleToParkTheCar() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        boolean isParked = parkingLot.parkCar();
        Assertions.assertFalse(isParked);
    }
}