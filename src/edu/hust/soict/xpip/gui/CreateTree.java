package edu.hust.soict.xpip.gui;

import java.util.*;

import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.hust.soict.xpip.entities.Node;
import edu.hust.soict.xpip.entities.NodeElement;
import edu.hust.soict.xpip.entities.TextNode;

public class CreateTree implements TreeSelectionListener  {
     private Node root;
     private JTree tree;
     private JTextPane textpane;
     
	private HashMap<DefaultMutableTreeNode,Node> m1;
     
     public CreateTree(Node root) {
		this.root = root;
		m1 = new HashMap<>(); 
		textpane = new JTextPane();
	}
     
     public JTextPane getTextpane() {
 		return textpane;
 	}

   
    /**  Tạo 1 Nút  trong cây hiển thị 
     * */
    public DefaultMutableTreeNode buildnode(String name){
    	return new DefaultMutableTreeNode(name);
    } 
    
    /** Đệ Quy tạo cây hiển thị
     * 
     * */
    public void createNode(Node tnode, DefaultMutableTreeNode t_root){
    	  if(tnode!=null){
    		  m1.put(t_root, tnode);
    		  List<Node> list = tnode.getChild();
             
    		  if(list!=null){
    			  for(Node node : list){
    				  
    				  DefaultMutableTreeNode temp = buildnode(node.name()); 
    				  t_root.add(temp);
    				  
    				  createNode(node,temp);
    				  
    			  }
    		  }		
    	  }
    }
    
  
     /**Thông tin của nút
      * */
    public String info (DefaultMutableTreeNode dnode){
    	Node node = m1.get(dnode);
    	String s="";
    	if(node instanceof NodeElement){
    		s+="Name: "+((NodeElement)node).getName();
    		s+="\nAttribute: "+((NodeElement)node).getAttList();
    		s+="\nNamespace: "+((NodeElement)node).getNameSpace();
    	}
    	else
    		if(node instanceof TextNode){
    			s+="Content:\n";
    			s+= ((TextNode)node).getContent();
    			}
    	return s;
    }
    
    /** Bắt sự kiện cho cây
     * */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();
        textpane.setText(info(node));
        if (node == null) return;
        
        
    }
    
    /** Tạo Cây hiển thị
     * */
	public JTree create(){
    	DefaultMutableTreeNode top =buildnode(root.name());
    	this.tree = new JTree(top);
    	createNode(root,top);
    	tree.addTreeSelectionListener(this);
    	return this.tree;
    }
    
    
    
}
