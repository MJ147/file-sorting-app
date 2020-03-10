package com.mja.utils;

import java.io.File;

public class DirectoryUtils {

    static File directoryToCreate;

    public static void createDirectory(String path) {
        directoryToCreate = new File(path);
        directoryToCreate.mkdirs();
    }
}
