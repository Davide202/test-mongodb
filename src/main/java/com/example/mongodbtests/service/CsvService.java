package com.example.mongodbtests.service;



import com.example.mongodbtests.model.csv.CsvBean;
import com.example.mongodbtests.model.csv.NamedColumnBean;
import com.opencsv.*;
import com.opencsv.bean.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Service
@Log4j2
public class CsvService {

    private static final String COMMA_DELIMITER = ",";
    private static final Character SEMICOLON_DELIMITER = ';';
    private static String readFrom = "src/main/resources/data.csv";
    private static String writeTo = "src/main/resources/data2.csv";

    public <T> T getFile() throws Exception {
        writeClassToCsv();
        return (T) readCsvToClass();
//        return (T) readAllLines();
    }

    public List<String[]> readAllLines() throws Exception {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(SEMICOLON_DELIMITER)
                .withIgnoreQuotations(true)
                .build();
        try (Reader reader = Files.newBufferedReader(Path.of(readFrom))) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build()) {
                return csvReader.readAll();
            }
        }
    }

    public List<String[]> defaultReader() throws IOException {
        List<String[]> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(readFrom))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(String.valueOf(SEMICOLON_DELIMITER));
                result.add(values);
            }
        }
        return result;
    }

    @SneakyThrows
    private File writeClassToCsv(){
        List<CsvBean> list = Arrays.asList(new NamedColumnBean("name1",1),new NamedColumnBean("name2",2));

        Path path = Path.of(writeTo);
        Files.deleteIfExists(path);
        Files.createFile(path);
        try (Writer writer  = new FileWriter(writeTo)) {
//            CSVWriter writer1 = new CSVWriter(writer);
//            writer1.writeAll();
            StatefulBeanToCsv<CsvBean> sbc = new StatefulBeanToCsvBuilder<CsvBean>(writer)
//                    .withQuotechar('\'')
//                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withSeparator(SEMICOLON_DELIMITER)
                    .build();
            sbc.write(list);
        }
        return path.toFile();
    }

    @SneakyThrows
    public List<CsvBean> readCsvToClass( )  {
        Path path = Path.of(writeTo);
        Class clazz = NamedColumnBean.class;
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<CsvBean> cb = new CsvToBeanBuilder<CsvBean>(reader).withType(clazz)
//                    .withQuoteChar('\'')
//                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withSeparator(SEMICOLON_DELIMITER)
                    .build();
            return cb.parse();
        }
    }




}
