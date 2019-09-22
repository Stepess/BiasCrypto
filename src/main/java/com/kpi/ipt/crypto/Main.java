package com.kpi.ipt.crypto;

import com.kpi.ipt.crypto.service.CSVReader;
import com.kpi.ipt.crypto.service.Calculator;
import com.kpi.ipt.crypto.service.impl.CSVReaderImpl;
import com.kpi.ipt.crypto.service.impl.CalculatorImpl;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class Main {

    private static Calculator calculator = new CalculatorImpl();
    private static CSVReader csvReader = new CSVReaderImpl();

    public static void main(String[] args) {
        List<CSVRecord> read = csvReader.read("prob_02.csv");
        System.out.println(read);
    }
}
