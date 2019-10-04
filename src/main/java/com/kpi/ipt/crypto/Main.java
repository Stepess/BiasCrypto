package com.kpi.ipt.crypto;

import com.kpi.ipt.crypto.model.DeterminedDecisionFunction;
import com.kpi.ipt.crypto.service.CSVReader;
import com.kpi.ipt.crypto.service.Cryptoanalics;
import com.kpi.ipt.crypto.service.impl.CSVReaderImpl;
import com.kpi.ipt.crypto.service.impl.CryptoanalicsImpl;
import com.kpi.ipt.crypto.utils.ArrayMapper;
import com.kpi.ipt.crypto.utils.LostFunctionCalculator;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.List;

import static com.kpi.ipt.crypto.utils.DistributionCalculator.*;

public class Main {

    private static final int MESSAGE_DISTRIBUTION_ROW_INDEX = 0;
    private static final int CIPHERTEXT_DISTRIBUTION_ROW_INDEX = 1;

    private static CSVReader csvReader = new CSVReaderImpl();
    private static Cryptoanalics cryptoanalics = new CryptoanalicsImpl();

    public static void main(String[] args) {
        List<CSVRecord> messageAndKeyDistributions = csvReader.read("prob_02.csv");
        double[] messageDistribution = ArrayMapper.mapToDouble(
                messageAndKeyDistributions.get(MESSAGE_DISTRIBUTION_ROW_INDEX));

        System.out.println("Message distro: " + Arrays.toString( messageDistribution));
        double[] keyDistribution = ArrayMapper.mapToDouble(
                messageAndKeyDistributions.get(CIPHERTEXT_DISTRIBUTION_ROW_INDEX));

        System.out.println("Key distro: " + Arrays.toString( keyDistribution));
        int[][] cipherMatrix = ArrayMapper.mapToMatrix(csvReader.read("table_02.csv"));

        double[] ciphertextDistribution =
                calculateCipherDistribution(messageDistribution, keyDistribution, cipherMatrix);

        System.out.println("Cipher distro: " + Arrays.toString( ciphertextDistribution));
        double[][] messageAndCiphertextDistribution =
                calculateMessageAndCipherDistribution(messageDistribution, keyDistribution, cipherMatrix);

        System.out.println("Message and cipher distro: ");
        Arrays.stream(messageAndCiphertextDistribution)
                .map(Arrays::toString)
        .forEach(System.out::println);

        double[][] messageConditionalByCipherDistribution =
                calculateMessageConditionalByCipherDistribution(ciphertextDistribution, messageAndCiphertextDistribution);

        System.out.println("Conditional by cipher distro: ");
        Arrays.stream(messageConditionalByCipherDistribution)
                .map(Arrays::toString)
                .forEach(System.out::println);

        DeterminedDecisionFunction ddf =
                cryptoanalics.calculateDDF(messageConditionalByCipherDistribution);

        System.out.println(ddf);

        double lostFunctionAverage = LostFunctionCalculator.calculateALF(ddf, messageAndCiphertextDistribution);

        //0.737
        System.out.println("Lost function average: " + lostFunctionAverage);

    }
}
