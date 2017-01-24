package hwj4_si;

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
import hwj1_LEF.BTATaskLEF;

public class BTA_SI implements BinaryTreeAdder {

	@Override
	public int computeOnerousSum(Node root) {
		return new LinkedListSpliterator(root).parallelStream()
				.map(n -> n.getValue()).reduce(0, (a, b) -> a + b).intValue();
	}

	/**
	 * Mono-thread version
	 * @param root
	 * @return
	 */
	public int computeOnerousSumMonoThread(Node root){
		int sum = 0;
		
		BlockingQueue<Node> buffer = new LinkedBlockingQueue<Node>();
		buffer.offer(root);
		
		ExecutorService pool = Executors.newFixedThreadPool(1);
		CompletionService<Integer> taskCompletionService = new ExecutorCompletionService<Integer>(pool);
		CyclicBarrier barrier = new CyclicBarrier(1);

		for(int i = 0; i < 1; i++)
			taskCompletionService.submit(new BTATaskLEF(buffer, barrier));
		for(int taskHandled = 0; taskHandled < 1; taskHandled++){
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
