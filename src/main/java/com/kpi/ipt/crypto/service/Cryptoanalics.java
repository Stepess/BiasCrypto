package com.kpi.ipt.crypto.service;

import com.kpi.ipt.crypto.model.DeterminedDecisionFunction;

public interface Cryptoanalics {

    DeterminedDecisionFunction calculateDDF(double[][] messageConditionalByCipherDistribution);

}
