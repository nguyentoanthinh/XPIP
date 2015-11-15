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
import edu.hust.soict.xpip.entities.SelfClosingElement;
import edu.hust.soict.xpip.entities.StartElement;
import edu.hust.soict.xpip.entities.XMLDeclarationElement;
import edu.hust.soict.xpip.exception.BuildElementException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    private XMLTextProcessor textProcesser;

    public ChunkParser(char[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
        textProcesser = new XMLTextProcessor();
    }

    /**
     * Phân tích mảnh dữ liệu
     *
     * @return
     * @throws Exception
     */
    @Override
    public Chunk call() throws Exception {
        StringBuilder stack = new StringBuilder();

        for (int i = start; i <= end; i++) {
            stack.append(data[i]);
            if (data[i] == '>') {
                String eText = new String(stack);
                stack.delete(0, stack.length());
                buildElement(eText);

            }
        }

        return null;
    }

    /**
     * Nhận dạng kiểu element
     *
     * @param eText là một đoạn text
     * @return Phần tử XML tương ứng với eText
     */
    public Element buildElement(String eText) throws BuildElementException {
        if (eText == null) {
            return null;
        }

        Element result = null;
        if (textProcesser.isXMLDeclaration(eText)) {      // Là header
            result = new XMLDeclarationElement(textProcesser.getAttributeList(eText));
        } else if (textProcesser.isStartElement(eText)
                || textProcesser.isSelfClosingElement(eText)) {
            String ePrefix = textProcesser.getPrefix(eText);
            NameSpace eNameSpace = null;
            if (ePrefix != null) {
                eNameSpace = new NameSpace(ePrefix, null);
            }
            String eName = textProcesser.getElementName(eText);
            Map<String, String> attList = textProcesser.getAttributeList(eText);
            List<NameSpace> nameSpaces = null;

            Map<String, String> nameSpacesDefine = textProcesser.
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
            result = textProcesser.isStartElement(eText)
                    ? new StartElement(eNameSpace, eName, attList, nameSpaces)
                    : new SelfClosingElement(eNameSpace, eName, attList, nameSpaces);
        } else if (textProcesser.isEndElement(eText)) {
            String ePrefix = textProcesser.getPrefix(eText);
            NameSpace eNameSpace = null;
            if (ePrefix != null) {
                eNameSpace = new NameSpace(ePrefix, null);
            }
            String eName = textProcesser.getElementName(eText);
            result = new EndElement(eNameSpace, eName);
        }
        return result;
    }

}
