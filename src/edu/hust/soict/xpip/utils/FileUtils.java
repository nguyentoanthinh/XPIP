/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.utils;

import java.io.File;

/**
 *
 * @author thinhntb
 */
public class FileUtils {

    /**
     * Lấy phần mở rộng của tệp tin
     *
     * @param f
     * @return null nếu file là thư mục hoặc không có phần mở rộng
     */
    public static String getExtension(File f) {
        String result = null;

        if (f != null && !f.isDirectory()) {
            String fileName = f.getName();
            int lastDotPos = fileName.lastIndexOf('.');
            if (lastDotPos >= 0 && lastDotPos < fileName.length() - 1) {
                result = fileName.substring(lastDotPos);
            }
        }

        return result;
    }
}
