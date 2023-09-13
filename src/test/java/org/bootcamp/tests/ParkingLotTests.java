package org.bootcamp.tests;

import org.bootcamp.ParkingLot;
import org.bootcamp.ParkingLotException;
import org.bootcamp.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ParkingLotTests {

    @Test
    public void shouldBeAbleToParkTheCar(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        Assertions.assertDoesNotThrow(() -> parkingLot.park(car));
    }

    @Test
    public void shouldNotBeAbleToParkTheSameCarAgain() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        parkingLot.park(car);

        Assertions.assertThrows(ParkingLotException.class ,() -> parkingLot.park(car));
    }

    @Test
    public void shouldAbleToParkMultipleCar() {
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();

        Assertions.assertDoesNotThrow(() -> parkingLot.park(car1));
        Assertions.assertDoesNotThrow(() -> parkingLot.park(car2));
    }

    @Test
    public void shouldNotBeAbleToParkCarIfParkingIsFull() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        parkingLot.park(car1);
        parkingLot.park(car2);

        Assertions.assertThrows(ParkingLotException.class ,() -> parkingLot.park(car3));
    }

    @Test
    public void shouldCheckIfMyCarIsParked() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        parkingLot.park(car);

        int index = parkingLot.isMyCarParked(car);

        Assertions.assertNotEquals(-1, index);
    }

    @Test
    public void shouldBeAbleToUnparkTheCar() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        parkingLot.park(car);

        Assertions.assertDoesNotThrow(() -> parkingLot.unpark(car));
    }

    @Test
    public void shouldNotBeInParkingLotAfterUnpark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        parkingLot.park(car);

        parkingLot.unpark(car);


        int index = parkingLot.isMyCarParked(car);

        Assertions.assertEquals(-1, index);
    }

    @Test
    public void unparkTheCarWhichIsNotInParkingLotThrowsException() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.unpark(car));
    }

}