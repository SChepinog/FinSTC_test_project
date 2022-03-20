package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.eagleinvsys.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StandardCsvConverterTests {

    // TODO: implement JUnit 5 tests for StandardCsvConverter

    StandardCsvConverter standardCsvConverter = new StandardCsvConverter(new CsvConverter());

    @Test
    public void nullCollection() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        standardCsvConverter.convert(null, outputStream);
        Assertions.assertEquals("", Utils.outputStreamToString(outputStream));
    }

    @Test
    public void emptyCollection() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        standardCsvConverter.convert(Collections.emptyList(), outputStream);
        Assertions.assertEquals("", Utils.outputStreamToString(outputStream));
    }

    @Test
    public void firstTest() {
        Map<String, String> testMap = Utils.generateMapForKeys("1", "2", "3");
        List<Map<String, String>> data = Collections.singletonList(testMap);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        standardCsvConverter.convert(data, outputStream);
        System.out.println(Utils.outputStreamToString(outputStream)); //TODO check appeared csv
    }

    //TODO add tests for several maps with different key set
}