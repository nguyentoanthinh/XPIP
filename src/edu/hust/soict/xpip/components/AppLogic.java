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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Cung cấp các chức năng cho chương trình
 * @author thinhntb
 */
public class AppLogic {
    
    /**
     * Tệp tin XML cần phân tích
     */
    private File inputFile;
    private String except;
    /**
     * Các mảnh sau khi được phân tích
     */
    private Chunk[] chunks;
     
    /** Độ sâu của chunk
     * */
    private int[] d; 
    
    /**Nút gốc của cây
     * */
    private Node root;
    
    private ExecutorService service;
    
    public AppLogic(){
    	
    }
    
    public AppLogic setInputFile(File inputFile){
        this.inputFile = inputFile;
        this.except=null;
        return this;
    }
    
    public String getExcept(){
    	return this.except;
    }
    /**
     * Phân tích
     * @return Thời gian thự hiện song song
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
        
        /** Chia chunk và xử lý cây song song  trên mỗi chunk 
         * */
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
        
        boolean c = true;
        for(int i = 0; i < pos.size() - 1; i++){
        		if(chunks[i].getException()!=null){
        			c=false;
        			this.except = new String(chunks[i].getException().getMessage());
        			break;
        		}
        			
        	
        }
        
        if(c){
        
	        /*** Tính độ sâu của chunk(song song) 
	        * */
	        int d[] = new int[ft.length+1] ;
	        d[0]=0;
	        for (int i = 0; i < ft.length; i++) {  
	                d[i+1] = chunks[i].getDepth();
	                
	        }
	
	        
	        d = prefixsum(d);
	        for (int i = 0; i < ft.length; i++) {
	           chunks[i].setDepth(d[i]);  
	        }
	        
	        /*** Tính  độ sâu của nút 
	         * và nút phải nhất trên từng độ sâu của cây trong mỗi chunk
	         * (Song song)
	         * */
	        
	        for (int i = 0; i < pos.size() - 1; i++){
	            CalculateDepth_RightMostNode Cal = new CalculateDepth_RightMostNode(chunks[i]);
	            ft[i] = service.submit(Cal);
	            chunks[i] = (Chunk) ft[i].get(); 
	            
	         }
	        
	        
	         
	        for (int i = 0; i < pos.size() - 1; i++){
	        	LinkingNode lk = new LinkingNode(chunks, i);
	        	ft[i] = service.submit(lk);
	            chunks[i] = (Chunk) ft[i].get(); 
	        }
	        
	  
	        
	        /**Gán nút gốc
	         * */
	        Node t=chunks[0].getNodeList().get(0);
	        if(t.name().equals("xml")) {
	        	if(chunks[0].getNodeList().size()==1)
	        		t=chunks[1].getNodeList().get(0);
	        	else
	        		t=chunks[0].getNodeList().get(1);
	        	}
	        
	        this.root=t;
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    
    /**
     * Thiết lập số luồng
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
    
    /** tính prefixsum của 1 mảng
     * */
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
