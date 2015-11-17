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
import java.util.ArrayList;
import java.util.List;

/**
 * Load dữ liệu, phân mảnh.
 *
 * @author thinhntb
 */
public class FileChunker {

    /**
     * File đầu vào
     */
    private File inputFile;

    /**
     * Số mảnh (mặc định là 10 mảnh)
     */
    public final static int NUM_CHUNK = 10;

    private char[] data;

    public FileChunker(File inputFile) {
        this.inputFile = inputFile;
    }
    
    public char[] getData(){
        return data;
    }

    /**
     * Thực hiện phân mảnh dữ liệu thành NUM_CHUNK phần
     *
     * @return null nếu data = null
     * @throws java.io.IOException
     */
    public List<Integer> chunk() throws IOException {
        data = load();
        int chunkSize = data.length / NUM_CHUNK;
        List<Integer> result = new ArrayList<>();
        result.add(0);

        for (int i = 1; i < NUM_CHUNK; i++) {
            int endPos = i * chunkSize - 1;
            while (data[endPos + 1] != '<') {
                endPos--;
            }
            result.add(endPos);
        }

        result.add(data.length - 1);

        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).equals(result.get(i + 1))) {
                result.remove(i + 1);
                i--;
            }
        }

        return result;
    }

    /**
     * Load nội dung file ra một mảng ký tự
     *
     * @return
     * @throws IOException
     */
    private char[] load() throws IOException {
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
