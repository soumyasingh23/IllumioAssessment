package service;

import model.LookupEntry;
import model.LookupKey;
import org.junit.jupiter.api.Test;
import service.impl.CountServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CountServiceTest {

    private final CountService countService = new CountServiceImpl();

    @Test
    void testGetTagCountMap() {
        // Test Data
//        List<LookupEntry> lookupEntries = Arrays.asList(
//                new LookupEntry(80, "http", "email"),
//                new LookupEntry(81, "http", "Email"),
//                new LookupEntry(31, "udp", "sv_p2")
//        );
//
//        Map<String, Long> map = countService.getTagCountMap(lookupEntries);
//
//        assertEquals(map.size(), 2);
//        assertEquals(map.get("email"), 2);
//        assertEquals(map.get("sv_p2"), 1);

    }

    @Test
    void testGetPortProtocolCombination() {
        // Test Data
//        List<LookupEntry> lookupEntries = Arrays.asList(
//                new LookupEntry(80, "http", "email"),
//                new LookupEntry(81, "http", "Email"),
//                new LookupEntry(81, "Http", "Email"),
//                new LookupEntry(31, "udp", "sv_p2")
//        );
//
//        Map<LookupKey, Long> map = countService.getPortProtocolCombination(lookupEntries);
//
//        assertEquals(map.size(), 3);
//        assertEquals(map.get(new LookupKey(81, "http")), 2);
//        assertEquals(map.get(new LookupKey(80, "http")), 1);
    }
}

