package service;

import constant.FileConstant;
import model.LogEntry;
import model.LookupKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static constant.FileConstant.*;

public class LogService {

    private MetadataService metadataService;
    private ReaderService readerService;

    public LogService() {
        metadataService = new MetadataService();
        readerService = new ReaderService();
    }

    public List<LogEntry> readLogInput() {
        Map<Integer, String> protocolMap = metadataService.getProtocolMap();
        Map<LookupKey, String> tagMap = metadataService.getTagMap();

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
