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
public class NameSpace {
    private String prefix;
    private String url;

    public NameSpace(String prefix, String url) {
        this.prefix = prefix;
        this.url = url;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getUrl() {
        return url;
    }
}
