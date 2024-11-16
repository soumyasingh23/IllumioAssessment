package service.impl;

import constant.FileConstant;
import model.LogEntry;
import model.LookupKey;
import service.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogProcessorServiceImpl implements LogProcessorService {

    private MetadataService metadataService = new MetadataService();
    private LogService logService = new LogService();
    private WriterService writerService = new WriterServiceImpl();
    Map<LookupKey, Long> keyMap = new HashMap<>();
    Map<String, Long> tagCountMap = new HashMap<>();;

    public void processLogs() throws IOException {

        List<LogEntry> logEntries = logService.readLogInput();

        while(logEntries.size() > 0) {
            for(LogEntry logEntry: logEntries) {
                processTagCount(logEntry);
                processKeyCount(logEntry);
            }
            logEntries = logService.readLogInput();
        }

        if(tagCountMap.containsKey(null)) {
            Long value = tagCountMap.remove(null);
            tagCountMap.put("untagged", value);
        }
        writerService.writeTagCountMap(tagCountMap);
        writerService.writeKeyCountMap(keyMap);
    }

    void processTagCount(LogEntry logEntry) {
        tagCountMap.put(logEntry.getTag(), tagCountMap.getOrDefault(logEntry.getTag(), 0L)+1);
    }

    void processKeyCount(LogEntry logEntry) {
        LookupKey key = new LookupKey(logEntry.getDstport(), logEntry.getProtocol());
        keyMap.put(key, keyMap.getOrDefault(key, 0L)+1);
    }

}
