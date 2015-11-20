package edu.hust.soict.xpip.components;

import java.util.List;
import java.util.concurrent.Callable;

import edu.hust.soict.xpip.entities.Chunk;
import edu.hust.soict.xpip.entities.Node;

public class CalculateRightMostNode implements Callable<Chunk> {
    
	private Chunk chunk;
	
	public CalculateRightMostNode(Chunk chunk) {
		super();
		this.chunk = chunk;
	}

	@Override
	public Chunk call() throws Exception {
		List<Node> ln = chunk.getNodeList();
		Node[] rmNode = new Node[1000];
		if(ln!=null)
			for (Node node : ln){
				int d = node.getDepth();
			    rmNode[d]=node;	
			}
		chunk.setRightmost(rmNode);
		return chunk;
	}

}
