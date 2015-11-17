/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import edu.hust.soict.xpip.entities.Chunk;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Cung cấp các chức năng của chương trình
 * @author thinhntb
 */
public class AppLogic {
    
    /**
     * Tệp tin xml cần phân tích
     */
    private File inputFile;
    
    /**
     * Các mảnh dữ liệu sau khi được phân tích
     */
    private Chunk[] chunks;

    private ExecutorService service;
    
    public AppLogic(){}
    
    public AppLogic setInputFile(File inputFile){
        this.inputFile = inputFile;
        return this;
    }
    
    /**
     * Phân tích
     * @return Thời gian thực hiện song song
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public long parse() throws IOException, InterruptedException, ExecutionException{
        FileChunker fileChunker = new FileChunker(inputFile);
        List<Integer> pos = fileChunker.chunk();
        
        chunks = new Chunk[pos.size() - 1];
        Future[] ft = new Future[pos.size() - 1];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < pos.size() - 1; i++) {
            int start = pos.get(i) + 1;
            int end = pos.get(i + 1);
            if (i == 0) {
                start = pos.get(i);
            }
            ChunkParser cp = new ChunkParser(fileChunker.getData(), start, end);
            ft[i] = service.submit(cp);
        }
        for (int i = 0; i < ft.length; i++) {
                chunks[i] = (Chunk) ft[i].get();
        }
        
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
    /**
     * Thiết lập lại số luồng
     * @param numOfThread 
     */
    public void resetPool(int numOfThread){
        if (service != null) {
            service.shutdown();
        }
        service = Executors.newFixedThreadPool(numOfThread);
    }
}
