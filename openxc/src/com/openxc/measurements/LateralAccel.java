package com.openxc.measurements;

import com.openxc.units.Rando;
import com.openxc.util.Range;


public class LateralAccel extends BaseMeasurement<Rando> {
    private final static Range<Rando> RANGE = new Range<Rando>(
            new Rando(-30.0), new Rando(30.0));
    public final static String ID = "veh_lat_acc";

    public LateralAccel(Rando value) {
        super(value, RANGE);
    }

    public LateralAccel(Number value) {
        this(new Rando(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
