package service;

import model.LogEntry;
import model.LookupEntry;
import model.LookupKey;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface CountService {

    Map<String, Long> getTagCountMap(List<LogEntry> logEntries);

    Map<LookupKey, Long> getPortProtocolCombination(List<LogEntry> logEntries);
}
