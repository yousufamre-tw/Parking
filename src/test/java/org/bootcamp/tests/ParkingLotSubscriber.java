package org.bootcamp.tests;

import org.bootcamp.ParkingLotStatus;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ParkingLotSubscriber implements PropertyChangeListener {

    public ParkingLotStatus parkingLotStatus;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        parkingLotStatus = (ParkingLotStatus)evt.getNewValue();
    }
}
