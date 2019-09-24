package com.kpi.ipt.crypto.utils;

public final class DistributionCalculator {

    private static final int CIPHERTEXT_POWER = 20;

    private DistributionCalculator() {}

    public static double[][] calculateMessageConditionalByCipherDistribution(double[] ciphertextDistribution, double[][] messageAndCiphertextDistribution) {
        double[][] messageConditionalCiphertextDistribution = new double[CIPHERTEXT_POWER][CIPHERTEXT_POWER];
        for (int i = 0; i < CIPHERTEXT_POWER; i++) {
            messageConditionalCiphertextDistribution[i] = new double[CIPHERTEXT_POWER];
        }


        for (int message = 0; message < messageAndCiphertextDistribution.length; message++) {
            for (int cipher = 0; cipher < CIPHERTEXT_POWER; cipher++) {
                messageConditionalCiphertextDistribution[cipher][message] =
                        messageAndCiphertextDistribution[cipher][message] /
                                ciphertextDistribution[cipher];
            }
        }

        return messageConditionalCiphertextDistribution;
    }

    public static double[] calculateCipherDistribution(
            double[] messageDistribution,
            double[] keyDistribution,
            int[][] cipherMatrix) {
        double[] ciphertextDistribution = new double[CIPHERTEXT_POWER];

        for (int cipher = 0; cipher < CIPHERTEXT_POWER; cipher++) {

            for (int key = 0; key < keyDistribution.length; key++) {
                for (int message = 0; message < messageDistribution.length; message++) {
                    if (cipherMatrix[key][message] == cipher) {
                        ciphertextDistribution[cipher] += messageDistribution[message] * keyDistribution[key];
                    }
                }
            }

        }

        return ciphertextDistribution;
    }

    public static double[][] calculateMessageAndCipherDistribution(
            double[] messageDistribution,
            double[] keyDistribution,
            int[][] cipherMatrix) {
        double[][] messageAndCiphertextDistribution = new double[CIPHERTEXT_POWER][messageDistribution.length];
        for (int i = 0; i < CIPHERTEXT_POWER; i++) {
            messageAndCiphertextDistribution[i] = new double[messageDistribution.length];
        }


        for (int cipher = 0; cipher < CIPHERTEXT_POWER; cipher++) {
            for (int message = 0; message < messageDistribution.length; message++) {

                for (int key = 0; key < keyDistribution.length; key++) {
                    if (cipherMatrix[key][message] == cipher) {
                        messageAndCiphertextDistribution[cipher][message] += messageDistribution[message] * keyDistribution[cipher];
                    }
                }

            }
        }

        return messageAndCiphertextDistribution;
    }

}
