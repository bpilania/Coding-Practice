import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;


public class ExploringTrees {

	public static void main(String args[]) {
		Tree tree = new Tree();
		TreeNode root = tree.insert(10);
		tree.insert(5);
		tree.insert(20);
		tree.insert(1);
		tree.insert(7);
		tree.insert(13);
		tree.insert(21);

		System.out.println("Duplicates are: "+tree.countDuplicates(root, null));
		tree.inOrderMorris(root);
		System.out.println("This was Morris in - retrival");

		tree.inOrderTraversalIteratively(root);
		System.out.println("This was iterative in - retrival");

		tree.preOrderTraversalIteratively(root);

		System.out.println("This was iterative pre - retrival");

		tree.postOrderTraversalIteratively(root);
		System.out.println("This was iterative post - retrival");
		ArrayList<LinkedList<TreeNode>> lists = tree
				.makeLinkedListFromLevelsBFS(root);
		System.out.println("Height of tree is: " + tree.findHeight(root));
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>();
		tree.createLinkedListOfLevelsRecursive(root, 0, list);

		TreeNode ancestor = tree.findFirstCommonAncestorOwn(root, 5, 20,
				new ArrayList<TreeNode>(),
				new Hashtable<Integer, ArrayList<TreeNode>>());
		System.out.println("Ancestor is: " + ancestor.value);

		TreeNode ancestorNew = tree.findFirstCommonAncestorOther(root, 5, 20);
		System.out.println("Ancestor is: " + ancestorNew.value);

		tree.inOrderTraversal(root);
		tree.isBalanced(root);

		int[] array = { 1, 2, 3, 4, 5, 6, 7 };

		TreeNode newTree = tree.minHeightTreeFromSortedNumbers(array, 0,
				array.length - 1);
		tree.inOrderTraversal(newTree);
		System.out.println("----------------");
		if (tree.isBST(newTree))
			System.out.println("Is BST");
		else
			System.out.println("Not a BST");

		if (tree.search(root, 0))
			System.out.println("Element Found");
		else
			System.out.println("Element not found");

		TreeNode btree = new TreeNode(1);
		TreeNode rootBtree = btree;

		btree.left = new TreeNode(2);
		btree = btree.left;

		btree.left = new TreeNode(4);
		btree = btree.left;

		btree.left = new TreeNode(3);
		btree = btree.left;

		btree.left = new TreeNode(4);
		btree = btree.left;

		btree.left = new TreeNode(3);
		btree = btree.left;

		btree.left = new TreeNode(1);
		btree = btree.left;

		btree = rootBtree;

		btree = btree.left;

		btree.right = new TreeNode(5);
		btree = btree.right;

		btree.right = new TreeNode(3);
		btree = btree.right;

		btree = rootBtree;

		btree.right = new TreeNode(3);
		btree = btree.right;

		btree.right = new TreeNode(5);
		btree = btree.right;

		btree = rootBtree;

		btree = btree.right;

		btree.left = new TreeNode(5);
		/*
		 * ArrayList<ArrayList<Integer>> list = new
		 * ArrayList<ArrayList<Integer>>(); tree.findPathsToSumToN(rootBtree,
		 * 10, new ArrayList<Integer>(), list); for(ArrayList<Integer> s :
		 * list){ System.out.println(s); }
		 */
		PathFinder pFind = tree.findAllPathsToN(rootBtree, 5, new PathFinder());
		for (Stack<Integer> stack : pFind.allPaths)
			System.out.println(stack);

		System.out.println(1 << 3);

	}
}

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;

	TreeNode(int value) {
		this.value = value;
		left = null;
		right = null;
	}
}

class Result {
	boolean foundA;
	boolean foundB;
	TreeNode common;

