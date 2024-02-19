package ru.gb.hw8.utils;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class Logger {
    private final String fileName;

    public void log(String message) {
        checkDir();
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkDir() {
        File logFile = new File(fileName);
        if (!logFile.exists()) {
            try {
                logFile.getParentFile().mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
