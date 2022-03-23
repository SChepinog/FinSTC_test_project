package org.eagleinvsys.test.converters.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvConverter implements Converter {
    private String separator = ",";
    private boolean needHeaders = true;
    private Charset charset = StandardCharsets.UTF_8;

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        collectionToConvert = handleNullCase(collectionToConvert);
        StringBuilder resultBuilder = new StringBuilder();

        Collection<String> headers = collectionToConvert.getHeaders();
        addHeaders(headers, resultBuilder);
        addRecords(collectionToConvert, headers, resultBuilder);
        writeResultToStream(outputStream, resultBuilder);
    }

    private ConvertibleCollection handleNullCase(ConvertibleCollection collectionToConvert) {
        if (collectionToConvert == null) {
            collectionToConvert = new CsvCollection(Collections.emptyList());
        }
        return collectionToConvert;
    }

    private void addHeaders(Collection<String> headers, StringBuilder builder) {
        if (needHeaders) {
            builder.append(collectionToString(headers, false));
        }
    }

    private void addRecords(ConvertibleCollection collectionToConvert, Collection<String> headers, StringBuilder builder) {
        boolean isFirstRecord = true;
        for (ConvertibleMessage record : collectionToConvert.getRecords()) {
            boolean needNewLineBeforeString = needHeaders || !isFirstRecord;
            builder.append(recordToString(headers, record, needNewLineBeforeString));
            isFirstRecord = false;
        }
    }

    private void writeResultToStream(OutputStream outputStream, StringBuilder builder) {
        try {
            outputStream.write(builder.toString().getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot write result to output stream");
        }
    }

    private String recordToString(Iterable<String> headers, ConvertibleMessage record, boolean needNewSting) {
        List<String> recordAsList = new ArrayList<>();
        for (String header : headers) {
            recordAsList.add(record.getElement(header));
        }
        return collectionToString(recordAsList, needNewSting);
    }

    private String collectionToString(Iterable<String> strings, boolean needNewString) {
        StringBuilder builder = new StringBuilder();
        if (needNewString) {
            builder.append("\n");
        }
        if (strings != null) {
            builder.append(
                String.join(separator, strings).replaceAll("\n", "")
            );
        }
        return builder.toString();
    }

    public CsvConverter setSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    public CsvConverter setNeedHeaders(boolean needHeaders) {
        this.needHeaders = needHeaders;
        return this;
    }

    public CsvConverter setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }
}