package trees.trietrees;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import trees.trietree.TrieTreeCn;
import trees.trietree.TrieTreeCn.TrieNode;

public class TrieTreeCnTest {
	 
	TrieTreeCn cnTree = new TrieTreeCn();
	List<String> l1, l2, l3, l4;
	
	@Before
	public void prepare(){
		assertTrue(cnTree.getRoot() == null);
		 
		 String one = "我，喜欢，吃，橙子";
		 String two = "我，不，喜欢，吃，橙子";
		 String three = "她，喜欢，做，爱，做的，事情";
		 
		 String four = "她，喜欢，做，爱" ;
		 
		  l1 = new ArrayList<>();
		  l2 = new ArrayList<>();
		  l3 = new ArrayList<>();
		  l4 = new ArrayList<>();
		 
		 String[] s1 = one.split("，");
		 for(String s: s1){
			 l1.add(s);
		 }
		
		 String[] s2 = two.split("，");
		 for(String s: s2){
			 l2.add(s);
		 }
		 
		 String[] s3 = three.split("，");
		 for(String s: s3){
			 l3.add(s);
		 }
		 
		 String[] s4 = four.split("，");
		 for(String s : s4){
				l4.add(s);
			}
		 
		
	} 
	
	
	@Test
	public void testBuildTree(){
		 cnTree.buildTree(l1);
		 cnTree.buildTree(l2);
		 cnTree.buildTree(l3);
		 cnTree.buildTree(l4);
		 assertTrue(cnTree.getRoot().getValue() == null);
		 assertTrue(cnTree.getRoot().getParent() == null);
		 TrieNode first = cnTree.getRoot().getChild("我");
		 assertTrue(first != null);
		 TrieNode second = first.getChild("喜欢");
		 assertTrue(second != null);
		 TrieNode third = second.getChild("吃");
		 assertTrue(third != null);
		 TrieNode fourth = third.getChild("橙子");
		 assertTrue(fourth != null);
		 assertTrue("橙子".equals(fourth.getValue()));
		 assertTrue(fourth.isTerminal());
		 
		 TrieNode no = first.getChild("不");
		 assertTrue("不".equals(no.getValue()));
		 TrieNode dislike = no.getChild("喜欢");
		 assertTrue("喜欢".equals(dislike.getValue()));
		 TrieNode eat = dislike.getChild("吃");
		 assertTrue("吃".equals(eat.getValue()));
		 TrieNode orange = eat.getChild("橙子");
		 assertTrue("橙子".equals(orange.getValue()) && orange.isTerminal());
		 
		 TrieNode t_first = cnTree.getRoot().getChild("她");
		 assertTrue("她".equals(t_first.getValue()));
		 TrieNode t_second = t_first.getChild("喜欢");
		 assertTrue("喜欢".equals(t_second.getValue()));
		 TrieNode t_third = t_second.getChild("做");
		 assertTrue("做".equals(t_third.getValue()));
		 TrieNode t_fourth = t_third.getChild("爱");
		 assertTrue("爱".equals(t_fourth.getValue()));
		 TrieNode t_fifth = t_fourth.getChild("做的");
		 assertTrue("做的".equals(t_fifth.getValue()));
		 TrieNode t_sixth = t_fifth.getChild("事情");
		 assertTrue("事情".equals(t_sixth.getValue()) && t_sixth.isTerminal());
		 
		 assertTrue(t_fourth.isTerminal());
		 
		 cnTree.delete(l4);
		 assertTrue(!t_fourth.isTerminal());
		 
		 cnTree.delete(l3);
		 assertTrue(cnTree.getRoot().getChild("她") == null);
		 
		 cnTree.delete(l2);
		 assertTrue(first != null && first.getValue().equals("我"));
		 assertTrue(second != null && second.getValue().equals("喜欢"));
		 assertTrue(third != null && third.getValue().equals("吃"));
		 assertTrue(fourth != null );
		 assertTrue("橙子".equals(fourth.getValue()));
		 assertTrue(fourth.isTerminal());
		 
		 cnTree.delete(l1);
		 assertTrue(!cnTree.getRoot().haveChildren());
		 
		 cnTree.clear();
		assertTrue(cnTree.getRoot() == null);

	}
	
	@Test
	public void testChangeValue(){
		 prepare();
		 cnTree.buildTree(l1);
		 cnTree.buildTree(l2);
		 cnTree.buildTree(l3);
		 cnTree.buildTree(l4);
		
		 cnTree.changeValue(l1,"苹果");
		 TrieNode first = cnTree.getRoot().getChild("我");
		 assertTrue(first != null);
		 TrieNode second = first.getChild("喜欢");
		 assertTrue(second != null);
		 TrieNode third = second.getChild("吃");
		 assertTrue(third != null);
		 TrieNode fourth = third.getChild("苹果");
		 assertTrue("苹果".equals(fourth.getValue()));
		 assertTrue(fourth.isTerminal());
		
		
		
	}
	
}
