/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

import java.util.Map;

/**
 *
 * @author thinhntb
 */
public class XMLDeclarationElement extends Element{
     /**
     * Danh sách các thuộc tính
     */
    private Map<String, String> attList;

    private XMLDeclarationElement(String name, Map<String, String> attList) {
        super(name);
        this.attList = attList;
    }
    
    public XMLDeclarationElement(Map<String, String> attList){
        this("xml", attList);
    }
    
    /**
     * Lấy ra danh sách các thuộc tính
     * @return 
     */
    public Map<String, String> getAttList() {
        return attList;
    }
}
