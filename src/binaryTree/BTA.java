package binaryTree;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BTA implements BinaryTreeAdder{

	private int availableProcessors;
	private final ExecutorService pool;
	private BlockingQueue<Node> buffer;

	public BTA(){
		availableProcessors = Runtime.getRuntime().availableProcessors();
		pool = Executors.newFixedThreadPool(availableProcessors);
		buffer = new LinkedBlockingQueue<Node>();
	}


	@Override
	public int computeOnerousSum(Node root) {
		int sum = 0;
		buffer.offer(root);
		CompletionService<Integer> taskCompletionService = new ExecutorCompletionService<Integer>(pool);

		for(int i = 0; i < availableProcessors; i++){
			taskCompletionService.submit(
					new Callable<Integer>() {

						@Override
						public Integer call() throws Exception {
							int val = 0;
							while(!buffer.isEmpty()){
								Node head = buffer.take();
								Node head_sx = head.getSx();
								Node head_dx = head.getDx();
								if(head_sx != null)
									buffer.put(head_sx);
								if(head_dx != null)
									buffer.put(head_dx);
								val = val + head.getValue();
							}
							System.out.println(val);
							return val;
						}
					});
		}
		for(int taskHandled = 0; taskHandled < availableProcessors; taskHandled++){
			try {
//				System.out.println(taskCompletionService.take().get());
				sum += taskCompletionService.take().get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return sum;
	}

	/**
	 * Mono-thread version
	 * @param root
	 * @return
	 */
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
