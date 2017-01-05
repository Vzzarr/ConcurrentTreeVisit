package hwj1;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import binaryTree.BinaryTreeAdder;
import binaryTree.Node;

public class BTA implements BinaryTreeAdder{

	private int availableProcessors;

	public BTA(){
		availableProcessors = Runtime.getRuntime().availableProcessors();
	}


	@Override
	public int computeOnerousSum(Node root) {
		int sum = 0;
		
		BlockingQueue<Node> buffer = new LinkedBlockingQueue<Node>();
		buffer.offer(root);
		
		ExecutorService pool = Executors.newFixedThreadPool(availableProcessors);
		CompletionService<Integer> taskCompletionService = new ExecutorCompletionService<Integer>(pool);
		CyclicBarrier barrier = new CyclicBarrier(availableProcessors);

		for(int i = 0; i < availableProcessors; i++)
			taskCompletionService.submit(new BTATask(buffer, barrier));
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
			taskCompletionService.submit(new BTATask(buffer, barrier));
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

	
	
	
	
	
//	public int computeOnerousSumTask(Node root){
//		buffer.offer(root);
//		FutureTask<Integer> future = new FutureTask<Integer>(new Callable<Integer>() {
//			public Integer call() throws Exception {
//				while(!buffer.isEmpty()){
//					Node head = buffer.take();
//					Node head_sx = head.getSx();
//					Node head_dx = head.getDx();
//					if(head_sx != null)
//						buffer.put(head_sx);
//					if(head_dx != null)
//						buffer.put(head_dx);
//					lockRV.lock();
//					try {
//						returnValue = returnValue + head.getValue();
//					} finally {
//						lockRV.unlock();
//					}
//				}
//				return returnValue;
//			}
//		});
//		pool.execute(future);
//		while(true){
//			try {
//				if(future.isDone())
//					return returnValue;
//			} catch (Exception e) {}
//
//		}
//	}
}
