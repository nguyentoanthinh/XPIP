/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

import java.util.List;
import java.util.Map;

/**
 * Lớp thực thể đại diện cho thẻ mở
 * @author thinhntb
 */
public class StartElement extends Element{
    /**
     * Danh sách các thuộc tính
     */
    private Map<String, String> attList;

    /**
     * Danh sách nameSpace mà thẻ mở này định nghĩa.
     * Nếu thẻ không định nghĩa nameSpace nào thì thuộc tính này bằng null
     */
    private List<NameSpace> nameSpaces;
    
    public StartElement(NameSpace ns, String name) {
        super(ns, name);
    }
    
    public StartElement(NameSpace ns, String name, Map<String, String> attList, List<NameSpace> nameSpaces) {
        super(ns, name);
        this.attList = attList;
        this.nameSpaces = nameSpaces;
    }
    
    public StartElement(String name, Map<String, String> attList) {
        this(null, name, attList, null);
    }

    /**
     * Lấy ra danh sách các thuộc tính
     * @return 
     */
    public Map<String, String> getAttList() {
        return attList;
    }
}
