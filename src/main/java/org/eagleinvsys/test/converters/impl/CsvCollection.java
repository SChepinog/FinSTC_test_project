package org.eagleinvsys.test.converters.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvCollection implements ConvertibleCollection {
    private final Set<String> headers;
    private final List<ConvertibleMessage> records;

    public CsvCollection(List<Map<String, String>> rawData) {
        this.headers = getAllKeysSet(rawData);
        this.records = getAllRecords(rawData);
    }

    private Set<String> getAllKeysSet(List<Map<String, String>> rawData) {
        Set<String> keySet = new HashSet<>();
        if (rawData != null) {
            rawData.stream()
                .filter(Objects::nonNull)
                .forEach(record -> keySet.addAll(record.keySet()));
        }
        return keySet;
    }

    private List<ConvertibleMessage> getAllRecords(List<Map<String, String>> rawData) {
        return rawData == null
            ? Collections.emptyList()
            : rawData.stream()
            .map(CsvRecord::new)
            .collect(Collectors.toList());
    }

    @Override
    public Collection<String> getHeaders() {
        return headers;
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return records;
    }
}
