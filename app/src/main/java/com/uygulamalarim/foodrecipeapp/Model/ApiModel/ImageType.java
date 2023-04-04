package com.uygulamalarim.foodrecipeapp.Model.ApiModel;

import java.io.IOException;

public enum ImageType {
    JPG;

    public String toValue() {
        switch (this) {
            case JPG:
                return "jpg";
        }
        return null;
    }

    public static ImageType forValue(String value) throws IOException {
        if (value.equals("jpg")) return JPG;
        throw new IOException("Cannot deserialize ImageType");
    }
}
