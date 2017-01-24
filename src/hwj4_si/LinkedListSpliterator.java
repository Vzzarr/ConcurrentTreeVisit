package hwj4_si;

import java.util.LinkedList;
import java.util.Spliterator;

import binaryTreeUtils.Node;

public class LinkedListSpliterator extends LinkedList<Node> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Node node;
	private LinkedList<Node> list;
	
	public LinkedListSpliterator(Node node) {
		this.node = node;
		this.list = new LinkedList<>();
		this.list.add(this.node);
	}

	@Override
	public Spliterator<binaryTreeUtils.Node> spliterator() {
		return new BTASpliterator(this.list, 0, this.list.size());
	}

}
