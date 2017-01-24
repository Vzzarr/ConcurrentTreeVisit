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

	private int value;

	public BTA_SI() {
		value = 0;
	}

	@Override
	public int computeOnerousSum(Node root) {
		return new LinkedListSpliterator(root).parallelStream()
				.map(n -> n.getValue()).reduce(0, (a, b) -> a + b).intValue();
		
		
		/*buffer.add(root);
		BTASpliterator bta_spliterator = new BTASpliterator(buffer, 0, buffer.size());
		Consumer<Node> action = x -> extractNode(x);
		System.out.println(buffer.size());
		while(bta_spliterator.tryAdvance(action)){
			
		}
		System.out.println(buffer.size());*/
		
		
		
//		processBuffer(buffer.spliterator());

//		Consumer<Node> action = x -> extractNode(x);
//		while(buffer.spliterator().tryAdvance(action)){
//			if(buffer.spliterator().estimateSize() > 5)
//				buffer.spliterator().trySplit();
//		}
		
//		System.out.println(buffer.size());

	}

//	private void processBuffer(Spliterator<Node> split){
////		Consumer<Node> action = x -> extractNode(x);
////		while(split.tryAdvance(action)){
////			if(split.estimateSize() > 5)
////				processBuffer(split.trySplit());
////		}
//		Consumer<Node> action = x -> extractNode(x);
//		while(buffer.spliterator().tryAdvance(action)){
//			if(buffer.spliterator().estimateSize() > 5)
//				buffer.spliterator().trySplit();
//		}
//	}
//
//	private void extractNode(Node node) {
//		buffer.remove(node);
//		System.out.println("******");
//		Node head_sx = node.getSx();
//		Node head_dx = node.getDx();
//
//		if(head_sx != null)
//			buffer.add(head_sx);
//		if(head_dx != null)
//			buffer.add(head_dx);
//		this.value += node.getValue();
//	}



	/**
	 * Mono-thread version
	 * @param root
	 * @return
	 */
	public int computeOnerousSumMonoThread(Node root){
		LinkedListSpliterator buffer = new LinkedListSpliterator(root);
		return buffer.stream().map(n -> n.getValue()).reduce(0, (a, b) -> a + b).intValue();
	}







}
