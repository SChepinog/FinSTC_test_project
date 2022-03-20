package org.eagleinvsys.test.converters;

import java.util.Collections;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.CsvRecord;
import org.eagleinvsys.util.Utils;
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
        Map<String, String> testMap = Utils.generateMapForKeys("1", "2", "3", null, "null", "");
        CsvRecord testRecord = new CsvRecord(testMap);
        testMap.forEach((key, value) ->
            Assertions.assertEquals(value, testRecord.getElement(key))
        );
    }
}
