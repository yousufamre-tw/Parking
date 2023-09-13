package org.bootcamp.tests;

import org.example.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTests {

    @Test
    public void shouldBeAbleToParkTheCar(){
        ParkingLot parkingLot = new ParkingLot();

        boolean isParked = parkingLot.park();

        Assertions.assertTrue(isParked);
    }

}
