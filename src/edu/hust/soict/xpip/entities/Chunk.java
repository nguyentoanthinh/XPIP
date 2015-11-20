/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.entities;

import edu.hust.soict.xpip.exception.BuildElementException;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author thinhntb
 */
public class Chunk {

    /**
     * Danh sách cây trong mảnh
     */
    private List<Node> nodeList;

    /**
     * Những thẻ mở mà chưa có thẻ đóng tương ứng trong mảnh
     */
    private Queue<StartElement> unresolvedStartElement;

    /**
     * Những thẻ đóng mà chưa có thẻ mở tương ứng trong mảnh.
     */
    private Queue<EndElement> unresolvedEndElement;

    /**
     * Ngoại lệ xảy ra (!= null) nếu cấu trúc các thẻ trong mảnh không đúng
     * <root>
     * <a>
     * </b>
     * ...
     */
    private int depth;
    private BuildElementException exception;
    private Node[] rightmost;
   

    public Chunk(BuildElementException e) {
        exception = e;
    }

    public Chunk(List<Node> nodeList, Queue<StartElement> unresolvedStartElement,
            Queue<EndElement> unresolvedEndElement) {
        this.nodeList = nodeList;
        this.unresolvedStartElement = unresolvedStartElement;
        this.unresolvedEndElement = unresolvedEndElement;
        this.depth = unresolvedStartElement.size()-unresolvedEndElement.size();
        
    }

    public Node[] getRightmost() {
		return rightmost;
	}

	public void setRightmost(Node[] rightmost) {
		this.rightmost = rightmost;
	}

	public List<Node> getNodeList() {
        return nodeList;
    }

    public Queue<StartElement> getUnresolvedStartElement() {
        return unresolvedStartElement;
    }

    public Queue<EndElement> getUnresolvedEndElement() {
        return unresolvedEndElement;
    }

    public BuildElementException getException() {
        return exception;
    }

	public int getDepth() {
		return this.depth;
	}

	public void setDepth(int depth) {
		this.depth=depth;
		
	}

}
