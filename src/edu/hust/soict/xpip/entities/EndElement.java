/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

/**
 * Lớp thực thể đại diện cho thẻ đóng
 *
 * @author thinhntb
 */
public class EndElement extends Element {

    public EndElement(NameSpace ns, String name) {
        super(ns, name);
    }

    public EndElement(String name) {
        super(name);
    }
}
