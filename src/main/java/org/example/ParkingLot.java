package org.example;


public class ParkingLot {
    private static ParkingLot parkingLotInstance;
    private static Boolean[] parkingSpots = {false};

    private ParkingLot() {
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
