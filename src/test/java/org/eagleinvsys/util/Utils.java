package org.eagleinvsys.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

public class Utils {

    public static String outputStreamToString(ByteArrayOutputStream outputStream) {
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    public static Map<String, String> generateMapForKeys(String... keys) {
        Set<String> keySet = new HashSet<>(Arrays.asList(keys));
        return keySet.stream()
            .map(key -> Pair.of(key, UUID.randomUUID().toString()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }
}
