package com.openxc.measurements;

import com.openxc.units.Rando;
import com.openxc.util.Range;


public class WheelSpeedFR extends BaseMeasurement<Rando> {
    private final static Range<Rando> RANGE = new Range<Rando>(
            new Rando(-100.0), new Rando(300.0));
    public final static String ID = "whl_spd_frnt_r";

    public WheelSpeedFR(Rando value) {
        super(value, RANGE);
    }

    public WheelSpeedFR(Number value) {
        this(new Rando(value));
    }

    @Override
    public String getGenericName() {
        return ID;
    }
}
