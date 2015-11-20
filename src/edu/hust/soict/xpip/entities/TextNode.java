/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

import java.util.List;

/**
 *
 * @author thinhntb
 */
public class TextNode implements Node{

    /**
     * Node cha của node.
     * Node parent = null --> Chưa tìm nút cha của text node
     */
    private Node parent;
    private String content;
    private int depth;
    
    public TextNode(String content){
        this.content = content;
    }
    
    @Override
    public void setParent(Node node) {
        parent = node;
        parent.addChild(this);
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void addChild(Node node) {
    }

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public void setDepth(int depth) {
		this.depth=depth;
		
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return depth;
	}

	@Override
	public List<Node> getChild() {
	     return null;
	}
    
    
}
