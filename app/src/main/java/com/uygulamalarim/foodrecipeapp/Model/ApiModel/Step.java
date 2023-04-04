package com.uygulamalarim.foodrecipeapp.Model.ApiModel;

import java.util.List;

public class Step {
    private long number;
    private List<Ent> ingredients;
    private List<Ent> equipment;
    private String step;
    private Length length;

    public long getNumber() {
        return number;
    }

    public void setNumber(long value) {
        this.number = value;
    }

    public List<Ent> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ent> value) {
        this.ingredients = value;
    }

    public List<Ent> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Ent> value) {
        this.equipment = value;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String value) {
        this.step = value;
    }

    public Length getLength() {
        return length;
    }

    public void setLength(Length value) {
        this.length = value;
    }
}
