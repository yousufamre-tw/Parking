package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class ParkingLot {
    private static ParkingLot parkingLotInstance;
    private static ArrayList<Vehicle> parkingSpots;
    private static final int PARKING_SIZE = 2;

    private ParkingLot() {
        parkingSpots  = new ArrayList<Vehicle>(Collections.nCopies(PARKING_SIZE, null));
    }

    public static synchronized ParkingLot getInstance() {
        if (parkingLotInstance == null) parkingLotInstance = new ParkingLot();
        return parkingLotInstance;
    }

    private int findAvailableSpot(ArrayList<Vehicle> parkingSpots) {
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (parkingSpots.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private void park(ArrayList<Vehicle> parkingSpots, int availableSpot,Vehicle vehicle) {
        parkingSpots.set(availableSpot, vehicle);
    }

    public boolean park(Vehicle vehicle) {
        int availableSpot = findAvailableSpot(this.parkingSpots);
        if (availableSpot!=-1) {
            park(parkingSpots, availableSpot, vehicle);
            return true;
        } else {
            return false;
        }
    }
}