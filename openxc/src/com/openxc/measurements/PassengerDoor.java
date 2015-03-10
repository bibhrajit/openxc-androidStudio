package com.openxc.measurements;

import com.openxc.units.Boolean;

/**
 * The ParkingBrakeStatus measurement knows if the parking brake is engaged or not.
 */
public class PassengerDoor extends BaseMeasurement<Boolean> {
    public final static String ID = "passenger_door";

    public PassengerDoor(Boolean value) {
        super(value);
    }

    public PassengerDoor(java.lang.Boolean value) {
        this(new Boolean(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
