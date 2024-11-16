package org.example;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import model.LookupEntry;
import model.LookupKey;
import service.CountService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        List<LookupEntry> lookupEntries = getLookupEntries();
        CountService countService = new CountService();

        // #1 Count of matches for each tag
        System.out.println("\n\nCount of matches for each tag: ");
        System.out.println("Tag Count");
        Map<String, Long> tagCountMap = countService.getTagCountMap(lookupEntries);
        for (String tag : tagCountMap.keySet()) {
            System.out.println(tag + " " + tagCountMap.get(tag));
        }

        // #2 Count of matches for each port/protocol combination
        System.out.println("\n\nCount of matches for each port/protocol combination: ");
        System.out.println("Port Protocol Count");
        Map<LookupKey, Long> map = countService.getPortProtocolCombination(lookupEntries);
        for (LookupKey key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }


    }

    // read the lookup csv and return the list of rows
    private static List<LookupEntry> getLookupEntries() {
        String filePath = "LookupTable.csv"; // CSV file

        List<LookupEntry> lookupEntries = new ArrayList<>();

        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CsvToBean<LookupEntry> csvToBean = new CsvToBeanBuilder<LookupEntry>(reader)
                    .withType(LookupEntry.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            lookupEntries = csvToBean.parse();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lookupEntries;
    }
}