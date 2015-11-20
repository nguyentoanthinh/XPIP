package edu.hust.soict.xpip.components; 

import java.util.List;
import java.util.concurrent.Callable;

import edu.hust.soict.xpip.entities.Chunk;
import edu.hust.soict.xpip.entities.Node;

/***Tính độ sâu của nút 
 * và nút phải nhất cho từng độ sâu trên cây của chunk
 * */
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
            /** Độ sâu của nút = độ sâu của chunk + Độ sâu trên chunk
             * */
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
