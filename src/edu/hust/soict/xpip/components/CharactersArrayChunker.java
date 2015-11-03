/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Cung cấp tính năng phân mảnh mảng ký tự thành các phần
 *
 * @author thinhntb
 */
public class CharactersArrayChunker {

    /**
     * Số mảnh (mặc định là 10 mảnh)
     */
    public final static int NUM_CHUNK = 10;

    /**
     * Mảng ký tự đầu vào
     */
    private char[] data;

    public CharactersArrayChunker(char[] data) {
        this.data = data;
    }

    /**
     * Thực hiện phân mảnh dữ liệu thành NUM_CHUNK phần
     *
     * @return null nếu data = null
     */
    public List<Integer> chunk() {
        if (data == null) {
            return null;
        }
        
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
            if (result.get(i).equals(result.get(i + 1))){
                result.remove(i + 1);
                i--;
            }
        }
        
        return result;
    }

}
