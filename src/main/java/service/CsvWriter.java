package service;

import com.opencsv.CSVWriter;
import constant.FileConstant;
import model.LookupEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CsvWriter {

    public static void writeMapToCsv(Map<String, Long> map) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FileConstant.LOOKUP_FILE))) {

            String[] header = {"tag", "count"};
            writer.writeNext(header);

            for (Map.Entry<String, Long> entry : map.entrySet()) {
                String[] row = {entry.getKey(), String.valueOf(entry.getValue())};
                writer.writeNext(row);
            }

            System.out.println("CSV file written successfully to: " + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void writeMapToCsv(List<LookupEntry> lookupEntryList) {
//        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
//            // Write header
//            String[] header = {"tag", "count"};
//            writer.writeNext(header);
//
//            // Write map entries
//            for (Map.Entry<String, Long> entry : map.entrySet()) {
//                String[] row = {entry.getKey(), String.valueOf(entry.getValue())};
//                writer.writeNext(row);
//            }
//
//            System.out.println("CSV file written successfully to: " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
