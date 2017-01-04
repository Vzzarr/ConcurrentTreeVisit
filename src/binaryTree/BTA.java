package binaryTree;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class BTA implements BinaryTreeAdder{

	private final ExecutorService pool;
	private BlockingQueue<Node> buffer;
	
	public BTA(){
		pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	
	@Override
	public int computeOnerousSum(Node root) {
		buffer.offer(root);
		for(int i = 0; i < 4; i++){	//riscrivere meglio: finché l'albero non è finito
			FutureTask<Integer> future = new FutureTask<Integer>(new Callable<Integer>() {
				public Integer call() throws Exception {
					Node head = buffer.take();
					Node head_sx = head.getSx();
					Node head_dx = head.getDx();
					buffer.put(head_sx);
					buffer.put(head_dx);
					return head.getValue();
				}
			});
			pool.execute(future);
		}
		return 0;
	}

}
