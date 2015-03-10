package com.openxc.measurements;

import com.openxc.units.Boolean;

/**
 * The ParkingBrakeStatus measurement knows if the parking brake is engaged or not.
 */
public class RearDriverDoor extends BaseMeasurement<Boolean> {
    public final static String ID = "rear_left_door";

    public RearDriverDoor(Boolean value) {
        super(value);
    }

    public RearDriverDoor(java.lang.Boolean value) {
        this(new Boolean(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
