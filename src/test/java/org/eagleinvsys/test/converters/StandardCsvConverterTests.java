package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        standardCsvConverter.convert(data, outputStream);
        System.out.println(Utils.outputStreamToString(outputStream));
        Assertions.assertEquals(
            "1,2,3\n111,222,333",
            Utils.outputStreamToString(outputStream)
        );
    }

//    public void

    //TODO add tests for several maps with different key set
}