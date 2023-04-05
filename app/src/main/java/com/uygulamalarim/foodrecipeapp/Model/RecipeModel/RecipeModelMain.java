// RecipeModelMain.java

//YApi QuickType插件生成，具体参考文档:https://github.com/RmondJone/YapiQuickType

package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

import java.util.List;

public class RecipeModelMain {
    private long number;
    private long totalResults;
    private long offset;
    private List<Result> results;

    public long getNumber() { return number; }
    public void setNumber(long value) { this.number = value; }

    public long getTotalResults() { return totalResults; }
    public void setTotalResults(long value) { this.totalResults = value; }

    public long getOffset() { return offset; }
    public void setOffset(long value) { this.offset = value; }

    public List<Result> getResults() { return results; }
    public void setResults(List<Result> value) { this.results = value; }
}


