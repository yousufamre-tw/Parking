package org.bootcamp.tests;

import org.example.ParkingLot;
import org.example.ParkingLotException;
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
    public void shouldBeAbleToParkTheCar(){
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle = new Vehicle();
        Assertions.assertDoesNotThrow(() -> parkingLot.park(vehicle));
    }

    @Test
    public void shouldAbleToParkMultipleCar() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle1 = new Vehicle();
        Assertions.assertDoesNotThrow(() -> parkingLot.park(vehicle1));
        Vehicle vehicle2 = new Vehicle();
        Assertions.assertDoesNotThrow(() -> parkingLot.park(vehicle2));
    }

    @Test
    public void shouldNotBeAbleToParkCarIfParkingIsFull() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle1 = new Vehicle();
        Assertions.assertDoesNotThrow(() -> parkingLot.park(vehicle1));

        Vehicle vehicle2 = new Vehicle();
        Assertions.assertDoesNotThrow(() -> parkingLot.park(vehicle2));

        Vehicle vehicle3 = new Vehicle();
        Assertions.assertThrows(ParkingLotException.class ,() -> parkingLot.park(vehicle3));
    }

    @Test
    public void shouldCheckIfMyCarIsParked() throws ParkingLotException {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle = new Vehicle();

        parkingLot.park(vehicle);

        int index = parkingLot.isMyCarParked(vehicle);

        Assertions.assertNotEquals(-1, index);
    }

    @Test
    public void shouldBeAbleToUnparkTheCar() throws ParkingLotException {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle = new Vehicle();

        parkingLot.park(vehicle);

        Assertions.assertDoesNotThrow(() -> parkingLot.unpark(vehicle));
    }

    @Test
    public void shouldNotBeInParkingLotAfterUnpark() throws ParkingLotException {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle = new Vehicle();

        parkingLot.park(vehicle);

        parkingLot.unpark(vehicle);


        int index = parkingLot.isMyCarParked(vehicle);

        Assertions.assertEquals(-1, index);
    }

    @Test
    public void unparkTheCarWhichIsNotInParkingLotThrowsException() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Vehicle vehicle = new Vehicle();

        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.unpark(vehicle));
    }

}