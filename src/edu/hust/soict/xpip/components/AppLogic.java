/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import edu.hust.soict.xpip.entities.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Cung cáº¥p cÃ¡c chá»©c nÄƒng cá»§a chÆ°Æ¡ng trÃ¬nh
 * @author thinhntb
 */
public class AppLogic {
    
    /**
     * Tá»‡p tin xml cáº§n phÃ¢n tÃ­ch
     */
    private File inputFile;
    
    /**
     * CÃ¡c máº£nh dá»¯ liá»‡u sau khi Ä‘Æ°á»£c phÃ¢n tÃ­ch
     */
    private Chunk[] chunks;
     
    private int[] d; // Độ sâu của chunk
    private Node root;// Gốc của cây
    
    private ExecutorService service;
    
    public AppLogic(){}
    
    public AppLogic setInputFile(File inputFile){
        this.inputFile = inputFile;
        return this;
    }
    
    /**
     * PhÃ¢n tÃ­ch
     * @return Thá»�i gian thá»±c hiá»‡n song song
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
        int d[] = new int[ft.length+1] ;
        d[0]=0;
        for (int i = 0; i < ft.length; i++) {
                chunks[i] = (Chunk) ft[i].get();
                d[i+1] = chunks[i].getDepth();
        }
       
       
        
        d = prefixsum(d);
        for (int i = 0; i < ft.length; i++) {
           chunks[i].setDepth(d[i]);  
           
        }
        
//        for (int i = 0; i < pos.size() - 1; i++){
//            CalculateDepth_RightMostNode Cal = new CalculateDepth_RightMostNode(chunks[i]);
//            ft[i] = service.submit(Cal);
//            chunks[i] = (Chunk) ft[i].get(); 
//            
//         }
//        
//        
//         
//        for (int i = 0; i < pos.size() - 1; i++){
//        	LinkingNode lk = new LinkingNode(chunks, i);
//        	ft[i] = service.submit(lk);
//            chunks[i] = (Chunk) ft[i].get(); 
//        }
        
      
        Node t=chunks[0].getNodeList().get(0);
        if(t.name().equals("xml")) {
        	if(chunks[0].getNodeList().size()==1)
        		t=chunks[1].getNodeList().get(0);
        	else
        		t=chunks[0].getNodeList().get(1);
        	}
        
     
        this.root=t;
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
    /**
     * Thiáº¿t láº­p láº¡i sá»‘ luá»“ng
     * @param numOfThread 
     */
   
    public void resetPool(int numOfThread){
        if (service != null) {
            service.shutdown();
        }
        service = Executors.newFixedThreadPool(numOfThread);
    }
    
    public Node getRoot(){
    	return this.root;
    }
    
    public int[] prefixsum (int[] a){
    	int[] s= new int[a.length];
    	for(int i=0; i<a.length; i++){
    		s[i]=0;
    		for(int j=0; j<=i; j++){
    			s[i]+=a[j];
    		}
    	}
    	return s;
    }
}
