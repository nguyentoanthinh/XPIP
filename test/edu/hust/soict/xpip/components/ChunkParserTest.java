/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import edu.hust.soict.xpip.entities.Chunk;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thinhntb
 */
public class ChunkParserTest {
    char [] d = {'a','a','g','>'};
    public ChunkParserTest() {
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
     * Test of call method, of class ChunkParser.
     */
    @Test
    public void testCall() throws Exception {
        System.out.println("call");
        ChunkParser instance = new ChunkParser(d, 0, d.length - 1);
//        Chunk result = instance.call();
        instance.buildElement("<?xml version=\"1.0\" ?>");
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
