package com.mja.utils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    private int moveToDevCounter = 0;
    private int moveToTestCounter = 0;

    public void fileListener(String path) throws IOException, InterruptedException {
        Path faxFolder = Paths.get(path);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        boolean valid;
        do {
            WatchKey watchKey = watchService.take();

            for (WatchEvent event : watchKey.pollEvents()) {
                if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    File afile = new File(path + event.context());
                    String fileExtension = event.context().toString().substring(event.context().toString().lastIndexOf(".")+1);
                    if ("xml".equals(fileExtension)) {
                        afile.renameTo(new File(path + "DEV/" + afile.getName()))
                        moveToDevCounter++;
                    }
                    if ("jar".equals(fileExtension)) {
                        if (isCreationDateOdd(afile.getPath()) ) {
                            afile.renameTo(new File(path + "DEV/" + afile.getName()));
                            moveToDevCounter++;
                        } else {
                            afile.renameTo(new File(path + "TEST/" + afile.getName()));
                            moveToTestCounter++;
                        }
                    }
                    fileCounter(moveToDevCounter, moveToTestCounter);
                }
            }
            valid = watchKey.reset();
        } while (valid);
    }

    public boolean isCreationDateOdd(String path) {
        int hour = 0;
        try {
            BasicFileAttributes attr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
            String fileTime = attr.creationTime().toString();
            hour = Integer.valueOf(fileTime.substring(11,13));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (hour+1) % 2 == 0;
    }

    public void fileCounter (int moveToDevCounter, int moveToTestCounter) {

        FileWriter fw = null;
        try {
            fw = new FileWriter("./HOME/count.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter writer = new PrintWriter(bw);
        String textAll = "Files moved: " + (moveToDevCounter + moveToTestCounter);
        String textDev = "Files moved to DEV: " + moveToDevCounter;
        String textTest = "Files moved to TEST: " + moveToTestCounter;
        writer.println(textAll);
        writer.println(textDev);
        writer.println(textTest);
        writer.close();
    }


}
