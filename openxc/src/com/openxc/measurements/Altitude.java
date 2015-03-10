package com.openxc.measurements;

import com.openxc.units.Rando;
import com.openxc.util.Range;


public class Altitude extends BaseMeasurement<Rando> {
    private final static Range<Rando> RANGE = new Range<Rando>(
            new Rando(-20460.0), new Rando(20460.0));
    public final static String ID = "altitude";

    public Altitude(Rando value) {
        super(value, RANGE);
    }

    public Altitude(Number value) {
        this(new Rando(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
