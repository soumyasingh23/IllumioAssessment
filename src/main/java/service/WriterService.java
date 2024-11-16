package service;

import model.LookupKey;

import java.io.IOException;
import java.util.Map;


public interface WriterService {
    void writeTagCountMap(Map<String, Long> tagCountMap) throws IOException;
    void writeKeyCountMap(Map<LookupKey, Long> keyMap) throws IOException;
}
