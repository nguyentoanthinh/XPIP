/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.components;

import edu.hust.soict.xpip.entities.Chunk;
import edu.hust.soict.xpip.entities.Element;
import edu.hust.soict.xpip.entities.EndElement;
import edu.hust.soict.xpip.entities.NameSpace;
import edu.hust.soict.xpip.entities.Node;
import edu.hust.soict.xpip.entities.SelfClosingElement;
import edu.hust.soict.xpip.entities.StartElement;
import edu.hust.soict.xpip.entities.TextNode;
import edu.hust.soict.xpip.entities.XMLDeclarationElement;
import edu.hust.soict.xpip.exception.BuildElementException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;

/**
 * Cung cấp hàm thự hiện phân tích một mảnh dữ liệu
 *
 * @author thinhntb
 */
public class ChunkParser implements Callable<Chunk> {

    /**
     * Mảng chứa các ký tự trong tài liệu xml
     */
    private char[] data;

    /**
     * Vị trí bắt đầu và kết thúc của mảnh dữ liệu cần phân tích
     */
    private int start;
    private int end;

    private XMLTextProcessor textProcessor;

    public ChunkParser(char[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
        textProcessor = new XMLTextProcessor();
    }

    /**
     * Phân tích mảnh dữ liệu
     *
     * @return
     * @throws Exception
     */
    @Override
    public Chunk call() throws Exception {
        List<Node> trees = new ArrayList<>();
        Queue<StartElement> unresolveStartElement = new ArrayDeque<>();
        Queue<EndElement> unresolveEndElement = new ArrayDeque<>();

        Stack<StartElement> elementStack = new Stack<>();       // Cấu trúc dữ liệu của thuật toán
        StringBuilder charStack = new StringBuilder();
        for (int i = start; i <= end; i++) {
            charStack.append(data[i]);
            if (data[i] == '>') {
                String text = new String(charStack);
                text = text.trim();
                int openPos = text.indexOf('<');
                if (openPos < 0) {              // Không tìm được ký tự mở thẻ 
                    return new Chunk(new BuildElementException("Không đúng cú "
                            + "pháp xml tại: " + text));
                } else if (openPos > 0) {         // Ký tự mở thẻ ở vị > 0 --> Có text trước thẻ
                    String content = text.substring(0, openPos);
                    if (elementStack.isEmpty()) {    // Stack rỗng --> TextNode này có cha ở chunk khác
                        trees.add(new TextNode(content));
                    } else {
                        TextNode textNode = new TextNode(content);
                        textNode.setParent(elementStack.peek());
                    }
                }

                String eText = text.substring(openPos);
                charStack.delete(0, charStack.length());
                Element e = buildElement(eText);
                if (e instanceof StartElement) {
                    StartElement se = (StartElement) e;
                    if (!elementStack.isEmpty()) {
                        se.setParent(elementStack.peek());
                    } else {
                        trees.add((Node) e);
                    }
                    elementStack.push(se);
                } else if (e instanceof EndElement) {
                    if (elementStack.isEmpty()) {       // Stack rỗng --> Đây là UnresolveEnd
                        unresolveEndElement.add((EndElement) e);
                    } else {
                        StartElement se = elementStack.pop();   // Lấy ra startElement ở đầu Stack
                        EndElement ee = (EndElement) e;
                        if (!isMatch(se, ee)) {       // Thẻ đóng khớp với thẻ mở ở đầu stack
                            return new Chunk(new BuildElementException("Không khớp với thẻ mở"
                                    + " tại thẻ đóng: " + text));
                        }
                    }
                } else if (e instanceof SelfClosingElement) {
                    SelfClosingElement sc = (SelfClosingElement) e;
                    if (!elementStack.isEmpty()) {
                        sc.setParent(elementStack.peek());
                    } else {
                        trees.add((Node) e);
                    }
                } else if (e instanceof XMLDeclarationElement) {
                    trees.add((Node) e);
                }
            }
        }

        /**
         * Thêm những thẻ còn lại trong stack vào unresolveStartElement
         */
        elementStack.stream().forEach((se) -> {
            unresolveStartElement.offer(se);
        });
        return new Chunk(trees, unresolveStartElement, unresolveEndElement);
    }

    /**
     * Nhận dạng kiểu element
     *
     * @param eText là một đoạn text
     * @return Phần tử XML tương ứng với eText
     */
    public Element buildElement(String eText) {
        if (eText == null) {
            return null;
        }

        Element result = null;
        if (textProcessor.isStartElement(eText)
                || textProcessor.isSelfClosingElement(eText)) {
            String ePrefix = textProcessor.getPrefix(eText);
            NameSpace eNameSpace = null;
            if (ePrefix != null) {
                eNameSpace = new NameSpace(ePrefix, null);
            }
            String eName = textProcessor.getElementName(eText);
            Map<String, String> attList = textProcessor.getAttributeList(eText);
            List<NameSpace> nameSpaces = null;

            Map<String, String> nameSpacesDefine = textProcessor.
                    getNameSpaceDefine(eText);
            if (nameSpacesDefine != null) {
                nameSpaces = new ArrayList<>();
                Set<String> keys = nameSpacesDefine.keySet();
                for (Iterator<String> prefixes = keys.iterator();
                        prefixes.hasNext();) {
                    String prefix = prefixes.next();
                    String url = nameSpacesDefine.get(prefix);
                    NameSpace dNameSpace = new NameSpace(prefix, url);
                    nameSpaces.add(dNameSpace);
                    if (prefix.equals(ePrefix)) {
                        eNameSpace = dNameSpace;
                    }
                }
            }
            result = textProcessor.isStartElement(eText)
                    ? new StartElement(eNameSpace, eName, attList, nameSpaces)
                    : new SelfClosingElement(eNameSpace, eName, attList);
        } else if (textProcessor.isEndElement(eText)) {
            String ePrefix = textProcessor.getPrefix(eText);
            NameSpace eNameSpace = null;
            if (ePrefix != null) {
                eNameSpace = new NameSpace(ePrefix, null);
            }
            String eName = textProcessor.getElementName(eText);
            result = new EndElement(eNameSpace, eName);
        } else if (textProcessor.isXMLDeclaration(eText)) {      // Là header
            result = new XMLDeclarationElement(textProcessor.getAttributeList(eText));
        }
        return result;
    }

    /**
     *
     * @return true
     */
    private boolean isMatch(StartElement se, EndElement ee) {
        if (se == null || ee == null) {
            return false;
        }
        String seName = se.getName();
        String eeName = ee.getName();
        return seName.equals(eeName);
    }

}
