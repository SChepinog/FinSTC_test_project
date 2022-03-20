package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.eagleinvsys.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardCsvConverterTests {

    StandardCsvConverter standardCsvConverter = new StandardCsvConverter(new CsvConverter());
    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void refreshOutputStream() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void nullCollection() {
        standardCsvConverter.convert(null, outputStream);
        Assertions.assertEquals("", Utils.outputStreamToString(outputStream));
    }

    @Test
    public void emptyCollection() {
        standardCsvConverter.convert(Collections.emptyList(), outputStream);
        Assertions.assertEquals("", Utils.outputStreamToString(outputStream));
    }

    @Test
    public void oneRecordInCsv() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "111");
        testMap.put("2", "222");
        testMap.put("3", "333");
        List<Map<String, String>> data = Collections.singletonList(testMap);

        standardCsvConverter.convert(data, outputStream);
        Assertions.assertEquals(
            "1,2,3\n111,222,333",
            Utils.outputStreamToString(outputStream)
        );
    }

    @Test
    public void severalRecords() {
        Map<String, String> testMap1 = new HashMap<>();
        testMap1.put("1", "111");
        testMap1.put("2", "222");
        testMap1.put("3", "333");
        Map<String, String> testMap2 = new HashMap<>();
        testMap2.put("1", "11");
        testMap2.put("2", "22");
        testMap2.put("3", "33");
        List<Map<String, String>> data = Arrays.asList(testMap1, testMap2);

        standardCsvConverter.convert(data, outputStream);
        Assertions.assertEquals(
            "1,2,3\n111,222,333\n11,22,33",
            Utils.outputStreamToString(outputStream)
        );
    }

    @Test
    public void recordsWithNull() {
        Map<String, String> testMap1 = new HashMap<>();
        testMap1.put("1", "111");
        testMap1.put("2", "222");
        testMap1.put("3", "333");
        Map<String, String> testMap2 = new HashMap<>();
        testMap2.put("1", "11");
        testMap2.put("2", null);
        testMap2.put(null, "33");
        List<Map<String, String>> data = Arrays.asList(testMap1, testMap2);

        standardCsvConverter.convert(data, outputStream);
        Assertions.assertEquals(
            "null,1,2,3\nnull,111,222,333\n33,11,null,null",
            Utils.outputStreamToString(outputStream)
        );
    }
}