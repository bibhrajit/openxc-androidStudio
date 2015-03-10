package com.openxc.measurements;

import com.openxc.units.Rando;
import com.openxc.util.Range;


public class LongAccel extends BaseMeasurement<Rando> {
    private final static Range<Rando> RANGE = new Range<Rando>(
            new Rando(-30.0), new Rando(30.0));
    public final static String ID = "veh_long_acc";

    public LongAccel(Rando value) {
        super(value, RANGE);
    }

    public LongAccel(Number value) {
        this(new Rando(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
