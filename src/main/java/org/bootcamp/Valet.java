package org.bootcamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Valet {
    private List<ParkingLot> parkingLots;

    public Valet() {
        parkingLots = new ArrayList<ParkingLot>(3);
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
    }

    public void park(Vehicle vehicle) throws ParkingLotException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getParkingStatus() == ParkingLotStatus.NOT_FULL) {
                parkingLot.park(vehicle);
                return;
            }
        }
        throw new ParkingLotException("All Parking Lots are Full");
    }


    public void unpark(Vehicle vehicle) throws ParkingLotException {
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.isMyCarParked(vehicle)!=-1){
                parkingLot.unpark(vehicle);
                return;
            }
        }
        throw new ParkingLotException("Car not found in any parking lot");
    }

}
