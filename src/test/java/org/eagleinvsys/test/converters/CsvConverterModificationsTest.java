package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.CsvCollection;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CsvConverterModificationsTest {

    private CsvConverter csvConverter;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void createConverter() {
        csvConverter = new CsvConverter();
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void separatorTest() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "111");
        testMap.put("2", "222");
        testMap.put("3", "333");
        List<Map<String, String>> data = Collections.singletonList(testMap);
        ConvertibleCollection convertibleMessage = new CsvCollection(data);

        csvConverter
            .setSeparator(";")
            .convert(convertibleMessage, outputStream);
        Assertions.assertEquals(
            "1;2;3\n111;222;333",
            Utils.outputStreamToString(outputStream)
        );

        outputStream.reset();
        csvConverter
            .setSeparator(":")
            .convert(convertibleMessage, outputStream);
        Assertions.assertEquals(
            "1:2:3\n111:222:333",
            Utils.outputStreamToString(outputStream)
        );
    }

    @Test
    public void encodingTest() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "111");
        testMap.put("2", "222");
        testMap.put("3", "333");
        List<Map<String, String>> data = Collections.singletonList(testMap);
        ConvertibleCollection convertibleMessage = new CsvCollection(data);

        csvConverter
            .setCharset(StandardCharsets.US_ASCII)
            .convert(convertibleMessage, outputStream);
        Assertions.assertEquals(
            "1,2,3\n111,222,333",
            Utils.outputStreamToString(outputStream, StandardCharsets.US_ASCII)
        );
    }

    @Test
    public void headerPresenceTest() {
        Map<String, String> testMap1 = new HashMap<>();
        testMap1.put("1", "111");
        testMap1.put("2", "222");
        testMap1.put("3", "333");
        Map<String, String> testMap2 = new HashMap<>();
        testMap2.put("1", "1111");
        testMap2.put("2", "2222");
        testMap2.put("3", "3333");
        List<Map<String, String>> data = Arrays.asList(testMap1, testMap2);
        ConvertibleCollection convertibleMessage = new CsvCollection(data);

        csvConverter
            .setNeedHeaders(false)
            .convert(convertibleMessage, outputStream);
        Assertions.assertEquals(
            "111,222,333\n1111,2222,3333",
            Utils.outputStreamToString(outputStream, StandardCharsets.US_ASCII)
        );

        outputStream.reset();
        csvConverter
            .setNeedHeaders(true)
            .convert(convertibleMessage, outputStream);
        Assertions.assertEquals(
            "1,2,3\n111,222,333\n1111,2222,3333",
            Utils.outputStreamToString(outputStream, StandardCharsets.US_ASCII)
        );
    }
}
