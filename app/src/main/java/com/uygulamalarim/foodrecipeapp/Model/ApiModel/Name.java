package com.uygulamalarim.foodrecipeapp.Model.ApiModel;

import java.io.IOException;

public enum Name {
    EMPTY, FOR_THE_CHICKEN_WING_DRUMMETTES, SERVE_HOT_OR_WARM_TO_PREPARE_RANCH_Dip;

    public String toValue() {
        switch (this) {
            case EMPTY:
                return "";
            case FOR_THE_CHICKEN_WING_DRUMMETTES:
                return "For the Chicken Wing Drummettes";
            case SERVE_HOT_OR_WARM_TO_PREPARE_RANCH_Dip:
                return "Serve hot or warm.To prepare \"Ranch\" Dip";
        }
        return null;
    }

    public static Name forValue(String value) throws IOException {
        if (value.equals("")) return EMPTY;
        if (value.equals("For the Chicken Wing Drummettes")) return FOR_THE_CHICKEN_WING_DRUMMETTES;
        if (value.equals("Serve hot or warm.To prepare \"Ranch\" Dip"))
            return SERVE_HOT_OR_WARM_TO_PREPARE_RANCH_Dip;
        throw new IOException("Cannot deserialize Name");
    }
}
