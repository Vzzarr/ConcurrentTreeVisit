package hwj4_si;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

import binaryTreeUtils.BinaryTreeAdder;
import binaryTreeUtils.Node;
import hwj1_LEF.BTATaskLEF;

public class BTA_SI implements BinaryTreeAdder {

	private List<Node> buffer;
	private int value;

	public BTA_SI() {
		buffer = new ArrayList<>();
		value = 0;
	}

	@Override
	public int computeOnerousSum(Node root) {
		buffer.add(root);
		BTA_Spliterator bta_spliterator = new BTA_Spliterator(buffer, 0, buffer.size());
		Consumer<Node> action = x -> extractNode(x);
		System.out.println(buffer.size());
		while(bta_spliterator.tryAdvance(action)){
			
		}
		System.out.println(buffer.size());
		
		
		
//		processBuffer(buffer.spliterator());

//		Consumer<Node> action = x -> extractNode(x);
//		while(buffer.spliterator().tryAdvance(action)){
//			if(buffer.spliterator().estimateSize() > 5)
//				buffer.spliterator().trySplit();
//		}
		
//		System.out.println(buffer.size());

		return this.value;
	}

	private void processBuffer(Spliterator<Node> split){
//		Consumer<Node> action = x -> extractNode(x);
//		while(split.tryAdvance(action)){
//			if(split.estimateSize() > 5)
//				processBuffer(split.trySplit());
//		}
		Consumer<Node> action = x -> extractNode(x);
		while(buffer.spliterator().tryAdvance(action)){
			if(buffer.spliterator().estimateSize() > 5)
				buffer.spliterator().trySplit();
		}
	}

	private void extractNode(Node node) {
		buffer.remove(node);
		System.out.println("******");
		Node head_sx = node.getSx();
		Node head_dx = node.getDx();

		if(head_sx != null)
			buffer.add(head_sx);
		if(head_dx != null)
			buffer.add(head_dx);
		this.value += node.getValue();
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
