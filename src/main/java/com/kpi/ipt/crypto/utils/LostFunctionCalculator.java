package com.kpi.ipt.crypto.utils;

import com.kpi.ipt.crypto.model.DeterminedDecisionFunction;

public final class LostFunctionCalculator {

    private LostFunctionCalculator() {
    }

    public static double calculateALF(DeterminedDecisionFunction ddf, double[][] messageAndCiphertextDistribution) {
        double lostFunctionAverage = 0;

        for (int message = 0; message < messageAndCiphertextDistribution[0].length; message++) {
            for (int cipher = 0; cipher < messageAndCiphertextDistribution.length; cipher++) {
                if (ddf.decide(cipher) != message) {
                    lostFunctionAverage += messageAndCiphertextDistribution[cipher][message];
                }
            }
        }

        return lostFunctionAverage;
    }

}
