/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thinhntb
 */
public abstract class NodeElement extends Element implements Node{
    
    /**
     * Nút cha
     */
    protected Node parent;
    
    /**
     * Danh sách các con. Nếu là nút là thì thuộc tính này bằng null
     */
    protected List<Node> childsList;
    
    /**
     * Danh sách các thuộc tính
     */
    protected Map<String, String> attList;

    /**
     * Danh sách nameSpace mà thẻ mở này định nghĩa.
     * Nếu thẻ không định nghĩa nameSpace nào thì thuộc tính này bằng null
     */
    protected List<NameSpace> nameSpaces;
    
    public NodeElement(NameSpace ns, String name, Map<String, String> attList, List<NameSpace> nameSpaces) {
        super(ns, name);
        this.attList = attList;
        this.nameSpaces = nameSpaces;
        childsList = new ArrayList<>();
    }
    
    public NodeElement(NameSpace ns, String name, Map<String, String> attList) {
        super(ns, name);
        this.attList = attList;
        this.nameSpaces = null;
    }
    
    public NodeElement(String name, Map<String, String> attList) {
        this(null, name, attList, null);
    }

    /**
     * Lấy ra danh sách các thuộc tính
     * @return 
     */
    public Map<String, String> getAttList() {
        return attList;
    }

    @Override
    public void setParent(Node node) {
        this.parent = node;
        node.addChild(this);
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void addChild(Node node) {
        childsList.add(node);
    }
}
