package net.zhoubian.app.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.zhoubian.app.model.User;

public class BinaryTree<T,V> {

	public static final class Node<K,L> {

		long code; // data used as key value
		Map<K, L> data; // other data
		Node<K,L> leftChild; // this Node's left child
		Node<K,L> rightChild; // this Node's right child

		public void displayNode() {
			// (see Listing 8.1 for method body)
			System.out.print("{" + code + ", ");
			for(Object o: data.keySet()){
				System.out.print(o + ":");
			}
			System.out.print("} ");
		}
		
		public Map<K, L> getData() {
			return data;
		}

	}

	private Node<T,V> root; // the only data field in Tree

	public synchronized Node<T,V> find(long code) // find Node with given code
	{ // (assumes non-empty tree)
		Node<T,V> current = root; // start at root
		while (current.code != code) // while no match,
		{
			if (code < current.code) // go left?
				current = current.leftChild;
			else
				current = current.rightChild; // or go right?
			if (current == null) // if no child,
				return null; // didn't find it
		}
		return current; // found it
	}

	public Node<T,V> minimum() // returns Node with minimum code value
	{
		Node<T,V> current, last = null;
		current = root; // start at root
		while (current != null) // until the bottom,
		{
			last = current; // remember Node
			current = current.leftChild; // go to left child
		}
		return last;
	}

	public synchronized void insert(long code, T t, V v) {
		Node<T,V> newNode = null;
		Map<T, V> map = null;
		if (root == null){
			newNode = new Node<T,V>(); // make new Node
			newNode.code = code; // insert data
			map = new HashMap<T, V>();
			map.put(t, v);
			newNode.data = map;
			root = newNode;// no Node in root
		}
			
		else // root occupied
		{
			Node<T,V> current = root; // start at root
			Node<T,V> parent;
			while (true) // (exits internally)
			{
				parent = current;
				if (code == current.code){
					map = current.data;
					if(map.containsKey(t)){
						return;
					}
					map.put(t, v);
					return;
				}
				else if (code < current.code) // go left?
				{
					current = current.leftChild;
					if (current == null) // if end of the line,
					{ // insert on left
						newNode = new Node<T,V>(); // make new Node
						newNode.code = code; // insert data
						map = new HashMap<T, V>();
						map.put(t, v);
						newNode.data = map;
						parent.leftChild = newNode;
						return;
					}
				} // end if go left
				else // or go right?
				{
					current = current.rightChild;
					if (current == null) // if end of the line
					{ // insert on right
						newNode = new Node<T,V>(); // make new Node
						newNode.code = code; // insert data
						map = new HashMap<T, V>();
						map.put(t, v);
						newNode.data = map;
						parent.rightChild = newNode;
						return;
					}
				} // end else go right
			} // end while
		} // end else not root
	} // end insert()

	// 用被删除节点A的右子树的最左节点作为替代A的节点，并修改相应的最左或最右节点的父节点的指针
	public synchronized boolean delete(long code, T t) // delete Node with given code
	{ // (assumes non-empty list)
		Node<T,V> current = root;
		Node<T,V> parent = root;
		Map<T, V> map = null;
		boolean isLeftChild = true;
		while (current.code != code) // search for Node
		{
			parent = current;
			if (code < current.code) // go left?
			{
				isLeftChild = true;
				current = current.leftChild;
			} else // or go right?
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) // end of the line,
				return false; // didn't find it
		} // end while
		// found Node to delete
		// continues...
		
		map = current.data;
		map.remove(t);

		if(map.isEmpty()){//如果节点上没有数据
			// delete() continued...
			// if no children, simply delete it
			if (current.leftChild == null && current.rightChild == null) {
				if (current == root){// if root,
					root = null; // tree is empty
				}
					
				else if (isLeftChild){
					parent.leftChild = null; // disconnect
				}
					
				else
					// from parent
					parent.rightChild = null;
			}
			// continues...

			// delete() continued...
			// if no right child, replace with left subtree
			else if (current.rightChild == null)
				if (current == root)
					root = current.leftChild;
				else if (isLeftChild) // left child of parent
					parent.leftChild = current.leftChild;
				else
					// right child of parent
					parent.rightChild = current.leftChild;
			// if no left child, replace with right subtree
			else if (current.leftChild == null)
				if (current == root)
					root = current.rightChild;
				else if (isLeftChild) // left child of parent
					parent.leftChild = current.rightChild;
				else
					// right child of parent
					parent.rightChild = current.rightChild;
			// continued...

			// delete() continued
			else // two children, so replace with inorder successor
			{
				// get successor of Node to delete (current)
				Node<T,V> successor = getSuccessor(current);
				// connect parent of current to successor instead
				if (current == root)
					root = successor;
				else if (isLeftChild)
					parent.leftChild = successor;
				else
					parent.rightChild = successor;
				// connect successor to current's left child
				successor.leftChild = current.leftChild;
			} // end else two children
			// (successor cannot have a left child)
		}
		
