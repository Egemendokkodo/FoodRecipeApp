package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

import java.io.IOException;

public enum Unit {
    EMPTY, G, IU, KCAL, MG, UNIT, UNIT_G;

    public String toValue() {
        switch (this) {
            case EMPTY:
                return "";
            case G:
                return "g";
            case IU:
                return "IU";
            case KCAL:
                return "kcal";
            case MG:
                return "mg";
            case UNIT:
                return "%";
            case UNIT_G:
                return "\u00b5g";
        }
        return null;
    }

    public static Unit forValue(String value) throws IOException {
        if (value.equals("")) return EMPTY;
        if (value.equals("g")) return G;
        if (value.equals("IU")) return IU;
        if (value.equals("kcal")) return KCAL;
        if (value.equals("mg")) return MG;
        if (value.equals("%")) return UNIT;
        if (value.equals("\u00b5g")) return UNIT_G;
        throw new IOException("Cannot deserialize Unit");
    }
}
