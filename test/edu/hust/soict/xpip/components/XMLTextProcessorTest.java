/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author thinhntb
 */
public class XMLTextProcessorTest {
    
    public XMLTextProcessorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isXMLDeclaration method, of class XMLTextProcessor.
     */
//    @Test
    public void testIsXMLDeclaration() {
        System.out.println("isXMLDeclaration");
        String text = "<?xml version=\"1.0\" ?>";
        XMLTextProcessor instance = new XMLTextProcessor();
        boolean result = instance.isXMLDeclaration(text);
        
    }

    /**
     * Test of getAttributeList method, of class XMLTextProcessor.
     */
//    @Test
    public void testGetAttributeList() {
        System.out.println("getAttributeList");
//        String text = "<?xml =\"1.0\" asdf=\"ádfd\" version=\"2.0\"?>";
        String text = "<?xml =\"1.0\" as:df=\"ádfd\" version=\"2.0\"?>";
        XMLTextProcessor instance = new XMLTextProcessor();
        Map<String, String> result = instance.getAttributeList(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isStartElement method, of class XMLTextProcessor.
     */
//    @Test
    public void testIsStartElement() {
        System.out.println("isStartElement");
//        String text = "<s asd:fasdf = \"asdf\" asddfasdf = \"asdf\" asdfafsdf = \"asdf\">";
        String text = "<xml =\"1.0\" as:df=\"ádfd\" version=\"2.0\" >";
        XMLTextProcessor instance = new XMLTextProcessor();
        boolean result = instance.isStartElement(text);
    }

    /**
     * Test of isEndElement method, of class XMLTextProcessor.
     */
//    @Test
    public void testIsEndElement() {
        System.out.println("isEndElement");
        String text = "</sdgf:asdfsdf >";
        XMLTextProcessor instance = new XMLTextProcessor();
        boolean result = instance.isEndElement(text);
    }

    /**
     * Test of getElementName method, of class XMLTextProcessor.
     */
//    @Test
    public void testGetElementName() {
        System.out.println("getElementName");
        String eText = "<sasdfasdf sfg = \"asdf\" sfg = \"asdf\" sfg = \"asdf\">";
        XMLTextProcessor instance = new XMLTextProcessor();
        String result = instance.getElementName(eText);
    }

    /**
     * Test of isSelfClosingElement method, of class XMLTextProcessor.
     */
//    @Test
    public void testIsSelfClosingElement() {
        System.out.println("isSelfClosingElement");
//        String eText = "<adfs as:df=\"asdfsdf\" />";
        String eText = "<xml d=\"1.0\" as:df=\"ádfd\" version=\"2.0\" />";
        XMLTextProcessor instance = new XMLTextProcessor();
        boolean result = instance.isSelfClosingElement(eText);
        
    }

    /**
     * Test of getPrefix method, of class XMLTextProcessor.
     */
//    @Test
    public void testGetPrefix() {
        System.out.println("getPrefix");
        String eText = "<sdfa:adfs as:df=\"asdfsdf\" />";
        XMLTextProcessor instance = new XMLTextProcessor();
        String result = instance.getPrefix(eText);
    }

    /**
     * Test of getNameSpaceDefine method, of class XMLTextProcessor.
     */
    @Test
    public void testGetNameSpaceDefine() {
        System.out.println("getNameSpaceDefine");
        String eText = "<a xmlns:adsf=\"google.com\" xmlns:adsf1=\"google1.com\" xmlns:adsf2=\"gádfoogle.com\" >";
        XMLTextProcessor instance = new XMLTextProcessor();
        Map<String, String> result = instance.getNameSpaceDefine(eText);
    }
    
}
