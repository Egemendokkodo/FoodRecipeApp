package com.uygulamalarim.foodrecipeapp.Model;

import java.io.IOException;

public enum Unit {
    FAHRENHEIT, MINUTES;

    public String toValue() {
        switch (this) {
            case FAHRENHEIT:
                return "Fahrenheit";
            case MINUTES:
                return "minutes";
        }
        return null;
    }

    public static Unit forValue(String value) throws IOException {
        if (value.equals("Fahrenheit")) return FAHRENHEIT;
        if (value.equals("minutes")) return MINUTES;
        throw new IOException("Cannot deserialize Unit");
    }
}
