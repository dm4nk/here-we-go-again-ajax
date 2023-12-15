package com.dm4nk.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static void print(String text) {
        System.out.printf("[%s] %s%n", DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy").withZone(ZoneId.systemDefault()).format(Instant.now()), text);
    }

    public static void debug(String text) {
        print("\u001B[33m[DEBUG]\u001B[0m " + text);
    }

    public static void error(String text) {
        print("\u001B[31m[ERROR]\u001B[0m " + text);
    }
}
