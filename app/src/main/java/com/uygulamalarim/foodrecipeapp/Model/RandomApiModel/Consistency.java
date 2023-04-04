package com.uygulamalarim.foodrecipeapp.Model.RandomApiModel;

import java.io.IOException;

public enum Consistency {
    LIQUID, SOLID;

    public String toValue() {
        switch (this) {
            case LIQUID:
                return "LIQUID";
            case SOLID:
                return "SOLID";
        }
        return null;
    }

    public static Consistency forValue(String value) throws IOException {
        if (value.equals("LIQUID")) return LIQUID;
        if (value.equals("SOLID")) return SOLID;
        throw new IOException("Cannot deserialize Consistency");
    }
}
