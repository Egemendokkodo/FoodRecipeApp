
package com.uygulamalarim.foodrecipeapp.Model.ApiModel;

import java.util.List;

public class MainModel {
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



