package org.eagleinvsys.test.converters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.eagleinvsys.test.converters.impl.CsvCollection;
import org.eagleinvsys.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvCollectionTest {

    @Test
    public void sameRecordsTest() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "111");
        testMap.put("2", "222");
        List<Map<String, String>> testList = Arrays.asList(testMap, testMap);
        CsvCollection testCollection = new CsvCollection(testList);

        Assertions.assertEquals(new HashSet<>(Arrays.asList("1", "2")), testCollection.getHeaders());
        for (ConvertibleMessage record : testCollection.getRecords()) {
            Assertions.assertAll(
                () -> Assertions.assertEquals("111", record.getElement("1")),
                () -> Assertions.assertEquals("222", record.getElement("2"))
            );
        }
    }

    @Test
    public void countRecordsTest() {
        Map<String, String> testMap = Utils.generateMapForKeys("1", "2");
        List<Map<String, String>> testList = Arrays.asList(testMap, testMap, null, new HashMap<>());
        CsvCollection testCollection = new CsvCollection(testList);

        int recordsCount = countRecords(testCollection.getRecords());
        Assertions.assertEquals(4, recordsCount);
    }

    private int countRecords(Iterable<ConvertibleMessage> records) {
        AtomicInteger count = new AtomicInteger();
        records.forEach(r -> count.getAndIncrement());
        return count.get();
    }
}
