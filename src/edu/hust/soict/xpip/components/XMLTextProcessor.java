/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Nhận dạng, trích xuất thông tin từ thẻ ở dạng text
 *
 * @author thinhntb
 */
public class XMLTextProcessor {

    private final static String XML_DECLARATION_PATTERN = "<\\?xml\\b.* version=\"1.\\d\"(.*)\\?>";
    private final static String START_ELEMENT_PATTERN = "<([\\D]\\S*:)?([\\w&&\\D][\\w\\.]*?)(\\s+([\\D]\\S*:)?[\\w&&\\D][\\w\\.]*\\s*=\\s*\".*?\")*>";
    private final static String END_ELEMENT_PATTERN = "</([\\D]\\S*:)?([\\w&&\\D][\\w\\.]*?)\\s*>";
    private final static String SELF_CLOSING_ELEMENT_PATTERN = "<([\\D]\\S*:)?([\\w&&\\D][\\w\\.]*?)(\\s+([\\D]\\S*:)?[\\w&&\\D][\\w\\.]*\\s*=\\s*\".*?\")*[\\s]*/>";
    
    private final static String ELEMENT_PREFIX_PATTERN = "<([\\D]\\S*):";
    private final static String ELEMENT_NAME_PATTERN = "<[/]?([\\D]\\S*:)?(([\\w&&\\D][\\w\\.]*?)[\b/>\\s])";
    private final static String NAMESPACE_DEFINE_PATTERN = "xmlns:([\\w&&\\D][\\w\\.]*)\\s*=\\s*\"(.*?)\"";
    private final static String ATTRIBUTE_PATTERN = "(([\\D]\\S*:)?[\\w&&\\D][\\w\\.]*)\\s*=\\s*\"(.*?)\"";
    
    private final Pattern elementPrefixPattern;
    private final Pattern elementNamePattern;
    private final Pattern nameSpaceDefinePattern;
    private final Pattern attributePattern;

    public XMLTextProcessor() {
        elementPrefixPattern = Pattern.compile(ELEMENT_PREFIX_PATTERN);
        elementNamePattern = Pattern.compile(ELEMENT_NAME_PATTERN);
        nameSpaceDefinePattern = Pattern.compile(NAMESPACE_DEFINE_PATTERN);
        attributePattern = Pattern.compile(ATTRIBUTE_PATTERN);
    }

    /**
     * @param text
     * @return true nếu là thẻ khai báo xml
     */
    public boolean isXMLDeclaration(String text) {
        if (text == null) {
            return false;
        }
        return text.matches(XML_DECLARATION_PATTERN);
    }
    
    /**
     * SelfClosing là thẻ không có thẻ đóng. VD: <br />
     * @param eText
     * @return true nếu eText là thẻ self closing
     */
    public boolean isSelfClosingElement(String eText){
        if (eText == null){
            return false;
        }
        
        return eText.matches(SELF_CLOSING_ELEMENT_PATTERN);
    }
    
    /**
     * 
     * @param text
     * @return true nếu Text là thẻ mở
     */
    public boolean isStartElement(String text){
        if (text == null){
            return false;
        }
        
        return text.matches(START_ELEMENT_PATTERN);
    }
    
    /**
     * 
     * @param text
     * @return true nếu text là thẻ đóng
     */
    public boolean isEndElement(String text){
        if (text == null){
            return false;
        }
        return text.matches(END_ELEMENT_PATTERN);
    }
    
    /**
     * Lấy ra prefix của thẻ
     * @return null nếu không tồn tại Prefix
     */
    public String getPrefix(String eText){
        Matcher matcher = elementPrefixPattern.matcher(eText);
        while (matcher.find()) {            
            return matcher.group(1);
        }
        return null;
    }

    /**
     * Lấy ra tên thẻ
     * @param eText
     * @return 
     */
    public String getElementName(String eText){
        if (eText ==null){
            return null;
        }
        
        Matcher matcher = elementNamePattern.matcher(eText);
        while (matcher.find()) {            
            return matcher.group(3);
        }
        
        return null;
    }
    
    /**
     * Lấy ra các thuộc tính định nghĩa name space trong thẻ.
     * Map<K,V>: K là  prefix, V là  url
     * @param eText
     * @return null nếu thẻ không định nghĩa namespace nào
     */
    public Map<String, String> getNameSpaceDefine(String eText){
        if (eText == null){
            return null;
        }
        
        Map<String, String> result = new HashMap<>();
        Matcher matcher = nameSpaceDefinePattern.matcher(eText);
        while (matcher.find()) {            
            result.put(matcher.group(1), matcher.group(2));
        }
        
        return result.isEmpty()? null : result;
    }
    
    /**
     * Lấy ra các thuộc tính
     *
     * @param text
     * @return null nếu không tìm thấy thuộc tính nào
     */
    public Map<String, String> getAttributeList(String text) {
        if (text == null) {
            return null;
        }

        Map<String, String> rs = new HashMap<>();
        Matcher matcher = attributePattern.matcher(text);
        while (matcher.find()) {
            String attName = matcher.group(1);
            String attValue = matcher.group(3);
            rs.put(attName, attValue);
        }

        return rs.isEmpty() ? null : rs;
    }

}
