package service.impl;

import model.LogEntry;
import model.LookupKey;
import service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constant.FileConstants.*;

public class LogProcessorServiceImpl implements LogProcessorService {

    private MetadataService metadataService = new MetadataService();
    private WriterService writerService = new WriterServiceImpl();
    private ReaderService readerService = new ReaderService();
    Map<LookupKey, Long> keyMap = new HashMap<>();
    Map<String, Long> tagCountMap = new HashMap<>();

    Map<Integer, String> protocolMap;
    Map<LookupKey, String> tagMap;

    public void processLogs() throws IOException {

        protocolMap = metadataService.getProtocolMap();
        tagMap = metadataService.getTagMap();

        List<LogEntry> logEntries = readLogInput();

        while(logEntries.size() > 0) {
            for(LogEntry logEntry: logEntries) {
                processTagCount(logEntry);
                processKeyCount(logEntry);
            }
            logEntries = readLogInput();
        }

        // set all unknown tags to untagged
        if(tagCountMap.containsKey(null)) {
            Long value = tagCountMap.remove(null);
            tagCountMap.put("untagged", value);
        }
        exportData();
    }

    void processTagCount(LogEntry logEntry) {
        tagCountMap.put(logEntry.getTag(), tagCountMap.getOrDefault(logEntry.getTag(), 0L)+1);
    }

    void processKeyCount(LogEntry logEntry) {
        LookupKey key = new LookupKey(logEntry.getDstport(), logEntry.getProtocol());
        keyMap.put(key, keyMap.getOrDefault(key, 0L)+1);
    }

    void exportData() throws IOException {
        writerService.writeTagCountMap(tagCountMap);
        writerService.writeKeyCountMap(keyMap);
    }

    public List<LogEntry> readLogInput() {

        List<LogEntry> logList = new ArrayList<>();

        List<String[]> lines = readerService.readLogFile(BATCH_SIZE);

        for(String[] values : lines){

            int port = Integer.parseInt(values[PORT_COLUMN]);
            int protocolNumber = Integer.parseInt(values[PROTOCOL_COLUMN]);
            String protocol = protocolMap.getOrDefault(protocolNumber, "");
            String tag = tagMap.get(new LookupKey(port, protocol));

            LogEntry logEntry = new LogEntry(port, protocolNumber, protocol, tag);
            logList.add(logEntry);
        }

        return logList;
    }

}
