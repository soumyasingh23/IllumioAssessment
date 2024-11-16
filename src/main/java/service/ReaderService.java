package service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import constant.FileConstant;
import model.LogEntry;
import model.LookupEntry;
import model.LookupKey;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static constant.FileConstant.*;


public class ReaderService {
    InputStream inputStream;
    BufferedReader reader;

    public ReaderService() {
         inputStream = ReaderService.class.getClassLoader().getResourceAsStream(FileConstant.LOG_FILE);
         reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    static Map<Integer, String> protocolMap;
    static Map<LookupKey, String> tagMap;

    public List<String[]> readFile(String filePath, String separator) {
        List<String[]> lines = new ArrayList<>();
        try  {
            InputStream inputStream = ReaderService.class.getClassLoader().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.readLine();
            String line;
            while((line = reader.readLine())!=null) {
                String[] values = line.split(separator);
                lines.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }



    private static void readLookupEntries() {
        String filePath = FileConstant.LOOKUP_FILE;

        List<LookupEntry> lookupEntries = new ArrayList<>();

        try (InputStream inputStream = ReaderService.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CsvToBean<LookupEntry> csvToBean = new CsvToBeanBuilder<LookupEntry>(reader)
                    .withType(LookupEntry.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            lookupEntries = csvToBean.parse();

            tagMap = lookupEntries.stream().collect(Collectors.toMap(e-> new LookupKey(e.getDstport(), e.getProtocol().toLowerCase()), LookupEntry::getTag, (k1, k2) -> k1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String[]> readLogFile(int batchSize) {
        List<String[]> list = new ArrayList<>();
        try  {
            String line;
            int read = 0;

            while(read < batchSize && (line = reader.readLine())!=null) {
                String[] values = line.split(SPACE_SEPARATOR);
                list.add(values);
                read++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
