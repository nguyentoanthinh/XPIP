/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Cung cấp chức năng load các ký tự từ tệp tin lên mảng.
 *
 * @author thinhntb
 */
public class CharactersLoader {

    private File inputFile;

    public CharactersLoader(File inputFile) {
        this.inputFile = inputFile;
    }

    public char[] load() throws IOException {
        if (inputFile == null) {
            throw new NullPointerException("Null pointer at inuptFile!");
        }

//        char[] buf = new char[1024];
//        int numRead = 0;
//        StringBuilder builder = new StringBuilder();
//        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//        
//        while ((numRead = reader.read(buf)) != -1) {
//            builder.append(buf, 0, numRead);
////            System.out.println(builder.length());
//        }
//        if (reader != null) {
//            reader.close();
//        }
//        return builder.toString().toCharArray();
//        FileReader reader = new FileReader(inputFile);
//        char buffer[] = new char[(int)inputFile.length()];
//        reader.read(buffer, 0, buffer.length);
////        for (char c : buffer) {
////            System.out.print(c);
////        }
//        return buffer;
        char[] buf = new char[1024];
        int numRead = 0;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        int count = 0;
        while (reader.read() != -1) {
            count++;
        }
        char rs[] = new char[count];
        int index = 0;
        reader.close();
        reader = new BufferedReader(new FileReader(inputFile));
        while ((numRead = reader.read(buf)) != -1) {
            for (int i = 0; i < buf.length; i++) {
                if (index < rs.length) {
                    rs[index] = buf[i];
                    index++;
                }
            }
        }
        if (reader != null) {
            reader.close();
        }
//        for (int i = 0; i < rs.length; i++) {
//            System.out.print(rs[i]);
//        }
        return rs;
    }

}
