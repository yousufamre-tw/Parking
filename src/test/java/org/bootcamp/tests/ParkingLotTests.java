package org.bootcamp.tests;

import org.bootcamp.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(-1, index);
    }

    @Test
    public void unparkTheCarWhichIsNotInParkingLotThrowsException() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.unpark(car));
    }

    @Test
    public void shouldNotifyTheOwnerAboutParkingStatusBeingFull() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotSubscriber observer = new ParkingLotSubscriber();

        parkingLot.addPropertyChangeListener(observer);

        Car car1 = new Car();
        Car car2 = new Car();

        parkingLot.park(car1);
        parkingLot.park(car2);

        assertEquals(observer.parkingLotStatus, ParkingLotStatus.FULL);
    }


    @Test
    public void shouldNotifyTheOwnerAndCopAboutParkingStatusBeingFull() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotSubscriber owner = new ParkingLotSubscriber();
        ParkingLotSubscriber trafficCop = new ParkingLotSubscriber();

        parkingLot.addPropertyChangeListener(owner);
        parkingLot.addPropertyChangeListener(trafficCop);

        Car car1 = new Car();
        Car car2 = new Car();

        parkingLot.park(car1);
        parkingLot.park(car2);

        assertEquals(owner.parkingLotStatus, ParkingLotStatus.FULL);
        assertEquals(trafficCop.parkingLotStatus, ParkingLotStatus.FULL);
    }

    @Test
    public void shouldNotifyCopAboutParkingStatusBeingNotFullAfterUnpark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotSubscriber trafficCop = new ParkingLotSubscriber();

        parkingLot.addPropertyChangeListener(trafficCop);
        Car car1 = new Car();
        Car car2 = new Car();

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.unpark(car1);
        assertEquals(trafficCop.parkingLotStatus, ParkingLotStatus.NOT_FULL);
    }

    @Test
    public void shouldNotifyOwnerAboutParkingStatusBeingNotFullAfterUnpark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotSubscriber owner = new ParkingLotSubscriber();

        parkingLot.addPropertyChangeListener(owner);
        Car car1 = new Car();
        Car car2 = new Car();

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.unpark(car1);
        assertEquals(owner.parkingLotStatus, ParkingLotStatus.NOT_FULL);
    }
    @Test
    public void shouldNotifyCopAboutParkingStatusBeingNotFullAfterUnparkAndIsReadyForRepark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotSubscriber trafficCop = new ParkingLotSubscriber();

        parkingLot.addPropertyChangeListener(trafficCop);
        Car car1 = new Car();
        Car car2 = new Car();

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.unpark(car1);

        Car car3 = new Car();
        Assertions.assertDoesNotThrow(()->parkingLot.park(car3));

        assertEquals(trafficCop.parkingLotStatus, ParkingLotStatus.FULL);
    }

}