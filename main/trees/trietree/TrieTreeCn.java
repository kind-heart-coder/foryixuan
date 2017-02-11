package trees.trietree;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieTreeCn {
	
	private TrieNode root;
	
	public TrieTreeCn(){
		root = null;
	}
	
	public TrieNode getRoot(){
		return root;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void buildTree(List<String> words){
		if(isEmpty()){
			root = new TrieNode(null, null,false);
		}
		appendFrom(root, words);
	}
	
	
	public void constructTree(Collection<List<String>> listOfWords){
		for(List<String> l : listOfWords){
			buildTree(l);
		}
	}
	
	public void clear(){
		this.root = null;
	}
	

	public void appendFrom(TrieNode node,List<String> words){
		
		if(node == null ||  words.size() <= 0){
			throw new IllegalArgumentException("Illegal Argument!");
		}
		
		TrieNode currentNode = node;
		
		int size = words.size();
		int  i = 0;
		
		while(i < size && currentNode.hasKey(words.get(i))){
			currentNode = currentNode.getChild(words.get(i));
			i++;
		}
		
		if(i == size){
			if(!currentNode.isTerminal()){
				currentNode.setTerminal(true);
			}
			return;
		}
	
		while(i < size - 1){
			currentNode = currentNode.addChild(words.get(i), false);
			i++;
		}
		currentNode.addChild(words.get(i), true);
	}
	
	public void delete(List<String> words){
		TrieNode lastNode = searchNode(words);
		if(lastNode == null){
			return;
		}
		
		if(lastNode.haveChildren()){
			lastNode.setTerminal(false);
		}else{
			TrieNode parent = lastNode.parent;
			while(parent != null && !parent.isTerminal()){
				parent.removeChild(lastNode.getValue());
				lastNode = parent;
				parent = lastNode.parent;
			}
		}
	}

	//be cautious to use this function, since if two String s1 s2 exist and 
	//suppose s1 is subString of s2 use of this function may cause unexpected 
	//behavior.
	public void changeValue(List<String> words, String s){
		TrieNode node = searchNode(words);
		if(node == null){
			throw new IllegalArgumentException("Can not find word sequences");
		}
		node.setValue(s);
		node.parent.children.put(s, node);
		
	}
	
	//A secure version of #changeValue(List<String> words, String s)
	public void changeValue( List<String> completeWords, int index, String s){
		throw new UnsupportedOperationException("I need to see my friend and will implement later!");
	}
	
	
	private TrieNode searchNode(List<String> words){
		TrieNode node = root;
		boolean exist = true;
		for(String s:words){
			if(node.hasKey(s)){
				node = node.getChild(s);
			}else{
				exist = false;
				break;
			}
		}
		
		if(exist){
			return node;
		}
		
		return null;
	}
	
	public static class TrieNode{
		private String value;
		
		private Map<String, TrieNode> children; 
		
		private TrieNode parent;
		
		//indicator variable to deal with two string aS and bS and if aS is a sub string of bS
		private boolean terminal;
		
		public boolean isTerminal(){
			return terminal;
		}
		
		public void setTerminal(boolean isTrue){
			this.terminal = isTrue;
		}
		
		public boolean haveChildren(){
			return children != null && !children.isEmpty();
		}
		
		public TrieNode(String value, TrieNode parent, boolean terminal){
			this.value = value;
			this.parent = parent;
			this.terminal = terminal;
		}
		
		public TrieNode addChild(String s, boolean terminal){
			if(children == null){
				children = new HashMap<String, TrieTreeCn.TrieNode>();
			}
			TrieNode newChildhild = new TrieNode(s, this, terminal);
			children.put(s, newChildhild);
			return newChildhild;
		}
		
		public void setParent(TrieNode p){
			this.parent = p;
		}
		
		public TrieNode getParent(){
			return parent;
		}
		
		public String getValue(){
			return value;
		}
		
		public boolean hasKey(String s){
			return haveChildren() && children.containsKey(s);
		}
		
		public TrieNode getChild(String s){
			return children.get(s);
		}
		
		public void removeChild(String s){
			children.remove(s);
			if(children.isEmpty()){
				children = null;
			}
		}
		
		public void setValue(String s){
			this.value = s;
		}
		
		
	}
}
