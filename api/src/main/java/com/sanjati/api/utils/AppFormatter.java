package com.sanjati.api.utils;

import java.time.format.DateTimeFormatter;

public class AppFormatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }
}
