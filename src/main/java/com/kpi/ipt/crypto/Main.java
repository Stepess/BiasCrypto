package com.kpi.ipt.crypto;

import com.kpi.ipt.crypto.utils.ArrayMapper;
import com.kpi.ipt.crypto.service.CSVReader;
import com.kpi.ipt.crypto.service.impl.CSVReaderImpl;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

import static com.kpi.ipt.crypto.utils.DistributionCalculator.*;

public class Main {

    private static final int MESSAGE_DISTRIBUTION_ROW_INDEX = 0;
    private static final int CIPHERTEXT_DISTRIBUTION_ROW_INDEX = 1;

    private static CSVReader csvReader = new CSVReaderImpl();

    public static void main(String[] args) {
        List<CSVRecord> messageAndKeyDistributions = csvReader.read("prob_02.csv");
        double[] messageDistribution = ArrayMapper.mapToDouble(
                messageAndKeyDistributions.get(MESSAGE_DISTRIBUTION_ROW_INDEX));
        double[] keyDistribution = ArrayMapper.mapToDouble(
                messageAndKeyDistributions.get(CIPHERTEXT_DISTRIBUTION_ROW_INDEX));

        int[][] cipherMatrix = ArrayMapper.mapToMatrix(csvReader.read("table_02.csv"));

        double[] ciphertextDistribution =
                calculateCipherDistribution(messageDistribution, keyDistribution, cipherMatrix);

        double[][] messageAndCiphertextDistribution =
                calculateMessageAndCipherDistribution(messageDistribution, keyDistribution, cipherMatrix);

        double[][] messageConditionalByCipherDistribution =
                calculateMessageConditionalByCipherDistribution(ciphertextDistribution, messageAndCiphertextDistribution);

    }
}
