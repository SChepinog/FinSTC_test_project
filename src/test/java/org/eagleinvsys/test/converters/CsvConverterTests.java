package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
import java.util.Collections;

import org.eagleinvsys.test.converters.impl.CsvCollection;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvConverterTests {

    CsvConverter csvConverter = new CsvConverter();
    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void refreshOutputStream() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void nullCollection() {
        csvConverter.convert(null, outputStream);
        Assertions.assertEquals("", Utils.outputStreamToString(outputStream));
    }

    @Test
    public void emptyCollection() {
        csvConverter.convert(new CsvCollection(Collections.emptyList()), outputStream);
        Assertions.assertEquals("", Utils.outputStreamToString(outputStream));
    }
}