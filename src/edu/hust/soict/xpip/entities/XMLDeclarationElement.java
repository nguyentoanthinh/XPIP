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
public class XMLDeclarationElement extends NodeElement{

    public XMLDeclarationElement(Map<String, String> attList){
        super(null, "xml", attList);
    }
}
