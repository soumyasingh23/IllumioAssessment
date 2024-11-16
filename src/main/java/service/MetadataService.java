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


public class MetadataService {
    private static ReaderService readerService;



    private Map<Integer, String> protocolMap;
    private Map<LookupKey, String> tagMap;

    public MetadataService() {
        readerService = new ReaderService();
        protocolMap = new HashMap<>();
        tagMap = new HashMap<>();
    }
    public Map<Integer, String> getProtocolMap() {
        List<String[]> protocols = readerService.readFile(PROTOCOL_MAPPING_FILE, COMMA_SEPARATOR);

        for(String[] values : protocols) {
            protocolMap.put(Integer.parseInt(values[0]), "".equals(values[1]) ? "untagged": values[1].toLowerCase());
        }
        return protocolMap;
    }



    public Map<LookupKey, String> getTagMap() {
        List<String[]> lines = readerService.readFile(LOOKUP_FILE, COMMA_SEPARATOR);

        List<LookupEntry> lookupEntries = new ArrayList<>();

        for(String[] values : lines) {
            int port = Integer.parseInt(values[0]);
            String protocol = values[1].toLowerCase();
            String tag = values[2];
            LookupEntry lookupEntry = new LookupEntry(port, protocol, tag);
            lookupEntries.add(lookupEntry);
        }

        tagMap = lookupEntries.stream().filter(Objects::nonNull).collect(Collectors.toMap(e-> new LookupKey(e.getDstport(), e.getProtocol().toLowerCase()), LookupEntry::getTag, (k1, k2)-> k1));
        return tagMap;
    }


}
