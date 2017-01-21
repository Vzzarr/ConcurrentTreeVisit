package hwj3_fj;

import java.util.concurrent.ForkJoinPool;

import binaryTreeUtils.BinaryTreeAdder;
import binaryTreeUtils.Node;

public class BTA_FJ implements BinaryTreeAdder {

	public BTA_FJ() {
	}

	@Override
	public int computeOnerousSum(Node root) {		
		ForkJoinPool fjpool = new ForkJoinPool();
		return fjpool.invoke(new BTATaskFJ(root));
	}
	
	/**
	 * Mono-thread version
	 * @param root
	 * @return
	 */
	public int computeOnerousSumMonoThread(Node root){
		ForkJoinPool fjpool = new ForkJoinPool(1);
		return fjpool.invoke(new BTATaskFJ(root));
	}
}
