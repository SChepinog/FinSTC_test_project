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
        if (collectionToConvert == null) {
            collectionToConvert = new CsvCollection(Collections.emptyList());
        }
        Collection<String> headers = collectionToConvert.getHeaders();
        StringBuilder fileBuilder = new StringBuilder();
        if (needHeaders) {
            fileBuilder.append(collectionToString(headers, false));
        }
        collectionToConvert.getRecords()
            .forEach(record -> fileBuilder.append(recordToString(headers, record)));
        try {
            outputStream.write(fileBuilder.toString().getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot write result to output stream");
        }
    }

    private String recordToString(Iterable<String> headers, ConvertibleMessage record) {
        List<String> recordAsList = new ArrayList<>();
        for (String header : headers) {
            recordAsList.add(record.getElement(header));
        }
        return collectionToString(recordAsList, true);
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

    public String getSeparator() {
        return separator;
    }

    public CsvConverter setSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    public boolean isNeedHeaders() {
        return needHeaders;
    }

    public CsvConverter setNeedHeaders(boolean needHeaders) {
        this.needHeaders = needHeaders;
        return this;
    }

    public Charset getCharset() {
        return charset;
    }

    public CsvConverter setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }
}