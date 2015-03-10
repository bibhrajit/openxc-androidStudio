package com.openxc.measurements;

import com.openxc.units.Boolean;

/**
 * The ParkingBrakeStatus measurement knows if the parking brake is engaged or not.
 */
public class RearPassengerDoor extends BaseMeasurement<Boolean> {
    public final static String ID = "rear_right_door";

    public RearPassengerDoor(Boolean value) {
        super(value);
    }

    public RearPassengerDoor(java.lang.Boolean value) {
        this(new Boolean(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
