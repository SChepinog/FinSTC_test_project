package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sun.org.apache.xpath.internal.operations.String;

class StandardCsvConverterTests {

    // TODO: implement JUnit 5 tests for StandardCsvConverter

    StandardCsvConverter standardCsvConverter = new StandardCsvConverter(new CsvConverter());

    @Test
    public void testForEmptyCollection() {
        OutputStream outputStream = new ByteArrayOutputStream();
        standardCsvConverter.convert(Collections.emptyList(), outputStream);
        Assertions.assertEquals("", outputStream.toString());
    }
}