package org.eagleinvsys.test.converters.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvCollection implements ConvertibleCollection {
    private final Set<String> headers;
    private final List<ConvertibleMessage> records;

    public CsvCollection(List<Map<String, String>> rawData) {
        List<Map<String, String>> notNullData = filterData(rawData);
        this.headers = getAllKeysSet(notNullData);
        this.records = getAllRecords(notNullData);
    }

    private List<Map<String, String>> filterData(List<Map<String, String>> rawData) {
        return Optional.of(rawData)
            .map(rd -> rd.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }

    private Set<String> getAllKeysSet(List<Map<String, String>> notNullRawData) {
        Set<String> keySet = new HashSet<>();
        notNullRawData.forEach(record -> keySet.addAll(record.keySet()));
        return keySet;
    }

    private List<ConvertibleMessage> getAllRecords(List<Map<String, String>> notNullRawData) {
        return notNullRawData.stream()
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
