package service.impl;

import model.LogEntry;
import model.LookupEntry;
import model.LookupKey;
import service.CountService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CountServiceImpl implements CountService {

    public Map<String, Long> getTagCountMap(List<LogEntry> logEntries) {
        // convert tag to lowercase and create map of tag-count
        return logEntries
                .stream()
                .collect(Collectors.groupingBy(e-> Objects.isNull(e.getTag()) ? "" : e.getTag().toLowerCase(), Collectors.counting()));

    }

    public Map<LookupKey, Long> getPortProtocolCombination(List<LogEntry> logEntries) {
        return logEntries
                .stream()
                .collect(Collectors.groupingBy(e -> new LookupKey(e.getDstport(), e.getProtocol().toLowerCase()), Collectors.counting()));
    }
}
