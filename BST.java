package bst;

public class BST<T extends Comparable<T>>{
	private Node<T>				root;
	private int					size;

	class Node<T extends Comparable<T>>{
		private	T				data;
		private Node<T>			parent;
		private Node<T>			left_child;
		private Node<T>			right_child;

		//Constructors are private so that they are not accessible by the user. 
		private Node(){
			data				= null;
			parent				= null;
			left_child			= null;
			right_child			= null;
		}
		private Node(T data){
			this.data			= data;
			parent				= null;
			left_child			= null;
			right_child			= null;
		}
		T get_data() {
			return data;
		}
		void set_data(T data) {
			this.data 			= data;
		}
		Node<T> get_left_child(){
			return left_child;
		}
		Node<T> get_right_child(){
			return right_child;
		}
		Node<T> get_parent(){
			return parent;
		}
		boolean is_leaf() {
			return left_child == null && right_child == null;
		}

		boolean set_left_child(Node<T> new_left_child) {
			if (new_left_child == this)
				return false;

			left_child			= new_left_child;
			if (new_left_child != null)
				new_left_child.parent = this;
			return true;
		}
		boolean set_right_child(Node<T> new_right_child) {
			if (new_right_child == this)
				return false;

			right_child			= new_right_child;
			if (new_right_child != null)
				new_right_child.parent = this;
			return true;
		}
	}

	public BST() {
		root					= new Node<>();
	}
	public BST(T data) {
		root					= new Node<>(data);
	}
	private boolean go_left(Node<T> root_node, T data) {
		return root_node.data.compareTo(data) > 0;
	}
	private boolean go_right(Node<T> root_node, T data) {
		return root_node.data.compareTo(data) < 0;
	}
	
	public Node<T> find(Node<T> root, T data){
		if (null == root || (root.is_leaf() && root.data.compareTo(data) != 0))
			return null;

		if (root.data.compareTo(data) == 0)
			return root;

		else if (go_left(root, data))
			return find(root.left_child, data);

		else
			return find(root.right_child, data);
	}

	public boolean insert(T data) {
		if (null == data)
			return false;

		Node<T> new_node 		= new Node<>(data);
		find_place_for(root, new_node);		
		size++;
		return true;
	}

	private Node<T> find_place_for(Node<T> root_of_subtree, Node<T> new_node){
		if (null == root_of_subtree.data) {
			root_of_subtree.data = new_node.data;
			root_of_subtree.set_left_child(new_node.left_child);
			root_of_subtree.set_right_child(new_node.right_child);
			return root_of_subtree;
		}

		else if (go_left(root_of_subtree, new_node.data)) {
			if (null == root_of_subtree.left_child) {
				root_of_subtree.set_left_child(new_node);
				return root_of_subtree;
			}
			else
				return find_place_for(root_of_subtree.left_child, new_node);
		}
		else {
			if (null == root_of_subtree.right_child) {
				root_of_subtree.set_right_child(new_node);
				return root_of_subtree;
			}
			else
				return find_place_for(root_of_subtree.right_child, new_node);
		}
	}

	public boolean delete(T data) {
		Node<T> node	= find(root, data);
		if (null == node)
			return false;
		
		if (root == node) {
			root 		= null;
			size		= 0;
			return true;
		}

		BST<T>.Node<T> parent_node				= node.parent;
		if (node == parent_node.left_child)
			parent_node.set_left_child(null);
		else
			parent_node.set_right_child(null);

		size 			= size - size_of(node);
		return true;
	}

	public Node<T> get_root(){
		return root;
	}

	private int size_of(Node<T> root_of_subtree) {
		if (null == root_of_subtree)
			return 0;
		if (root_of_subtree.is_leaf())
			return 1;
		else
			return 1 + size_of(root_of_subtree.left_child) + size_of(root_of_subtree.right_child);
	}
	
	private Node<T> get_least_common_ancestor(Node<T> first, Node<T> second){
		if (root == first || root == second)
			return null;
		if (first == second)
			return first.parent;
		if (first == second.parent)
			return first.parent;
		if (second == first.parent)
			return second.parent;
		
		//the nodes could belong to the same path or different paths
		Node<T> current_root	= root;
		
		//while they go in the same direction
		while ((go_left(current_root, first.data) && go_left(current_root, second.data)) || (go_right(current_root, first.data) && go_right(current_root, second.data))) {
			if (current_root == first.parent || current_root == second.parent)
				return current_root;
			
			if (go_left(current_root, first.data))
				current_root 	= current_root.left_child;
			else
				current_root 	= current_root.right_child;
		}
		
		return current_root;
	}
	
	public T get_least_common_ancestor(T first, T second) {
		if (null == first || null == second)
			return null;
		
		Node<T> first_node 		= find(root, first);
		Node<T> second_node 	= find(root, second);
		
		if (null == first_node || null == second_node)
			return null;
		
		Node<T> least_common_ancestor = get_least_common_ancestor(first_node, second_node);
		if (null != least_common_ancestor)
			return least_common_ancestor.data;
		return null;
	}
	
	public int get_size() {
		return size;
	}
}
