package com.kpi.ipt.crypto;

import com.kpi.ipt.crypto.service.ArrayMapper;
import com.kpi.ipt.crypto.service.CSVReader;
import com.kpi.ipt.crypto.service.Calculator;
import com.kpi.ipt.crypto.service.impl.CSVReaderImpl;
import com.kpi.ipt.crypto.service.impl.CalculatorImpl;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class Main {

    private static final int MESSAGE_DISTRIBUTION_ROW_INDEX = 0;
    private static final int CIPHERTEXT_DISTRIBUTION_ROW_INDEX = 1;

    private static final int CIPHERTEXT_POWER = 20;

    private static Calculator calculator = new CalculatorImpl();
    private static CSVReader csvReader = new CSVReaderImpl();

    public static void main(String[] args) {
        List<CSVRecord> messageAndKeyDistributions = csvReader.read("prob_02.csv");
        double[] messageDistribution = ArrayMapper.mapToDouble(
                messageAndKeyDistributions.get(MESSAGE_DISTRIBUTION_ROW_INDEX));
        double[] keyDistribution = ArrayMapper.mapToDouble(
                messageAndKeyDistributions.get(CIPHERTEXT_DISTRIBUTION_ROW_INDEX));

        int[][] cipherMatrix = ArrayMapper.mapToMatrix(csvReader.read("table_02.csv"));

        double[] ciphertextDistribution = new double[CIPHERTEXT_POWER];


        /*for (int cipher = 0; cipher < CIPHERTEXT_POWER; cipher++) {
            for (int key = 0; key < CIPHERTEXT_POWER; key++) {
                for (int message = 0; message < CIPHERTEXT_POWER; message++) {
                    if (cipherMatrix[key][message] == cipher) {
                        ciphertextDistribution[cipher] += messageDistribution[message] * keyDistribution[key];
                    }
                }
            }
        }*/

        for (int i = 0; i < cipherMatrix.length; i++) {
            for (int j = 0; j < cipherMatrix[i].length; j++) {
                int ciphertextNumber = cipherMatrix[i][j];
                ciphertextDistribution[ciphertextNumber] += messageDistribution[j] * keyDistribution[i];
            }
        }

        double[][] messageAndCiphertextDistribution = new double[CIPHERTEXT_POWER][CIPHERTEXT_POWER];
        for (int i = 0; i < CIPHERTEXT_POWER; i++) {
            messageAndCiphertextDistribution[i] = new double[CIPHERTEXT_POWER];
        }

        /*for (int cipher = 0; cipher < CIPHERTEXT_POWER; cipher++) {
            for (int message = 0; message < CIPHERTEXT_POWER; message++) {
                if (cipher == cipherMatrix[message][cipher]) {
                    messageAndCiphertextDistribution[message][cipher] += messageDistribution[message] * keyDistribution[key];
                }
            }
        }*/

        for (int i = 0; i < messageAndCiphertextDistribution.length; i++) {
            for (int j = 0; j < messageAndCiphertextDistribution[i].length; j++) {
                for (int cipher = 0; cipher < CIPHERTEXT_POWER; cipher++) {
                    if (cipher == cipherMatrix[i][j]) {
                        messageAndCiphertextDistribution[j][i] += messageDistribution[j] * keyDistribution[i];
                    }
                }
            }
        }

        double[][] messageConditionalCiphertextDistribution = new double[CIPHERTEXT_POWER][CIPHERTEXT_POWER];
        for (int i = 0; i < CIPHERTEXT_POWER; i++) {
            messageConditionalCiphertextDistribution[i] = new double[CIPHERTEXT_POWER];
        }

        for (int i = 0; i < CIPHERTEXT_POWER; i++) {
            for (int j = 0; j < CIPHERTEXT_POWER; j++) {
                messageConditionalCiphertextDistribution[i][j] = messageAndCiphertextDistribution[i][j] / ciphertextDistribution[j];
            }
        }

    }
}
