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
import javax.swing.JOptionPane;

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
        if (inputFile == null){
            throw new NullPointerException("Null pointer at inuptFile!");
        }
        
        char[] rawData;
        BufferedReader reader = null;
        StringBuilder builder = null;

        reader = new BufferedReader(new FileReader(inputFile));
        builder = new StringBuilder();
        char[] buf = new char[1024];
        int numRead = 0;
        
        while ((numRead = reader.read(buf)) != -1) {
            builder.append(buf, 0, numRead);
        }
        rawData = builder.toString().toCharArray();

        if (reader != null) {
            reader.close();
        }
        
        return rawData;
    }

}
