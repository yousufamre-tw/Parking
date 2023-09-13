package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class ParkingLot {
    private static ParkingLot parkingLotInstance;
    private static ArrayList<Object> parkingSpots;
    private static final int PARKING_SIZE = 2;

    private ParkingLot() {
        parkingSpots  = new ArrayList<>(Collections.nCopies(PARKING_SIZE, null));
    }

    public static synchronized ParkingLot getInstance() {
        if (parkingLotInstance == null) parkingLotInstance = new ParkingLot();
        return parkingLotInstance;
    }

    private int findAvailableSpot(ArrayList<Object> parkingSpots) {
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (parkingSpots.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private void park(ArrayList<Object> parkingSpots, int availableSpot,Object vehicle) throws ParkingLotException {
        if(isMyCarParked(vehicle)!=-1)
            throw new ParkingLotException("Cannot park the same car again");
        parkingSpots.set(availableSpot, vehicle);
    }

    public void park(Object vehicle) throws ParkingLotException {
        int availableSpot = findAvailableSpot(this.parkingSpots);
        if (availableSpot==-1)
            throw new ParkingLotException("No parking spot available");
        park(parkingSpots, availableSpot, vehicle);
    }

    public int isMyCarParked(Object vehicle){
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (parkingSpots.get(i) != null && parkingSpots.get(i).equals(vehicle)) {
                return i;
            }
        }
        return -1;
    }

    public void unpark(Object vehicle) throws ParkingLotException {
        int spot = isMyCarParked(vehicle);
        if(spot==-1)
            throw new ParkingLotException("Car not available to unpark from parking lot");
        parkingSpots.set(spot, null);
    }
}