/*
   * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

import java.util.List;
import java.util.Map;

/**
 *  Thẻ không có thẻ đóng. VD <br />
 * @author thinhntb
 */
public class SelfClosingElement extends NodeElement {

    public SelfClosingElement(NameSpace ns, String name, Map<String, String> attList) {
        super(ns, name, attList);
    }
    
}