		return true;
	} // end delete()

	public void printTree() {
		System.out.print("前序遍历：");
		preOrder(root);
		System.out.println();
		System.out.print("中序遍历：");
		inOrder(root);
		System.out.println();
		System.out.print("后序遍历：");
		postOrder(root);
		System.out.println();
	}

	// returns Node with next-highest value after delNode
	// goes to right child, then right child's left descendants
	// 获得右子树的最左节点
	private Node<T,V> getSuccessor(Node<T,V> delNode) {
		Node<T,V> successorParent = delNode;
		Node<T,V> successor = delNode;
		Node<T,V> current = delNode.rightChild; // go to right child
		while (current != null) // until no more
		{ // left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left child
		}
		// if successor not
		if (successor != delNode.rightChild) // right child,
		{ // make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;// ?
		}
		return successor;
	}

	private void preOrder(Node<T,V> localRoot) {
		if (localRoot != null) {
			localRoot.displayNode();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void inOrder(Node<T,V> localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			localRoot.displayNode();
			inOrder(localRoot.rightChild);
		}
	}

	private void postOrder(Node<T,V> localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.displayNode();
		}
	}

	public static void main(String[] args) {
		testBinaryTree();
	}
	
	public static void testBinaryTree(){
		TreeMap<Long,Object> treeMap = new TreeMap<Long,Object>();
		
		BinaryTree<Long,User> biTree = new BinaryTree<Long,User>();
		User user = new User();
		user.setUid(new Long(500));
		biTree.insert(50, user.getUid(), user);
		treeMap.put(new Long(50), user);
		
		user = new User();
		user.setUid(new Long(300));
		biTree.insert(30, user.getUid(), user);
		treeMap.put(new Long(30), user);
		
		user = new User();
		user.setUid(new Long(3000));
		biTree.insert(30, user.getUid(), user);
		treeMap.put(new Long(30), user);
		
		user = new User();
		user.setUid(new Long(250));
		biTree.insert(25, user.getUid(), user);
		treeMap.put(new Long(25), user);
		
		user = new User();
		user.setUid(new Long(350));
		biTree.insert(35, user.getUid(), user);
		treeMap.put(new Long(35), user);
		
		user = new User();
		user.setUid(new Long(550));
		biTree.insert(55, user.getUid(), user);
		treeMap.put(new Long(55), user);
		
		user = new User();
		user.setUid(new Long(530));
		biTree.insert(53, user.getUid(), user);
		treeMap.put(new Long(53), user);
		
		user = new User();
		user.setUid(new Long(600));
		biTree.insert(60, user.getUid(), user);
		treeMap.put(new Long(60), user);
		
		user = new User();
		user.setUid(new Long(100));
		biTree.insert(10, user.getUid(), user);
		treeMap.put(new Long(10), user);
		
		user = new User();
		user.setUid(new Long(150));
		biTree.insert(15, user.getUid(), user);
		treeMap.put(new Long(15), user);
		
		user = new User();
		user.setUid(new Long(320));
		biTree.insert(32, user.getUid(), user);
		treeMap.put(new Long(32), user);
		
		user = new User();
		user.setUid(new Long(330));
		biTree.insert(33, user.getUid(), user);
		treeMap.put(new Long(33), user);
		
		System.out.println(treeMap);
		System.out.println(treeMap.ceilingEntry(new Long(33)));

		Node<Long,User> found = biTree.find(30);
		if (found != null)
			System.out.println("Found the Node with key 30");
		else
			System.out.println("Could not find node with key 30");
		// biTree.minimum().displayNode();
		biTree.printTree();
		System.out.println("删除后...");
		user = new User();
		user.setUid(new Long(300));
		biTree.delete(30, user.getUid());
		biTree.printTree();
		
		//删除前树结构图，只列出key
//							50
//				30						55
//		25				35		53				60
//	10				32
//		15				33
		//删除key为30节点后
//							50
//				32						55
//		25				35		53				60
//	10				33
//		15
	}

}
