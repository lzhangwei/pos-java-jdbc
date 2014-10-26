package com.thoughtworks.iamcoach.pos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Scanner {

    public List<String> readFile(String pathstr) {
        Path path = Paths.get(pathstr);
        List<String> result = null;
        try {
            result = Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
