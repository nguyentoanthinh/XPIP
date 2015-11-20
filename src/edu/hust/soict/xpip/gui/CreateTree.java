package edu.hust.soict.xpip.gui;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;



import edu.hust.soict.xpip.entities.Node;

public class CreateTree  {
     private Node root;
     private JTree tree;
     
     public CreateTree() {
 		
 	}
     public CreateTree(Node root) {
		this.root = root;
	}
     
  
    
    /* Tạo 1 Nút  trong cây hiển thị 
     * */
    public DefaultMutableTreeNode buildnode(String name){
    	return new DefaultMutableTreeNode(name);
    } 
    
    /* Đệ Quy tạo cây hiển thị
     * 
     * */
    public void createNode(Node tnode, DefaultMutableTreeNode t_root){
    	  if(tnode!=null){
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
    
  
  
    /*Tạo Cây hiển thị
     * */
    
    
    public JTree create(){
    	DefaultMutableTreeNode top =buildnode(root.name());
    	this.tree = new JTree(top);
    	createNode(root,top);
    	return this.tree;
    }
    
    
    
    
}
