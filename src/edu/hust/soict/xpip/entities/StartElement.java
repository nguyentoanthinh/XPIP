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
public class StartElement extends NodeElement{

    public StartElement(NameSpace ns, String name, Map<String, String> attList, List<NameSpace> nameSpaces) {
        super(ns, name, attList, nameSpaces);
    }
    
}
