package org.example;

import java.util.Arrays;

public class ParkingLot {
    private static ParkingLot parkingLotInstance;
    private static Boolean[] parkingSpots;
    private static final int PARKING_SIZE = 2;

    private ParkingLot() {
        parkingSpots = new Boolean[PARKING_SIZE];
        Arrays.fill(parkingSpots, Boolean.FALSE);
    }

    public static synchronized ParkingLot getInstance() {
        if (parkingLotInstance == null) parkingLotInstance = new ParkingLot();
        return parkingLotInstance;
    }

    private int findAvailableSpot(Boolean[] parkingSpots) {
        for (int i = 0; i < parkingSpots.length; i++) {
            if (!parkingSpots[i]) {
                return i;
            }
        }
        return -1;
    }

    private void parkCar(Boolean[] parkingSpots, int spotIndex) {
        parkingSpots[spotIndex] = true;
    }

    public boolean parkCar() {
        int availableSpot = findAvailableSpot(this.parkingSpots);
        if (availableSpot != -1) {
            parkCar(parkingSpots, availableSpot);
            return true;
        } else {
            return false;
        }
    }
}