	public Result(boolean a, boolean b, TreeNode c) {
		foundA = a;
		foundB = b;
		common = c;
	}
}
	class Tree {
		TreeNode root = null;

		TreeNode insert(int value) {
			TreeNode node = new TreeNode(value);
			if (root == null) {
				root = node;
				return root;
			}
			System.out.println("Inserting " + node.value);
			insertHelper(root, node);

			return root;
		}

		public TreeNode findCommonAncestor(TreeNode root, TreeNode node1,
				TreeNode node2) {
			Result res = findCommonAncestorHelper(root, node1, node2);
			return res.common;
		}

		public Result findCommonAncestorHelper(TreeNode root, TreeNode node1,
				TreeNode node2) {
			if (root == null)
				return new Result(false, false, null);
			Result left = findCommonAncestorHelper(root.left, node1, node2);
			if (left.common != null)
				return left;
			if (root.value == node1.value)
				left.foundA = true;
			if (root.value == node2.value)
				left.foundB = true;
			if (left.foundA && left.foundB) {
				left.common = root;
				return left;
			}
			Result right = findCommonAncestorHelper(root.right, node1, node2);
			if (right.common != null)
				return right;
			if (root.value == node1.value)
				right.foundA = true;
			if (root.value == node2.value)
				right.foundB = true;
			if (right.foundA && right.foundB) {
				right.common = root;
				return right;
			}
			left.foundA = left.foundA | right.foundA;
			left.foundB = left.foundB | right.foundB;
			if (left.foundA && left.foundB)
				left.common = root;
			return left;
		}

		public void inOrderMorris(TreeNode root) {
			TreeNode current = root;
			TreeNode pre = null;
			
		    while (current != null) {
		        if (current.left == null) {
		            System.out.println(current.value);
		            current = current.right;
		        } else {
		            pre = current.left;

		            while (pre.right != null && pre.right != current) {
		                pre = pre.right;
		            }

		            if (pre.right == null) {
		                pre.right = current;
		                current = current.left;
		            } else {
		                pre.right = null;
			            System.out.println(current.value);
		                current = current.right;
		            }
		        }
		    }
		}
		

		int countDuplicates(TreeNode node, TreeNode lastSeen){
			if(node == null){
				return 0;
			}
			int count;
			count = countDuplicates(node.left, lastSeen);
			if(lastSeen != null && lastSeen.value == node.value){
				count++;
			}
			lastSeen = node;
			count = count + countDuplicates(node.right, lastSeen);
			
			return count;
		}


	TreeNode insertHelper(TreeNode root, TreeNode node) {
		if(root == null) return node;
		if(node.value < root.value)
			root.left = insertHelper(root.left, node);
		else
			root.right = insertHelper(root.right, node);
		return root;
	}

	void inOrderTraversalIteratively(TreeNode node) {
		if (node == null)
			return;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);
		TreeNode current = node;
		while (stack.size() > 0) {
			if (stack.peek().left != null && current != null) {
				current = current.left;
				stack.push(current);
				continue;
			}
			current = stack.pop();
			System.out.println(current.value);
			current = current.right;
			if (current != null) {
				stack.push(current);
			}
		}
	}

	void preOrderTraversalIteratively(TreeNode node) {
		if (node == null)
			return;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);
		TreeNode current = node;
		System.out.println(current.value);
		while (!stack.isEmpty()) {
			if (current != null && current.left != null) {
				current = current.left;
				stack.push(current);
				System.out.println(current.value);
				continue;
			}
			current = stack.pop().right;
			if (current != null) {
				stack.push(current);
				System.out.println(current.value);
			}

		}

	}

	void postOrderTraversalIteratively(TreeNode node) {
		if (node == null)
			return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);
		TreeNode lastPop = null;
		TreeNode current = null;
		while (!stack.isEmpty()) {
			current = stack.peek();
			if (lastPop != current.left && lastPop != current.right
					&& current.left != null) {
				stack.push(current.left);
				continue;
			}
			if (lastPop != current.right && current.right != null) {
				stack.push(current.right);
				continue;
			}
			lastPop = stack.pop();
			System.out.println(lastPop.value);
		}
	}

	void inOrderTraversal(TreeNode root) {
		if (root == null)
			return;

		inOrderTraversal(root.left);
		System.out.println(root.value);
		inOrderTraversal(root.right);
	}

	void isBalanced(TreeNode root) {

		try {
			isBalancedHelper(root);
			System.out.println("Balanced");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	int isBalancedHelper(TreeNode root) throws Exception {
		if (root == null)
			return 0;

		int lHeight = isBalancedHelper(root.left);

		int rHeight = isBalancedHelper(root.right);

		if (Math.abs(lHeight - rHeight) > 1)
			throw new Exception("unbalanced tree");

		return Math.max(lHeight, rHeight) + 1;

	}

	TreeNode minHeightTreeFromSortedNumbers(int[] values, int left, int right) {
		if (left > right)
			return null;

		int mid = (right + left) / 2;

		TreeNode midNode = new TreeNode(values[mid]);
		midNode.left = minHeightTreeFromSortedNumbers(values, left, mid - 1);
		midNode.right = minHeightTreeFromSortedNumbers(values, mid + 1, right);

		return midNode;
	}

	static public ArrayList<LinkedList<TreeNode>> makeLinkedListFromLevelsBFS(
			TreeNode root) {
		if (root == null)
			return null;
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		LinkedList<TreeNode> l1 = new LinkedList<TreeNode>();
		l1.add(root);
		Queue<LinkedList<TreeNode>> q1 = new LinkedList<LinkedList<TreeNode>>();
		q1.add(l1);

		while (!q1.isEmpty()) {
			LinkedList<TreeNode> poped = q1.poll();
			result.add(poped);
			LinkedList<TreeNode> newList = new LinkedList<TreeNode>();
			for (TreeNode tn : poped) {
				if (tn.left != null)
					newList.add(tn.left);
				if (tn.right != null)
					newList.add(tn.right);
			}
			if (newList.size() > 0)
				q1.add(newList);
		}

		return result;
	}

	boolean isBST(TreeNode node) {
		if (node.left == null && node.right == null
				|| (node.left == null && node.value <= node.right.value)
				|| (node.right == null && node.value > node.left.value))
			return true;

		if (node.left.value >= node.value || node.value > node.right.value)
			return false;

		return isBST(node.left) && isBST(node.right);

	}

	boolean search(TreeNode node, int element) {
		if (node == null)
			return false;

		if (node.value == element)
			return true;

		return search(node.left, element) || search(node.right, element);

	}

	ArrayList<Integer> findPathsToSumToN(TreeNode node, int sum,
			ArrayList<Integer> path, ArrayList<ArrayList<Integer>> list) {
		if (node == null)
			return null;

		path = new ArrayList<Integer>(path);

		path.add(node.value);
		ArrayList<Integer> addPath = findPathsHelper(path, sum);
		if (addPath != null) {
			list.add(addPath);
		}
		findPathsToSumToN(node.left, sum, path, list);

		findPathsToSumToN(node.right, sum, path, list);

		return path;
	}

	ArrayList<Integer> findPathsHelper(ArrayList<Integer> path, int value) {
		if (path == null)
			return null;

		ArrayList<Integer> addPath = new ArrayList<Integer>();

		int sum = 0;

		for (int i = path.size() - 1; i >= 0; i--) {
			sum = sum + path.get(i);
			addPath.add(path.get(i));
			if (sum == value)
				return addPath;
		}
		return null;
	}

	PathFinder findAllPathsToN(TreeNode node, int value, PathFinder pFind) {
		if (node == null)
			return null;

		pFind.path.push(node.value);

		if (node.value == value) {
			pFind.allPaths.add((Stack<Integer>) pFind.path.clone());
		}

		PathFinder pFindLeftStatus = findAllPathsToN(node.left, value, pFind);
		PathFinder pFindRightStatus = findAllPathsToN(node.right, value, pFind);

		pFind.path.pop();

		return pFind;
	}

	PathFinder findPathToN(TreeNode node, int value, PathFinder pFind) {

		if (node == null)
			return pFind;

		if (pFind.found == true)
			return pFind;

		pFind.path.push(node.value);

		if (node.value == value) {
			pFind.found = true;
			pFind.allPaths.add(pFind.path);
			return pFind;
		}

		pFind = findPathToN(node.left, value, pFind);
		pFind = findPathToN(node.right, value, pFind);

		if (pFind.found == false)
			pFind.path.pop();

		return pFind;
	}

	static int findHeight(TreeNode node) {
		if (node == null)
			return 0;

		return 1 + Math.max(findHeight(node.left), findHeight(node.right));
	}

	void createLinkedListOfLevelsRecursive(TreeNode node, int level,
			ArrayList<LinkedList<Integer>> list) {
		if (node == null)
			return;
		try {
			if (list.get(level) != null) {
				list.get(level).add(node.value);
			}
		} catch (Exception e) {
			list.add(new LinkedList<Integer>());
			list.get(level).add(node.value);
		}

		createLinkedListOfLevelsRecursive(node.left, level + 1, list);
		createLinkedListOfLevelsRecursive(node.right, level + 1, list);

	}

	TreeNode findFirstCommonAncestorOwn(TreeNode node, int a, int b,
			ArrayList<TreeNode> path,
			Hashtable<Integer, ArrayList<TreeNode>> paths) {
		if (node == null)
			return null;

		path.add(node);

		if (node.value == a && !paths.containsKey(a))
			paths.put(a, new ArrayList<TreeNode>(path));
		else if (node.value == b && !paths.containsKey(b))
			paths.put(b, new ArrayList<TreeNode>(path));

		if (paths.size() == 2) {
			ArrayList<TreeNode> one = paths.get(a);
			ArrayList<TreeNode> two = paths.get(b);
			int index = 0;
			while (one.get(index).value == two.get(index).value)
				index++;
			return one.get(index - 1);
		}
		TreeNode left = findFirstCommonAncestorOwn(node.left, a, b, path, paths);
		TreeNode right = findFirstCommonAncestorOwn(node.right, a, b, path,
				paths);

		path.remove(path.size() - 1);

		return left != null ? left : right;

	}

	TreeNode findFirstCommonAncestorOther(TreeNode node, int a, int b) {
		if (node == null)
			return null;

		boolean isAOnLeft = search(node.left, a);
		boolean isBOnLeft = search(node.left, b);

		if (isAOnLeft != isBOnLeft)
			return node;

		TreeNode temp;
		if (isAOnLeft) {
			temp = findFirstCommonAncestorOther(node.left, a, b);
			if (temp != null)
				return temp;
		} else
			temp = findFirstCommonAncestorOther(node.right, a, b);

		return temp;
	}

}

class PathFinder {
	Stack<Integer> path = new Stack<Integer>();
	ArrayList<Stack<Integer>> allPaths = new ArrayList<Stack<Integer>>();
	boolean found = false;
}
