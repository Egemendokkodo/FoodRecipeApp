package com.uygulamalarim.foodrecipeapp.Model;

import java.io.IOException;

public enum Gaps {
    NO;

    public String toValue() {
        switch (this) {
            case NO:
                return "no";
        }
        return null;
    }

    public static Gaps forValue(String value) throws IOException {
        if (value.equals("no")) return NO;
        throw new IOException("Cannot deserialize Gaps");
    }
}
