package com.kpi.ipt.crypto.service;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class ArrayMapper {

    public static double[] mapToDouble(CSVRecord csvRecord) {
        int recordNumber = csvRecord.size();
        double[] arr = new double[recordNumber];

        for (int i = 0; i < recordNumber; i++) {
            arr[i] = Double.parseDouble(csvRecord.get(i));
        }

        return arr;
    }

    public static int[][] mapToMatrix(List<CSVRecord> table) {
        int[][] arr = new int[table.size()][];

        for (int i = 0; i < table.size(); i++) {
            arr[i] = mapToInt(table.get(i));
        }

        return arr;
    }

    private static int[] mapToInt(CSVRecord csvRecord) {
        int recordNumber = csvRecord.size();
        int[] arr = new int[recordNumber];

        for (int i = 0; i < recordNumber; i++) {
            arr[i] = Integer.parseInt(csvRecord.get(i));
        }

        return arr;
    }

}
