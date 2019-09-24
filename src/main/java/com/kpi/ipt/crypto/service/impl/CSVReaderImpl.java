package com.kpi.ipt.crypto.service.impl;

import com.kpi.ipt.crypto.service.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class CSVReaderImpl implements CSVReader {

    private static Logger log = Logger.getLogger(CSVReaderImpl.class.getName());

    @Override
    public List<CSVRecord> read(String fileName) {
        try {
            Path path = Paths.get(CSVReaderImpl.class.getClassLoader().getResource(fileName).toURI());
            try(
                    BufferedReader bufferedReader = Files.newBufferedReader(path);
                    CSVParser parser = CSVParser.parse(bufferedReader, CSVFormat.DEFAULT)) {
                return parser.getRecords();
            }
            catch (IOException e) {
                log.severe("Something wrong with reading file");
                throw new UncheckedIOException(e);
            }
        } catch (URISyntaxException e) {
            log.severe("Can't build path to file " + fileName);
            throw new RuntimeException(e);
        }
    }
}
