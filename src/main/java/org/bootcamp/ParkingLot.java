package org.bootcamp;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;

public class ParkingLot  {
    private ArrayList<Vehicle> parkingSpots;

    private PropertyChangeSupport support;
    private ParkingLotStatus parkingSpotStatus;
    private static final int PARKING_SIZE = 2;

    public ParkingLot() {
        parkingSpots  = new ArrayList<>(Collections.nCopies(PARKING_SIZE, null));
        parkingSpotStatus = ParkingLotStatus.NOT_FULL;
        support = new PropertyChangeSupport(this);
    }



    private int findAvailableSpot(ArrayList<Vehicle> parkingSpots) {
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (parkingSpots.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private void park(ArrayList<Vehicle> parkingSpots, int availableSpot, Vehicle vehicle) throws ParkingLotException {
        if(isMyCarParked(vehicle)!=-1)
            throw new ParkingLotException("Cannot park the same car again");
        parkingSpots.set(availableSpot, vehicle);
    }

    public void park(Vehicle vehicle) throws ParkingLotException {
        int availableSpot = findAvailableSpot(this.parkingSpots);
        if (availableSpot==-1)
            throw new ParkingLotException("No parking spot available");
        park(parkingSpots, availableSpot, vehicle);

        availableSpot = findAvailableSpot(this.parkingSpots);
        if (availableSpot==-1)
            setParkingLotStatus(ParkingLotStatus.FULL);
    }

    public int isMyCarParked(Vehicle vehicle){
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (parkingSpots.get(i) != null && parkingSpots.get(i).equals(vehicle)) {
                return i;
            }
        }
        return -1;
    }

    public void unpark(Vehicle vehicle) throws ParkingLotException {
        int spot = isMyCarParked(vehicle);
        if(spot==-1)
            throw new ParkingLotException("Car not available to unpark from parking lot");
        parkingSpots.set(spot, null);
        if(parkingSpotStatus == ParkingLotStatus.FULL)
            setParkingLotStatus(ParkingLotStatus.NOT_FULL);
    }

    public void setParkingLotStatus(ParkingLotStatus value) {
        support.firePropertyChange("parkingSpotStatus", this.parkingSpotStatus, value);
        this.parkingSpotStatus = value;
    }
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

}