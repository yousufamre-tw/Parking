package org.example;


public class ParkingLot {
    Boolean[] parkingSpots =  { false, true, true, false };


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

   public boolean park(){
       int availableSpot = findAvailableSpot(this.parkingSpots);
       if (availableSpot != -1) {
           parkCar(parkingSpots, availableSpot);
           return true;
       } else {
           return false;
       }
   }




}
