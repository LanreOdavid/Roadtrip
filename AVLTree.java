package comp2402a5;
// Thanks to Pat Morin for the skeleton of this file!

import java.util.Random;

public class AVLTree<T> extends
		BinarySearchTree<AVLTree.Node<T>, T> implements SSet<T> {
	Random rand;

	public static class Node<T> extends BSTNode<Node<T>, T> {
		public int h; // the height of the node
	}

	public AVLTree() {
		sampleNode = new Node<T>();
		rand = new Random();
		c = new DefaultComparator<T>();
	}

	public int height(Node<T> u) {
		return (u == null) ? 0 : u.h;
	}

	public boolean add(T x) {
		Node<T> u = new Node<T>();
		u.x = x;
		if (super.add(u)) {
			for (Node<T> w = u; w != nil; w = w.parent) {
				// walk back up to the root adjusting heights
				w.h = Math.max(height(w.left), height(w.right)) + 1;
			}
			fixup(u);
			return true;
		}
		return false;
	}

	public void splice(Node<T> u) {
		Node<T> w = u.parent;
		super.splice(u);
		for (Node<T> z = u; z != nil; z = z.parent)
			z.h = Math.max(height(z.left), height(z.right)) + 1;
		fixup(w);
	}

	public void checkHeights(Node<T> u) {
		if (u == nil)
			return;
		checkHeights(u.left);
		checkHeights(u.right);
		if (height(u) != 1 + Math.max(height(u.left), height(u.right)))
			throw new RuntimeException("Check heights shows incorrect heights");
		int dif = height(u.left) - height(u.right);
		if (dif < -1 || dif > 1)
			throw new RuntimeException("Check heights found height difference of " + dif);
	}

	/**
	 * TODO: finish writing this method
	 * 
	 * @param u
	 */
	public void fixup(Node<T> u) {
		while (u != nil) {
			int dif = height(u.left) - height(u.right);
			if (dif > 1) {
				// left subtree is taller than right subtree
				if (height(u.left.left) >=height(u.left.right)) {
					// left-left case, do a right rotation
					rotateRight(u);
				} else {
					// left-right case, do a left-right rotation
					rotateLeft(u.left);
					rotateRight(u);
				}
			} else if (dif < -1) {
				// right subtree is taller than left subtree
				if (height(u.right.right) >= height(u.right.left)) {
					// right-right case, do a left rotation
					rotateLeft(u);
				} else {
					// right-left case, do a right-left rotation
					rotateRight(u.right);
					rotateLeft(u);
				}
			}
			// TODO: additional tasks, if necessary
			u = u.parent;
		}
	}

	public void rotateLeft(Node<T> u) {
		// TODO: Perform a left rotation and recompute height values at u and u.parent
		super.rotateLeft(u);

		for (Node<T> w = u; w != nil; w = w.parent) {
			
			w.h = Math.max(height(w.left), height(w.right)) + 1;
		}

	}

	public void rotateRight(Node<T> u) {
		// TODO: Perform a right rotation and recompute height values at u and u.parent
		super.rotateRight(u);

		for (Node<T> w = u; w != nil; w = w.parent) {
			
			w.h = Math.max(height(w.left), height(w.right)) + 1;
		}
	}
}
