package org.eagleinvsys.test.converters.impl;

import java.util.Map;

import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvRecord implements ConvertibleMessage {

    private final Map<String, String> data;

    public CsvRecord(Map<String, String> recordData) {
        if (recordData == null) {
            throw new IllegalArgumentException("Data for CsvRecord must not be null");
        }
        this.data = recordData;
    }

    @Override
    public String getElement(String elementId) {
        return data.get(elementId);
    }
}
