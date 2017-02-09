package trees.trietree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TrieTreeCn {
	
	private TrieNode root;
	
	static final TrieNode SHARED_LEAF = new TrieNode(); 
	
	static final TrieNode EMPTY_ROOT = new TrieNode();
	
	public TrieTreeCn(){
		root = EMPTY_ROOT;
	}
	
	public TrieNode getRoot(){
		return root;
	}
	
	public boolean isEmpty(){
		return root == EMPTY_ROOT;
	}
	

	public void buildPhrase(List<String> words){
		
		
		
	}
	
	
	
	static class TrieNode{
		private String value;
		
		private Map<String, TrieNode> children; 
		
		private TrieNode parent;
		
		
		public boolean isLeaf(){
			return children == null;
		}
		
		public TrieNode(){
		
		}
		
		public TrieNode(String value, TrieNode p){
			this.value = value;
			this.parent = p;
		}
		
		public TrieNode(String value){
			this.value = value;
		}
		
		public void add(String s){
			if(children == null){
				children = new HashMap<String, TrieTreeCn.TrieNode>();
			}
			children.put(s, SHARED_LEAF);
		}
		
		private void setParent(TrieNode p){
			this.parent = p;
		}
		
		public TrieNode getParent(){
			return parent;
		}
		
		public String getValue(){
			return value;
		}
		
		public boolean isSharedLeaf(){
			return this ==  SHARED_LEAF;
		}
		
		public TrieNode getChild(String s){
			return children.get(s);
		}
		
	}
	
	
	
	
	
	
}
