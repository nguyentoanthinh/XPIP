/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

/**
 *
 * @author thinhntb
 */
public class TextNode implements Node{

    /**
     * Nút cha của node.
     * Nếu parent = null --> Chưa tìm được nút cha của text node
     */
    private Node parent;
    private String content;
    
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
    
}
