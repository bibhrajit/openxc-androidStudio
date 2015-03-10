package com.openxc.measurements;

/**
 * Created by bhalder on 2/19/2015.
 */

import com.openxc.units.Rando;
import com.openxc.util.Range;

public class ACIndicator extends BaseMeasurement<Rando> {
    public final static String ID = "ac_indicator";

    public ACIndicator(Rando value) {
        super(value);
    }

    public ACIndicator(Number value) {
        this(new Rando(value));
    }
    @Override
    public String getGenericName() {
        return ID;
    }

}
