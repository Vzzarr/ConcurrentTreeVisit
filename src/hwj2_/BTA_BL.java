package hwj2_;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import binaryTreeUtils.BinaryTreeAdder;
import binaryTreeUtils.Node;

public class BTA_BL implements BinaryTreeAdder {

	private int availableProcessors;
	private int numElements;
	
	public BTA_BL(int numE) {
		availableProcessors = Runtime.getRuntime().availableProcessors();
		numElements = numE;
	}
	
	@Override
	public int computeOnerousSum(Node root) {
		int sum = 0;
		
		BlockingQueue<ArrayDeque<Node>> listDeque = new LinkedBlockingQueue<ArrayDeque<Node>>(availableProcessors);
		for(int i = 0; i < availableProcessors; i++)
			listDeque.add(new ArrayDeque<Node>(numElements));
		BlockingQueue<Node> buffer = new LinkedBlockingQueue<Node>();
		buffer.offer(root);
		
		ExecutorService pool = Executors.newFixedThreadPool(availableProcessors);
		CompletionService<Integer> taskCompletionService = new ExecutorCompletionService<Integer>(pool);
		CyclicBarrier barrierDeq = new CyclicBarrier(availableProcessors);

		for(int j = 0; j < availableProcessors; j++)
			taskCompletionService.submit(new BTATaskBL(buffer, barrierDeq, listDeque));
		for(int taskHandled = 0; taskHandled < availableProcessors; taskHandled++){
			try {
				sum += taskCompletionService.take().get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		pool.shutdown();
		return sum;
	}

}
