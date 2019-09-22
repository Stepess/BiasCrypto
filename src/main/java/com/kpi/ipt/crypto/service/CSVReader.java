package com.kpi.ipt.crypto.service;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface CSVReader {

    List<CSVRecord> read(String fileName);

}
