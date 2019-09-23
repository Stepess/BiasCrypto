package com.kpi.ipt.crypto;

import com.kpi.ipt.crypto.service.ArrayMapper;
import com.kpi.ipt.crypto.service.CSVReader;
import com.kpi.ipt.crypto.service.Calculator;
import com.kpi.ipt.crypto.service.impl.CSVReaderImpl;
import com.kpi.ipt.crypto.service.impl.CalculatorImpl;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
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


        for (int i = 0; i < cipherMatrix.length; i++) {
            for (int j = 0; j < cipherMatrix[i].length; j++) {
                int ciphertextNumber = cipherMatrix[i][j];
                ciphertextDistribution[ciphertextNumber] += messageDistribution[j] * keyDistribution[i];
            }
        }


    }
}
