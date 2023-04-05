package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

import java.util.List;

public class AnalyzedInstruction {
    private String name;
    private List<Step> steps;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> value) {
        this.steps = value;
    }
}
