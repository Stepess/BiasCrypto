package com.kpi.ipt.crypto.service.impl;

import com.kpi.ipt.crypto.model.DeterminedDecisionFunction;
import com.kpi.ipt.crypto.service.Cryptoanalics;

import static com.kpi.ipt.crypto.constant.Constants.CIPHERTEXT_POWER;

public class CryptoanalicsImpl implements Cryptoanalics {

    @Override
    public DeterminedDecisionFunction calculateDDF(double[][] messageConditionalByCipherDistribution) {
        int[] determinedDecisionFunction = new int[CIPHERTEXT_POWER];

        for (int cipher = 0; cipher < CIPHERTEXT_POWER; cipher++) {
            double max = 0;
            int index = 0;

            for (int message = 0; message < messageConditionalByCipherDistribution[0].length; message++) {
                if (messageConditionalByCipherDistribution[cipher][message] > max) {
                    max = messageConditionalByCipherDistribution[cipher][message];
                    index = message;
                }
            }

            determinedDecisionFunction[cipher] = index;
        }

        return new DeterminedDecisionFunction(determinedDecisionFunction);
    }

}
