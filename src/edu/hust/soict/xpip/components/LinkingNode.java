package edu.hust.soict.xpip.components;

import java.util.List;
import java.util.concurrent.Callable;

import edu.hust.soict.xpip.entities.Chunk;
import edu.hust.soict.xpip.entities.Node;

public class LinkingNode implements Callable<Chunk>{
	private Chunk[] chunks;
	private int chunkid;

	/** Liên kết các nút 
	 * */
	public LinkingNode(Chunk[] chunks, int chunkid) {
		super();
		this.chunks = chunks;
		this.chunkid = chunkid;
	}

    
	@Override
	public Chunk call() throws Exception {
		Chunk chunk = chunks[chunkid];
		try{
		
		List<Node> ln = chunk.getNodeList();
		if(chunkid>=1) {
			for(Node node: ln){
				int d = node.getDepth();
				
				
				if(node.getParent()==null){
					int index= chunkid;
					Node temp = null;
					while(temp==null ){
						index--;
						temp=chunks[index].getRightmost()[d-1];
						
					}
						node.setParent(temp);
				}
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return chunk;
	}
   
}
