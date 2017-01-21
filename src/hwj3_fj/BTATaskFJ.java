package hwj3_fj;

import java.util.concurrent.RecursiveTask;

import binaryTreeUtils.Node;

public class BTATaskFJ extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int THRESHOLD = 5;
	private Node node;
	
	public BTATaskFJ(Node node) {
		this.node = node;
	}

	@Override
	protected Integer compute() {
		if(nodeNumber(node) < THRESHOLD)
			return computeSerial(node);
		else{
			BTATaskFJ left = new BTATaskFJ(node.getSx());
			BTATaskFJ right = new BTATaskFJ(node.getDx());
			left.fork();
			int rightAns = right.compute().intValue();
			int leftAns = left.join().intValue();
			return leftAns + rightAns + node.getValue();
		}
	}
	
	/***
	 * calculates the sum of all the values of nodes in the tree recursively
	 * @param node
	 * @return
	 */
	private int computeSerial(Node node){
		int value;
		if(node == null)
			value = 0;
		else{
			value = node.getValue();
			if(node.getSx() != null)
				value += computeSerial(node.getSx());
			if(node.getDx() != null)
				value += computeSerial(node.getDx());
		}
		return value;
	}
	
	/**
	 * calculates the number of nodes in the input tree
	 * @param node
	 * @return
	 */
	private int nodeNumber(Node node){
		if(node == null)
			return 0;
		else
			return 1 + nodeNumber(node.getSx()) + nodeNumber(node.getDx());
	}
}
