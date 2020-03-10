package com.mja;

import com.mja.utils.DirectoryUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        DirectoryUtils.createDirectory("./HOME/DEV");
        DirectoryUtils.createDirectory("./HOME/TEST");

    }
}