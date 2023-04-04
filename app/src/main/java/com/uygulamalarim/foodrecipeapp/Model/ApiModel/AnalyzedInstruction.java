package com.uygulamalarim.foodrecipeapp.Model.ApiModel;

import java.util.List;

public class AnalyzedInstruction {
    private Name name;
    private List<Step> steps;

    public Name getName() {
        return name;
    }

    public void setName(Name value) {
        this.name = value;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> value) {
        this.steps = value;
    }
}
