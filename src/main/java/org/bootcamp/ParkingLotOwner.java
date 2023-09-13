package org.bootcamp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ParkingLotOwner implements PropertyChangeListener {

    public ParkingLotStatus parkingLotStatusForOwner;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        parkingLotStatusForOwner = (ParkingLotStatus)evt.getNewValue();
    }
}
