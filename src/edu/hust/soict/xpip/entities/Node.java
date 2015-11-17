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
public interface Node {
    /**
     * Thiết lập nút cha
     * @param node
     * @return 
     */
    void setParent(Node node);
    
    /**
     * Lấy ra nút cha
     * @return 
     */
    Node getParent();
    
    /**
     * Thêm 1 nút con
     * @param node 
     */
    void addChild(Node node);
}
