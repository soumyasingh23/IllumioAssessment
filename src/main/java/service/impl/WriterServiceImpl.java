package service.impl;

import model.LookupKey;
import service.WriterService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static constant.FileConstant.*;

public class WriterServiceImpl implements WriterService {

    public void writeTagCountMap(Map<String, Long> tagCountMap) throws IOException {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(TAG_COUNT_FILE, false))) {
            outFile.write(TAG_COUNT_HEADER);
            outFile.newLine();
            for (Map.Entry<String, Long> entry : tagCountMap.entrySet()) {
                outFile.write(entry.getKey() + "," + entry.getValue());
                outFile.newLine();
            }
        } catch (Exception ex) {
            throw new IOException("Unable to write to file " + TAG_COUNT_FILE);
        }
    }

    public void writeKeyCountMap(Map<LookupKey, Long> keyMap) throws IOException {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(KEY_COUNT_FILE, false))) {
            outFile.write(KEY_HEADER);
            outFile.newLine();
            for (Map.Entry<LookupKey, Long> entry : keyMap.entrySet()) {
                outFile.write(entry.getKey() + "," + entry.getValue());
                outFile.newLine();
            }
        } catch (Exception ex) {
            throw new IOException("Unable to write to file " + KEY_COUNT_FILE);
        }
    }
}
