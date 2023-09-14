package org.bootcamp.tests;

import org.bootcamp.ParkingLot;
import org.bootcamp.ParkingLotException;
import org.bootcamp.Valet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValetTests {
    @Test
    public void shouldBeAbleToParkTheCarInFirstAvailableParkingLot(){

        Valet valet = new Valet();
        Car car = new Car();

        Assertions.assertDoesNotThrow(() -> valet.park(car));
    }

    @Test
    public void shouldThrowExceptionIfNoneOFTheParkingLotIsAvailable() throws ParkingLotException {

        Valet valet = new Valet();
        for(int carCounter=0; carCounter<6; carCounter++){
            valet.park(new Car());
        }

        Assertions.assertThrows(ParkingLotException.class, ()->valet.park(new Car()));
    }

    @Test
    public void shouldBeAbleToUnParkTheCarFromParkingLot() throws ParkingLotException {

        Valet valet = new Valet();
        Car car = new Car();
        valet.park(car);

        Assertions.assertDoesNotThrow(() -> valet.unpark(car));
    }

    @Test
    public void shouldThrowExceptionIfCarIsNotInParkingLotToUnpark() {

        Valet valet = new Valet();
        Car car = new Car();

        Assertions.assertThrows(ParkingLotException.class, () -> valet.unpark(car));
    }
}
