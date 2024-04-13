package com.data.record;

import com.data.Time;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Recordwrite {
    public static void write(String str) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src\\com\\data\\record\\traderecord.txt", true);
        BufferedOutputStream f = new BufferedOutputStream(fileOutputStream);
        f.write(str.getBytes());
        f.close();
    }
}
