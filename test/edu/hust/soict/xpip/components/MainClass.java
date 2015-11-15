/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thinhntb
 */
public class MainClass {
//
//    public static void main(String[] args) {
////        String pattStr = "\u00e9gal"; // gal
////        String[] input = {"\u00e9gal", // gal - this one had better match :-)
////            "e\u0301gal", // e + "Combining acute accent"
////            "e\u02cagal", // e + "modifier letter acute accent"
////            "ègal", // e + single quote
////            "e\u00b4gal", // e + Latin-1 "acute"
////    };
////        Pattern pattern = Pattern.compile(pattStr, Pattern.CANON_EQ);
////        for (int i = 0; i < input.length; i++) {
////            if (pattern.matcher(input[i]).matches()) {
////                System.out.println(pattStr + " matches input " + input[i]);
////            } else {
////                System.out.println(pattStr + " does not match input " + input[i]);
////            }
////        }
//        
//          String text    =
//            "This is the text to be searched " +
//            "for occurrences of the http:// pattern.";
//
//        String patternString = ".*http://.*";
//
//        Pattern pattern = Pattern.compile(patternString);
//
//        Matcher matcher = pattern.matcher(text);
//        boolean matches = matcher.matches();
//        System.out.println(matches);
//    }
    public static void main(String[] args) {

        String text    =
                  "Johna writes about this, and Johnb writes about that," +
                          " and Johnc writes about everything. "
                ;

        String patternString1 = ".+?";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            System.out.println("found: " + matcher.group() + ", start: " + matcher.start()+ ", end: " + matcher.end());
        }
    }
}
