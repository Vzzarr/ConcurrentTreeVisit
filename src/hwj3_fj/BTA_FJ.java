package hwj3_fj;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;

import binaryTreeUtils.BinaryTreeAdder;
import binaryTreeUtils.Node;
import hwj1_LEF.BTATaskLEF;

public class BTA_FJ implements BinaryTreeAdder {

	public BTA_FJ() {
	}

	@Override
	public int computeOnerousSum(Node root) {
		BlockingQueue<Node> buffer = new LinkedBlockingQueue<Node>();
		buffer.offer(root);
		
		ForkJoinPool fjpool = new ForkJoinPool();
		return fjpool.invoke(new BTATaskFJ(buffer));
	}
	
	/**
	 * Mono-thread version
	 * @param root
	 * @return
	 */
	public int computeOnerousSumMonoThread(Node root){
		BlockingQueue<Node> buffer = new LinkedBlockingQueue<Node>();
		buffer.offer(root);
		
		ForkJoinPool fjpool = new ForkJoinPool(1);
		return fjpool.invoke(new BTATaskFJ(buffer));
	}
}
