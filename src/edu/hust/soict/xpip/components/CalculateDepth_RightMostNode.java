package edu.hust.soict.xpip.components; 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import edu.hust.soict.xpip.entities.Chunk;
import edu.hust.soict.xpip.entities.Node;

public class CalculateDepth_RightMostNode implements Callable<Chunk> {
      private Chunk chunk;
 
      public CalculateDepth_RightMostNode(Chunk chunk) {
    	  this.chunk = chunk;
      }
	@Override
	public Chunk call() throws Exception {		
		List<Node> ln = chunk.getNodeList();
		Node[] rmNode = new Node[1000];
		if(ln!=null){

			for (Node node : ln){
				int dn = node.getDepth(); //offset
				int dc = chunk.getDepth();//base   	
				node.setDepth(dn+dc);
			    rmNode[dn+dc]=node;	
			}
			chunk.setRightmost(rmNode);
		}
		
		return chunk;
		
	}
	
      
}
