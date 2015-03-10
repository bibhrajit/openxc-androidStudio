package com.openxc.measurements;

import com.openxc.units.Rando;
import com.openxc.util.Range;


public class YawRate extends BaseMeasurement<Rando> {
    private final static Range<Rando> RANGE = new Range<Rando>(
            new Rando(-360.0), new Rando(360.0));
    public final static String ID = "veh_yaw_rate";

    public YawRate(Rando value) {
        super(value, RANGE);
    }

    public YawRate(Number value) {
        this(new Rando(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
