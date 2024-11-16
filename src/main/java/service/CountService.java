package service;

import model.LookupEntry;
import model.LookupKey;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CountService {

    public Map<String, Long> getTagCountMap(List<LookupEntry> lookupEntryList) {
        // convert tag to lowercase and create map of tag-count
        return lookupEntryList
                .stream()
                .collect(Collectors.groupingBy(e-> Objects.isNull(e.getTag()) ? "" : e.getTag().toLowerCase(), Collectors.counting()));

    }

    public Map<LookupKey, Long> getPortProtocolCombination(List<LookupEntry> lookupEntryList) {
        return lookupEntryList
                .stream()
                .collect(Collectors.groupingBy(e -> new LookupKey(e.getDstport(), e.getProtocol().toLowerCase()), Collectors.counting()));
    }
}
