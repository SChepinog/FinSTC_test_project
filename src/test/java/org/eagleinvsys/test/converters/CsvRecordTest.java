package org.eagleinvsys.test.converters;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.CsvRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvRecordTest {

    @Test
    public void recordCanBeNull() {
        CsvRecord record = new CsvRecord(null);
        Assertions.assertAll(
            () -> Assertions.assertNull(record.getElement("test")),
            () -> Assertions.assertNull(record.getElement(null))
        );
    }

    @Test
    public void emptyRecord() {
        CsvRecord record = new CsvRecord(Collections.emptyMap());
        Assertions.assertAll(
            () -> Assertions.assertNull(record.getElement("test")),
            () -> Assertions.assertNull(record.getElement(null))
        );
    }

    @Test
    public void recordWorks() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "111");
        testMap.put("2", "222");
        testMap.put("3", null);
        testMap.put(null, "444");
        CsvRecord testRecord = new CsvRecord(testMap);
        testMap.forEach((key, value) ->
            Assertions.assertEquals(value, testRecord.getElement(key))
        );
        Assertions.assertNull(testRecord.getElement("5"));
    }
}
