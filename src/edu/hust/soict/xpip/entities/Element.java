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
public abstract class Element {

    /**
     * Tên
     */
    private String name;

    /**
     * NameSpace. Nếu thẻ không có namespace thì thuộc tính này bằng null. Nếu
     * prefix khác null, mà url bằng null chứng tỏ namespace của thẻ này chưa
     * được giải quyết.
     */
    private NameSpace ns;

    
    public Element(NameSpace ns, String name) {
        this.name = name;
        this.ns = ns;
    }

    public Element(String name) {
        this(null, name);
    }

    public String getName() {
        return name;
    }

    public NameSpace getNameSpace() {
        return ns;
    }
}
