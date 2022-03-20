package org.eagleinvsys.test.converters.impl;

import java.util.HashMap;
import java.util.Map;

import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvRecord implements ConvertibleMessage {

    private final Map<String, String> data;

    public CsvRecord(Map<String, String> recordData) {
        this.data = recordData == null ? new HashMap<>() : recordData;
    }

    @Override
    public String getElement(String elementId) {
        return data.get(elementId);
    }
}
