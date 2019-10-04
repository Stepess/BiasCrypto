package com.kpi.ipt.crypto.model;

import java.util.Arrays;

public class DeterminedDecisionFunction {

    private int[] ddf;

    public DeterminedDecisionFunction(int[] ddf) {
        this.ddf = Arrays.copyOf(ddf, ddf.length);
    }

    public int[] getDdf() {
        return Arrays.copyOf(ddf, ddf.length);
    }

    public int decide(int ciphertext) {
        return ddf[ciphertext];
    }

    @Override
    public String toString() {
        return "DeterminedDecisionFunction{" +
                "ddf=" + Arrays.toString(ddf) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeterminedDecisionFunction that = (DeterminedDecisionFunction) o;
        return Arrays.equals(ddf, that.ddf);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ddf);
    }
}
