package com.mja;

import com.mja.utils.DirectoryUtils;
import com.mja.utils.FileUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        DirectoryUtils.createDirectory("./HOME/DEV");
        DirectoryUtils.createDirectory("./HOME/TEST");

        FileUtils fileUtils = new FileUtils();
        fileUtils.fileListener("./Home/");
    }
}