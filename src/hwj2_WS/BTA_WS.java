package hwj2_WS;

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

public class BTA_WS implements BinaryTreeAdder {

	//se l'albero è bilanciato è pari al logaritmo del numero di nodi,  la profondità altrimenti
	private int numElements;	
	private int availableProcessors;
	
	public BTA_WS(int numE) {
		availableProcessors = Runtime.getRuntime().availableProcessors();
		numElements = numE;
	}
	
	@Override
	public int computeOnerousSum(Node root) {
		int sum = 0;
		
		BlockingQueue<ArrayDeque<Node>> listDeque = new LinkedBlockingQueue<ArrayDeque<Node>>(availableProcessors);
		for(int i = 0; i < availableProcessors; i++)
			listDeque.add(new ArrayDeque<Node>(availableProcessors * numElements));
		BlockingQueue<Node> buffer = new LinkedBlockingQueue<Node>();
		buffer.offer(root);
		
		ExecutorService pool = Executors.newFixedThreadPool(availableProcessors);
		CompletionService<Integer> taskCompletionService = new ExecutorCompletionService<Integer>(pool);
		CyclicBarrier barrierDeq = new CyclicBarrier(availableProcessors);

		//sottomette i task ai vari thread
		for(int j = 0; j < availableProcessors; j++)
			taskCompletionService.submit(new BTATaskWS(buffer, barrierDeq, listDeque));
		//calcola il risultato elaborato dai vari thread
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
		
		BlockingQueue<ArrayDeque<Node>> listDeque = new LinkedBlockingQueue<ArrayDeque<Node>>(1);
		for(int i = 0; i < 1; i++)
			listDeque.add(new ArrayDeque<Node>(1 * numElements));
		BlockingQueue<Node> buffer = new LinkedBlockingQueue<Node>();
		buffer.offer(root);
		
		ExecutorService pool = Executors.newFixedThreadPool(1);
		CompletionService<Integer> taskCompletionService = new ExecutorCompletionService<Integer>(pool);
		CyclicBarrier barrierDeq = new CyclicBarrier(1);

		//sottomette i task ai vari thread
		for(int j = 0; j < 1; j++)
			taskCompletionService.submit(new BTATaskWS(buffer, barrierDeq, listDeque));
		//calcola il risultato elaborato dai vari thread
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
