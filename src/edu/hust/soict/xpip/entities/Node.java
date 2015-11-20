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
public interface Node {
    /**
     * Thiết lập node cha
     * @param node
     * @return 
     */
    void setParent(Node node);
    
    /**
     * Lấy ra node cha
     * @return 
     */
    Node getParent();
    
    /**
     * Thêm một node con
     * @param node 
     */
    void addChild(Node node);
    
    List<Node> getChild();
    String name();
    
    
    void setDepth(int depth);
    int getDepth();
}
