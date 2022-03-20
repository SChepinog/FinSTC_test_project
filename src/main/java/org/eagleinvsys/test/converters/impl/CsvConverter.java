package org.eagleinvsys.test.converters.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvConverter implements Converter {
    private final String separator = ",";
    private final boolean needHeaders = true;

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        Collection<String> headers = collectionToConvert.getHeaders();
        StringBuilder fileBuilder = new StringBuilder();
        if (needHeaders) {
            fileBuilder.append(collectionToString(headers, false));
        }
        collectionToConvert.getRecords().forEach(record -> {
            fileBuilder.append(recordToString(headers, record));
        });
        try {
            outputStream.write(fileBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot write result to output stream");
        }
    }

    private String recordToString(Collection<String> headers, ConvertibleMessage record) {
        List<String> collect = headers.stream().map(record::getElement).collect(Collectors.toList());
        return collectionToString(collect, true);
    }

    private String collectionToString(Iterable<String> strings, boolean needNewString) {
        StringBuilder builder = new StringBuilder();
        if (needNewString) {
            builder.append("\n");
        }
        if (strings != null) { //TODO what to do with null, "null" and ""
            builder.append(String.join(separator, strings));
        }
        return builder.toString();
    }
}