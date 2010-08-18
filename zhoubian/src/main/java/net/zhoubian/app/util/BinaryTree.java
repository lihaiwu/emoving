package net.zhoubian.app.util;

import java.util.HashMap;
import java.util.Map;

import net.zhoubian.app.model.User;

public class BinaryTree {

	private static class Node {

		long code; // data used as key value
		Map<Long, User> data; // other data
		Node leftChild; // this Node's left child
		Node rightChild; // this Node's right child

		public void displayNode() {
			// (see Listing 8.1 for method body)
			System.out.print("{" + code + ", ");
			for(Object o: data.keySet()){
				System.out.print(o + ":");
			}
			System.out.print("} ");
		}

	}

	private Node root; // the only data field in Tree

	public synchronized Node find(long code) // find Node with given code
	{ // (assumes non-empty tree)
		Node current = root; // start at root
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
	
	public synchronized boolean findUser(long code, User user) // find User with given code and User
	{ // (assumes non-empty tree)
		Node current = find(code);
		Map<Long, User> map = current.data;
		return map.containsKey(user.getUid())?true:false;
	}

	public Node minimum() // returns Node with minimum code value
	{
		Node current, last = null;
		current = root; // start at root
		while (current != null) // until the bottom,
		{
			last = current; // remember Node
			current = current.leftChild; // go to left child
		}
		return last;
	}

	public synchronized void insert(long code, User user) {
		Node newNode = null;
		Map<Long, User> map = null;
		if (root == null){
			newNode = new Node(); // make new Node
			newNode.code = code; // insert data
			map = new HashMap<Long, User>();
			map.put(user.getUid(), user);
			newNode.data = map;
			root = newNode;// no Node in root
		}
			
		else // root occupied
		{
			Node current = root; // start at root
			Node parent;
			while (true) // (exits internally)
			{
				parent = current;
				if (code == current.code){
					map = current.data;
					if(map.containsKey(user.getUid())){
						return;
					}
					map.put(user.getUid(), user);
					return;
				}
				else if (code < current.code) // go left?
				{
					current = current.leftChild;
					if (current == null) // if end of the line,
					{ // insert on left
						newNode = new Node(); // make new Node
						newNode.code = code; // insert data
						map = new HashMap<Long, User>();
						map.put(user.getUid(), user);
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
						newNode = new Node(); // make new Node
						newNode.code = code; // insert data
						map = new HashMap<Long, User>();
						map.put(user.getUid(), user);
						newNode.data = map;
						parent.rightChild = newNode;
						return;
					}
				} // end else go right
			} // end while
		} // end else not root
	} // end insert()

	// 用被删除节点A的右子树的最左节点作为替代A的节点，并修改相应的最左或最右节点的父节点的指针
	public synchronized boolean delete(long code, User user) // delete Node with given code
	{ // (assumes non-empty list)
		Node current = root;
		Node parent = root;
		Map<Long, User> map = null;
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
		map.remove(user.getUid());

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
				Node successor = getSuccessor(current);
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
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; // go to right child
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

	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			localRoot.displayNode();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			localRoot.displayNode();
			inOrder(localRoot.rightChild);
		}
	}

	private void postOrder(Node localRoot) {
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
		BinaryTree biTree = new BinaryTree();
		User user = new User();
		user.setUid(new Long(500));
		biTree.insert(50, user);
		user = new User();
		user.setUid(new Long(300));
		biTree.insert(30, user);
		
		user = new User();
		user.setUid(new Long(3000));
		biTree.insert(30, user);
		
		user = new User();
		user.setUid(new Long(250));
		biTree.insert(25, user);
		user = new User();
		user.setUid(new Long(350));
		biTree.insert(35, user);
		user = new User();
		user.setUid(new Long(550));
		biTree.insert(55, user);
		user = new User();
		user.setUid(new Long(530));
		biTree.insert(53, user);
		user = new User();
		user.setUid(new Long(600));
		biTree.insert(60, user);
		user = new User();
		user.setUid(new Long(100));
		biTree.insert(10, user);
		user = new User();
		user.setUid(new Long(150));
		biTree.insert(15, user);
		user = new User();
		user.setUid(new Long(320));
		biTree.insert(32, user);
		user = new User();
		user.setUid(new Long(330));
		biTree.insert(33, user);

		Node found = biTree.find(30);
		if (found != null)
			System.out.println("Found the Node with key 30");
		else
			System.out.println("Could not find node with key 30");
		// biTree.minimum().displayNode();
		biTree.printTree();
		System.out.println("删除后...");
		user = new User();
		user.setUid(new Long(300));
		biTree.delete(30, user);
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
